package com.localbakery.account.service;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.localbakery.account.domain.Account;
import com.localbakery.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class S3UploadService {

    @Value("http://localbakery.s3-website.ap-northeast-2.amazonaws.com")
    private String s3BaseUrl;

    @Value("localbakery")
    private String bucketName;

    @Value("images")
    private String folderName;

    private AmazonS3 amazonS3Client;

    private AccountRepository accountRepository;

    @Autowired
    public S3UploadService(AmazonS3 amazonS3Client, AccountRepository accountRepository) {
        this.amazonS3Client = amazonS3Client;
        this.accountRepository = accountRepository;
    }


    @Transactional
    public String updateProfileImage(String email, MultipartFile profileImage) {
        Account user = accountRepository.findByEmail(email);

        String s3Url = "https://localbakery.s3.amazonaws.com/images/";

        Map<String, String> filePath = uploadFileToS3(profileImage);

        String imageUrl = s3Url + filePath.get("fileUrl").split("images")[1];

        user.setImageUrl(imageUrl);

        accountRepository.save(user);

        return imageUrl;

    }

    public Map<String, String> uploadFileToS3(MultipartFile multipartfile) {

        Map<String, String> response = new HashMap<>();

        if (multipartfile != null && !multipartfile.isEmpty()) {

            String filePathName = multipartfile.getOriginalFilename();

            File file = new File(filePathName);

            try (FileOutputStream fos = new FileOutputStream(file)) {

                if (!file.exists()) {
                    file.createNewFile();
                }

                fos.write(multipartfile.getBytes());
                fos.flush();

                /* uploading file to S3 */
                amazonS3Client.putObject(new PutObjectRequest(bucketName, folderName + "/" + file.getName(), file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

                /* Url location of the uploaded file in S3. You should save it in database */
                String s3FileAccessUrl = s3BaseUrl.concat(bucketName).concat(folderName).concat(file.getName())
                        .replaceAll("\\s", "+");

                response.put("fileUrl", s3FileAccessUrl);

                file.delete();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

}