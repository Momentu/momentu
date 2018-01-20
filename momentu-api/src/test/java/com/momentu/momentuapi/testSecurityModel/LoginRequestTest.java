package com.momentu.momentuapi.testSecurityModel;

import com.momentu.momentuapi.security.exceptions.AuthMethodNotSupportedException;
import com.momentu.momentuapi.security.model.LoginRequest;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//This is a test class for LoginRequest class
public class LoginRequestTest {

    //This is a test object from LoginRequest class
    LoginRequest loginRequestTest;

    //Testing LoginRequest constructor
    @Test
    public void testLoginRequest() {
        try {
            assertNull(loginRequestTest);
            loginRequestTest = new LoginRequest("Fahad","1234567890");
            assertNotNull(loginRequestTest);
        }catch (Exception e){
            fail("LoginRequest constructor can't be used");
        }
    }

    //Testing get username method
    @Test
    public void testGetUsername() {
        try {
            assertNull(loginRequestTest);
            loginRequestTest = new LoginRequest("Fahad","1234567890");
            assertEquals("Fahad",loginRequestTest.getUsername());
        }catch (Exception e){
            fail("get username method doesn't work properly");
        }
    }

    //Testing get password method
    @Test
    public void testGetPassword() {
        try {
            assertNull(loginRequestTest);
            loginRequestTest = new LoginRequest("Fahad","1234567890");
            assertEquals("1234567890",loginRequestTest.getPassword());
        }catch (Exception e){
            fail("get password method doesn't work properly");
        }
    }
}

