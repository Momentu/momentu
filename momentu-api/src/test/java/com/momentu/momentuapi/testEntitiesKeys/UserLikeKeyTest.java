package com.momentu.momentuapi.testEntitiesKeys;

import com.momentu.momentuapi.entities.keys.UserLikeKey;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

//This is a test class for UserLikeKey class
public class UserLikeKeyTest {

    //This is a test object from UserLikeKey class
    UserLikeKey userLikeKey;

    //Testing UserLikeKey constructor without parameters
    @Test
    public void testUserLikeKey() {
        try {
            assertNull(userLikeKey);
            userLikeKey = new UserLikeKey();
            assertNotNull(userLikeKey);
        }catch (Exception e){
            fail("UserLikeKey constructor without parameters can't be used");
        }
    }

    //Testing UserLikeKey constructor with parameters
    @Test
    public void testUserLikeKeyWithParameter() {
        try {
            assertNull(userLikeKey);
            userLikeKey = new UserLikeKey();
            Long userId= 1L;
            Long mediaMetaId = 2L;
            userLikeKey = new UserLikeKey(userId,mediaMetaId);
            assertNotNull(userLikeKey);
            assertEquals(userId, userLikeKey.getUserId());
            assertEquals(mediaMetaId, userLikeKey.getMediaMetaId());
        }catch (Exception e){
            fail("UserLikeKey constructor with parameters can't be used");
        }
    }

    //Testing userId set method
    @Test
    public void testSetUserId() {
        try {
            userLikeKey = new UserLikeKey();
            Long userId= 1L;
            userLikeKey.setUserId(userId);
            assertEquals(userId, userLikeKey.getUserId());
        }catch (Exception e){
            fail("userId value has not been set correctly");
        }
    }
    //Testing userId get method
    @Test
    public void testGetLabel() {
        try {
            userLikeKey = new UserLikeKey();
            Long userId= 1L;
            userLikeKey.setUserId(userId);
            assertEquals(userId, userLikeKey.getUserId());
        }catch (Exception e){
            fail("userId value has not been gotten correctly");
        }
    }

    //Testing mediaMetaId set method
    @Test
    public void testSetLocationId() {
        try {
            userLikeKey = new UserLikeKey();
            Long userId= 1L;
            userLikeKey.setUserId(userId);
            assertEquals(userId, userLikeKey.getUserId());
        }catch (Exception e){
            fail("mediaMetaId value has not been set correctly");
        }
    }
    //Testing mediaMetaId get method
    @Test
    public void testGetLocationId() {
        try {
            userLikeKey = new UserLikeKey();
            Long mediaMetaId= 1L;
            userLikeKey.setMediaMetaId(mediaMetaId);
            assertEquals(mediaMetaId, userLikeKey.getMediaMetaId());
        }catch (Exception e){
            fail("mediaMetaId value has not been gotten correctly");
        }
    }

}
