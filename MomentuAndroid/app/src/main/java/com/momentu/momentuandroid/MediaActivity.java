package com.momentu.momentuandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MediaActivity extends AppCompatActivity {


    private String mCityName;
    private String mStateName;

    static String token;

    public String hashtag;

    ImageView im;

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

        im = (ImageView)findViewById(R.id.imageCenter);

        Picasso.with(this).load(url).into(im);

    }
}
