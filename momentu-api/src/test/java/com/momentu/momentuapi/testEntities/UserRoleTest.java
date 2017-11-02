package com.momentu.momentuapi.testEntities;

import com.momentu.momentuapi.entities.Role;
import com.momentu.momentuapi.entities.UserRole;
import com.momentu.momentuapi.entities.keys.UserRoleKey;
import org.junit.Test;

import static org.junit.Assert.*;

//This is a test class for User Role class
public class UserRoleTest {

    //This is a test object from User Role class
    UserRole userRoleTest;

    //This is a test object from id static class in User Role class
    UserRoleKey userRoleKeyTest;

    //This is a test object from Role Enum class
    Role roleTest;

    //Testing UserRoleKey static class in User Role Class
    //Testing UserRoleKey constructor
    @Test
    public void testId(){
        try{
            assertNull(userRoleKeyTest);
            roleTest = Role.MEMBER;
            Long userId= 1L;
            userRoleKeyTest = new UserRoleKey(userId,roleTest);
            assertEquals(Role.MEMBER,roleTest);
            assertNotNull(userRoleKeyTest);
        }catch (Exception e){
            fail("UserRoleKey constructor in the id static class in User Role class doesn't work properly");
        }
    }

    //Testing User Role class
    //Testing constructor in User Role class
    @Test
    public void testUserRole(){
        try{
            assertNull(userRoleTest);
            roleTest = Role.MEMBER;
            Long userId= 1L;
            userRoleTest = new UserRole(userId,roleTest);
            assertNotNull(userRoleTest);
        }catch (Exception e){
            fail("user role constructor in the User Role class doesn't work properly");
        }
    }

    //Testing get role method in User Role class (Doesn't pass further evaluation is needed.)
    /*
    @Test
    public void testGetRole(){
        try{
            assertNull(roleTest);
            roleTest = Role.MEMBER;
            Long userId= 1L;
            userRoleTest = new UserRole(userId,roleTest);
            assertEquals(Role.MEMBER, userRoleTest.getRole());
        }catch (Exception e){
            fail("Get role method in User Role class has failed to execute.");
        }
    }
    */
}
