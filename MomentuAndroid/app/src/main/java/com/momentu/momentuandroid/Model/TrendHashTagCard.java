package com.momentu.momentuandroid.Model;

/**
 * Created by Jane on 11/2/2017.
 */

public class TrendHashTagCard {
    private String[] trendHashTags = new String[6];
    public TrendHashTagCard(String[] inTrendHashTags) {
        trendHashTags = inTrendHashTags;
    }
    public String[] getTrendHashTags() {
        return trendHashTags;
    }
}
