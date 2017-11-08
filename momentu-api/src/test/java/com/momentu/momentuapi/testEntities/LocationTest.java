package com.momentu.momentuapi.testEntities;

import com.momentu.momentuapi.entities.Hashtag;
import com.momentu.momentuapi.entities.Location;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//This is a test class for Location class
public class LocationTest {

    //This is a test object from Location class
    Location locationTest = new Location();

    //Testing Location constructor
    @Test
    public void testLocation() {
        try {
            assertTrue(locationTest.getCity()==null);
        }catch (Exception e){
            fail("Location constructor can't be used without parameters");
        }
    }

    //Testing Location constructor with parameters
    @Test
    public void testLocationWithParameters() {
        try {
            Location locationTest2 = new Location("Chicago","IL");
            assertEquals("Chicago",locationTest2.getCity());
            assertEquals("IL",locationTest2.getState());
        }catch (Exception e){
            fail("Location constructor can't be used with parameters");
        }
    }

    //Testing city set method
    @Test
    public void testSetCity(){
        try {
            String city = "Chicago";
            locationTest.setCity(city);
            assertEquals(city, locationTest.getCity());
        }catch (Exception e){
            fail("City value has not been set correctly");
        }

    }

    //Testing city get method
    @Test
    public void testGetCity(){
        try {
            String city = "Chicago";
            locationTest.setCity(city);
            assertEquals(city, locationTest.getCity());
        }catch (Exception e){
            fail("City value has not been gotten correctly");
        }
    }

    //Testing state set method
    @Test
    public void testSetState(){
        try {
            String state = "IL";
            locationTest.setState(state);
            assertEquals(state, locationTest.getState());
        }catch (Exception e){
            fail("State value has not been set correctly");
        }
    }

    //Testing state get method
    @Test
    public void testGetState(){
        try {
            String state = "IL";
            locationTest.setState(state);
            assertEquals(state, locationTest.getState());
        }catch (Exception e){
            fail("State value has not been gotten correctly");
        }
    }

    /*
    //Testing Hashtags get method
    @Test
    public void testGetHashtags(){
        try {
            Hashtag hashtag1 = new Hashtag();
            hashtagTest.setLongitude(longitude);
            assertEquals(longitude, hashtagTest.getLongitude());
        }catch (Exception e){
            fail("longitude value has not been gotten correctly");
        }
    }
    */
}
