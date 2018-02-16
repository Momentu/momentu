package com.momentu.momentuandroid;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.otaliastudios.zoom.ZoomImageView;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class MediaActivity extends AppCompatActivity {

    private String mCityName;
    private String mStateName;
    VideoView iv;
    static String token;
    public String hashtag;
    public String mediaType ="";
    ZoomImageView zoomImageView;
    public String url;
    int position = 0;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        mCityName = getIntent().getStringExtra("city");
        mStateName = getIntent().getStringExtra("state");
        token = getIntent().getStringExtra("token");
        hashtag = getIntent().getStringExtra("hashtag");
        position = getIntent().getIntExtra("position",0);
        url = getIntent().getStringExtra("url");
        mediaType = getIntent().getStringExtra("mediaType");
        iv = (VideoView) findViewById(R.id.videoCenter);
        zoomImageView = (ZoomImageView) findViewById(R.id.imageView);

        Log.d("MediaActivity","Just got mediaType: " + mediaType);
        if(mediaType.equals("image")){
            iv.setVisibility(View.INVISIBLE);
            zoomImageView.setVisibility(View.VISIBLE);
            Picasso.with(this).load(url).into(zoomImageView);
        }else if(mediaType.equals("video/mp4")){
            mediaController = new MediaController(this);
            zoomImageView.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.VISIBLE);
            Uri uri = null;
           // try {
                //URL myurl = new URL(url);
                uri = Uri.parse(url.toString());
//            }catch(MalformedURLException e) {
//                System.out.print(e.getMessage());
//            }
////            }catch (URISyntaxException e){
////                System.out.print(e.getMessage());
////            }
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
