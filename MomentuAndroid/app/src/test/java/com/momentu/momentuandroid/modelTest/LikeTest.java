package com.momentu.momentuandroid.modelTest;

/**
 * Created by Fahad on 11/14/17.
 */

import com.momentu.momentuandroid.Model.Like;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Like Class Junit Test
 */
public class LikeTest {

    //An object from Like class to check its functionality
    Like like = null;

    //Testing the constructor with parameters of Like class
    @Test
    public void TestLike() throws Exception {
        try {
            assertNull(like);
            int likesCount = 10;
            boolean isLiked = true;
            like = new Like(likesCount,isLiked);
            assertNotNull(like);
        }catch (Exception e){
            fail("Creating a like object with parameters fails.");
        }
    }

    //Testing get likesCount method in Like class
    @Test
    public void TestGetLikeCount() throws Exception {
        try {
            int likesCount = 10;
            boolean isLiked = true;
            like = new Like(likesCount,isLiked);
            assertEquals(10,like.getLikesCount());
        }catch (Exception e){
            fail("get likesCount method doesn't work properly.");
        }
    }

    //Testing get isLiked method in Like class
    @Test
    public void TestGetIsLiked() throws Exception {
        try {
            int likesCount = 10;
            boolean isLiked = true;
            like = new Like(likesCount,isLiked);
            assertEquals(true,like.getIsLiked());
        }catch (Exception e){
            fail("get isLiked method doesn't work properly.");
        }
    }

    //Testing set isLiked method in Like class
    @Test
    public void TestSetIsLiked() throws Exception {
        try {
            int likesCount = 10;
            boolean isLiked = true;
            like = new Like(likesCount,isLiked);
            like.setIsLiked(false);
            assertEquals(false,like.getIsLiked());
        }catch (Exception e){
            fail("set isLiked method doesn't work properly.");
        }
    }

    //Testing addLikesCount method in Like class
    @Test
    public void TestAddLikesCount() throws Exception {
        try {
            int likesCount = 10;
            boolean isLiked = true;
            like = new Like(likesCount,isLiked);
            like.addLikesCount();
            assertEquals(11,like.getLikesCount());
        }catch (Exception e){
            fail("addLikesCount method doesn't work properly.");
        }
    }

    //Testing minusLikesCount method in Like class
    @Test
    public void TestMinusLikesCount() throws Exception {
        try {
            int likesCount = 10;
            boolean isLiked = true;
            like = new Like(likesCount,isLiked);
            like.minusLikesCount();
            assertEquals(9,like.getLikesCount());
        }catch (Exception e){
            fail("addLikesCount method doesn't work properly.");
        }
    }
}
