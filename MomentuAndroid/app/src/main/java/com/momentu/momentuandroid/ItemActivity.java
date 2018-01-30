package com.momentu.momentuandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.momentu.momentuandroid.Adapter.FeedAdapter;

public class ItemActivity extends AppCompatActivity {

    private String mCityName;
    private String mStateName;

    static String token;

    public String hashtag;

    ImageView im;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        mCityName = getIntent().getStringExtra("city");
        mStateName = getIntent().getStringExtra("state");
        token = getIntent().getStringExtra("token");
        hashtag = getIntent().getStringExtra("hashtag");
        position = getIntent().getIntExtra("position",0);


        im = (ImageView)findViewById(R.id.imageCenter);

        im.setImageDrawable(FeedAdapter.feedItems.get(position).getMedia());

    }
}
