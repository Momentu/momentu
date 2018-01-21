package com.momentu.momentuapi.testSecurityConfig;

import com.momentu.momentuapi.security.config.JwtSettings;
import com.momentu.momentuapi.security.model.token.JwtTokenFactory;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//This is a test class for JwtSettings class
public class JwtSettingsTest {

    //This is a test object from JwtSettings class
    JwtSettings jwtSettingsTest;

    //Testing setRefreshTokenExpTime method.
    @Test
    public void testSetRefreshTokenExpTime() {
        try {
            assertNull(jwtSettingsTest);
            JwtSettings jwtSettingsTest = new JwtSettings();
            jwtSettingsTest.setRefreshTokenExpTime(new Integer(5));
            assertEquals(new Integer(5),jwtSettingsTest.getRefreshTokenExpTime());
        }catch (Exception e){
            fail("setRefreshTokenExpTime method doesn't work properly.");
        }
    }

    //Testing getRefreshTokenExpTime method.
    @Test
    public void testGetRefreshTokenExpTime() {
        try {
            assertNull(jwtSettingsTest);
            JwtSettings jwtSettingsTest = new JwtSettings();
            jwtSettingsTest.setRefreshTokenExpTime(new Integer(5));
            assertEquals(new Integer(5),jwtSettingsTest.getRefreshTokenExpTime());
        }catch (Exception e){
            fail("getRefreshTokenExpTime method doesn't work properly.");
        }
    }

    //Testing setTokenExpirationTime method.
    @Test
    public void testSetTokenExpirationTime() {
        try {
            assertNull(jwtSettingsTest);
            JwtSettings jwtSettingsTest = new JwtSettings();
            jwtSettingsTest.setTokenExpirationTime(new Integer(5));
            assertEquals(new Integer(5),jwtSettingsTest.getTokenExpirationTime());
        }catch (Exception e){
            fail("setTokenExpirationTime method doesn't work properly.");
        }
    }

    //Testing getTokenExpirationTime method.
    @Test
    public void testGetTokenExpirationTime() {
        try {
            assertNull(jwtSettingsTest);
            JwtSettings jwtSettingsTest = new JwtSettings();
            jwtSettingsTest.setTokenExpirationTime(new Integer(5));
            assertEquals(new Integer(5),jwtSettingsTest.getTokenExpirationTime());
        }catch (Exception e){
            fail("getTokenExpirationTime method doesn't work properly.");
        }
    }

    //Testing setTokenIssuer method.
    @Test
    public void testSetTokenIssuer() {
        try {
            assertNull(jwtSettingsTest);
            JwtSettings jwtSettingsTest = new JwtSettings();
            jwtSettingsTest.setTokenIssuer("Token Test");
            assertEquals("Token Test",jwtSettingsTest.getTokenIssuer());
        }catch (Exception e){
            fail("setTokenIssuer method doesn't work properly.");
        }
    }

    //Testing GetTokenIssuer method.
    @Test
    public void testGetTokenIssuer() {
        try {
            assertNull(jwtSettingsTest);
            JwtSettings jwtSettingsTest = new JwtSettings();
            jwtSettingsTest.setTokenIssuer("Token Test");
            assertEquals("Token Test",jwtSettingsTest.getTokenIssuer());
        }catch (Exception e){
            fail("getTokenIssuer method doesn't work properly.");
        }
    }

    //Testing setTokenSigningKey method.
    @Test
    public void testSetTokenSigningKey() {
        try {
            assertNull(jwtSettingsTest);
            JwtSettings jwtSettingsTest = new JwtSettings();
            jwtSettingsTest.setTokenSigningKey("Token Test");
            assertEquals("Token Test",jwtSettingsTest.getTokenSigningKey());
        }catch (Exception e){
            fail("setTokenSigningKey method doesn't work properly.");
        }
    }

    //Testing getTokenSigningKey method.
    @Test
    public void testGetTokenSigningKey() {
        try {
            assertNull(jwtSettingsTest);
            JwtSettings jwtSettingsTest = new JwtSettings();
            jwtSettingsTest.setTokenSigningKey("Token Test");
            assertEquals("Token Test",jwtSettingsTest.getTokenSigningKey());
        }catch (Exception e){
            fail("getTokenSigningKey method doesn't work properly.");
        }
    }

}
