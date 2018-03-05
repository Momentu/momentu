package com.momentu.momentuandroid.Services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.momentu.momentuandroid.Data.RestClient;
import com.momentu.momentuandroid.FeedActivity;
import com.momentu.momentuandroid.Model.MyPostsItem;
import com.momentu.momentuandroid.PostsOfYouActivity;
import com.momentu.momentuandroid.PostsYouHaveLikedActivity;
import com.momentu.momentuandroid.Utility.JSONParser;
import com.momentu.momentuandroid.Utility.RequestPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by ialawwad on 1/29/18.
 */

public class GetPersonalMediaService extends IntentService {
    public static final String MY_IMAGE_SERVICE_MESSAGE = "myPersonalMediaServiceMessage";
    public static final String MY_IMAGE_SERVICE_PAYLOAD = "myPersonalMediaServicePayload";
    private static final String TAG = "GetMediaService";


    public GetPersonalMediaService(){super("GetMediaService");}


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        ArrayList<Parcelable> items = new ArrayList<>();
        int code = intent.getIntExtra("code",-1);

        String response = null;
        try {
            if (code == 4)
                response = new RestClient().findMediaCurrentUserPosted();
            else if (code == 5)
                response = new RestClient().findMediaCurrentUserLiked();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Parcelable> mediaList = JSONParser.parseJASON(response,4);

        for(Parcelable myPosts:mediaList)
            items.add(myPosts);

        Intent messageIntent;
        if (code == 4)
            messageIntent = new Intent(getApplicationContext(), PostsOfYouActivity.class);
        else
            messageIntent = new Intent(getApplicationContext(), PostsYouHaveLikedActivity.class);
        messageIntent.setAction(MY_IMAGE_SERVICE_MESSAGE);

        messageIntent.putExtra(MY_IMAGE_SERVICE_PAYLOAD, items);


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
