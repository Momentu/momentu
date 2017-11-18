package com.momentu.momentuandroid.Model;

/**
 * Created by Jane on 10/19/2017.
 */


import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

public class HashtagSuggestion implements SearchSuggestion {

    private String mHashTag;
    private boolean mIsHistory = false;

    public HashtagSuggestion(String suggestion) {
        this.mHashTag = suggestion;
    }

    public HashtagSuggestion(Parcel source) {
        this.mHashTag = source.readString();
        this.mIsHistory = source.readInt() != 0;
    }

    public void setIsHistory(boolean isHistory) {
        this.mIsHistory = isHistory;
    }

    public boolean getIsHistory() {
        return this.mIsHistory;
    }

    @Override
    public String getBody() {
        return mHashTag;
    }

    public static final Creator<HashtagSuggestion> CREATOR = new Creator<HashtagSuggestion>() {
        @Override
        public HashtagSuggestion createFromParcel(Parcel in) {
            return new HashtagSuggestion(in);
        }

        @Override
        public HashtagSuggestion[] newArray(int size) {
            return new HashtagSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mHashTag);
        dest.writeInt(mIsHistory ? 1 : 0);
    }
}