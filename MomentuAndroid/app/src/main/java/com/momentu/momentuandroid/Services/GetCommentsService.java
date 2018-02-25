package com.momentu.momentuandroid.Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.momentu.momentuandroid.CommentsActivity;
import com.momentu.momentuandroid.Data.RestClient;
import com.momentu.momentuandroid.FeedActivity;
import com.momentu.momentuandroid.Model.CommentRow;
import com.momentu.momentuandroid.Model.MediaUrlStorage;
import com.momentu.momentuandroid.Utility.JSONParser;
import com.momentu.momentuandroid.Utility.RequestPackage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ialawwad on 1/29/18.
 */

public class GetCommentsService extends IntentService {
    public static final String MY_COMMENTS_SERVICE_MESSAGE = "myCommentsServiceMessage";
    public static final String MY_COMMENTS_SERVICE_PAYLOAD = "myCommentsServicePayload";
    private static final String TAG = "GetCommentsService";


    public GetCommentsService(){super("GetCommentsService");}


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        long mediaId =
                intent.getLongExtra("mediaId", 0);
//        Serializable commentsActivityContext = intent.getSerializableExtra("commentActivityContext");

        String response;
        try {
            response = RestClient.retrieve_comments(mediaId, FeedActivity.token);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Log.d(TAG+"B4Prs", response);
//        ArrayList<Parcelable> objectArray = JSONParser.parseJASON(response, 3);
//        if(objectArray != null)
//            Log.d(TAG + "frPrs", objectArray.toString());
//        else
//            Log.d(TAG + "frPrs", "objectArray is null and code= " + 3);


        //**************
        ArrayList<CommentRow> objArray = new ArrayList<CommentRow>();
        JSONObject obj = null;
        try {
            obj = new JSONObject(response);

        Log.d("JSON2ARRAYNothingyet", obj.toString());
        JSONObject newJson = obj.getJSONObject("_embedded");
        Log.d("JSON2ARRAYNewJASON", newJson.toString());

        JSONArray newArr = newJson.getJSONArray("mediaComments");
        Log.d("JSON2ARRAYCities", newArr.toString());
        CommentRow aComment;

        for (int i = 0; i < newArr.length(); i++) {
            JSONObject newObj = newArr.getJSONObject(i);
            aComment = new CommentRow(newObj.getString("comment"),
                    newObj.getString("username"));
            objArray.add(aComment);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //**************

        Intent messageIntent;

        messageIntent = new Intent(getApplicationContext(), CommentsActivity.class);
        messageIntent.setAction(MY_COMMENTS_SERVICE_MESSAGE);

        messageIntent.putExtra(MY_COMMENTS_SERVICE_PAYLOAD, objArray);

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
