package com.momentu.momentuandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.momentu.momentuandroid.Adapter.MyLikesAdapter;
import com.momentu.momentuandroid.Model.MyPostsItem;
import com.momentu.momentuandroid.Model.PostsYouHaveLikedItem;

import java.util.ArrayList;
import java.util.List;

public class PostsYouHaveLikedActivity extends AppCompatActivity {

    public String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_you_have_liked);

        token = getIntent().getStringExtra("token");

        GridView gridview = (GridView) findViewById(R.id.gridviewMyLikes);

        List<PostsYouHaveLikedItem> allItems = getAllItemObject();
        MyLikesAdapter myLikesAdapter = new MyLikesAdapter(PostsYouHaveLikedActivity.this, allItems);
        gridview.setAdapter(myLikesAdapter);


    }


    private List<PostsYouHaveLikedItem> getAllItemObject(){
        MyPostsItem myLikes = null;
        List<PostsYouHaveLikedItem> items = new ArrayList<>();
        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
                "http://kb4images.com/images/image/37185176-image.jpg",
                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
                "Chicago","IL"));

        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
                "http://kb4images.com/images/image/37185176-image.jpg",
                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
                "Chicago","IL"));

        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
                "http://kb4images.com/images/image/37185176-image.jpg",
                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
                "Chicago","IL"));

        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
                "http://kb4images.com/images/image/37185176-image.jpg",
                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
                "Chicago","IL"));

        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
                "http://kb4images.com/images/image/37185176-image.jpg",
                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
                "Chicago","IL"));

        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
                "http://kb4images.com/images/image/37185176-image.jpg",
                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
                "Chicago","IL"));

        items.add(new PostsYouHaveLikedItem(0,"#Halloween",
                "http://kb4images.com/images/image/37185176-image.jpg",
                "http://kb4images.com/images/image/37185176-image.jpg","image",1000,
                "Chicago","IL"));


        return items;
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
