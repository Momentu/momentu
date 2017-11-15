package com.momentu.momentuandroid;

/**
 * Created by Fahad on 11/14/17.
 */


import android.graphics.drawable.Drawable;

import com.momentu.momentuandroid.Model.FeedItem;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.Like;

import org.junit.Test;

import java.math.BigInteger;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * FeedItem Class Junit Test
 */
public class FeedItemTest {

    //An object from FeedItem class to check its functionality
    FeedItem feedItem = null;

    //Testing the constructor with parameters of FeedItem class
    @Test
    public void TestFeedItem() throws Exception {
        try {
            assertNull(feedItem);
            BigInteger id= BigInteger.valueOf(1L);
            BigInteger userID= BigInteger.valueOf(1L);
            Hashtag hashTag = new Hashtag("#event", 10) ;
            String description = "This is a test description";
            Drawable media=null;
            BigInteger locationID= BigInteger.valueOf(1L);
            Date createdTime = new Date();
            Boolean removed = false;
            Like likeFeed = new Like(10, true);
            feedItem = new FeedItem(id,userID,hashTag,media,description,locationID,createdTime,removed,likeFeed);
            assertNotNull(feedItem);
        }catch (Exception e){
            fail("Creating a like object with parameters fails.");
        }
    }

    //Testing the getHashTag method in FeedItem class
    @Test
    public void TestGetHashTag() throws Exception {
        try {
            assertNull(feedItem);
            BigInteger id= BigInteger.valueOf(1L);
            BigInteger userID= BigInteger.valueOf(1L);
            Hashtag hashTag = new Hashtag("#event", 10) ;
            String description = "This is a test description";
            Drawable media=null;
            BigInteger locationID= BigInteger.valueOf(1L);
            Date createdTime = new Date();
            Boolean removed = false;
            Like likeFeed = new Like(10, true);
            feedItem = new FeedItem(id,userID,hashTag,media,description,locationID,createdTime,removed,likeFeed);
            assertEquals(hashTag,feedItem.getHashTag());
        }catch (Exception e){
            fail("getHashTag method doesn't work properly.");
        }
    }

    //Testing the getDescription method in FeedItem class
    @Test
    public void TestGetDescription() throws Exception {
        try {
            assertNull(feedItem);
            BigInteger id= BigInteger.valueOf(1L);
            BigInteger userID= BigInteger.valueOf(1L);
            Hashtag hashTag = new Hashtag("#event", 10) ;
            String description = "This is a test description";
            Drawable media=null;
            BigInteger locationID= BigInteger.valueOf(1L);
            Date createdTime = new Date();
            Boolean removed = false;
            Like likeFeed = new Like(10, true);
            feedItem = new FeedItem(id,userID,hashTag,media,description,locationID,createdTime,removed,likeFeed);
            assertEquals("This is a test description",feedItem.getDescription());
        }catch (Exception e){
            fail("getDescription method doesn't work properly.");
        }
    }

    //Testing the getMedia method in FeedItem class
    @Test
    public void TestGetMedia() throws Exception {
        try {
            assertNull(feedItem);
            BigInteger id= BigInteger.valueOf(1L);
            BigInteger userID= BigInteger.valueOf(1L);
            Hashtag hashTag = new Hashtag("#event", 10) ;
            String description = "This is a test description";
            Drawable media=null;
            BigInteger locationID= BigInteger.valueOf(1L);
            Date createdTime = new Date();
            Boolean removed = false;
            Like likeFeed = new Like(10, true);
            feedItem = new FeedItem(id,userID,hashTag,media,description,locationID,createdTime,removed,likeFeed);
            assertEquals(null,feedItem.getMedia());
        }catch (Exception e){
            fail("getMedia method doesn't work properly.");
        }
    }

    //Testing the getLike method in FeedItem class
    @Test
    public void TestGetLike() throws Exception {
        try {
            assertNull(feedItem);
            BigInteger id= BigInteger.valueOf(1L);
            BigInteger userID= BigInteger.valueOf(1L);
            Hashtag hashTag = new Hashtag("#event", 10) ;
            String description = "This is a test description";
            Drawable media=null;
            BigInteger locationID= BigInteger.valueOf(1L);
            Date createdTime = new Date();
            Boolean removed = false;
            Like likeFeed = new Like(10, true);
            feedItem = new FeedItem(id,userID,hashTag,media,description,locationID,createdTime,removed,likeFeed);
            assertEquals(likeFeed,feedItem.getLike());
        }catch (Exception e){
            fail("getLike method doesn't work properly.");
        }
    }

}
