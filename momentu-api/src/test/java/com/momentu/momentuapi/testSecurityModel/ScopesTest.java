package com.momentu.momentuapi.testSecurityModel;

import com.momentu.momentuapi.security.model.Scopes;
import com.momentu.momentuapi.security.model.UserContext;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//This is a test class for Enum Scopes class
public class ScopesTest {

    //Testing authority method
    @Test
    public void testAuthority() {
        try {
            Scopes scopesTest = Scopes.REFRESH_TOKEN;
            scopesTest.authority();
            assertEquals("ROLE_REFRESH_TOKEN",scopesTest.authority());
        } catch (Exception e) {
            fail("authority method doesn't work properly.");
        }
    }
}
