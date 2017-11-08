package com.momentu.momentuapi.testEntities;


import com.momentu.momentuapi.entities.UserLike;
import com.momentu.momentuapi.entities.UserRole;
import com.momentu.momentuapi.entities.keys.UserLikeKey;
import com.momentu.momentuapi.entities.keys.UserRoleKey;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

//This is a test class for UserLikeTest class
public class UserLikeTest {

    //This is a test object from User Like class
    UserLike userLike = new UserLike();

    //Testing userLikeKey set method
    @Test
    public void testSetUserLikeKey(){
        try {
            Long userId = 1L;
            Long mediaMetaId = 2L;
            UserLikeKey userLikeKey = new UserLikeKey(userId,mediaMetaId);
            userLike.setUserLikeKey(userLikeKey);
            assertEquals(userLikeKey, userLike.getUserLikeKey());
        }catch (Exception e){
            fail("User Like Key value has not been set correctly");
        }
    }

    //Testing userLikeKey get method
    @Test
    public void testGetUserLikeKey(){
        try {
            Long userId = 1L;
            Long mediaMetaId = 2L;
            UserLikeKey userLikeKey = new UserLikeKey(userId,mediaMetaId);
            userLike.setUserLikeKey(userLikeKey);
            assertEquals(userLikeKey, userLike.getUserLikeKey());
        }catch (Exception e){
            fail("User Like Key value has not been gotten correctly");
        }
    }

}
