package com.momentu.momentuandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ialawwad on 1/29/18.
 */

public class MediaUrlStorage implements Parcelable {
    private String originalUrl;
    private String thumbnilUrl;
    private String media_type;

    public MediaUrlStorage(String originalUrl, String thumbnilUrl, String media_type){
        this.originalUrl = originalUrl;
        this.thumbnilUrl = thumbnilUrl;
        this.media_type = media_type;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String imageUrl) {
        this.originalUrl = imageUrl;
    }

    public String getThumbnilUrl() {
        return thumbnilUrl;
    }

    public void setThumbnilUrl(String thumbnilUrl) {
        this.thumbnilUrl = thumbnilUrl;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.originalUrl);
        parcel.writeString(this.thumbnilUrl);
    }

    protected MediaUrlStorage(Parcel in) {
        this.originalUrl = in.readString();
        this.thumbnilUrl = in.readString();
    }

    public static final Creator<MediaUrlStorage> CREATOR = new Creator<MediaUrlStorage>() {
        @Override
        public MediaUrlStorage createFromParcel(Parcel in) {
            return new MediaUrlStorage(in);
        }

        @Override
        public MediaUrlStorage[] newArray(int size) {
            return new MediaUrlStorage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public String toString(){
        return this.originalUrl;
    }
}
