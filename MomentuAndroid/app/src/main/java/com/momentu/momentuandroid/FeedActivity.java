package com.momentu.momentuandroid;

/**
 * Created by Jane on 11/11/2017.
 */

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.BindView;
import okhttp3.Response;

import com.momentu.momentuandroid.BaseActivity.FeedBaseActivity;
import com.momentu.momentuandroid.Data.EndPoints;
import com.momentu.momentuandroid.Data.RestClient;
import com.momentu.momentuandroid.Manager.PermissionsManager;
import com.momentu.momentuandroid.Model.FeedItem;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.MediaUrlStorage;
import com.momentu.momentuandroid.Model.Like;
import com.momentu.momentuandroid.Services.GetMediaService;
import com.momentu.momentuandroid.Utility.ConvertImagesToStringOfBytes;
import com.momentu.momentuandroid.Utility.DeviceParameterTools;
import com.momentu.momentuandroid.Adapter.FeedAdapter;
import com.momentu.momentuandroid.Adapter.FeedItemAnimator;
import com.momentu.momentuandroid.Utility.ImageHelper;
import com.momentu.momentuandroid.Utility.RequestPackage;
import com.momentu.momentuandroid.View.FeedContextMenuManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FeedActivity extends FeedBaseActivity implements FeedAdapter.OnFeedItemClickListener {
    public static final String ACTION_SHOW_LOADING_ITEM = "action_show_loading_item";
    private static final int ANIM_DURATION_TOOLBAR = 300;
    private static final int ANIM_DURATION_FAB = 400;
    public static final int CAMERA_REQUEST = 1999;
    public static final int CAMERA_REQUEST_VIDEO = 2999;

    //Feed RecyclerView
    @BindView(R.id.rvFeed)
    RecyclerView rvFeed;

    //Camera floating button
    @BindView(R.id.bCameraInFeed)
    FloatingActionButton fabCreate;

    @BindView(R.id.content)
    CoordinatorLayout clContent;

    public FeedAdapter feedAdapter;

    private boolean pendingIntroAnimation;

    public EditText hashtagInput;

    private String mCityName;
    private String mStateName;
    public static String token;

    public String hashtag;
    public Uri photoURI = null;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        setupFeed();

        final PermissionsManager permissionsManager = new PermissionsManager();
        mCityName = getIntent().getStringExtra("city");
        mStateName = getIntent().getStringExtra("state");
        token = getIntent().getStringExtra("token");
        hashtag = getIntent().getStringExtra("hashtag");


        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setEndpoint(EndPoints.HASHTAGS_ENDPOINT);
        requestPackage.setParam("label",hashtag);
        requestPackage.setParam("city",mCityName);
        requestPackage.setParam("state",mStateName);
        requestPackage.setToken(token);

        Intent intent = new Intent(this, GetMediaService.class);
        intent.putExtra(GetMediaService.REQUEST_PACKAGE_IMAGE, requestPackage);
        startService(intent);

        final ArrayList<MediaUrlStorage> urls = new ArrayList<MediaUrlStorage>();


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
                FeedActivity.CAMERA_REQUEST);

        ImageButton cameraButton = (ImageButton) this.findViewById(R.id.bCameraInFeed);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(permissionsManager.userHasPermission(FeedActivity.this)) {
                    final Dialog dialogCameraMode = new Dialog(FeedActivity.this);
                    dialogCameraMode.setContentView(R.layout.dialog_camera_mode);
                    Button photo = (Button) dialogCameraMode.findViewById(R.id.photo);
                    Button video = (Button) dialogCameraMode.findViewById(R.id.video);
                    Button cancel = (Button) dialogCameraMode.findViewById(R.id.cancel);

                    photo.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialogCameraMode.dismiss();
                            takePicture();
                        }
                    });

                    video.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialogCameraMode.dismiss();
                            recordVideo();
                        }
                    });
                    dialogCameraMode.show();

                    cancel.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            dialogCameraMode.dismiss();
                        }

                    });

                }else {
                    permissionsManager.requestPermission(FeedActivity.this);
                }
            }
        });

        //setup reciever 'mBroadcastReceiver'
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(GetMediaService.MY_IMAGE_SERVICE_MESSAGE));
    }

    //receive response from ConnectionService
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("IMAGE_Feed","jsut got in here reciever");

            ArrayList<MediaUrlStorage> temp = intent
                    .getParcelableArrayListExtra(GetMediaService.MY_IMAGE_SERVICE_PAYLOAD);

            if (temp == null) {
                Log.d("IMAGE_Feed","temp is null");
            }

            else {
                convertURL(temp);
//                Log.d("IMAGE_Feed", " just got this: " + temp.get(0));
            }
        }
    };

    private void recordVideo() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            Uri videoURI = null;
            try {
                File videoFile = ImageHelper.createTempVideoFile();
                path = videoFile.getAbsolutePath();
                videoURI = FileProvider.getUriForFile(FeedActivity.this,
                        getString(R.string.file_provider_authority),
                        videoFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
            takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoURI);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                takeVideoIntent.setClipData(ClipData.newRawUri("", videoURI));
                takeVideoIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,10);
            startActivityForResult(takeVideoIntent, CAMERA_REQUEST_VIDEO);
        }
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //Uri photoURI = null;
            try {
                File imageFile = ImageHelper.createTempImageFile();
                path = imageFile.getAbsolutePath();
                photoURI = FileProvider.getUriForFile(FeedActivity.this,
                        getString(R.string.file_provider_authority),
                        imageFile);

            } catch (IOException e) {
                e.printStackTrace();
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                takePictureIntent.setClipData(ClipData.newRawUri("", photoURI));
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            startActivityForResult(takePictureIntent, CAMERA_REQUEST);
        }
    }

    // The method is on activity result after capture a photo. A dialog will be displayed
    // for user to enter the hashtag name.
    //It passes the hashtage along with the location to RestClient to pass it to the backend
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            BitmapFactory.Options bitmapOptions = ImageHelper.provideCompressionBitmapFactoryOptions();
            final Bitmap imageBitmap = BitmapFactory.decodeFile(path, bitmapOptions);

            final Dialog dialogToPost = new Dialog(this);
            dialogToPost.setContentView(R.layout.dialog_to_post);
            final Button post = (Button) dialogToPost.findViewById(R.id.post);
            Button cancel = (Button) dialogToPost.findViewById(R.id.cancel);
            hashtagInput = (EditText) dialogToPost.findViewById(R.id.hashtagInput);

            hashtagInput.setText(hashtag);
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
                            restClient.media_upload(ConvertImagesToStringOfBytes.mediaToByteArray(imageBitmap), "image", params, token, FeedActivity.this);
                            //restClient.media(params, token, FeedActivity.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(restClient.status == 0) {
                            Toast.makeText(FeedActivity.this, hashtagInput.getText().toString() + " posted", Toast.LENGTH_LONG).show();
                            if(hashtagInput.getText().toString().equals(hashtag)) {
                                FeedItem myFeed = new FeedItem(0, null,
                                        new Hashtag(hashtagInput.getText().toString(), 1), photoURI.toString(), photoURI.toString(), "image",
                                        "",
                                        null, null, null,
                                        new Like(0, false));
                                feedAdapter.addFeed(myFeed);
                                feedAdapter.notifyDataSetChanged();
                            }
                        }else
                            Toast.makeText(FeedActivity.this, hashtagInput.getText().toString() + " cann't be posted", Toast.LENGTH_LONG).show();

                    }else
                    {
                        Toast.makeText(FeedActivity.this, "Wrong Hashtag Format", Toast.LENGTH_LONG).show();
                    }
                }
            });
            dialogToPost.show();
            cancel.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    dialogToPost.dismiss();
                    Toast.makeText(FeedActivity.this, "Post has been canceled", Toast.LENGTH_LONG).show();
                }

            });

        }else if (requestCode == CAMERA_REQUEST_VIDEO && resultCode == Activity.RESULT_OK){
            final Uri uriVideo = data.getData();

            final Dialog dialogToPost = new Dialog(this);
            dialogToPost.setContentView(R.layout.dialog_to_post);
            Button post = (Button) dialogToPost.findViewById(R.id.post);
            Button cancel = (Button) dialogToPost.findViewById(R.id.cancel);
            hashtagInput = (EditText) dialogToPost.findViewById(R.id.hashtagInput);
            hashtagInput.setText(hashtag);
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
                            restClient.media_upload(ConvertImagesToStringOfBytes.mediaToByteArray(uriVideo), "video/mp4", params, token, FeedActivity.this);
                            //recreate();
                            //restClient.media(params, token, HashTagSearchActivity.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (restClient.status == 0){
                            Toast.makeText(FeedActivity.this, hashtagInput.getText().toString() + " posted", Toast.LENGTH_LONG).show();
                            if(hashtagInput.getText().toString().equals(hashtag)) {
                                FeedItem myFeed = new FeedItem(0, null,
                                        new Hashtag(hashtagInput.getText().toString(), 1), uriVideo.toString(), uriVideo.toString(), "video/mp4",
                                        "",
                                        null, null, null,
                                        new Like(0, false));
                                feedAdapter.addFeed(myFeed);
                                feedAdapter.notifyDataSetChanged();
                            }
                        }else
                            Toast.makeText(FeedActivity.this, hashtagInput.getText().toString() + " cann't be posted", Toast.LENGTH_LONG).show();
                    }else
                    {
                        Toast.makeText(FeedActivity.this, "Wrong Hashtag Format", Toast.LENGTH_LONG).show();
                    }
                }
            });

            dialogToPost.show();

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

    public void showLikedSnackbar() {
        Snackbar.make(clContent, "Liked!", Snackbar.LENGTH_SHORT).show();
    }

    public void goToMediaActivity(int position, String url, String mediaType){

        Intent itemIntent = new Intent(this, MediaActivity.class);
        itemIntent.putExtra("token", token);
        itemIntent.putExtra("state", mStateName);
        itemIntent.putExtra("city", mCityName);
        itemIntent.putExtra("hashtag", hashtag);
        itemIntent.putExtra("position", position);
        itemIntent.putExtra("mediaType", mediaType);
        itemIntent.putExtra("url", url);
        startActivity(itemIntent);
    }

    public void goToCommentsActivity(int position, String originalUrl, String thumbnailUrl, String mediaType){

        Intent commentIntent = new Intent(this, CommentsActivity.class);
        commentIntent.putExtra("token", token);
        commentIntent.putExtra("hashtag", hashtag);
        commentIntent.putExtra("position", position);
        commentIntent.putExtra("mediaType", mediaType);
        commentIntent.putExtra("originalUrl", originalUrl);
        commentIntent.putExtra("thumbnailUrl", thumbnailUrl);
        startActivity(commentIntent);
    }

    public void convertURL(final ArrayList<MediaUrlStorage> response){

        new AsyncTask<String, Void, Response>() {
            @Override
            protected Response doInBackground(String... strings) {
                try {

                    for (MediaUrlStorage imageUrls : response) {
                        FeedItem myFeed = new FeedItem(imageUrls.getId(), null,
                                new Hashtag(hashtag, 1), imageUrls.getOriginalUrl(),imageUrls.getThumbnilUrl(), imageUrls.getMedia_type(),
                                "",
                                null, null, null,
                                new Like(imageUrls.getNumberOfLikes(), imageUrls.isLiked()));
                        feedAdapter.addFeed(myFeed);



                }
                } catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {

            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(mBroadcastReceiver);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}