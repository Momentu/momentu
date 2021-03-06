package com.momentu.momentuapi.testModels;

import com.momentu.momentuapi.entities.Location;
import com.momentu.momentuapi.entities.keys.HashtagKey;
import com.momentu.momentuapi.models.HashtagAndLocation;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

//This is a test class for HashtagAndLocation class
public class HashtagAndLocationTest {

    //This is a test object from HashtagAndLocation class
    HashtagAndLocation hashtagAndLocation;


    //Testing hashtagLabel set method
    @Test
    public void testHashtagAndLocation() {
        try {
            assertNull(hashtagAndLocation);
            hashtagAndLocation = new HashtagAndLocation("#Chicago","Chicago","IL");
            assertNotNull(hashtagAndLocation);
        }catch (Exception e){
            fail("hashtagLabel value has not been set correctly");
        }
    }


    //Testing hashtagLabel set method
    @Test
    public void testSetHashtagLabel() {
        try {
            hashtagAndLocation = new HashtagAndLocation();
            hashtagAndLocation.setHashtagLabel("#Chicago");
            assertEquals("#chicago",hashtagAndLocation.getHashtagLabel());
        }catch (Exception e){
            fail("hashtagLabel value has not been set correctly");
        }
    }
    //Testing hashtagLabel get method
    @Test
    public void testGetHashtagLabel() {
        try {
            hashtagAndLocation = new HashtagAndLocation();
            hashtagAndLocation.setHashtagLabel("#Chicago");
            assertEquals("#chicago",hashtagAndLocation.getHashtagLabel());
        }catch (Exception e){
            fail("hashtagLabel value has not been gotten correctly");
        }
    }

    //Testing city set method
    @Test
    public void testSetCity() {
        try {
            hashtagAndLocation = new HashtagAndLocation();
            hashtagAndLocation.setCity("Chicago");
            assertEquals("Chicago",hashtagAndLocation.getCity());
        }catch (Exception e){
            fail("city value has not been set correctly");
        }
    }

    //Testing city get method
    @Test
    public void testGetCity() {
        try {
            hashtagAndLocation = new HashtagAndLocation();
            hashtagAndLocation.setCity("Chicago");
            assertEquals("Chicago",hashtagAndLocation.getCity());
        }catch (Exception e){
            fail("city value has not been gotten correctly");
        }
    }

    //Testing state set method
    @Test
    public void testSetState() {
        try {
            hashtagAndLocation = new HashtagAndLocation();
            hashtagAndLocation.setState("IL");
            assertEquals("IL",hashtagAndLocation.getState());
        }catch (Exception e){
            fail("state value has not been set correctly");
        }
    }
    //Testing state get method
    @Test
    public void testGetState() {
        try {
            hashtagAndLocation = new HashtagAndLocation();
            hashtagAndLocation.setState("IL");
            assertEquals("IL",hashtagAndLocation.getState());
        }catch (Exception e){
            fail("state value has not been gotten correctly");
        }
    }

    //Testing isValid method
    @Test
    public void testIsValid() {
        try {
            hashtagAndLocation = new HashtagAndLocation();
            assertFalse(hashtagAndLocation.isValid());

            hashtagAndLocation.setState("");
            assertFalse(hashtagAndLocation.isValid());

            hashtagAndLocation.setCity("");
            assertFalse(hashtagAndLocation.isValid());

        }catch (Exception e){
            fail("is valid method doesn't work properly");
        }
    }
}
