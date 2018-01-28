package com.momentu.momentuandroid.modelTest;

/**
 * Created by Fahad on 1/23/18.
 */

import com.momentu.momentuandroid.Model.City;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * City Class Junit Test
 */

public class CityTest {

    //An object from City class to check its functionality
    City cityTest;


    //Testing the constructor without parameters of City class
    @Test
    public void TestCity() throws Exception {
        try {
            assertNull(cityTest);
            cityTest = new City("Chicago");
            assertNotNull(cityTest);
        }catch (Exception e){
            fail("the constructor without parameters of City class doesn't work properly.");
        }
    }

    //Testing setCityName method in City class
    @Test
    public void TestSetStateName() throws Exception {
        try {
            cityTest = new City("Chicago");
            cityTest.setCityName("Springfield");
            assertEquals("Springfield",cityTest.getCityName());
        }catch (Exception e){
            fail("the setCityName method of City class doesn't work properly.");
        }
    }

    //Testing getCityName method in City class
    @Test
    public void TestGetStateName() throws Exception {
        try {
            cityTest = new City("Chicago");
            cityTest.setCityName("Springfield");
            assertEquals("Springfield",cityTest.getCityName());
        }catch (Exception e){
            fail("the getCityName method of City class doesn't work properly.");
        }
    }

    //Testing describeContents method in City class
    @Test
    public void TestDescribeContents() throws Exception {
        try {
            cityTest = new City("Chicago");
            assertEquals(0,cityTest.describeContents());
        }catch (Exception e){
            fail("the describeContents method of City class doesn't work properly.");
        }
    }

    //Testing toString method in City class
    @Test
    public void TestToString() throws Exception {
        try {
            cityTest = new City("Chicago");
            assertEquals("Chicago",cityTest.toString());
        }catch (Exception e){
            fail("the toString method of City class doesn't work properly.");
        }
    }

}
