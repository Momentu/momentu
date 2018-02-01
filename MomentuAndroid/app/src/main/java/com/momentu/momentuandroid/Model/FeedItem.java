package com.momentu.momentuandroid.Model;

import android.graphics.drawable.Drawable;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by Jane on 11/13/2017.
 */

public class FeedItem {
    private BigInteger id;
    private BigInteger userID;
    private Hashtag hashTag;
    private String description;
    private String media;
    private BigInteger locationID;
    private Date createdTime;
    private Boolean removed;
    private Like likeFeed;

    public FeedItem(BigInteger id, BigInteger userID, Hashtag hashTag, String media, String description, BigInteger locationID, Date createdTime, Boolean removed, Like likeFeed) {
        this.id = id;
        this.userID = userID;
        this.hashTag = hashTag;
        this.media = media;
        this.description = description;
        this.locationID = locationID;
        this.createdTime = createdTime;
        this.removed = removed;
        this.likeFeed = likeFeed;
    }

    public Hashtag getHashTag(){
        return hashTag;
    }

    public String getDescription(){
        return description;
    }

    public String getMedia(){
        return media;
    }

    public Like getLike(){
        return likeFeed;
    }

}
