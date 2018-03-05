package com.momentu.momentuandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.momentu.momentuandroid.Adapter.MyLikesAdapter;
import com.momentu.momentuandroid.Model.MyPostsItem;
import com.momentu.momentuandroid.Model.PostsYouHaveLikedItem;
import com.momentu.momentuandroid.Services.GetPersonalMediaService;

import java.util.ArrayList;
import java.util.List;

public class PostsYouHaveLikedActivity extends AppCompatActivity {

    public String token;

    GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_you_have_liked);

//        token = getIntent().getStringExtra("token");

        gridview = (GridView) findViewById(R.id.gridviewMyLikes);

        Intent intent = new Intent(this, GetPersonalMediaService.class);
        intent.putExtra("code", 5);//this code is used to distinguish the way we parse the returned json file from the backend.
                                                // We use it in the JSONParser class. ALSO, for distingush which API should be called
        startService(intent);


        //setup reciever 'mBroadcastReceiver'
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(GetPersonalMediaService.MY_IMAGE_SERVICE_MESSAGE));




    }
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("IMAGE_Feed","jsut got in here reciever");

            ArrayList<MyPostsItem> items = intent
                    .getParcelableArrayListExtra(GetPersonalMediaService.MY_IMAGE_SERVICE_PAYLOAD);

            if (items == null) {
                Log.d("IMAGE_Feed","temp is null");
            }

            else {
                getAllItemObject(items);
//                Log.d("IMAGE_Feed", " just got this: " + temp.get(0));
            }
        }
    };


    private void getAllItemObject(ArrayList<MyPostsItem> items){

//        List<MyPostsItem> allItems = items;
        MyLikesAdapter myLikesAdapter = new MyLikesAdapter(PostsYouHaveLikedActivity.this, items);
        gridview.setAdapter(myLikesAdapter);


//        MyPostsItem myLikes = null;
//        List<PostsYouHaveLikedItem> items = new ArrayList<>();
//        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
//                "http://kb4images.com/images/image/37185176-image.jpg",
//                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
//                "Chicago","IL"));
//
//        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
//                "http://kb4images.com/images/image/37185176-image.jpg",
//                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
//                "Chicago","IL"));
//
//        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
//                "http://kb4images.com/images/image/37185176-image.jpg",
//                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
//                "Chicago","IL"));
//
//        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
//                "http://kb4images.com/images/image/37185176-image.jpg",
//                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
//                "Chicago","IL"));
//
//        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
//                "http://kb4images.com/images/image/37185176-image.jpg",
//                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
//                "Chicago","IL"));
//
//        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
//                "http://kb4images.com/images/image/37185176-image.jpg",
//                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
//                "Chicago","IL"));
//
//        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
//                "http://kb4images.com/images/image/37185176-image.jpg",
//                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
//                "Chicago","IL"));
//
//
//        return items;
    }

    public void goToMediaActivity(String mediaType, String url) {

        Intent mediaIntent = new Intent(this, MediaActivity.class);
        mediaIntent.putExtra("token", token);
        mediaIntent.putExtra("mediaType", mediaType);
        mediaIntent.putExtra("url", url);
        startActivity(mediaIntent);
    }

    public void goToFeedActivity(String mStateName, String mCityName, String hashtagLabel) {
        Intent feedIntent = new Intent(this, FeedActivity.class);
        feedIntent.putExtra("token", token);
        feedIntent.putExtra("state", mStateName);
        feedIntent.putExtra("city", mCityName);
        feedIntent.putExtra("hashtag", hashtagLabel);
        startActivity(feedIntent);
    }

    public void goToCommentsActivity(int position, String originalUrl, String thumbnailUrl, String mediaType, long mediaId, int likesCount, String hashtag){

        Intent commentIntent = new Intent(this, CommentsActivity.class);
        commentIntent.putExtra("token", token);
        commentIntent.putExtra("hashtag", hashtag);
        commentIntent.putExtra("position", position);
        commentIntent.putExtra("mediaType", mediaType);
        commentIntent.putExtra("originalUrl", originalUrl);
        commentIntent.putExtra("thumbnailUrl", thumbnailUrl);
        commentIntent.putExtra("mediaId", mediaId);
        commentIntent.putExtra("likesCount", likesCount);
        startActivity(commentIntent);
    }
}
