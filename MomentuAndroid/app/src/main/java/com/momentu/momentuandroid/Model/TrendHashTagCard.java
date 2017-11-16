package com.momentu.momentuandroid.Model;

/**
 * Created by Jane on 11/2/2017.
 */

public class TrendHashTagCard {
    private String[] trendHashTags;
    public TrendHashTagCard(String[] inTrendHashTags) {
        trendHashTags = inTrendHashTags;
    }
    public int HashTagCount() {return trendHashTags.length;}
    public String[] getTrendHashTags() {
        return trendHashTags;
    }
}
