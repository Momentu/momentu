package com.momentu.momentuandroid;

import com.momentu.momentuandroid.Model.User;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User Class Junit Test
 */
public class UserTest {

    //An object from User class to check its functionality
    private User userTest = null;

    //Testing the constructor of User class
    @Test
    public void TestUserUser() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            assertEquals("falharbi88", userTest.getUsername());
            assertEquals("Fahad", userTest.getFirstName());
            assertEquals("Alharbi", userTest.getLastName());
            assertEquals("falharbi88@gmail.com", userTest.getEmail());
            assertEquals("123456789", userTest.getPassword());
            assertEquals("Male", userTest.getGender());
        }catch (Exception e){
            fail("Creating a user object fails.");
        }
    }

    //Testing the set username method in User class
    @Test
    public void testSetUsername() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setUsername("Jerry");
            assertEquals("Jerry", userTest.getUsername());
        }catch(Exception e){
            fail("Setting a username fails.");
        }
    }

    //Testing the get username method in User class
    @Test
    public void testGetUsername() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setUsername("Jerry");
            assertEquals("Jerry", userTest.getUsername());
        }catch(Exception e){
            fail("Setting a username fails.");
        }
    }

    //Testing the set first name method in User class
    @Test
    public void testSetFirstName() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setFirstName("Jane");
            assertEquals("Jane", userTest.getFirstName());
        }catch(Exception e){
            fail("Setting a first name fails.");
        }
    }

    //Testing the get first name method in User class
    @Test
    public void testGetFirstName() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setFirstName("Jane");
            assertEquals("Jane", userTest.getFirstName());
        }catch(Exception e){
            fail("Getting a first name fails.");
        }
    }

    //Testing the set last name method in User class
    @Test
    public void testSetLastName() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setLastName("Jane");
            assertEquals("Jane", userTest.getLastName());
        }catch(Exception e){
            fail("Setting a last name fails.");
        }
    }

    //Testing the get last name method in User class
    @Test
    public void testGetLastName() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setLastName("Ibrahim");
            assertEquals("Ibrahim", userTest.getLastName());
        }catch(Exception e){
            fail("Setting a last name fails.");
        }
    }

    //Testing the set email method in User class
    @Test
    public void testSetEmail() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setEmail("Ibrahim@gmail.com");
            assertEquals("Ibrahim@gmail.com", userTest.getEmail());
        }catch(Exception e){
            fail("Setting an email fails.");
        }
    }

    //Testing the get email method in User class
    @Test
    public void testGetEmail() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setEmail("Ibrahim@gmail.com");
            assertEquals("Ibrahim@gmail.com", userTest.getEmail());
        }catch(Exception e){
            fail("Getting an email fails.");
        }
    }

    //Testing the set password method in User class
    @Test
    public void testSetPassword() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setPassword("101010101010");
            assertEquals("101010101010", userTest.getPassword());
        }catch(Exception e){
            fail("Setting a password fails.");
        }
    }

    //Testing the get password method in User class
    @Test
    public void testGetPassword() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setPassword("101010101010");
            assertEquals("101010101010", userTest.getPassword());
        }catch(Exception e){
            fail("Getting a password fails.");
        }
    }

    //Testing the set gender method in User class
    @Test
    public void testSetGender() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setFirstName("Jane");
            userTest.setGender("Female");
            assertEquals("Female", userTest.getGender());
        }catch(Exception e){
            fail("Setting a gender fails.");
        }
    }

    //Testing the get gender method in User class
    @Test
    public void testGetGender() throws Exception {
        try {
            userTest = new User("falharbi88", "Fahad", "Alharbi", "falharbi88@gmail.com", "123456789", "Male");
            userTest.setFirstName("Jane");
            userTest.setGender("Female");
            assertEquals("Female", userTest.getGender());
        }catch(Exception e){
            fail("Getting a gender fails.");
        }
    }
}