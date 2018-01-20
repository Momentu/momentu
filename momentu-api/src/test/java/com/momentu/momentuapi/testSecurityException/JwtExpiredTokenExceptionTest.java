package com.momentu.momentuapi.testSecurityException;

import com.momentu.momentuapi.security.exceptions.JwtExpiredTokenException;
import com.momentu.momentuapi.security.model.token.JwtToken;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//This is a test class for JwtExpiredTokenException class
public class JwtExpiredTokenExceptionTest {

    //This is a test object from JwtExpiredTokenException class
    JwtExpiredTokenException jwtExpiredTokenExceptionTest;

    //Testing JwtExpiredTokenException constructor
    @Test
    public void testJwtExpiredTokenException() {
        try {
            assertNull(jwtExpiredTokenExceptionTest);
            JwtToken token = new JwtToken() {
                @Override
                public String getToken() {
                    return "ABC";
                }
            };
            String msg = "Test";
            Throwable t = new Throwable();
            jwtExpiredTokenExceptionTest = new JwtExpiredTokenException(token,msg,t);
            assertNotNull(jwtExpiredTokenExceptionTest);
        }catch (Exception e){
            fail("JwtExpiredTokenException constructor can't be used");
        }
    }

    //Testing JwtExpiredTokenException constructor
    @Test
    public void testJwtExpiredTokenExceptionWithString() {
        try {
            assertNull(jwtExpiredTokenExceptionTest);
            String msg = "Test";
            jwtExpiredTokenExceptionTest = new JwtExpiredTokenException(msg);
            assertNotNull(jwtExpiredTokenExceptionTest);
        }catch (Exception e){
            fail("JwtExpiredTokenException WithString constructor can't be used");
        }
    }

    //Testing JwtExpiredTokenException constructor
    @Test
    public void testToken() {
        try {
            JwtToken token = new JwtToken() {
                @Override
                public String getToken() {
                    return "ABC";
                }
            };
            String msg = "Test";
            Throwable t = new Throwable();
            jwtExpiredTokenExceptionTest = new JwtExpiredTokenException(token,msg,t);
            assertEquals("ABC",jwtExpiredTokenExceptionTest.token());
        }catch (Exception e){
            fail("JwtExpiredTokenException constructor can't be used");
        }
    }
}