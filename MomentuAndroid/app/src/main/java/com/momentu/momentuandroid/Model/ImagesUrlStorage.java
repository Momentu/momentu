package com.momentu.momentuandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ialawwad on 1/29/18.
 */

public class ImagesUrlStorage implements Parcelable {
    private String imageUrl;

    public ImagesUrlStorage(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.imageUrl);
    }

    protected ImagesUrlStorage(Parcel in) {
        this.imageUrl = in.readString();
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
        return this.imageUrl;
    }
}
