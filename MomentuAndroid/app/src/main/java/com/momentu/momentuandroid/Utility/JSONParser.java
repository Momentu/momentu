package com.momentu.momentuandroid.Utility;

import android.os.Parcelable;
import android.util.Log;

import com.momentu.momentuandroid.Model.City;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.MediaUrlStorage;
import com.momentu.momentuandroid.Model.MyPostsItem;
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
                JSONObject newJson = obj.getJSONObject("_embedded");
                JSONArray newArr = newJson.getJSONArray("hashtags");//.getJSONArray("locations");
                Hashtag hashtag;
                for (int i = 0; i < newArr.length(); i++) {
                    hashtag = new Hashtag(newArr.getJSONObject(i).getString("label"), newArr.getJSONObject(i).getInt("count"));
                    objArray.add(hashtag);
                }
            }
            else if(code == 1){
                JSONObject obj = new JSONObject(jason);
                JSONObject newJson = obj.getJSONObject("_embedded");

                JSONArray newArr = newJson.getJSONArray("locations");
                StatesAndCities locations = new StatesAndCities();
                for (int i = 0; i < newArr.length(); i++) {
                    String state = newArr.getJSONObject(i).getString("state");
                    String city = newArr.getJSONObject(i).getString("city");
                    if (locations.getStates().contains(new State(state))) {
                        int indx = locations.getStates().indexOf(new State(state));
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
                JSONObject newJson = obj.getJSONObject("_embedded");

                JSONArray newArr = newJson.getJSONArray("locations");
                City city;
                for (int i = 0; i < newArr.length(); i++) {
                    city = new City(newArr.getJSONObject(i).getString("city"));
                    objArray.add(city);
                }
            }
            else if(code == 3){
                JSONObject obj = new JSONObject(jason);
                JSONObject newJson = obj.getJSONObject("_embedded");

                JSONArray newArr = newJson.getJSONArray("mediaMetas");
                MediaUrlStorage imageUrls;
                for (int i = 0; i < newArr.length(); i++) {
                    JSONObject newObj = newArr.getJSONObject(i);
                    imageUrls = new MediaUrlStorage(newObj.getString("imageLocation"),newObj.getString("thumbnailLocation")
                            ,newObj.getString("mediaType"),newObj.getInt("likeCount"), newObj.getLong("id"), newObj.getBoolean("liked"));
                    objArray.add(imageUrls);
                }
            }
            else if(code == 4){
                JSONObject obj = new JSONObject(jason);
                JSONObject newJson = obj.getJSONObject("_embedded");

                JSONArray newArr = newJson.getJSONArray("mediaMetas");
                MyPostsItem myPostsItem;
                for (int i = 0; i < newArr.length(); i++) {
                    JSONObject newObj = newArr.getJSONObject(i);
                    myPostsItem = new MyPostsItem(newObj.getLong("id"), newObj.getString("hashtagLabel"), newObj.getString("imageLocation"),
                            newObj.getString("thumbnailLocation"),newObj.getString("mediaType"),newObj.getInt("likeCount"), newObj.getString("city"),
                            newObj.getString("state"));
                    objArray.add(myPostsItem);
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
