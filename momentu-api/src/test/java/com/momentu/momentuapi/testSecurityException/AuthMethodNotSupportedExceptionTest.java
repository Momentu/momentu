package com.momentu.momentuapi.testSecurityException;


import com.momentu.momentuapi.entities.Hashtag;
import com.momentu.momentuapi.security.exceptions.AuthMethodNotSupportedException;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

//This is a test class for AuthMethodNotSupportedException class
public class AuthMethodNotSupportedExceptionTest {

    //This is a test object from AuthMethodNotSupportedException class
    AuthMethodNotSupportedException authMethodNotSupportedExceptionTest;

    //Testing AuthMethodNotSupportedException constructor
    @Test
    public void testAuthMethodNotSupportedException() {
        try {
            assertNull(authMethodNotSupportedExceptionTest);
            authMethodNotSupportedExceptionTest = new AuthMethodNotSupportedException("Test");
            assertNotNull(authMethodNotSupportedExceptionTest);
        }catch (Exception e){
            fail("AuthMethodNotSupportedException constructor can't be used");
        }
    }
}
