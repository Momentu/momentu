package com.momentu.momentuandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fahad on 2/25/18.
 */

public class MyPostsItem implements Parcelable {
    private long mediaId;
    private String hashtagLabel;
    private String originalUrl;
    private String thumbnailUrl;
    private String mediaType;
    private int likesCount;
    private String city;
    private String state;

    public MyPostsItem(long mediaId, String hashtagLabel, String originalUrl, String thumbnailUrl, String mediaType, int likesCount, String city, String state) {
        this.mediaId = mediaId;
        this.hashtagLabel = hashtagLabel;
        this.originalUrl = originalUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.mediaType = mediaType;
        this.likesCount = likesCount;
        this.city = city;
        this.state = state;
    }

    protected MyPostsItem(Parcel in) {
        mediaId = in.readLong();
        hashtagLabel = in.readString();
        originalUrl = in.readString();
        thumbnailUrl = in.readString();
        mediaType = in.readString();
        likesCount = in.readInt();
        city = in.readString();
        state = in.readString();
    }

    public static final Creator<MyPostsItem> CREATOR = new Creator<MyPostsItem>() {
        @Override
        public MyPostsItem createFromParcel(Parcel in) {
            return new MyPostsItem(in);
        }

        @Override
        public MyPostsItem[] newArray(int size) {
            return new MyPostsItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mediaId);
        dest.writeString(hashtagLabel);
        dest.writeString(originalUrl);
        dest.writeString(thumbnailUrl);
        dest.writeString(mediaType);
        dest.writeInt(likesCount);
        dest.writeString(city);
        dest.writeString(state);
    }
}
