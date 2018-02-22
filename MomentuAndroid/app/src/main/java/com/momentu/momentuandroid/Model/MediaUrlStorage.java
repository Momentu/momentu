package com.momentu.momentuandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;

/**
 * Created by ialawwad on 1/29/18.
 */

public class MediaUrlStorage implements Parcelable {
    private String originalUrl;
    private String thumbnilUrl;
    private String media_type;
    private int numberOfLikes;
    private long id;
//    private  boolean isliked;

    public MediaUrlStorage(String originalUrl, String thumbnilUrl, String media_type, int numberOfLikes, long id){
        this.originalUrl = originalUrl;
        this.thumbnilUrl = thumbnilUrl;
        this.media_type = media_type;
        this.numberOfLikes = numberOfLikes;
        this.id = id;
//        this.isliked = isliked;
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

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }
//
//    public boolean getDidIlikeIt() {
//        return isliked;
//    }
//
//    public void setDidIlikeIt(boolean isliked) {
//        this.isliked = isliked;
//    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.originalUrl);
        parcel.writeString(this.thumbnilUrl);
        parcel.writeString(this.media_type);
        parcel.writeInt(this.numberOfLikes);
        parcel.writeLong(this.id);
       // parcel.writeByte((byte)(this.isliked? 1 : 0));
    }

    protected MediaUrlStorage(Parcel in) {
        this.originalUrl = in.readString();
        this.thumbnilUrl = in.readString();
        this.media_type = in.readString();
        this.numberOfLikes = in.readInt();
        this.id = in.readInt();
       // this.isliked = in.readByte() != 0;
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
        return "MediaType: " + media_type + ", Original Url: " + this.originalUrl;
    }
}
