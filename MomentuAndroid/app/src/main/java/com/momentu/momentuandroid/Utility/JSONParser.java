package com.momentu.momentuandroid.Utility;

import android.os.Parcelable;
import android.util.Log;

import com.momentu.momentuandroid.Model.City;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.State;
import com.momentu.momentuandroid.Model.StatesAndCities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ibm_a on 11/9/2017.
 */

public class JSONParser {
    public static ArrayList<Parcelable> parseJASON(String jason, int code) {
        Log.d("JSON2ARRAY","Just got in 1");
        ArrayList<Parcelable> objArray = new ArrayList<Parcelable>();
        try {
            if(code == 0) {
                JSONObject obj = new JSONObject(jason);
                Log.d("JSON2ARRAYNothingyet", obj.toString());
                JSONObject newJson = obj.getJSONObject("_embedded");
                Log.d("JSON2ARRAYNewJASON", newJson.toString());

                JSONArray newArr = newJson.getJSONArray("hashtags");//.getJSONArray("locations");
                Log.d("JSON2ARRAY4NewARR", newArr.toString());
                Hashtag hashtag;
                for (int i = 0; i < newArr.length(); i++) {
                    hashtag = new Hashtag(newArr.getJSONObject(i).getString("label"), newArr.getJSONObject(i).getInt("count"));
                    objArray.add(hashtag);
                }
            }
            else if(code == 1){
                Log.d("JSON2ARRAY","Just got in 2");
                JSONObject obj = new JSONObject(jason);
                JSONObject newJson = obj.getJSONObject("_embedded");
                Log.d("JSON2ARRAYNewJASON", newJson.toString());

                JSONArray newArr = newJson.getJSONArray("locations");
                Log.d("JSON2ARRAY4NewARR", newArr.toString());
                StatesAndCities locations = new StatesAndCities();
                for (int i = 0; i < newArr.length(); i++) {
                    String state = newArr.getJSONObject(i).getString("state");
                    Log.d("ParserStep:", i + " state:" + state);
                    String city = newArr.getJSONObject(i).getString("city");
                    Log.d("ParserStep:", i + " city:" + city);
                    if (locations.getStates().contains(state) == true   ) {
                        Log.d("ParserStep:", i + " Found:" + state);
                        int indx = locations.getStates().indexOf(state);
                        if(!locations.getStates().get(indx).getCities().contains(city)){
                            locations.getStates().get(indx).addCity(city);}
                    } else {
                        locations.getStates().add(new State(state, city));
                    }
                }
                objArray.add(locations);
            }
            else if(code == 2){
                JSONObject obj = new JSONObject(jason);
                Log.d("JSON2ARRAYNothingyet", obj.toString());
                JSONObject newJson = obj.getJSONObject("_embedded");
                Log.d("JSON2ARRAYNewJASON", newJson.toString());

                JSONArray newArr = newJson.getJSONArray("locations");
                Log.d("JSON2ARRAYCities", newArr.toString());
                City city;
                for (int i = 0; i < newArr.length(); i++) {
                    city = new City(newArr.getJSONObject(i).getString("city"));
                    objArray.add(city);
                }
            }
            else
                Log.d("JSONParserOTHER", "THE CODE IS NOT RECOGNIZED");


        }
        catch (JSONException e) {
            return null;
        }
        return objArray;
    }
}
