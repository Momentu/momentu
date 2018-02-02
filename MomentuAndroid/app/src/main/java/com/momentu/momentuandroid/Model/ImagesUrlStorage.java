package com.momentu.momentuandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ialawwad on 1/29/18.
 */

public class ImagesUrlStorage implements Parcelable {
    private String originalUrl;
    private String thumbnilUrl;

    public ImagesUrlStorage(String originalUrl, String thumbnilUrl){
        this.originalUrl = originalUrl;
        this.thumbnilUrl = thumbnilUrl;
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

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.originalUrl);
        parcel.writeString(this.thumbnilUrl);
    }

    protected ImagesUrlStorage(Parcel in) {
        this.originalUrl = in.readString();
        this.thumbnilUrl = in.readString();
    }

    public static final Creator<ImagesUrlStorage> CREATOR = new Creator<ImagesUrlStorage>() {
        @Override
        public ImagesUrlStorage createFromParcel(Parcel in) {
            return new ImagesUrlStorage(in);
        }

        @Override
        public ImagesUrlStorage[] newArray(int size) {
            return new ImagesUrlStorage[size];
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
