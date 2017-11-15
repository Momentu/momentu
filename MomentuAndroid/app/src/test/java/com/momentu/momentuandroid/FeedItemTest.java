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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * FeedItem Class Junit Test
 */
public class FeedItemTest {

    //An object from FeedItem class to check its functionality
    FeedItem feedItem = null;

    //Testing the constructor with parameters of Like class
    @Test
    public void TestFeedItem() throws Exception {
        try {
            assertNull(feedItem);
            BigInteger id=null;
            BigInteger userID=null;
            Hashtag hashTag = new Hashtag("#event", 10) ;
            String description = "This is a test description";
            Drawable media=null;
            BigInteger locationID=null;
            Date createdTime = new Date();
            Boolean removed = false;
            Like likeFeed = new Like(10, true);
            feedItem = new FeedItem(id,userID,hashTag,media,description,locationID,createdTime,removed,likeFeed);
            assertNotNull(feedItem);
        }catch (Exception e){
            fail("Creating a like object with parameters fails.");
        }
    }
}
