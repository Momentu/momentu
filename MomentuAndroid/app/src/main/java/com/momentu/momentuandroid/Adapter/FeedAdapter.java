package com.momentu.momentuandroid.Adapter;

/**
 * Created by Jane on 11/11/2017.
 */

import android.content.Context;
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

import com.momentu.momentuandroid.FeedActivity;
import com.momentu.momentuandroid.Model.FeedItem;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.Model.Like;
import com.momentu.momentuandroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String ACTION_LIKE_BUTTON_CLICKED = "action_like_button_button";
    public static final String ACTION_LIKE_IMAGE_CLICKED = "action_like_image_button";

    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;

    private final List<FeedItem> feedItems = new ArrayList<>();

    private Context context;
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
                feedItems.get(adapterPosition).getLike().addLikesCount();
                notifyItemChanged(adapterPosition, ACTION_LIKE_IMAGE_CLICKED);
                if (context instanceof FeedActivity) {
                    ((FeedActivity) context).showLikedSnackbar();
                }
            }
        });

        // When clicking the like button, like the feed
        cellFeedViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = cellFeedViewHolder.getAdapterPosition();
                feedItems.get(adapterPosition).getLike().addLikesCount();
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
        feedItems.clear();
        feedItems.addAll(Arrays.asList(
                //TODO: Hard Coded feed item
                new FeedItem(null,null,
                        new Hashtag(context.getString(R.string.feed_hashtag), 1),
                        context.getResources().getDrawable(R.drawable.img_feed_center_1),
                        context.getString(R.string.feed_description_1),
                        null, null, null,
                        new Like(23, false)),
                new FeedItem(null,null,
                        new Hashtag(context.getString(R.string.feed_hashtag), 1),
                        context.getResources().getDrawable(R.drawable.img_feed_center_2),
                        context.getString(R.string.feed_description_2),
                        null, null, null,
                        new Like(2, false)),
                new FeedItem(null,null,
                        new Hashtag(context.getString(R.string.feed_hashtag), 1),
                        context.getResources().getDrawable(R.drawable.img_feed_center_1),
                        context.getString(R.string.feed_description_1),
                        null, null, null,
                        new Like(45, false)),
                new FeedItem(null,null,
                        new Hashtag(context.getString(R.string.feed_hashtag), 1),
                        context.getResources().getDrawable(R.drawable.img_feed_center_2),
                        context.getString(R.string.feed_description_2),
                        null, null, null,
                        new Like(159, false)),
                new FeedItem(null,null,
                        new Hashtag(context.getString(R.string.feed_hashtag), 1),
                        context.getResources().getDrawable(R.drawable.img_feed_center_1),
                        context.getString(R.string.feed_description_1),
                        null, null, null,
                        new Like(362, false)),
                new FeedItem(null,null,
                        new Hashtag(context.getString(R.string.feed_hashtag), 1),
                        context.getResources().getDrawable(R.drawable.img_feed_center_2),
                        context.getString(R.string.feed_description_2),
                        null, null, null,
                        new Like(93, false))
        ));
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
            this.feedItem = feedItem;
            int adapterPosition = getAdapterPosition();
            Log.d("Feed Position", Integer.toString(adapterPosition));
            ivFeedCenter.setImageDrawable(feedItem.getMedia());
            ivFeedHashTag.setText(feedItem.getHashTag().getLabel());
            ivFeedDescription.setText(feedItem.getDescription());
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
}
