package com.localbakery.account.controller;

import com.localbakery.account.service.S3UploadService;
import com.localbakery.api.controller.common.ResponseContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/profileImage")
@RequiredArgsConstructor
public class AmazonS3UploadController {

    private final S3UploadService s3UploadService;

    @PostMapping(value = "/upload")
    public ResponseContainer<String> uploadFile(@RequestParam(value="image") MultipartFile multipartfile) {
        if (multipartfile == null) {
            ResponseContainer.<String>builder()
                    .rMessage("image required")
                    .rCode("500")
                    .build();
        }

        return ResponseContainer.<String>builder()
                .rMessage("OK")
                .rCode("200")
                .rData(s3UploadService.updateProfileImage(SecurityContextHolder.getContext().getAuthentication().getName(), multipartfile))
                .build();
    }
}