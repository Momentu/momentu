package com.momentu.momentuandroid.Utility;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by ibm_a on 11/9/2017.
 */

public class JSONParser {
    public static ArrayList<String> parseJASON(String jason) {
        Log.d("JSON2ARRAY","Just got in 1");
        ArrayList<String> hashArray = new ArrayList<String>();
        try {
            Log.d("JSON2ARRAY","Just got in 2");

            JSONObject obj = new JSONObject(jason);
            Log.d("JSON2ARRAYNothingyet",obj.toString());
//            String pageName = obj.getJSONObject("pageInfo").getString("pageName");
            JSONObject newJson = obj.getJSONObject("_embedded");
            Log.d("JSON2ARRAYNewJASON", newJson.toString());

            JSONArray newArr = newJson.getJSONArray("hashtags").getJSONObject(0).getJSONObject("location").getJSONArray("locations");
            Log.d("JSON2ARRAY4NewARR", newArr.toString());
            for (int i = 0; i < newArr.length(); i++) {
                hashArray.add(newArr.getJSONObject(i).getString("hashtagLabel"));
            }
        } catch (JSONException e) {
            return null;
        }
        return hashArray;
    }

}
