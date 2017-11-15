package com.momentu.momentuandroid.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Parcelable;
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
    public static final String MY_SERVICE_MESSAGE_STATE = "myServiceMessage_State";
    public static final String REQUEST_PACKAGE = "requestPackage";
    public static final String MY_SERVICE_PAYLOAD = "myServicePayload";
    private static final String TAG = "ConnectionService";


    public ConnectionService(){super("ConnectionService");}
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        RequestPackage requestPackage =
                intent.getParcelableExtra(REQUEST_PACKAGE);
        int code = intent.getIntExtra("code", -1);

        String response;
        try {
            response = RestClient.get(requestPackage);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Log.d("IBMServiceBeforeParser", response);
        ArrayList<Parcelable> objectArray = JSONParser.parseJASON(response, code);
        if(objectArray != null)
            Log.d("IBMServiceAfterParser", objectArray.toString());
        else
            Log.d("IBMServiceAfterParser", "objectArray is null and code= " + code);

        Intent messageIntent;

        messageIntent = new Intent(MY_SERVICE_MESSAGE);

        messageIntent.putExtra(MY_SERVICE_PAYLOAD, objectArray);
        messageIntent.putExtra("code",code);

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