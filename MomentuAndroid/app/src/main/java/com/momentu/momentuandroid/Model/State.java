package com.momentu.momentuandroid.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by ibm_a on 11/13/2017.
 */

public class State implements Parcelable {

    String stateName;
    ArrayList<String>  cities;

    public State(String stateName){
        this.stateName = stateName;
        cities = new ArrayList<String>();
    }

    public State(String stateName, String city){
        this.stateName = stateName;
        this.cities = new ArrayList<String>();
        this.cities.add(city);
    }
    @Override
    public boolean equals(Object object)
    {
        boolean sameSame = false;
        String compare = (String) object;
            boolean result = this.stateName.equals(compare);
        return result;
    }

    @Override
    public int hashCode() {
        return stateName.hashCode();
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void setCities(ArrayList<String> cities) {
        this.cities = cities;
    }

    public void addCity(String city){
        this.cities.add(city);
    }

    protected State(Parcel in) {
        this.stateName = in.readString();
        this.cities = (ArrayList<String>) in.readSerializable();
    }

    public static final Creator<State> CREATOR = new Creator<State>() {
        @Override
        public State createFromParcel(Parcel in) {
            return new State(in);
        }

        @Override
        public State[] newArray(int size) {
            return new State[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(stateName);
        parcel.writeSerializable(cities);
    }
    @Override
    public String toString(){
        return this.stateName;
    }
}

