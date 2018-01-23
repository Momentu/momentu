package com.momentu.momentuandroid;

/**
 * Created by Fahad on 1/22/18.
 */

import com.momentu.momentuandroid.Model.State;
import com.momentu.momentuandroid.Model.StatesAndCities;
import com.momentu.momentuandroid.Model.TrendHashTagCard;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * StatesAndCities Class Junit Test
 */
public class StatesAndCitiesTest {

    //An object from StatesAndCities class to check its functionality
    StatesAndCities statesAndCitiesTest;


    //Testing the constructor without parameters of StatesAndCities class
    @Test
    public void TestStatesAndCities() throws Exception {
        try {
            assertNull(statesAndCitiesTest);
            statesAndCitiesTest = new StatesAndCities();
            assertNotNull(statesAndCitiesTest);
        }catch (Exception e){
            fail("the constructor without parameters of StatesAndCities class doesn't work properly.");
        }
    }

    //Testing the constructor with parameters of StatesAndCities class
    @Test
    public void TestStatesAndCitiesWithParameters() throws Exception {
        try {
            assertNull(statesAndCitiesTest);
            ArrayList<State> stateArray = new ArrayList<State>();
            stateArray.add(new State("IL","Chicago"));
            statesAndCitiesTest = new StatesAndCities(stateArray);
            assertNotNull(statesAndCitiesTest);
        }catch (Exception e){
            fail("the constructor with parameters of StatesAndCities class doesn't work properly.");
        }
    }

    //Testing setState method in StatesAndCities class
    @Test
    public void TestSetState() throws Exception {
        try {
            ArrayList<State> stateArray = new ArrayList<State>();
            stateArray.add(new State("IL","Chicago"));
            statesAndCitiesTest = new StatesAndCities();
            statesAndCitiesTest.setState(stateArray);
            assertEquals(stateArray,statesAndCitiesTest.getStates());
        }catch (Exception e){
            fail("the setState method of StatesAndCities class doesn't work properly.");
        }
    }

    //Testing getState method in StatesAndCities class
    @Test
    public void TestGetState() throws Exception {
        try {
            ArrayList<State> stateArray = new ArrayList<State>();
            stateArray.add(new State("IL","Chicago"));
            statesAndCitiesTest = new StatesAndCities();
            statesAndCitiesTest.setState(stateArray);
            assertEquals(stateArray,statesAndCitiesTest.getStates());
        }catch (Exception e){
            fail("the getState method of StatesAndCities class doesn't work properly.");
        }
    }

    //Testing describeContents method in StatesAndCities class
    @Test
    public void TestDescribeContents() throws Exception {
        try {
            ArrayList<State> stateArray = new ArrayList<State>();
            stateArray.add(new State("IL","Chicago"));
            statesAndCitiesTest = new StatesAndCities();
            statesAndCitiesTest.setState(stateArray);
            assertEquals(0,statesAndCitiesTest.describeContents());
        }catch (Exception e){
            fail("the describeContents method of StatesAndCities class doesn't work properly.");
        }
    }

    //Testing toString method in StatesAndCities class
    @Test
    public void TestToString() throws Exception {
        try {
            ArrayList<State> stateArray = new ArrayList<State>();
            stateArray.add(new State("IL","Chicago"));
            statesAndCitiesTest = new StatesAndCities();
            statesAndCitiesTest.setState(stateArray);
            assertEquals(stateArray.toString(),statesAndCitiesTest.toString());
        }catch (Exception e){
            fail("the toString method of StatesAndCities class doesn't work properly.");
        }
    }

}
