package com.momentu.momentuandroid;

/**
 * Created by Jane on 11/11/2017.
 */

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.BindView;

import com.momentu.momentuandroid.BaseActivity.FeedBaseActivity;
import com.momentu.momentuandroid.Data.RestClient;
import com.momentu.momentuandroid.Model.FeedItem;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.Like;
import com.momentu.momentuandroid.Utility.ConvertImagesToStringOfBytes;
import com.momentu.momentuandroid.Utility.DeviceParameterTools;
import com.momentu.momentuandroid.Adapter.FeedAdapter;
import com.momentu.momentuandroid.Adapter.FeedItemAnimator;
import com.momentu.momentuandroid.View.FeedContextMenuManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FeedActivity extends FeedBaseActivity implements FeedAdapter.OnFeedItemClickListener {
    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";
    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;
    public static final int CAMERA_REQUEST = 1999;

    //Feed RecyclerView
    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;

    //Camera floating button
    @BindView(R.id.bCameraInFeed)
    FloatingActionButton fabCreate;

    @BindView(R.id.content)
    CoordinatorLayout clContent;

    private FeedAdapter feedAdapter;

    private boolean pendingIntroAnimation;

    public EditText hashtagInput;

    private String mCityName;
    private String mStateName;

    static String token;

    public String hashtag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        setupFeed();

        mCityName = getIntent().getStringExtra("city");
        mStateName = getIntent().getStringExtra("state");
        token = getIntent().getStringExtra("token");
        hashtag = getIntent().getStringExtra("hashtag");

        if (savedInstanceState == null) {
            Log.d("savedInstanceState:", "null");
            pendingIntroAnimation = true;
        } else {
            feedAdapter.updateItems(false);
        }

        //Take picture/video
        //TODO: Need a "MediaActivity" to process the photo/video taken.
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA},
                HashTagSearchActivity.CAMERA_REQUEST);

        ImageButton cameraButton = (ImageButton) this.findViewById(R.id.bCameraInFeed);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
    }

    // The method is on activity result after capture a photo. A dialog will be displayed
    // for user to enter the hashtag name.
    //It passes the hashtage along with the location to RestClient to pass it to the backend
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
           final Bitmap imageBitmap = (Bitmap) extras.get("data");

            final Dialog dialogToPost = new Dialog(this);
            dialogToPost.setContentView(R.layout.dialog_to_post);
            Button post = (Button) dialogToPost.findViewById(R.id.post);
            Button cancel = (Button) dialogToPost.findViewById(R.id.cancel);
            hashtagInput = (EditText) dialogToPost.findViewById(R.id.hashtagInput);

            //On click listener for post button
            post.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if (hashtagInput.getText().toString().contains("#")) {
                        dialogToPost.dismiss();
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("hashtagLabel", hashtagInput.getText().toString());
                        params.put("city", mCityName);
                        params.put("state", mStateName);

                        RestClient restClient = new RestClient();
                        try {
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                            byte[] imageBytes = byteArrayOutputStream.toByteArray();
                            restClient.media_upload(imageBytes, params, token, FeedActivity.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(restClient.status == 0)
                            Toast.makeText(FeedActivity.this, hashtagInput.getText().toString() + " posted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(FeedActivity.this, hashtagInput.getText().toString() + " cann't be posted", Toast.LENGTH_LONG).show();

                    }else
                    {
                        Toast.makeText(FeedActivity.this, "Wrong Hashtag Format", Toast.LENGTH_LONG).show();
                    }
                }
            });
            dialogToPost.show();
            FeedItem myFeed = new FeedItem(null,null,
                    new Hashtag(hashtagInput.getText().toString(), 1),
                    new BitmapDrawable(getResources(), imageBitmap),
                    "HI",
                    null, null, null,
                    new Like(93, false));
            feedAdapter.addFeed(myFeed);
            //On click listener for cancel button
            cancel.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    dialogToPost.dismiss();
                    Toast.makeText(FeedActivity.this, "Post has been canceled", Toast.LENGTH_LONG).show();
                }

            });

        }
    }

    //Load adapter, listener and animator to the feed
    private void setupFeed() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvFeed.setLayoutManager(linearLayoutManager);

        feedAdapter = new FeedAdapter(this);
        feedAdapter.setOnFeedItemClickListener(this);
        rvFeed.setAdapter(feedAdapter);
        rvFeed.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                FeedContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvFeed.setItemAnimator(new FeedItemAnimator());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (ACTION_SHOW_LOADING_ITEM.equals(intent.getAction())) {
            showFeedLoadingItemDelayed();
        }
    }

    private void showFeedLoadingItemDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvFeed.smoothScrollToPosition(0);
                feedAdapter.showLoadingView();
            }
        }, 500);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        Log.d("onCreateOptionsMenu", "activated!");
        if (pendingIntroAnimation) {
            Log.d("pendingIntroAnimation", "True");
            pendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }

    private void startIntroAnimation() {
        fabCreate.setTranslationY(2 * getResources().getDimensionPixelOffset(R.dimen.btn_fab_size));

        int actionbarSize = DeviceParameterTools.dpToPx(56);
        getToolbar().setTranslationY(-actionbarSize);
        getIvLogo().setTranslationY(-actionbarSize);

        //Toolbar and logo first slide down from top
        getToolbar().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
        getIvLogo().animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        //Then feed content will slide up from bottom
                        startContentAnimation();
                    }
                })
                .start();
    }

    private void startContentAnimation() {
        fabCreate.animate()
                .translationY(0)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setStartDelay(300)
                .setDuration(ANIM_DURATION_FAB)
                .start();
        feedAdapter.updateItems(true);
    }

    public void itemActivity(int position){

        Intent itemIntent = new Intent(this, ItemActivity.class);
        itemIntent.putExtra("token", token);
        itemIntent.putExtra("state", mStateName);
        itemIntent.putExtra("city", mCityName);
        itemIntent.putExtra("hashtag", hashtag);
        itemIntent.putExtra("position", position);
        startActivity(itemIntent);
    }

    public void showLikedSnackbar() {
        Snackbar.make(clContent, "Liked!", Snackbar.LENGTH_SHORT).show();
    }
}