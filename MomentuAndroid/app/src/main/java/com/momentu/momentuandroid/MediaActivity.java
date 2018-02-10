package com.momentu.momentuandroid;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    ImageButton playIcon;
    ImageButton pauseIcon;
    static String token;
    public String hashtag;
    public String mediaType ="";
    ZoomImageView zoomImageView;
    public String url;
    int position = 0;

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
        playIcon = (ImageButton)findViewById(R.id.playIcon);
        pauseIcon = (ImageButton)findViewById(R.id.pauseIcon);
        zoomImageView = (ZoomImageView) findViewById(R.id.imageView);

        if(mediaType.equals("image")){
            iv.setVisibility(View.INVISIBLE);
            playIcon.setVisibility(View.INVISIBLE);
            pauseIcon.setVisibility(View.INVISIBLE);
            zoomImageView.setVisibility(View.VISIBLE);
            Picasso.with(this).load(url).into(zoomImageView);
        }else if(mediaType.equals("video")){
            zoomImageView.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.VISIBLE);
            playIcon.setVisibility(View.VISIBLE);
            pauseIcon.setVisibility(View.VISIBLE);
            Uri uri = null;
            try {
                URL myurl = new URL(url);
                uri = Uri.parse(myurl.toURI().toString());
            }catch(MalformedURLException e){
                System.out.print(e.getMessage());
            }catch (URISyntaxException e){
                System.out.print(e.getMessage());
            }
            iv.setVideoURI(uri);

            playIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.start();
                    playIcon.setVisibility(View.INVISIBLE);
                }
            });

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iv.isPlaying()){
                        playIcon.setVisibility(View.INVISIBLE);
                        pauseIcon.setVisibility(View.VISIBLE);
                        iv.stopPlayback();
                    }else{
                        playIcon.setVisibility(View.INVISIBLE);
                        pauseIcon.setVisibility(View.INVISIBLE);
                        iv.start();

                    }
                }
            });

            pauseIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iv.start();
                    pauseIcon.setVisibility(View.INVISIBLE);
                    playIcon.setVisibility(View.INVISIBLE);
                }
            });


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
