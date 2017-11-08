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
            //hashtagKeyTest = new HashtagKey();
            //assertNull(hashtagKeyTest);
            String label="hashtagLabel";
            Long locationId=1L;
            Location location = new Location ("TestCity", "TestState");
            hashtagKeyTest.setLabel(label);
            hashtagKeyTest.setLocation(location);
            hashtagKeyTest = new HashtagKey(label,locationId);
            assertEquals("hashtagLabel",hashtagKeyTest.getLabel());
        }catch (Exception e){
            System.out.print(e.getCause());
            fail("HashtagKey constructor with parameters can't be used");
        }
    }


    //Testing label set method
    @Test
    public void testSetLabel() {
        try {
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
            //hashtagKeyTest = new HashtagKey();
            //assertNull(hashtagKeyTest);
            String label="hashtagLabel";
            Long locationId=1L;
            Location location = new Location ("TestCity", "TestState");
            hashtagKeyTest.setLabel(label);
            hashtagKeyTest.setLocation(location);
            hashtagKeyTest = new HashtagKey(label,locationId);
            assertEquals("hashtagLabel",hashtagKeyTest.getLabel());
        }catch (Exception e){
            System.out.print(e.getCause());
            fail("HashtagKey constructor with parameters can't be used");
        }
    }
    */
}
