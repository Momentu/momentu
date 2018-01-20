package com.momentu.momentuapi.testSecurityModel;

import com.momentu.momentuapi.security.model.LoginRequest;
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

//This is a test class for UserContext class
public class UserContextTest {

    //This is a test object from UserContext class
    UserContext userContextTest;

    //Testing UserContext constructor
    @Test
    public void testUserContext() {
        try {
            assertNull(userContextTest);
            String username = "Fahad";
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            authorities.add(new SimpleGrantedAuthority("Admin"));
            userContextTest = new UserContext(username,authorities);
            assertNotNull(userContextTest);
        } catch (Exception e) {
            fail("UserContext constructor can't be used");
        }
    }

    //Testing Create user method with blank username. It will throw an exception.
    @Test
    public void testCreateWithBlankUsername() {
        try {
            assertNull(userContextTest);
            String username = "Fahad";
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("Admin"));
            UserContext.create("",authorities);
            fail("It doesn't throw an exception in passing blank username.");
        } catch (Exception e) {
            assertEquals("Username is blank",e.getMessage());
        }
    }

    //Testing Create user method with legal parameters
    @Test
    public void testCreateWithLegalParameters() {
        try {
            assertNull(userContextTest);
            UserContext userContextTest2;
            String username = "Fahad";
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("Admin"));
            userContextTest2 = UserContext.create("Fahad",authorities);

            userContextTest = new UserContext(username,authorities);
            assertEquals(userContextTest.getUsername(),UserContext.create("Fahad",authorities).getUsername());

        } catch (Exception e) {
            fail("Create method doesn't work properly when passing with legal parameters.");
        }
    }

    //Testing Get username method
    @Test
    public void testGetUsername() {
        try {
            String username = "Fahad";
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("Admin"));
            userContextTest = new UserContext(username,authorities);
            assertEquals("Fahad",userContextTest.getUsername());

        } catch (Exception e) {
            fail("Get username method doesn't work properly.");
        }
    }

    //Testing Get authorities method
    @Test
    public void testGetAuthorities() {
        try {
            String username = "Fahad";
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("Admin"));
            userContextTest = new UserContext(username,authorities);
            assertEquals(authorities,userContextTest.getAuthorities());

        } catch (Exception e) {
            fail("Get authorities method doesn't work properly.");
        }
    }
}
