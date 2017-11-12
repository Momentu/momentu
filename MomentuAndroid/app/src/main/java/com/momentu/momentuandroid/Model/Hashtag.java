package com.momentu.momentuandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ibm_a on 11/10/2017.
 */

public class Hashtag implements Parcelable {

    private String label;
    private int count;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int countc) {
        this.count = count;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.label);
        dest.writeInt(this.count);
    }

    public Hashtag(String label, int count) {
        this.label = label;
        this.count = count;
    }

    protected Hashtag(Parcel in) {
        this.label = in.readString();
        this.count = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Hashtag> CREATOR = new Parcelable.Creator<Hashtag>() {
        @Override
        public Hashtag createFromParcel(Parcel source) {
            return new Hashtag(source);
        }

        @Override
        public Hashtag[] newArray(int size) {
            return new Hashtag[size];
        }
    };
}
