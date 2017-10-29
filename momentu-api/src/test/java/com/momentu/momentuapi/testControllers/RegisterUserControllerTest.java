package com.momentu.momentuapi.testControllers;

import com.momentu.momentuapi.controllers.RegisterUserController;
import com.momentu.momentuapi.entities.User;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

//This is a test class for register user controller class
public class RegisterUserControllerTest {

    //This is a test object from register user controller class
    RegisterUserController registerUserControllerTest = new RegisterUserController();

    //Testing register method in Register User Controller class with empty username value
    @Test
    public void testRegister1() {
        try {
            User userTest = new User();
            userTest.setUsername("");
            PersistentEntityResourceAssembler persistentEntityResourceAssembler = null;
            registerUserControllerTest.register(userTest,persistentEntityResourceAssembler);
            fail("the register method didn't throw an exception when passing an empty username");
        } catch (Exception e) {
            assertEquals("Missing value for registration", e.getMessage());
        }

    }

    /*
    //Testing register method in Register User Controller class with a new username
    @Test
    public void testRegister2() {
        //try {
            //ResponseEntity<PersistentEntityResource> responseEntityTest=null;
            User userTest = new User();
            userTest.setUsername("meharbi88");
            userTest.setEmail("mohammed@gmail.com");
            userTest.setPassword("123456789");
            userTest.setGender("male");
            userTest.setFirstName("mohammed");
            userTest.setLastName("alharbi");
            PersistentEntityResourceAssembler persistentEntityResourceAssembler = Mockito.mock(PersistentEntityResourceAssembler.class);
            assertEquals(ResponseEntity.ok(persistentEntityResourceAssembler.toResource(userTest)),
                    registerUserControllerTest.register(userTest,persistentEntityResourceAssembler));
        //} catch (Exception e) {
         //   fail("failed");
        //}

    }

    //Testing register method in Register User Controller class with existing username
    @Test
    public void testRegister3() {

        User userTest = new User();
        userTest.setUsername("jsmith040");
    }


    //Testing register method in Register User Controller class with existing email
    @Test
    public void testRegister4() {
        User userTest = new User();
        userTest.setEmail("johnsmith040@gmail.com");

    }
    */
}
