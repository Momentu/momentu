package com.momentu.momentuapi.testSecurityModelToken;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.momentu.momentuapi.security.model.LoginRequest;
import com.momentu.momentuapi.security.model.UserContext;
import com.momentu.momentuapi.security.model.token.AccessJwtToken;
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

    //Testing AccessJwtToken constructor
    @Test
    public void testAccessJwtToken() {
       try {
            //assertNull(accessJwtTokenTest);
            //String rawToken = "ABC";
            //List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            //authorities.add(new SimpleGrantedAuthority("Admin"));
            //UserContext userContext = new UserContext("Fahad",authorities);
            //Claims claims = Jwts.claims().setSubject(userContext.getUsername());
            //accessJwtTokenTest = new AccessJwtToken(rawToken,claims);
            //assertNotNull(accessJwtTokenTest);
        }catch (Exception e){
            fail("LoginRequest constructor can't be used");
        }
    }


}
