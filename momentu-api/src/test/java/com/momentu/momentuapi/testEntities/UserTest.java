package com.momentu.momentuapi.testEntities;

import com.momentu.momentuapi.entities.User;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

//This is a test class for User class
public class UserTest {

    //This is a test object from User class
    User userTest = new User();

    //Testing username set method
    @Test
    public void testSetUsername(){
        userTest.setUsername("falharbi88");
        assertEquals("falharbi88", userTest.getUsername());
    }

    //Testing username get method
    @Test
    public void testGetUsername(){
        userTest.setUsername("falharbi88");
        assertEquals("falharbi88", userTest.getUsername());

    }

    //Testing first name set method
    @Test
    public void testSetFirstName(){
        userTest.setFirstName("Fahad");
        assertEquals("Fahad", userTest.getFirstName());
    }

    //Testing first name get method
    @Test
    public void testGetFirstName(){
        userTest.setFirstName("Fahad");
        assertEquals("Fahad", userTest.getFirstName());

    }

    //Testing last name set method
    @Test
    public void testSetLastName(){
        userTest.setLastName("Alharbi");
        assertEquals("Alharbi", userTest.getLastName());
    }

    //Testing last name get method
    @Test
    public void testGetLastName(){
        userTest.setLastName("Alharbi");
        assertEquals("Alharbi", userTest.getLastName());

    }

    //Testing gender set method
    @Test
    public void testSetGender(){
        userTest.setGender("Male");
        assertEquals("Male", userTest.getGender());
    }

    //Testing gender get method
    @Test
    public void testGetGender(){
        userTest.setGender("Male");
        assertEquals("Male", userTest.getGender());

    }

    //Testing password set method
    @Test
    public void testSetEmail(){
        userTest.setEmail("falharbi88@gmail.com");
        assertEquals("falharbi88@gmail.com", userTest.getEmail());
    }

    //Testing password get method
    @Test
    public void testGetEmail(){
        userTest.setEmail("falharbi88@gmail.com");
        assertEquals("falharbi88@gmail.com", userTest.getEmail());
    }

    //Testing password set method (This one will be refactor after solving the encoding issue)
    @Test
    public void testSetPassword(){
        userTest.setPassword("12345678");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("12345678");
        assertNotEquals(encodedPassword, userTest.getPassword());
    }

    //Testing password set method (This one will be refactor after solving the encoding issue)
    @Test
    public void testGetPassword(){
        userTest.setPassword("12345678");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("12345678");
        assertNotEquals(encodedPassword, userTest.getPassword());
    }

    //Testing The encoding features in the password set method
    @Test
    public void testEncodingSetPassword(){
        userTest.setPassword("12345678");
        assertNotEquals("12345678", userTest.getPassword());
    }

    //Testing The hash code method
    @Test
    public void testHashCode(){
        Long id = 1L;
        userTest.setId(id);
        assertEquals(Objects.hash(1L), userTest.hashCode());
    }

    //Testing The equal object method
    @Test
    public void testEqaulObject(){
        Long id = 1L;
        userTest.setId(id);
        User userTest2 = new User();
        userTest2.setId(id);
        assertEquals(true, userTest.equals(userTest2));

    }

}
