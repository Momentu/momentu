package com.momentu.momentuapi.testSecurityModelToken;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.momentu.momentuapi.security.config.JwtSettings;
import com.momentu.momentuapi.security.model.LoginRequest;
import com.momentu.momentuapi.security.model.UserContext;
import com.momentu.momentuapi.security.model.token.AccessJwtToken;
import com.momentu.momentuapi.security.model.token.JwtTokenFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//This is a test class for AccessJwtToken class
public class AccessJwtTokenTest{

    //This is a test object from AccessJwtToken class
    AccessJwtToken accessJwtTokenTest;

    //Testing AccessJwtToken constructor **Protected constructor
    @Test
    public void testAccessJwtToken() {
       try {
           /*
           String rawToken="Token";
           Claims claims = null;
           claims.setSubject("claims");
           accessJwtTokenTest = new AccessJwtToken(rawToken,claims);
            */
        }catch (Exception e){
            fail("AccessJwtToken constructor can't be used");
        }
    }

    //Testing getClaims method
    @Test
    public void testGetClaims() {
        try {

        }catch (Exception e){
            fail("getClaims method doesn't work properly");
        }
    }

    //Testing getToken method
    @Test
    public void testGetToken() {
        try {

        }catch (Exception e){
            fail("getToken method doesn't work properly");
        }
    }


}
