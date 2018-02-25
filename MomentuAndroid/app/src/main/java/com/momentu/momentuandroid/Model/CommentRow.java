package com.momentu.momentuandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fahad on 2/20/18.
 */

public class CommentRow implements Parcelable{

    private String username;
    private String comment;


    public CommentRow(String username, String comment) {
        this.username = username;
        this.comment = comment;
    }


    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.username);
        parcel.writeString(this.comment);
    }

    protected CommentRow(Parcel in) {
        this.comment = in.readString();
        this.username = in.readString();
    }

    public static final Parcelable.Creator<CommentRow> CREATOR = new Parcelable.Creator<CommentRow>() {
        @Override
        public CommentRow createFromParcel(Parcel in) {
            return new CommentRow(in);
        }

        @Override
        public CommentRow[] newArray(int size) {
            return new CommentRow[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
