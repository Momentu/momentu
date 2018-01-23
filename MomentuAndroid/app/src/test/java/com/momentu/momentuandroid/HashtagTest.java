package com.momentu.momentuandroid;

/**
 * Created by Fahad on 11/14/17.
 */

import android.graphics.drawable.Drawable;
import android.os.Parcel;

import com.momentu.momentuandroid.Model.FeedItem;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.Like;
import com.momentu.momentuandroid.Model.State;
import com.momentu.momentuandroid.Model.StatesAndCities;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Hashtag Class Junit Test
 */
public class HashtagTest {

    //An object from Hashtag class to check its functionality
    Hashtag hashtag = null;

    //Testing the constructor with parameters of Hashtag class
    @Test
    public void TestHashtag() throws Exception {
        try {
            assertNull(hashtag);
            String label = "#event";
            int count = 10;
            hashtag = new Hashtag(label,count);
            assertNotNull(hashtag);
        }catch (Exception e){
            fail("Creating a hashtag object with parameters fails.");
        }
    }
    /*
    //Testing the constructor with Parcel of Hashtag class
    @Test
    public void TestHashtagWithParcel() throws Exception {
        try {
            assertNull(hashtag);
            String label = "#event";
            int count = 10;
            hashtag = new Hashtag(label,count);
            Parcel parcel =null;
            parcel.setDataPosition(0);
            //hashtag.writeToParcel(parcel,hashtag.describeContents());
            //parcel = Parcel.obtain();
            //hashtag.writeToParcel(parcel,0);
            //parcel.setDataPosition(0);
            //assertEquals("#event",parcel.readString());
            //Hashtag createdFromParcel = Hashtag.CREATOR.createFromParcel(parcel);
            //assertThat(createdFromParcel.getAuthor(), is("author"));
            //assertThat(createdFromParcel.getTitle(), is("title"));

        }catch (Exception e){
            fail("Creating a hashtag With Parcel object with parameters fails.");
        }
    }
    */

    //Testing the setCount method in Hashtag class
    @Test
    public void TestSetCount() throws Exception {
        try {
            assertNull(hashtag);
            String label = "#event";
            int count = 10;
            hashtag = new Hashtag(label,count);
            hashtag.setCount(9);
            assertEquals(9,hashtag.getCount());
        }catch (Exception e){
            fail("setCount method doesn't work properly.");
        }
    }

    //Testing the getCount method in Hashtag class
    @Test
    public void TestGetCount() throws Exception {
        try {
            assertNull(hashtag);
            String label = "#event";
            int count = 10;
            hashtag = new Hashtag(label,count);
            hashtag.setCount(9);
            assertEquals(9,hashtag.getCount());
        }catch (Exception e){
            fail("getCount method doesn't work properly.");
        }
    }

    //Testing the setLabel method in Hashtag class
    @Test
    public void TestSetLabel() throws Exception {
        try {
            assertNull(hashtag);
            String label = "#event";
            int count = 10;
            hashtag = new Hashtag(label,count);
            hashtag.setLabel("#fahad");
            assertEquals("#fahad",hashtag.getLabel());
        }catch (Exception e){
            fail("setLabel method doesn't work properly.");
        }
    }

    //Testing the getLabel method in Hashtag class
    @Test
    public void TestGetLabel() throws Exception {
        try {
            assertNull(hashtag);
            String label = "#event";
            int count = 10;
            hashtag = new Hashtag(label,count);
            hashtag.setLabel("#fahad");
            assertEquals("#fahad",hashtag.getLabel());
        }catch (Exception e){
            fail("getLabel method doesn't work properly.");
        }
    }

    //Testing describeContents method in Hashtag class
    @Test
    public void TestDescribeContents() throws Exception {
        try {
            assertNull(hashtag);
            String label = "#event";
            int count = 10;
            hashtag = new Hashtag(label,count);
            assertEquals(0,hashtag.describeContents());
        }catch (Exception e){
            fail("the describeContents method of Hashtag class doesn't work properly.");
        }
    }

    //Testing toString method in Hashtag class
    @Test
    public void TestToString() throws Exception {
        try {
            assertNull(hashtag);
            String label = "#event";
            int count = 10;
            hashtag = new Hashtag(label,count);
            assertEquals("#event",hashtag.toString());
        }catch (Exception e){
            fail("the toString method of Hashtag class doesn't work properly.");
        }
    }


}
