package com.momentu.momentuapi.storage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "aws.s3")
public class S3Settings {
    private String mediaBucketName;
    private String accessKeyId;
    private String secretAccessKey;

    public String getMediaBucketName() {
        return mediaBucketName;
    }

    public void setMediaBucketName(String mediaBucketName) {
        this.mediaBucketName = mediaBucketName;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }
}

