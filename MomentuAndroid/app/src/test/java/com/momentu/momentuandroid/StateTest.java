package com.momentu.momentuandroid;

import com.momentu.momentuandroid.Model.State;
import com.momentu.momentuandroid.Model.StatesAndCities;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Fahad on 1/22/18.
 */

/**
 * State Class Junit Test
 */
public class StateTest {

    //An object from State class to check its functionality
    State stateTest;


    //Testing the constructor without parameters of State class
    @Test
    public void TestState() throws Exception {
        try {
            assertNull(stateTest);
            stateTest = new State("IL");
            assertNotNull(stateTest);
        }catch (Exception e){
            fail("the constructor without parameters of State class doesn't work properly.");
        }
    }

    //Testing the constructor with parameters of State class
    @Test
    public void TestStateWithParameters() throws Exception {
        try {
            assertNull(stateTest);
            stateTest = new State("IL","Chicago");
            assertNotNull(stateTest);
        }catch (Exception e){
            fail("the constructor with parameters of State class doesn't work properly.");
        }
    }

    //Testing the equals method in State class
    @Test
    public void TestEqual() throws Exception {
        try {
            State stateTest2 = new State("IL","Chicago");
            stateTest = new State("IL","Chicago");
            assertTrue(stateTest.equals(stateTest2));
        }catch (Exception e){
            fail("the equals method of State class doesn't work properly.");
        }
    }

    //Testing the hashcode method in State class
    @Test
    public void TestHashCode() throws Exception {
        try {
            stateTest = new State("IL","Chicago");
            assertNotEquals(stateTest.getStateName(),stateTest.hashCode());
        }catch (Exception e){
            fail("the hashcode method of State class doesn't work properly.");
        }
    }

    //Testing describeContents method in State class
    @Test
    public void TestDescribeContents() throws Exception {
        try {
            stateTest = new State("IL","Chicago");
            assertEquals(0,stateTest.describeContents());
        }catch (Exception e){
            fail("the describeContents method of State class doesn't work properly.");
        }
    }

    //Testing toString method in State class
    @Test
    public void TestToString() throws Exception {
        try {
            stateTest = new State("IL","Chicago");
            assertEquals("IL",stateTest.toString());
        }catch (Exception e){
            fail("the toString method of State class doesn't work properly.");
        }
    }

    //Testing setStateName method in State class
    @Test
    public void TestSetStateName() throws Exception {
        try {
            stateTest = new State("IL","Chicago");
            stateTest.setStateName("NY");
            assertEquals("NY",stateTest.getStateName());
        }catch (Exception e){
            fail("the setStateName method of State class doesn't work properly.");
        }
    }

    //Testing getStateName method in State class
    @Test
    public void TestGetStateName() throws Exception {
        try {
            stateTest = new State("IL","Chicago");
            stateTest.setStateName("NY");
            assertEquals("NY",stateTest.getStateName());
        }catch (Exception e){
            fail("the getStateName method of State class doesn't work properly.");
        }
    }

    //Testing setCities method in State class
    @Test
    public void TestSetCities() throws Exception {
        try {
            ArrayList<String> citiesTest = new ArrayList<String>();
            citiesTest.add("Chicago");
            citiesTest.add("Springfield");
            stateTest = new State("IL");
            stateTest.setCities(citiesTest);
            assertEquals(citiesTest,stateTest.getCities());
        }catch (Exception e){
            fail("the setCities method of State class doesn't work properly.");
        }
    }

    //Testing getCities method in State class
    @Test
    public void TestGetCities() throws Exception {
        try {
            ArrayList<String> citiesTest = new ArrayList<String>();
            citiesTest.add("Chicago");
            citiesTest.add("Springfield");
            stateTest = new State("IL");
            stateTest.setCities(citiesTest);
            assertEquals(citiesTest,stateTest.getCities());
        }catch (Exception e){
            fail("the getCities method of State class doesn't work properly.");
        }
    }

    //Testing addCity method in State class
    @Test
    public void TestAddCity() throws Exception {
        try {
            ArrayList<String> citiesTest = new ArrayList<String>();
            citiesTest.add("Chicago");
            citiesTest.add("Springfield");
            stateTest = new State("IL");
            stateTest.setCities(citiesTest);
            assertEquals(2,stateTest.getCities().size());
            stateTest.addCity("Naperville");
            assertEquals(3,stateTest.getCities().size());
        }catch (Exception e){
            fail("the addcity method of State class doesn't work properly.");
        }
    }

}
