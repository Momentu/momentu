package com.momentu.momentuapi.testEntitiesKeys;


import com.momentu.momentuapi.entities.Hashtag;
import com.momentu.momentuapi.entities.Location;
import com.momentu.momentuapi.entities.keys.HashtagKey;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

//This is a test class for HashtagKey class
public class HashtagKeyTest {

    //This is a test object from HashtagKey class
    HashtagKey hashtagKeyTest;

    //Testing HashtagKey constructor without parameters
    @Test
    public void testHashtagKey() {
        try {
            assertNull(hashtagKeyTest);
            hashtagKeyTest = new HashtagKey();
            assertNotNull(hashtagKeyTest);
        }catch (Exception e){
            fail("HashtagKey constructor without parameters can't be used");
        }
    }
    /*
    //Testing HashtagKey constructor with parameters
    @Test
    public void testHashtagKeyWithParameter() {
        try {
            hashtagKeyTest = new HashtagKey();
            Location location = new Location();
            location.setCity("Chicago");
            location.setState("IL");
            location.setId(1L);
            hashtagKeyTest = new HashtagKey("hashtagLabel",1L);
            hashtagKeyTest.setLocation(location);
            assertEquals("hashtagLabel", hashtagKeyTest.getLabel());
        }catch (Exception e){
            fail("HashtagKey constructor with parameters can't be used");
        }
    }
    */

    //Testing label set method
    @Test
    public void testSetLabel() {
        try {
            hashtagKeyTest = new HashtagKey();
            Location location = new Location();
            location.setCity("Chicago");
            location.setState("IL");
            hashtagKeyTest.setLocation(location);
            String label="hashtagLabel";
            hashtagKeyTest.setLabel(label);
            assertEquals("hashtagLabel",hashtagKeyTest.getLabel());
        }catch (Exception e){
            fail("label value has not been set correctly");
        }
    }
    //Testing label get method
    @Test
    public void testGetLabel() {
        try {
            hashtagKeyTest = new HashtagKey();
            Location location = new Location();
            location.setCity("Chicago");
            location.setState("IL");
            hashtagKeyTest.setLocation(location);
            String label="hashtagLabel";
            hashtagKeyTest.setLabel(label);
            assertEquals("hashtagLabel",hashtagKeyTest.getLabel());
        }catch (Exception e){
            fail("label value has not been gotten correctly");
        }
    }

    //Testing LocationId set method
    @Test
    public void testSetLocationId() {
        try {
            hashtagKeyTest = new HashtagKey();
            Location location = new Location();
            location.setCity("Chicago");
            location.setState("IL");
            hashtagKeyTest.setLocation(location);
            Long locationId=1L;
            hashtagKeyTest.setLocationId(locationId);
            assertEquals(locationId,hashtagKeyTest.getLocationId());
        }catch (Exception e){
            fail("LocationId value has not been set correctly");
        }
    }
    //Testing LocationId get method
    @Test
    public void testGetLocationId() {
        try {
            hashtagKeyTest = new HashtagKey();
            Location location = new Location();
            location.setCity("Chicago");
            location.setState("IL");
            hashtagKeyTest.setLocation(location);
            Long locationId=1L;
            hashtagKeyTest.setLocationId(locationId);
            assertEquals(locationId,hashtagKeyTest.getLocationId());
        }catch (Exception e){
            fail("LocationId value has not been gotten correctly");
        }
    }

    //Testing Location set method
    @Test
    public void testSetLocation() {
        try {
            hashtagKeyTest = new HashtagKey();
            Location location = new Location();
            location.setCity("Chicago");
            location.setState("IL");
            hashtagKeyTest.setLocation(location);
            assertEquals(location,hashtagKeyTest.getLocation());
            assertEquals("Chicago",hashtagKeyTest.getLocation().getCity());
            assertEquals("IL",hashtagKeyTest.getLocation().getState());
        }catch (Exception e){
            fail("Location value has not been set correctly");
        }
    }
    //Testing LocationId get method
    @Test
    public void testGetLocation() {
        try {
            hashtagKeyTest = new HashtagKey();
            Location location = new Location();
            location.setCity("Chicago");
            location.setState("IL");
            hashtagKeyTest.setLocation(location);
            assertEquals(location,hashtagKeyTest.getLocation());
            assertEquals("Chicago",hashtagKeyTest.getLocation().getCity());
            assertEquals("IL",hashtagKeyTest.getLocation().getState());
        }catch (Exception e){
            fail("Location value has not been gotten correctly");
        }
    }
}
