package com.momentu.momentuandroid.Adapter;

/**
 * Created by Jane on 10/21/2017.
 */


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.arlib.floatingsearchview.util.Util;
import com.momentu.momentuandroid.Model.Hashtag;
import com.momentu.momentuandroid.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsListAdapter extends RecyclerView.Adapter<SearchResultsListAdapter.ViewHolder> {

    private List<Hashtag> mDataSet = new ArrayList<>();

    private int mLastAnimatedItemPosition = -1;
    private OnItemClickListener mItemsOnClickListener;

    public void swapData(List<Hashtag> mNewDataSet) {
        mDataSet = mNewDataSet;
        notifyDataSetChanged();
    }

    public void clear(){
        mDataSet.clear();
    }

    public void setItemsOnClickListener(OnItemClickListener onClickListener) {
        this.mItemsOnClickListener = onClickListener;
    }

    @Override
    public SearchResultsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_results_list_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultsListAdapter.ViewHolder holder, final int position) {

        Hashtag hashtagSuggestion = mDataSet.get(position);
        holder.mHashTag.setText(hashtagSuggestion.getLabel());

        int postCount = hashtagSuggestion.getCount();
        if(postCount == 1){
            holder.mContent.setText("1 post");
        } else {
            holder.mContent.setText(postCount + " posts");
        }

        if (mLastAnimatedItemPosition < position) {
            animateItem(holder.itemView);
            mLastAnimatedItemPosition = position;
        }

        if (mItemsOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemsOnClickListener.onClick(mDataSet.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    private void animateItem(View view) {
        view.setTranslationY(Util.getScreenHeight((Activity) view.getContext()));
        view.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(3.f))
                .setDuration(700)
                .start();
    }

    public interface OnItemClickListener {
        void onClick(Hashtag Hashtag);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mHashTag;
        public final TextView mContent;
        public final View mTextContainer;

        public ViewHolder(View view) {
            super(view);
            mHashTag = (TextView) view.findViewById(R.id.moment_hastag);
            mContent = (TextView) view.findViewById(R.id.hashtag_description);
            mTextContainer = view.findViewById(R.id.text_container);
        }
    }
}
