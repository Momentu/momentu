package com.momentu.momentuandroid.Parcelable;

/**
 * Created by Fahad on 1/24/18.
 */

import android.os.Parcel;
import com.momentu.momentuandroid.Model.HashtagSuggestion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * HashtagSuggestion Class Junit Test for Parcelable
 */
public class HashtagSuggestionParcelTest {

    //An object from HashtagSuggestion class to check its functionality
    HashtagSuggestion hashtagSuggestionTest = null;

    //Testing the Parcelable feature of of Hashtag class
    @Test
    public void TestHashtagSuggestionWithParcel() throws Exception {
        try {
            assertNull(hashtagSuggestionTest);
            hashtagSuggestionTest = new HashtagSuggestion("#Chicago");
            hashtagSuggestionTest.setIsHistory(true);

            // Write the data
            Parcel parcel = Parcel.obtain();

            hashtagSuggestionTest.writeToParcel(parcel, hashtagSuggestionTest.describeContents());

            // After you're done with writing, you need to reset the parcel for reading.
            parcel.setDataPosition(0);

            // Read the data
            HashtagSuggestion createdFromParcel = hashtagSuggestionTest.CREATOR.createFromParcel(parcel);
            String createdFromParcelMHashTag = createdFromParcel.getBody();
            Boolean createdFromParcelMIsHistory = createdFromParcel.getIsHistory();

            // Verify that the received data is correct.
            assertEquals("#Chicago",createdFromParcelMHashTag);
            assertEquals(true,createdFromParcelMIsHistory);


        }catch (Exception e){
            fail("Creating a HashtagSuggestion With Parcel object with parameters fails.");
        }
    }

}
