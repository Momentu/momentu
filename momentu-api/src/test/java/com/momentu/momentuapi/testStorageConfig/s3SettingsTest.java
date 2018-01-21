package com.momentu.momentuapi.testStorageConfig;

import com.momentu.momentuapi.security.config.JwtSettings;
import com.momentu.momentuapi.storage.config.S3Settings;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

//This is a test class for s3Settings class
public class s3SettingsTest {

    //This is a test object from JwtSettings class
    S3Settings s3SettingsTest;

    //Testing getMediaBucketName method.
    @Test
    public void testGetMediaBucketName() {
        try {
            assertNull(s3SettingsTest);
            s3SettingsTest = new S3Settings();
            s3SettingsTest.setMediaBucketName("Test");
            assertEquals("Test",s3SettingsTest.getMediaBucketName());
        }catch (Exception e){
            fail("getMediaBucketName method doesn't work properly.");
        }
    }

    //Testing setMediaBucketName method.
    @Test
    public void testSetMediaBucketName() {
        try {
            assertNull(s3SettingsTest);
            s3SettingsTest = new S3Settings();
            s3SettingsTest.setMediaBucketName("Test");
            assertEquals("Test",s3SettingsTest.getMediaBucketName());
        }catch (Exception e){
            fail("setMediaBucketName method doesn't work properly.");
        }
    }

    //Testing getAccessKeyId method.
    @Test
    public void testGetAccessKeyId() {
        try {
            assertNull(s3SettingsTest);
            s3SettingsTest = new S3Settings();
            s3SettingsTest.setAccessKeyId("Test Id");
            assertEquals("Test Id",s3SettingsTest.getAccessKeyId());
        }catch (Exception e){
            fail("getAccessKeyId method doesn't work properly.");
        }
    }

    //Testing setAccessKeyId method.
    @Test
    public void testSetAccessKeyId() {
        try {
            assertNull(s3SettingsTest);
            s3SettingsTest = new S3Settings();
            s3SettingsTest.setAccessKeyId("Test Id");
            assertEquals("Test Id",s3SettingsTest.getAccessKeyId());
        }catch (Exception e){
            fail("setAccessKeyId method doesn't work properly.");
        }
    }

    //Testing getSecretAccessKey method.
    @Test
    public void testGetSecretAccessKey() {
        try {
            assertNull(s3SettingsTest);
            s3SettingsTest = new S3Settings();
            s3SettingsTest.setSecretAccessKey("Secret Key");
            assertEquals("Secret Key",s3SettingsTest.getSecretAccessKey());
        }catch (Exception e){
            fail("getSecretAccessKey method doesn't work properly.");
        }
    }

    //Testing setSecretAccessKey method.
    @Test
    public void testSetSecretAccessKey() {
        try {
            assertNull(s3SettingsTest);
            s3SettingsTest = new S3Settings();
            s3SettingsTest.setSecretAccessKey("Secret Key");
            assertEquals("Secret Key",s3SettingsTest.getSecretAccessKey());
        }catch (Exception e){
            fail("setSecretAccessKey method doesn't work properly.");
        }
    }

}
