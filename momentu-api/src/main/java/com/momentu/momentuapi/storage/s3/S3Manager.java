package com.momentu.momentuapi.storage.s3;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class S3Manager {

    public void upload(AWSCredentials awsCredentials, String bucketName, String keyName,
                       InputStream inputStream, ObjectMetadata objectMetadata) {
        AmazonS3 s3client = new AmazonS3Client(awsCredentials);
        try {
            s3client.putObject(new PutObjectRequest(
                    bucketName, keyName, inputStream, objectMetadata));

        } catch (AmazonServiceException ase) {
            //TODO: add logging
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }
}