package com.momentu.momentuandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ibm_a on 11/13/2017.
 */

public class StatesAndCities implements Parcelable {

    private ArrayList<State> stateArray;

    public StatesAndCities(){
        stateArray = new ArrayList<State>();
    };

    public StatesAndCities(ArrayList<State> stateArray){
        this.stateArray = stateArray;
    }

    public void setState(ArrayList<State> stateArray){
        this.stateArray = stateArray;
    }

    public ArrayList<State> getStates(){
        return this.stateArray;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.stateArray);
    }

    protected StatesAndCities(Parcel in) {
        this.stateArray = (ArrayList<State>)in.readSerializable();
    }

    public static final Creator<StatesAndCities> CREATOR = new Creator<StatesAndCities>() {
        @Override
        public StatesAndCities createFromParcel(Parcel in) {
            return new StatesAndCities(in);
        }

        @Override
        public StatesAndCities[] newArray(int size) {
            return new StatesAndCities[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString(){
        return this.stateArray.toString();
    }
}

