package com.momentu.momentuapi.testEntities;

import com.momentu.momentuapi.entities.Hashtag;
import com.momentu.momentuapi.entities.Location;
import com.momentu.momentuapi.entities.keys.HashtagKey;
import org.junit.Test;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

//This is a test class for Hashtag class
public class HashtagTest {

    //This is a test object from Hashtag class
    Hashtag hashtagTest = new Hashtag();

    //Testing Hashtag constructor
    @Test
    public void testHashtag() {
        try {
            Hashtag testHashtag = new Hashtag();
            assertTrue(testHashtag.getCount()==null);
        }catch (Exception e){
            fail("Hashtag constructor can't be used");
        }
    }

    //Testing count set method
    @Test
    public void testSetCount() {
        try {
            Long count = 1L;
            hashtagTest.setCount(1L);
            assertEquals(count, hashtagTest.getCount());
        }catch (Exception e){
            fail("count value has not been set correctly");
        }
    }

    //Testing count get method
    @Test
    public void testGetCount(){
        try {
            Long count = 1L;
            hashtagTest.setCount(1L);
            assertEquals(count, hashtagTest.getCount());
        }catch (Exception e){
            fail("count value has not been gotten correctly");
        }
    }

    //Testing Hashtag Key set method
    @Test
    public void testSetHashtagKey(){
        try {
            hashtagTest = new Hashtag();
            String label="hashtagLabel";
            Location location = new Location("Chicaog", "IL");
            HashtagKey hashtagKey =  new HashtagKey();
            hashtagKey.setLocation(location);
            hashtagKey.getLocation().setId(1L);
            hashtagTest.setHashtagKey(hashtagKey);
            assertEquals(hashtagKey, hashtagTest.getHashtagKey());

        }catch (Exception e){
            fail("Hashtag Key value has not been set correctly");
        }

    }

    //Testing Hashtag Key get method
    @Test
    public void testGetHashtagKey(){
        try {
            hashtagTest = new Hashtag();
            String label="hashtagLabel";
            Location location = new Location("Chicaog", "IL");
            HashtagKey hashtagKey =  new HashtagKey();
            hashtagKey.setLocation(location);
            hashtagKey.getLocation().setId(1L);
            hashtagTest.setHashtagKey(hashtagKey);
            assertEquals(hashtagKey, hashtagTest.getHashtagKey());

        }catch (Exception e){
            fail("Hashtag Key value has not been gotten correctly");
        }
    }

    //Testing label get method
    @Test
    public void testGetLabel(){
        try {
            hashtagTest = new Hashtag();
            String label="hashtagLabel";
            Location location = new Location("Chicaog", "IL");
            HashtagKey hashtagKey =  new HashtagKey();
            hashtagKey.setLocation(location);
            hashtagKey.getLocation().setId(1L);
            hashtagKey.setLabel(label);
            hashtagTest.setHashtagKey(hashtagKey);
            assertEquals(label, hashtagTest.getLabel());

        }catch (Exception e){
            fail("Hashtag Key value has not been gotten correctly");
        }
    }

    //Testing location get method
    @Test
    public void testGetLocation(){
        try {
            hashtagTest = new Hashtag();
            String label="hashtagLabel";
            Location location = new Location("Chicaog", "IL");
            HashtagKey hashtagKey =  new HashtagKey();
            hashtagKey.setLocation(location);
            hashtagKey.getLocation().setId(1L);
            hashtagTest.setHashtagKey(hashtagKey);
            assertEquals(location, hashtagTest.getLocation());

        }catch (Exception e){
            fail("Hashtag Key value has not been gotten correctly");
        }
    }

    //Testing Latitude set method
    @Test
    public void testSetLatitude(){
        try {
            Long latitude = 1L;
            hashtagTest.setLatitude(latitude);
            assertEquals(latitude, hashtagTest.getLatitude());
        }catch (Exception e){
            fail("Latitude value has not been set correctly");
        }


    }

    //Testing Latitude get method
    @Test
    public void testGetLatitude(){
        try {
            Long latitude = 1L;
            hashtagTest.setLatitude(latitude);
            assertEquals(latitude, hashtagTest.getLatitude());
        }catch (Exception e){
            fail("Latitude value has not been gotten correctly");
        }
    }

    //Testing Longitude set method
    @Test
    public void testSetLongitude(){
        try {
            Long longitude = 1L;
            hashtagTest.setLongitude(longitude);
            assertEquals(longitude, hashtagTest.getLongitude());
        }catch (Exception e){
            fail("longitude value has not been set correctly");
        }
    }

    //Testing Longitude get method
    @Test
    public void testGetLongitude(){
        try {
            Long longitude = 1L;
            hashtagTest.setLongitude(longitude);
            assertEquals(longitude, hashtagTest.getLongitude());
        }catch (Exception e){
            fail("longitude value has not been gotten correctly");
        }
    }

}
