package com.localbakery.account.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AwsConfig {

    private String awsAccessKey;
    private String awsAccessSecretKey;
    private String awsRegion;

    public AwsConfig(@Value(value = "${cloud.aws.credentials.access-key}") String awsAccessKey,
                     @Value(value = "${cloud.aws.credentials.secret-key}") String awsAccessSecretKey,
                     @Value(value = "${cloud.aws.region.static}") String awsRegion) {
        this.awsAccessKey = awsAccessKey;
        this.awsAccessSecretKey = awsAccessSecretKey;
        this.awsRegion = awsRegion;
    }

    @Bean
    public AWSStaticCredentialsProvider getAwsCredentialsProvider() {
        BasicAWSCredentials awsCred = new BasicAWSCredentials(this.awsAccessKey, this.awsAccessSecretKey);
        return new AWSStaticCredentialsProvider(awsCred);
    }

    @Bean
    public AmazonS3 amazonS3Client() {
        return AmazonS3ClientBuilder.standard().withRegion(this.awsRegion).withCredentials(getAwsCredentialsProvider())
                .build();
    }
}