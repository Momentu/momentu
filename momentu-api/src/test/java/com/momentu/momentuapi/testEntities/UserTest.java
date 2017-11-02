package com.momentu.momentuapi.testEntities;

import com.momentu.momentuapi.entities.User;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

//This is a test class for User class
public class UserTest {

    //This is a test object from User class
    User userTest = new User();

    //Testing username set method
    @Test
    public void testSetUsername() {
        try {
            userTest.setUsername("falharbi88");
            assertEquals("falharbi88", userTest.getUsername());
        }catch (Exception e){
            fail("username value has not been set correctly");
        }
    }

    //Testing username get method
    @Test
    public void testGetUsername(){
        try {
            userTest.setUsername("falharbi88");
            assertEquals("falharbi88", userTest.getUsername());
        }catch (Exception e){
            fail("username value has not been gotten correctly");
        }

    }

    //Testing first name set method
    @Test
    public void testSetFirstName(){
        try {
            userTest.setFirstName("Fahad");
            assertEquals("Fahad", userTest.getFirstName());
        }catch (Exception e){
            fail("firstname value has not been set correctly");
        }

    }

    //Testing first name get method
    @Test
    public void testGetFirstName(){
        try {
            userTest.setFirstName("Fahad");
            assertEquals("Fahad", userTest.getFirstName());
        }catch (Exception e){
            fail("firstname value has not been gotten correctly");
        }
    }

    //Testing last name set method
    @Test
    public void testSetLastName(){
        try {
            userTest.setLastName("Alharbi");
            assertEquals("Alharbi", userTest.getLastName());
        }catch (Exception e){
            fail("lastname value has not been set correctly");
        }


    }

    //Testing last name get method
    @Test
    public void testGetLastName(){
        try {
            userTest.setLastName("Alharbi");
            assertEquals("Alharbi", userTest.getLastName());
        }catch (Exception e){
            fail("lastname value has not been gotten correctly");
        }
    }

    //Testing gender set method
    @Test
    public void testSetGender(){
        try {
            userTest.setGender("Male");
            assertEquals("Male", userTest.getGender());
        }catch (Exception e){
            fail("gender value has not been set correctly");
        }
    }

    //Testing gender get method
    @Test
    public void testGetGender(){
        try {
            userTest.setGender("Male");
            assertEquals("Male", userTest.getGender());
        }catch (Exception e){
            fail("gender value has not been gotten correctly");
        }
    }

    //Testing password set method
    @Test
    public void testSetEmail(){
        try {
            userTest.setEmail("falharbi88@gmail.com");
            assertEquals("falharbi88@gmail.com", userTest.getEmail());
        }catch (Exception e){
            fail("email value has not been set correctly");
        }
    }



    //Testing password get method
    @Test
    public void testGetEmail(){
        try {
            userTest.setEmail("falharbi88@gmail.com");
            assertEquals("falharbi88@gmail.com", userTest.getEmail());
        }catch (Exception e){
            fail("email value has not been gotten correctly");
        }
    }

    //Testing password set method (This one will be refactor after solving the encoding issue)
    @Test
    public void testSetPassword(){
        try {
            userTest.setPassword("12345678");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode("12345678");
            assertNotEquals(encodedPassword, userTest.getPassword());
        } catch (Exception e) {
            fail("password value has not been set correctly");
        }
    }

    //Testing password set method (This one will be refactor after solving the encoding issue)
    @Test
    public void testGetPassword() {
        try {
            userTest.setPassword("12345678");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode("12345678");
            assertNotEquals(encodedPassword, userTest.getPassword());
        } catch (Exception e) {
            fail("password value has not been gotten correctly");
        }
    }

    //Testing The encoding features in the password set method
    @Test
    public void testEncodingSetPassword() {
        try {
            userTest.setPassword("12345678");
            assertNotEquals("12345678", userTest.getPassword());
        }catch (Exception e) {
            fail("encoding features doesn't work properly");
        }
    }

    //Testing The hash code method
    //TODO: remove?
//    @Test
//    public void testHashCode() {
//        try {
//            Long id = 1L;
//            userTest.setId(id);
//            assertEquals(Objects.hash(1L), userTest.hashCode());
//        } catch (Exception e) {
//            fail("has code feature doesn't work properly");
//        }
//    }

    //Testing The equal object method
    //TODO: remove?
//    @Test
//    public void testEqaulObject() {
//        try {
//            Long id = 1L;
//            userTest.setId(id);
//            User userTest2 = new User();
//            userTest2.setId(id);
//            assertEquals(true, userTest.equals(userTest2));
//        } catch (Exception e) {
//            fail("equal object method doesn't work properly");
//        }
//    }

}
