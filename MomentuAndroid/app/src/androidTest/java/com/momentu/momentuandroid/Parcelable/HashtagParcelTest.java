package com.momentu.momentuandroid.Parcelable;

/**
 * Created by Fahad on 1/24/18.
 */

import android.content.Context;
import android.os.Parcel;
import android.test.mock.MockContext;
import android.util.Log;

import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.View.FeedContextMenu;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Hashtag Class Junit Test for Parcelable
 */
public class HashtagParcelTest {

    //An object from Hashtag class to check its functionality
    Hashtag hashtag = null;

    //Testing the Parcelable feature of of Hashtag class
    @Test
    public void TestHashtagWithParcel() throws Exception {
        try {
            assertNull(hashtag);
            String label = "#event";
            int count = 10;
            hashtag = new Hashtag(label,count);

            // Write the data
            Parcel parcel = Parcel.obtain();

            hashtag.writeToParcel(parcel, hashtag.describeContents());

            // After you're done with writing, you need to reset the parcel for reading.
            parcel.setDataPosition(0);

            // Read the data
            Hashtag createdFromParcel = hashtag.CREATOR.createFromParcel(parcel);
            String createdFromParcelHashtagLabel = createdFromParcel.getLabel();
            int createdFromParcelHashtagCount = createdFromParcel.getCount();

            // Verify that the received data is correct.
            assertEquals("#event",createdFromParcelHashtagLabel);
            assertEquals(10,createdFromParcelHashtagCount);


        }catch (Exception e){
            fail("Creating a hashtag With Parcel object with parameters fails.");
        }
    }

}
