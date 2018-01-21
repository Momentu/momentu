package com.momentu.momentuapi.testSecurityModelToken;

import com.momentu.momentuapi.security.config.JwtSettings;
import com.momentu.momentuapi.security.model.LoginRequest;
import com.momentu.momentuapi.security.model.UserContext;
import com.momentu.momentuapi.security.model.token.AccessJwtToken;
import com.momentu.momentuapi.security.model.token.JwtToken;
import com.momentu.momentuapi.security.model.token.JwtTokenFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//This is a test class for JwtTokenFactory class
public class JwtTokenFactoryTest {

    //This is a test object from LoginRequest class
    JwtTokenFactory jwtTokenFactoryTest;

    //Testing JwtTokenFactory constructor
    @Test
    public void testJwtTokenFactory() {
        try {
            assertNull(jwtTokenFactoryTest);
            JwtSettings settings = new JwtSettings();
            jwtTokenFactoryTest = new JwtTokenFactory(settings);
            assertNotNull(jwtTokenFactoryTest);
        }catch (Exception e){
            fail("JwtTokenFactory constructor can't be used");
        }
    }

    //Testing createAccessJwtToken method
    @Test
    public void testCreateAccessJwtToken() {
        try {
            /*
            JwtSettings settings = new JwtSettings();
            settings.setRefreshTokenExpTime(new Integer(5));
            settings.setTokenExpirationTime(new Integer(4));
            settings.setTokenIssuer("ABC");
            settings.setTokenSigningKey("DEF");
            jwtTokenFactoryTest = new JwtTokenFactory(settings);
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("Admin"));
            UserContext userContext = new UserContext("Fahad",authorities);
            assertNotNull(jwtTokenFactoryTest.createAccessJwtToken(userContext));
            //jwtTokenFactoryTest.createAccessJwtToken(userContext);
            //assertEquals(accessJwtTokenTest.getToken(),jwtTokenFactoryTest.createAccessJwtToken(userContext).getToken());
       */
        }catch (Exception e){
            fail("createAccessJwtToken method doesn't work properly");
        }
    }
    //Testing createAccessJwtToken method with empty username in user context
    @Test
    public void testCreateAccessJwtTokenEmptyUsername() {
        try {
            JwtSettings settings = new JwtSettings();
            settings.setRefreshTokenExpTime(new Integer(5));
            settings.setTokenExpirationTime(new Integer(4));
            settings.setTokenIssuer("ABC");
            settings.setTokenSigningKey("DEF");
            jwtTokenFactoryTest = new JwtTokenFactory(settings);
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("Admin"));
            UserContext userContext = new UserContext("",authorities);
            jwtTokenFactoryTest.createAccessJwtToken(userContext);
            fail("createAccessJwtToken method doesn't catch empty username.");
        }catch (Exception e){
            assertEquals("Cannot create JWT Token without username",e.getMessage());
        }
    }

    //Testing createAccessJwtToken method with null authority list
    @Test
    public void testCreateAccessJwtTokenNullAuthority() {
        try {
            JwtSettings settings = new JwtSettings();
            settings.setRefreshTokenExpTime(new Integer(5));
            settings.setTokenExpirationTime(new Integer(4));
            settings.setTokenIssuer("ABC");
            settings.setTokenSigningKey("DEF");
            jwtTokenFactoryTest = new JwtTokenFactory(settings);
            List<GrantedAuthority> authorities = null;
            UserContext userContext = new UserContext("Fahad",authorities);
            jwtTokenFactoryTest.createAccessJwtToken(userContext);
            fail("createAccessJwtToken method doesn't catch null authority list.");
        }catch (Exception e){
            assertEquals("User does not have priviliges",e.getMessage());
        }
    }

    //Testing createRefreshToken method
    @Test
    public void testCreateRefreshToken() {
        try {
            /*
            JwtSettings settings = new JwtSettings();
            settings.setRefreshTokenExpTime(new Integer(5));
            settings.setTokenExpirationTime(new Integer(4));
            settings.setTokenIssuer("ABC");
            settings.setTokenSigningKey("DEF");
            jwtTokenFactoryTest = new JwtTokenFactory(settings);

            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("Admin"));
            UserContext userContext = new UserContext("Fahad",authorities);
            JwtToken jwtToken;
            jwtToken = jwtTokenFactoryTest.createRefreshToken(userContext);
            assertNotNull(jwtToken);
            //jwtTokenFactoryTest.createAccessJwtToken(userContext);
            //assertEquals(accessJwtTokenTest.getToken(),jwtTokenFactoryTest.createAccessJwtToken(userContext).getToken());
        */
        }catch (Exception e){
            fail("createRefreshToken method doesn't work properly");
        }
    }
    //Testing CreateRefreshToken method with empty username in user context
    @Test
    public void testCreateRefreshTokenEmptyUsername() {
        try {
            JwtSettings settings = new JwtSettings();
            settings.setRefreshTokenExpTime(new Integer(5));
            settings.setTokenExpirationTime(new Integer(4));
            settings.setTokenIssuer("ABC");
            settings.setTokenSigningKey("DEF");
            jwtTokenFactoryTest = new JwtTokenFactory(settings);
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("Admin"));
            UserContext userContext = new UserContext("",authorities);
            jwtTokenFactoryTest.createRefreshToken(userContext);
            fail("CreateRefreshToken method doesn't catch empty username.");
        }catch (Exception e){
            assertEquals("Cannot create JWT Token without username",e.getMessage());
        }
    }
}
