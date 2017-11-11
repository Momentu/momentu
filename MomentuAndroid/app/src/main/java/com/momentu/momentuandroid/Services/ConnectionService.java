package com.momentu.momentuandroid.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.momentu.momentuandroid.Data.RestClient;
import com.momentu.momentuandroid.Utility.JSONParser;
import com.momentu.momentuandroid.Utility.RequestPackage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by ibm_a on 11/10/2017.
 */

public class ConnectionService extends IntentService {

    public static final String MY_SERVICE_MESSAGE = "myServiceMessage";
    public static final String REQUEST_PACKAGE = "requestPackage";
    public static final String MY_SERVICE_PAYLOAD = "myServicePayload";
    private static final String TAG = "ConnectionService";


    public ConnectionService(){super("ConnectionService");}
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        RequestPackage requestPackage =
                intent.getParcelableExtra(REQUEST_PACKAGE);

        String response;
        try {
            response = RestClient.Hashtages(requestPackage);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Log.d("IBMServiceBeforeParser", response);
        ArrayList<String> hashArray = JSONParser.parseJASON(response);
        if(hashArray != null)
        Log.d("IBMServiceAfterParser", hashArray.toString());
        else
            Log.d("IBMServiceAfterParser", "hashArray is null");

//
//        Gson gson = new Gson();
//        Hashtag hashtages = gson.fromJson(response, Hashtag.class);

        Intent messageIntent = new Intent(MY_SERVICE_MESSAGE);
        messageIntent.putExtra(MY_SERVICE_PAYLOAD, hashArray);
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

