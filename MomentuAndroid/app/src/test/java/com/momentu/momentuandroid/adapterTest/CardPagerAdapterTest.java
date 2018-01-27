package com.momentu.momentuandroid.adapterTest;

/**
 * Created by Fahad on 1/26/18.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.test.mock.MockContext;

import com.momentu.momentuandroid.Adapter.CardPagerAdapter;
import com.momentu.momentuandroid.Model.TrendHashTagCard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * CardPagerAdapter Class Junit Test
 */
public class CardPagerAdapterTest {


    //An object from CardPagerAdapter class to check its functionality
    CardPagerAdapter cardPagerAdapterTest;


    //Testing the constructor without parameters of CardPagerAdapter class
    @Test
    public void TestCardPagerAdapter() throws Exception {
        try {
            assertNull(cardPagerAdapterTest);
            cardPagerAdapterTest = new CardPagerAdapter();
            assertNotNull(cardPagerAdapterTest);
        }catch (Exception e){
            fail("the constructor without parameters of CardPagerAdapter class doesn't work properly.");
        }
    }

    //Testing the addCardItem method of CardPagerAdapter class
    @Test
    public void TestAddCardItem() throws Exception {
        try {
            assertNull(cardPagerAdapterTest);
            cardPagerAdapterTest = new CardPagerAdapter();
            assertEquals(0,cardPagerAdapterTest.getCount());
            String[] inTrendHashTags = new String[6];
            cardPagerAdapterTest.addCardItem(new TrendHashTagCard(inTrendHashTags));
            assertEquals(1,cardPagerAdapterTest.getCount());
        }catch (Exception e){
            fail("the addCardItem method of CardPagerAdapter class doesn't work properly.");
        }
    }

    //Testing the getBaseElevation method of CardPagerAdapter class
    @Test
    public void TestGetBaseElevation() throws Exception {
        try {
            assertNull(cardPagerAdapterTest);
            cardPagerAdapterTest = new CardPagerAdapter();
            assertEquals(0,cardPagerAdapterTest.getBaseElevation(), 0.000);
        }catch (Exception e){
            fail("the getBaseElevation method of CardPagerAdapter class doesn't work properly.");
        }
    }

    //Testing the getCount method of CardPagerAdapter class
    @Test
    public void TestGetCount() throws Exception {
        try {
            assertNull(cardPagerAdapterTest);
            cardPagerAdapterTest = new CardPagerAdapter();
            assertEquals(0,cardPagerAdapterTest.getCount());
            String[] inTrendHashTags = new String[6];
            cardPagerAdapterTest.addCardItem(new TrendHashTagCard(inTrendHashTags));
            assertEquals(1,cardPagerAdapterTest.getCount());
        }catch (Exception e){
            fail("the getCount method of CardPagerAdapter class doesn't work properly.");
        }
    }



}
