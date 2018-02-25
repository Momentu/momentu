package com.momentu.momentuandroid;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.momentu.momentuandroid.Adapter.CommentAdapter;
import com.momentu.momentuandroid.Adapter.CommentRowAnimator;
import com.momentu.momentuandroid.Adapter.FeedAdapter;
import com.momentu.momentuandroid.Adapter.FeedItemAnimator;
import com.momentu.momentuandroid.BaseActivity.FeedBaseActivity;
import com.momentu.momentuandroid.Data.RestClient;
import com.momentu.momentuandroid.Model.CommentRow;
import com.momentu.momentuandroid.Model.FeedItem;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.Like;
import com.momentu.momentuandroid.Model.MediaUrlStorage;
import com.momentu.momentuandroid.Services.GetCommentsService;
import com.momentu.momentuandroid.Utility.DeviceParameterTools;
import com.momentu.momentuandroid.View.CommentContextMenuManager;
import com.momentu.momentuandroid.View.FeedContextMenuManager;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import okhttp3.Response;

public class CommentsActivity extends FeedBaseActivity {
    private static final int ANIM_DURATION_TOOLBAR = 300;
    public static final String ACTION_SHOW_LOADING_COMMENT = "action_show_loading_comments";

    @BindView(R.id.rvCommentList)
    RecyclerView rvCommentList;
    public static CommentAdapter commentAdapter;
    private boolean pendingIntroAnimation;
    public ImageView imageView;
    public TextView ivFeedHashTag;
    public TextView ivFeedDescription;
    public TextView numberOfLikes;
    public ImageView profilePicture;
    public EditText comment;
    public ImageButton send;
    static String token;
    public String hashtag;
    public String mediaType ="";
    public long mediaId;
    public String originalUrl;
    public String thumbnailUrl;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        token = getIntent().getStringExtra("token");
        hashtag = getIntent().getStringExtra("hashtag");
        position = getIntent().getIntExtra("position",0);
        originalUrl = getIntent().getStringExtra("originalUrl");
        thumbnailUrl = getIntent().getStringExtra("thumbnailUrl");
        mediaType = getIntent().getStringExtra("mediaType");
        mediaId = getIntent().getLongExtra("mediaId",0);

        Log.d("onCreate","" + mediaId);

        imageView = (ImageView) findViewById(R.id.imageView);
        ivFeedHashTag = (TextView)  findViewById(R.id.ivFeedHashTag);
        ivFeedDescription= (TextView)  findViewById(R.id.ivFeedDescription);;
        numberOfLikes= (TextView)  findViewById(R.id.numberOfLikes);;
        profilePicture= (ImageView)  findViewById(R.id.profilePicture);;
        comment= (EditText)  findViewById(R.id.comment);;
        send = (ImageButton)  findViewById(R.id.send);;

        Picasso.with(this).load(thumbnailUrl).into(imageView);
        ivFeedHashTag.setText(hashtag);


        setupComments();


        Intent intent = new Intent(this, GetCommentsService.class);
        intent.putExtra("mediaId", mediaId);
        startService(intent);
        if (savedInstanceState == null) {
            Log.d("savedInstanceState:", "null");
            pendingIntroAnimation = true;
        } else {
            commentAdapter.updateItems(false);
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comment.getText().length()>0){
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("mediaMetaId",mediaId+"");
                    params.put("comment",comment.getText().toString());
                    try {
                        int statudCode = new RestClient().post_comment(params);
                        Log.d("StatusCode","code="+ statudCode);
                        if(statudCode == 200){
                            commentAdapter.addComment(new CommentRow(comment.getText().toString(),HashTagSearchActivity.username));
                            commentAdapter.notifyDataSetChanged();
                            comment.setText("");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    //TODO send the comment to the backend along with token and media id
                    //Toast.makeText(CommentsActivity.this, comment.getText().toString() + "sent", Toast.LENGTH_LONG).show();
//                    commentAdapter.notifyDataSetChanged();

                }
            }

        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMediaActivity(token,originalUrl,mediaType);

            }

        });

        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(GetCommentsService.MY_COMMENTS_SERVICE_MESSAGE));

    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Log.d("CommentsFeed","jsut got in the reciever");

            ArrayList<CommentRow> comments = intent
                    .getParcelableArrayListExtra(GetCommentsService.MY_COMMENTS_SERVICE_PAYLOAD);

            if (comments == null) {
                Log.d("CommentsFeed","temp is null");
            }

            else {
                gotSomeComments(comments);
//                Log.d("IMAGE_Feed", " just got this: " + temp.get(0));
            }
        }
    };

    public void gotSomeComments(final ArrayList<CommentRow> comments){

        new AsyncTask<String, Void, Response>() {
            @Override
            protected Response doInBackground(String... strings) {
                try {

                    for (CommentRow aComment : comments) {
                        commentAdapter.addComment(aComment);
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    private void setupComments() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return 300;
            }
        };
        rvCommentList.setLayoutManager(linearLayoutManager);
        commentAdapter = new CommentAdapter(this);
        rvCommentList.setAdapter(commentAdapter);
        rvCommentList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                CommentContextMenuManager.getInstance().onScrolled(recyclerView, dx, dy);
            }
        });
        rvCommentList.setItemAnimator(new CommentRowAnimator());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (ACTION_SHOW_LOADING_COMMENT.equals(intent.getAction())) {
            showCommentLoadingItemDelayed();
        }
    }

    private void showCommentLoadingItemDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvCommentList.smoothScrollToPosition(0);
                commentAdapter.showLoadingView();
            }
        }, 500);
        pendingIntroAnimation = false;
        startIntroAnimation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        if (pendingIntroAnimation) {
            pendingIntroAnimation = false;
            startIntroAnimation();
        }
        return true;
    }

    private void startIntroAnimation() {
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
        commentAdapter.updateItems(true);
    }

    public void goToMediaActivity(String token, String url, String mediaType){

        Intent itemIntent = new Intent(this, MediaActivity.class);
        itemIntent.putExtra("token", token);
        itemIntent.putExtra("mediaType", mediaType);
        itemIntent.putExtra("url", url);
        startActivity(itemIntent);
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
