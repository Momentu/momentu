package com.momentu.momentuapi.testSecurityModelToken;

import com.momentu.momentuapi.security.model.LoginRequest;
import com.momentu.momentuapi.security.model.token.RawAccessJwtToken;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//This is a test class for RawAccessJwtToken class
public class RawAccessJwtTokenTest {

    //This is a test object from LoginRequest class
    RawAccessJwtToken rawAccessJwtTokenTest;

    //Testing RawAccessJwtToken constructor
    @Test
    public void testRawAccessJwtToken() {
        try {
            assertNull(rawAccessJwtTokenTest);
            String token = "Fahad";
            rawAccessJwtTokenTest = new RawAccessJwtToken(token);
            assertNotNull(rawAccessJwtTokenTest);
        }catch (Exception e){
            fail("RawAccessJwtToken constructor can't be used");
        }
    }

    //Testing GetToken method
    @Test
    public void testGetToken() {
        try {
            //assertNull(rawAccessJwtTokenTest);
            String token = null;
            rawAccessJwtTokenTest = new RawAccessJwtToken(token);
            assertNull(rawAccessJwtTokenTest.getToken());
        }catch (Exception e){
            fail("GetToken method doesn't work properly.");
        }
    }

}
