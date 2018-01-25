package com.momentu.momentuandroid.Parcelable;

/**
 * Created by Fahad on 1/24/18.
 */

import android.os.Parcel;

import com.momentu.momentuandroid.Model.City;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * City Class Junit Test for Parcelable
 */
public class CityParcelTest {

    //An object from City class to check its functionality
    City cityTest;

    //Testing the Parcelable feature of City class
    @Test
    public void TestCityWithParcel() throws Exception {
        try {
            assertNull(cityTest);
            String cityName = "Chicago";
            cityTest = new City("Chicago");

            // Write the data
            Parcel parcel = Parcel.obtain();

            cityTest.writeToParcel(parcel, cityTest.describeContents());

            // After you're done with writing, you need to reset the parcel for reading.
            parcel.setDataPosition(0);

            // Read the data
            City createdFromParcel = cityTest.CREATOR.createFromParcel(parcel);
            String createdFromParcelCityName = createdFromParcel.getCityName();

            // Verify that the received data is correct.
            assertEquals("Chicago",createdFromParcelCityName);


        }catch (Exception e){
            fail("Creating a City With Parcel object with parameters fails.");
        }
    }
}
