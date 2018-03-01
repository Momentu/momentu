package com.momentu.momentuandroid.Model;

/**
 * Created by Fahad on 2/25/18.
 */

public class PostsYouHaveLikedItem {

    private long mediaId;
    private String hashtagLabel;
    private String originalUrl;
    private String thumbnailUrl;
    private String mediaType;
    private int likesCount;
    private String city;
    private String state;

    public PostsYouHaveLikedItem(long mediaId, String hashtagLabel, String originalUrl, String thumbnailUrl, String mediaType, int likesCount, String city, String state) {
        this.mediaId = mediaId;
        this.hashtagLabel = hashtagLabel;
        this.originalUrl = originalUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.mediaType = mediaType;
        this.likesCount = likesCount;
        this.city = city;
        this.state = state;
    }

    public long getMediaId() {
        return mediaId;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getHashtagLabel() {
        return hashtagLabel;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }


}
