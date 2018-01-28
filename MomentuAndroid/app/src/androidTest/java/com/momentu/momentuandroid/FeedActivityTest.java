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
public class FeedActivityTest {

    @Rule
    public ActivityTestRule<FeedActivity> mActivityTestRule = new ActivityTestRule<>(FeedActivity.class);

    //This is an UI Test that these the existence of all the activity widgets
    @Test
    public void feedActivityUITest(){

        Activity activity = mActivityTestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.rvFeed));
        assertNotNull(activity.findViewById(R.id.appBarLayout));
        assertNotNull(activity.findViewById(R.id.content));
        assertNotNull(activity.findViewById(R.id.toolbar));
        assertNotNull(activity.findViewById(R.id.bCameraInFeed));
    }
}
