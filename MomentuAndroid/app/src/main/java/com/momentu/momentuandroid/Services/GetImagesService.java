package com.momentu.momentuandroid.Services;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.momentu.momentuandroid.Data.RestClient;
import com.momentu.momentuandroid.FeedActivity;
import com.momentu.momentuandroid.Model.FeedItem;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.ImagesUrlStorage;
import com.momentu.momentuandroid.Model.Like;
import com.momentu.momentuandroid.Utility.JSONParser;
import com.momentu.momentuandroid.Utility.RequestPackage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ialawwad on 1/29/18.
 */

public class GetImagesService extends IntentService {
    public static final String REQUEST_PACKAGE_IMAGE = "requestPackage";
    public static final String MY_IMAGE_SERVICE_MESSAGE = "myImageServiceMessage";
    public static final String MY_IMAGE_SERVICE_PAYLOAD = "myImageServicePayload";
    private static final String TAG = "GetImagesService";


    public GetImagesService(){super("GetImagesService");}


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        RequestPackage requestPackage =
                intent.getParcelableExtra(REQUEST_PACKAGE_IMAGE);

        String response;
        try {
            response = RestClient.retrieve_media(requestPackage);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Log.d(TAG+"Be4Pars", response);
        ArrayList<Parcelable> objectArray = JSONParser.parseJASON(response, 3);
        if(objectArray != null)
            Log.d(TAG + "ftrPars", objectArray.toString());
        else
            Log.d(TAG + "ftrPars", "objectArray is null and code= " + 3);

        Intent messageIntent;

        messageIntent = new Intent(getApplicationContext(), FeedActivity.class);
        messageIntent.setAction(MY_IMAGE_SERVICE_MESSAGE);

        messageIntent.putExtra(MY_IMAGE_SERVICE_PAYLOAD, objectArray);

        //**************
        ArrayList<ImagesUrlStorage> objArray = new ArrayList<ImagesUrlStorage>();
        JSONObject obj = null;
        try {
            obj = new JSONObject(response);

        Log.d("JSON2ARRAYNothingyet", obj.toString());
        JSONObject newJson = obj.getJSONObject("_embedded");
        Log.d("JSON2ARRAYNewJASON", newJson.toString());

        JSONArray newArr = newJson.getJSONArray("mediaMetas");
        Log.d("JSON2ARRAYCities", newArr.toString());
        ImagesUrlStorage imageUrls;

        for (int i = 0; i < newArr.length(); i++) {
            imageUrls = new ImagesUrlStorage(newArr.getJSONObject(i).getString("imageLocation"),newArr.getJSONObject(i).getString("thumbnailLocation"));
            objArray.add(imageUrls);
        }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //**************

            Log.d("ConverURLToImage", "Just got in with a string");
        LocalBroadcastManager manager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}
