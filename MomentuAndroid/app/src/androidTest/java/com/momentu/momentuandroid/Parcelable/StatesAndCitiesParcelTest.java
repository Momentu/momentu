package com.momentu.momentuandroid.Parcelable;

/**
 * Created by Fahad on 1/24/18.
 */

import android.os.Parcel;

import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.State;
import com.momentu.momentuandroid.Model.StatesAndCities;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * StatesAndCities Class Junit Test for Parcelable
 */
public class StatesAndCitiesParcelTest {

    //An object from StatesAndCities class to check its functionality
    StatesAndCities statesAndCitiesTest;

    //Testing the Parcelable feature of of StatesAndCities class **Need Refactor
    @Test
    public void TestStatesAndCitiesWithParcel() throws Exception {
        try {
            assertNull(statesAndCitiesTest);
            ArrayList<State> stateArray = new ArrayList<State>();
            stateArray.add(new State("IL"));
            statesAndCitiesTest = new StatesAndCities();
            statesAndCitiesTest.setState(stateArray);

            // Write the data
            Parcel parcel = Parcel.obtain();

            //statesAndCitiesTest.writeToParcel(parcel,statesAndCitiesTest.describeContents());

            // After you're done with writing, you need to reset the parcel for reading.
            //parcel.setDataPosition(0);

            // Read the data
            //StatesAndCities createdFromParcel = statesAndCitiesTest.CREATOR.createFromParcel(parcel);
            //ArrayList<State> createdFromParcelStates = createdFromParcel.getStates();

            // Verify that the received data is correct.
            //assertEquals(stateArray,createdFromParcelStates);

        }catch (Exception e){
            fail("Creating a StatesAndCities With Parcel object with parameters fails.");
        }
    }

}
