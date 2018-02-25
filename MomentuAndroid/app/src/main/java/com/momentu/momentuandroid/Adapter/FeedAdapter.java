package com.momentu.momentuandroid.Adapter;

/**
 * Created by Jane on 11/11/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.momentu.momentuandroid.Data.RestClient;
import com.momentu.momentuandroid.FeedActivity;
import com.momentu.momentuandroid.Model.FeedItem;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.Like;
import com.momentu.momentuandroid.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";

    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;

    private final List<FeedItem> feedItems = new ArrayList<>();

    public static Context context;
    private OnFeedItemClickListener onFeedItemClickListener;

    private boolean showLoadingView = false;

    public FeedAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DEFAULT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
            CellFeedViewHolder cellFeedViewHolder = new CellFeedViewHolder(view);
            setupClickableViews(view, cellFeedViewHolder);
            return cellFeedViewHolder;
        }
        return null;
    }

    private void setupClickableViews(final View view, final CellFeedViewHolder cellFeedViewHolder) {

        // When clicking the image, like the feed (can be modified to other action)
        cellFeedViewHolder.ivFeedCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                //feedItems.get(adapterPosition).getLike().addLikesCount();
                //notifyItemChanged(adapterPosition, ACTION_LIKE_IMAGE_CLICKED);
                if (context instanceof FeedActivity) {
                    //if (feedItems.get(adapterPosition).getMedia_type().equals("image")) {
                        ((FeedActivity) context).goToMediaActivity(adapterPosition, feedItems.get(adapterPosition).getOrginalUrl(), feedItems.get(adapterPosition).getMedia_type());
                        //Toast.makeText(context, feedItems.get(adapterPosition).getOrginalUrl()+ " orginal", Toast.LENGTH_LONG).show();
                        //Toast.makeText(context, feedItems.get(adapterPosition).getThumbnilUrl()+ " thumbnail", Toast.LENGTH_LONG).show();
                    //}
                }
            }
        });

        // When clicking the like button, like the feed
        cellFeedViewHolder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                if (context instanceof FeedActivity) {
                    FeedItem currentFeedItem = feedItems.get(adapterPosition);
                    ((FeedActivity) context).goToCommentsActivity(adapterPosition, currentFeedItem.getOrginalUrl(),currentFeedItem.getThumbnilUrl(), currentFeedItem.getMedia_type(),currentFeedItem.getId());

                }
            }
        });

        //TODO: increment Likes and send it to the backend
        // When clicking the like button, like the feed
        cellFeedViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                FeedItem newFeedItem = feedItems.get(adapterPosition);
                if(newFeedItem.getLike().getIsLiked()){
                    try {
                        new RestClient().MediaUnlike(newFeedItem.getId(), FeedActivity.token);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    newFeedItem.getLike().setIsLiked(false);
//                    cellFeedViewHolder.btnLike.setImageResource(R.drawable.ic_heart_outline_grey);

                    newFeedItem.getLike().minusLikesCount();
                }else{

                    try {
                        new RestClient().MediaLike(newFeedItem.getId(), FeedActivity.token);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    newFeedItem.getLike().setIsLiked(true);
//                    cellFeedViewHolder.btnLike.setImageResource(R.drawable.ic_heart_red);
                    newFeedItem.getLike().addLikesCount();
                }
//                feedItems.get(adapterPosition).getLike().addLikesCount();
                notifyItemChanged(adapterPosition, ACTION_LIKE_BUTTON_CLICKED);

                if (context instanceof FeedActivity) {
                    ((FeedActivity) context).showLikedSnackbar();
                }
            }
        });

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((CellFeedViewHolder) viewHolder).bindView(feedItems.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (showLoadingView && position == 0) {
            return VIEW_TYPE_LOADER;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public void updateItems(boolean animated) {
//        feedItems.clear();
//        feedItems.addAll(Arrays.asList(
//                //TODO: Hard Coded feed item
//                new FeedItem(null,null,
//                        new Hashtag("Video", 1),
//                        "https://s3.amazonaws.com/androidvideostutorial/862009639.mp4",
//                        "https://s3.amazonaws.com/androidvideostutorial/862009639.mp4",
//                        "video/mp4","Video HardCoded",
//                        null, null, null,
//                        new Like(23, false))));
        if (animated) {
            notifyItemRangeInserted(0, feedItems.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public void setOnFeedItemClickListener(OnFeedItemClickListener onFeedItemClickListener) {
        this.onFeedItemClickListener = onFeedItemClickListener;
    }

    public void showLoadingView() {
        showLoadingView = true;
        notifyItemChanged(0);
    }

    public interface OnFeedItemClickListener {
        //TODO: Preserved listener for the next quarter: we can add more item to click under the feed, for example, comments.
    }

    //Define the UI for each feed
    public static class CellFeedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivFeedCenterVideo)
        VideoView ivFeedCenterVideo;
        @BindView(R.id.commentButton)
        ImageButton commentButton;
        @BindView(R.id.videoIcon)
        ImageButton videoIcon;
        @BindView(R.id.ivFeedCenter)
        ImageView ivFeedCenter;
        @BindView(R.id.ivFeedHashTag)
        TextView ivFeedHashTag;
        @BindView(R.id.ivFeedDescription)
        TextView ivFeedDescription;
        @BindView(R.id.btnLike)
        ImageButton btnLike;
        @BindView(R.id.vBgLike)
        View vBgLike;
        @BindView(R.id.ivLike)
        ImageView ivLike;
        @BindView(R.id.tsLikesCounter)
        TextSwitcher tsLikesCounter;
        @BindView(R.id.vImageRoot)
        FrameLayout vImageRoot;

        FeedItem feedItem;

        public CellFeedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(FeedItem feedItem) {
            Log.d("bindView","called");
            this.feedItem = feedItem;
            int adapterPosition = getAdapterPosition();
            Log.d("Feed Position", Integer.toString(adapterPosition));
            if (feedItem.getMedia_type().equals("image")){
                ivFeedCenterVideo.setVisibility(View.INVISIBLE);
                videoIcon.setVisibility(View.INVISIBLE);
                ivFeedCenter.setVisibility(View.VISIBLE);
                Picasso.with(context)
                        .load(Uri.parse(feedItem.getThumbnilUrl()))
                        .into(ivFeedCenter);
            }else if (feedItem.getMedia_type().equals("video/mp4")){
                ivFeedCenterVideo.setVisibility(View.INVISIBLE);
                videoIcon.setVisibility(View.VISIBLE);
                ivFeedCenter.setVisibility(View.VISIBLE);
                ivFeedCenter.setImageBitmap(getVideoThumbnail(feedItem.getOrginalUrl()));
                Uri uri = null;
                try {
                    URL myurl = new URL(feedItem.getThumbnilUrl());
                    uri = Uri.parse(myurl.toURI().toString());
                }catch(MalformedURLException e){
                    System.out.print(e.getMessage());
                }catch (URISyntaxException e){
                    System.out.print(e.getMessage());
                }
                ivFeedCenterVideo.setVideoURI(uri);

            }
            ivFeedHashTag.setText(feedItem.getHashTag().getLabel());
            ivFeedDescription.setText(feedItem.getDescription());
            Log.d("IsLike","isLike value: " + feedItem.getLike().getIsLiked());
            btnLike.setImageResource(feedItem.getLike().getIsLiked() ? R.drawable.ic_heart_red : R.drawable.ic_heart_outline_grey);
            tsLikesCounter.setCurrentText(vImageRoot.getResources().getQuantityString(
                    R.plurals.likes_count, feedItem.getLike().getLikesCount(), feedItem.getLike().getLikesCount()
            ));
        }

        public FeedItem getFeedItem() {
            return feedItem;
        }


    }
    public void addFeed(FeedItem myFeed){
        feedItems.add(myFeed);
    }
    public static Bitmap getVideoThumbnail(String path) {
        Bitmap bitmap = null;
        try {
            bitmap = new AsyncTask<String,Void,Bitmap>(){

                @Override
                protected Bitmap doInBackground(String... strings) {
                    Bitmap bitmap1 = null;
                    MediaMetadataRetriever mediaMetadataRetriever = null;
                    try {
                        mediaMetadataRetriever = new MediaMetadataRetriever();
                        if (Build.VERSION.SDK_INT >= 14)
                            mediaMetadataRetriever.setDataSource(strings[0], new HashMap<String, String>());
                        else
                            mediaMetadataRetriever.setDataSource(strings[0]);
                        //   mediaMetadataRetriever.setDataSource(videoPath);
                        bitmap1 = mediaMetadataRetriever.getFrameAtTime();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("Exception", "Exception in retriveVideoFrameFromVideo(String videoPath) for: "+ strings[0] + e.getMessage());

                    } finally {
                        if (mediaMetadataRetriever != null) {
                            mediaMetadataRetriever.release();
                        }
                    }
                    Log.d("GetAThumbnail","just made it");
                    return bitmap1;            }
            }.execute(path).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return bitmap;
    }
}
