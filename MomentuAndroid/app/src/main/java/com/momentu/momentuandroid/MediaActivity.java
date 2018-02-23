package com.momentu.momentuandroid;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.otaliastudios.zoom.ZoomImageView;
import com.squareup.picasso.Picasso;


public class MediaActivity extends AppCompatActivity {

    VideoView iv;
    static String token;
    public String mediaType ="";
    ZoomImageView zoomImageView;
    public String url;
    int position = 0;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        token = getIntent().getStringExtra("token");
        url = getIntent().getStringExtra("url");
        mediaType = getIntent().getStringExtra("mediaType");
        iv = (VideoView) findViewById(R.id.videoCenter);
        zoomImageView = (ZoomImageView) findViewById(R.id.imageView);

        if(mediaType.equals("image")){
            iv.setVisibility(View.INVISIBLE);
            zoomImageView.setVisibility(View.VISIBLE);
            Picasso.with(this).load(url).into(zoomImageView);
        }else if(mediaType.equals("video/mp4")){
            mediaController = new MediaController(this);
            zoomImageView.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.VISIBLE);
            Uri uri = null;
            uri = Uri.parse(url.toString());
            iv.setVideoURI(uri);
            iv.setMediaController(mediaController);
            mediaController.setAnchorView(iv);
            iv.start();
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if(hasFocus) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }


}
