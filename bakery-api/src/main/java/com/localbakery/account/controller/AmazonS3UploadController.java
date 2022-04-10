package com.localbakery.account.controller;

import com.localbakery.account.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/files")
@RequiredArgsConstructor
public class AmazonS3UploadController {

    private final S3UploadService s3UploadService;

    @PostMapping(value = "/upload")
    public ResponseEntity<Map<String, String>> uploadFile(String userName, MultipartFile multipartfile) {

        return ResponseEntity.ok(s3UploadService.updateProfileImage(userName, multipartfile));
    }
}