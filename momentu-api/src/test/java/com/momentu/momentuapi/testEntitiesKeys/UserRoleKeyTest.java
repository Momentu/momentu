package com.momentu.momentuapi.testEntitiesKeys;

import com.momentu.momentuapi.entities.Role;
import com.momentu.momentuapi.entities.keys.UserLikeKey;
import com.momentu.momentuapi.entities.keys.UserRoleKey;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//This is a test class for UserRoleKey class
public class UserRoleKeyTest {


    //This is a test object from UserRoleKey class
    UserRoleKey userRoleKey;

    //Testing UserRoleKey constructor without parameters
    @Test
    public void testUserRoleKey() {
        try {
            assertNull(userRoleKey);
            userRoleKey = new UserRoleKey();
            assertNotNull(userRoleKey);
        }catch (Exception e){
            fail("UserRoleKey constructor without parameters can't be used");
        }
    }

    //Testing UserRoleKey constructor with parameters
    @Test
    public void testUserRoleKeyWithParameter() {
        try {
            assertNull(userRoleKey);
            userRoleKey = new UserRoleKey();
            Long userId= 1L;
            Role role =Role.MEMBER;
            userRoleKey = new UserRoleKey(userId,role);
            assertNotNull(userRoleKey);
        }catch (Exception e){
            fail("UserRoleKey constructor with parameters can't be used");
        }
    }


}
