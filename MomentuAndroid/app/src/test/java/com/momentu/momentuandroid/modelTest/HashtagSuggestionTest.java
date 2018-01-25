package com.momentu.momentuandroid.modelTest;

/**
 * Created by Fahad on 1/23/18.
 */

import com.momentu.momentuandroid.Model.HashtagSuggestion;
import com.momentu.momentuandroid.Model.State;
import com.momentu.momentuandroid.Model.StatesAndCities;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * HashtagSuggestion Class Junit Test
 */
public class HashtagSuggestionTest {


    //An object from HashtagSuggestion class to check its functionality
    HashtagSuggestion hashtagSuggestionTest;


    //Testing the constructor without parameters of HashtagSuggestion class
    @Test
    public void TestHashtagSuggestion() throws Exception {
        try {
            assertNull(hashtagSuggestionTest);
            hashtagSuggestionTest = new HashtagSuggestion("#Chicago");
            assertNotNull(hashtagSuggestionTest);
        }catch (Exception e){
            fail("the constructor without parameters of HashtagSuggestion class doesn't work properly.");
        }
    }

    //Testing setIsHistory method in HashtagSuggestion class
    @Test
    public void TestSetIsHistory() throws Exception {
        try {
            hashtagSuggestionTest = new HashtagSuggestion("#Chicago");
            hashtagSuggestionTest.setIsHistory(true);
            assertTrue(hashtagSuggestionTest.getIsHistory());
        }catch (Exception e){
            fail("the setIsHistory method of HashtagSuggestion class doesn't work properly.");
        }
    }

    //Testing getIsHistory method in HashtagSuggestion class
    @Test
    public void TestGetIsHistory() throws Exception {
        try {
            hashtagSuggestionTest = new HashtagSuggestion("#Chicago");
            hashtagSuggestionTest.setIsHistory(true);
            assertTrue(hashtagSuggestionTest.getIsHistory());
        }catch (Exception e){
            fail("the getIsHistory method of HashtagSuggestion class doesn't work properly.");
        }
    }


    //Testing describeContents method in HashtagSuggestion class
    @Test
    public void TestDescribeContents() throws Exception {
        try {
            hashtagSuggestionTest = new HashtagSuggestion("#Chicago");
            assertEquals(0,hashtagSuggestionTest.describeContents());
        }catch (Exception e){
            fail("the describeContents method of HashtagSuggestion class doesn't work properly.");
        }
    }

    //Testing getBody method in HashtagSuggestion class
    @Test
    public void TestGetBody() throws Exception {
        try {
            hashtagSuggestionTest = new HashtagSuggestion("#Chicago");
            assertEquals("#Chicago",hashtagSuggestionTest.getBody());
        }catch (Exception e){
            fail("the getBody method of HashtagSuggestion class doesn't work properly.");
        }
    }



}
