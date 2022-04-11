package com.localbakery.account.controller;

import com.localbakery.account.service.S3UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/profileImage")
@RequiredArgsConstructor
public class AmazonS3UploadController {

    private final S3UploadService s3UploadService;

    @PostMapping(value = "/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam(value="image") MultipartFile multipartfile) {

        return ResponseEntity.ok(s3UploadService.updateProfileImage(SecurityContextHolder.getContext().getAuthentication().getName(), multipartfile));
    }
}