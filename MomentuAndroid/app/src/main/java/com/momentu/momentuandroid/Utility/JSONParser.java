package com.momentu.momentuandroid.Utility;

import android.util.Log;

import com.momentu.momentuandroid.Model.Hashtag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ibm_a on 11/9/2017.
 */

public class JSONParser {
    public static ArrayList<Hashtag> parseJASON(String jason) {
        Log.d("JSON2ARRAY","Just got in 1");
        Hashtag hashtag;
        ArrayList<Hashtag> hashArray = new ArrayList<Hashtag>();
        try {
            Log.d("JSON2ARRAY","Just got in 2");

            JSONObject obj = new JSONObject(jason);
            Log.d("JSON2ARRAYNothingyet",obj.toString());
            JSONObject newJson = obj.getJSONObject("_embedded");
            Log.d("JSON2ARRAYNewJASON", newJson.toString());

            JSONArray newArr = newJson.getJSONArray("hashtags");//.getJSONArray("locations");
            Log.d("JSON2ARRAY4NewARR", newArr.toString());
            for (int i = 0; i < newArr.length(); i++) {
                hashtag = new Hashtag(newArr.getJSONObject(i).getString("label"),newArr.getJSONObject(i).getInt("count"));
                hashArray.add(hashtag);
            }
        } catch (JSONException e) {
            return null;
        }
        return hashArray;
    }

}
