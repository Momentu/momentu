package com.momentu.momentuandroid.modelTest;

import com.momentu.momentuandroid.Model.TrendHashTagCard;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Fahad on 11/14/17.
 */

/**
 * TrednHashTagCard Class Junit Test
 */
public class TrednHashTagCardTest {

    //An object from TrednHashTagCard class to check its functionality
    TrendHashTagCard TrednHashTagCardTest = null;

    //Testing the constructor with parameters of TrednHashTagCard class
    @Test
    public void TestTrednHashTagCard() throws Exception {
        try {
            String[] trendHashTags = new String[6];
            TrednHashTagCardTest = new TrendHashTagCard(trendHashTags);
        }catch (Exception e){
            fail("Creating a TrednHashTagCardTest object with parameters fails.");
        }
    }

    //Testing the get TrednHashTagCard method in TrednHashTagCard class
    @Test
    public void TestGetTrednHashTagCard() throws Exception {
        try {
            String[] trendHashTags = new String[6];
            TrednHashTagCardTest = new TrendHashTagCard(trendHashTags);
            assertEquals(trendHashTags,TrednHashTagCardTest.getTrendHashTags());
        }catch (Exception e){
            fail("get TrednHashTagCard method doesn't work properly.");
        }
    }

    //Testing the HashTagCount method in TrednHashTagCard class
    @Test
    public void TestHashTagCount() throws Exception {
        try {
            String[] trendHashTags = new String[6];
            TrednHashTagCardTest = new TrendHashTagCard(trendHashTags);
            assertEquals(6,TrednHashTagCardTest.HashTagCount());
        }catch (Exception e){
            fail("HashTagCount method doesn't work properly.");
        }
    }
}
