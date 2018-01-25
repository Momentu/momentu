package com.momentu.momentuandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ibm_a on 11/14/2017.
 */

public class City implements Parcelable {

    private String cityName;

    public City(String stateName){
        this.cityName = stateName;
    }
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cityName);
        parcel.writeString(this.cityName);
    }

    protected City(Parcel in) {
        this.cityName = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel in) {
            return new City(in);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public String toString(){
        return this.cityName;
    }
}

