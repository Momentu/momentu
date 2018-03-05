package com.momentu.momentuandroid.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.momentu.momentuandroid.Data.RestClient;
import com.momentu.momentuandroid.FeedActivity;
import com.momentu.momentuandroid.Model.MediaUrlStorage;
import com.momentu.momentuandroid.Utility.JSONParser;
import com.momentu.momentuandroid.Utility.RequestPackage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ialawwad on 1/29/18.
 */

public class GetMediaService extends IntentService {
    public static final String REQUEST_PACKAGE_IMAGE = "requestPackage";
    public static final String MY_IMAGE_SERVICE_MESSAGE = "myImageServiceMessage";
    public static final String MY_IMAGE_SERVICE_PAYLOAD = "myImageServicePayload";
    private static final String TAG = "GetMediaService";


    public GetMediaService(){super("GetMediaService");}


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
