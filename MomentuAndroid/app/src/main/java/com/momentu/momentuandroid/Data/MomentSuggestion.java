package com.momentu.momentuandroid.Data;

/**
 * Created by Jane on 10/19/2017.
 */


import android.os.Parcel;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

public class MomentSuggestion implements SearchSuggestion {

    private String mHashTag;
    private boolean mIsHistory = false;

    public MomentSuggestion(String suggestion) {
        this.mHashTag = suggestion;
    }

    public MomentSuggestion(Parcel source) {
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

    public static final Creator<MomentSuggestion> CREATOR = new Creator<MomentSuggestion>() {
        @Override
        public MomentSuggestion createFromParcel(Parcel in) {
            return new MomentSuggestion(in);
        }

        @Override
        public MomentSuggestion[] newArray(int size) {
            return new MomentSuggestion[size];
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