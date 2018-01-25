package com.momentu.momentuandroid.Parcelable;

/**
 * Created by Fahad on 1/24/18.
 */

import android.os.Parcel;

import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.State;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * State Class Junit Test for Parcelable
 */
public class StateParcelTest {

    //An object from State class to check its functionality
    State stateTest = null;

    //Testing the Parcelable feature of State class
    @Test
    public void TestStateWithParcel() throws Exception {
        try {
            assertNull(stateTest);
            stateTest = new State("IL","Chicago");
            ArrayList<String> citiesTest = new ArrayList<String>();
            citiesTest.add("Chicago");
            // Write the data
            Parcel parcel = Parcel.obtain();

            stateTest.writeToParcel(parcel, stateTest.describeContents());

            // After you're done with writing, you need to reset the parcel for reading.
            parcel.setDataPosition(0);

            // Read the data
            State createdFromParcel = stateTest.CREATOR.createFromParcel(parcel);
            String createdFromParcelStateName = createdFromParcel.getStateName();
            ArrayList<String> createdFromParcelCities = createdFromParcel.getCities();

            // Verify that the received data is correct.
            assertEquals("IL",createdFromParcelStateName);
            assertEquals(citiesTest,createdFromParcelCities);


        }catch (Exception e){
            fail("Creating a State With Parcel object with parameters fails.");
        }
    }


}
