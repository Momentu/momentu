package com.momentu.momentuandroid;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Fahad on 1/26/18.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class HashTagSearchActivityTest {

    @Rule
    public ActivityTestRule<HashTagSearchActivity> mActivityTestRule = new ActivityTestRule<>(HashTagSearchActivity.class);

    //This is an UI Test that these the existence of all the activity widgets
    @Test
    public void hashTagSearchActivityUITest(){

        Activity activity = mActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.LocationHeader));
        assertNotNull(activity.findViewById(R.id.where_am_i));
        assertNotNull(activity.findViewById(R.id.bChangeLocation));
        assertNotNull(activity.findViewById(R.id.viewPager));
        assertNotNull(activity.findViewById(R.id.fragment_container));
        assertNotNull(activity.findViewById(R.id.viewEmpty));
        assertNotNull(activity.findViewById(R.id.bCamera));
        assertNotNull(activity.findViewById(R.id.nav_view));

    }
}
