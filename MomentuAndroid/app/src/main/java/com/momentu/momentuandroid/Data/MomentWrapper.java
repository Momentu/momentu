package com.momentu.momentuandroid.Data;

/**
 * Created by Jane on 10/19/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MomentWrapper implements Parcelable {

    @SerializedName("hashtag")
    @Expose
    private String hashtag;
    @SerializedName("content")
    @Expose
    private String content;

    private MomentWrapper(Parcel in) {
        hashtag = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hashtag);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static final Creator<MomentWrapper> CREATOR = new Creator<MomentWrapper>() {
        @Override
        public MomentWrapper createFromParcel(Parcel in) {
            return new MomentWrapper(in);
        }

        @Override
        public MomentWrapper[] newArray(int size) {
            return new MomentWrapper[size];
        }
    };
}