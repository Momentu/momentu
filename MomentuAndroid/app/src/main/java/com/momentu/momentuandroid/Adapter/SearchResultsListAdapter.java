package com.momentu.momentuandroid.Adapter;

/**
 * Created by akara on 10/21/2017.
 */


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.arlib.floatingsearchview.util.Util;
import com.momentu.momentuandroid.Data.MomentWrapper;
import com.momentu.momentuandroid.R;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsListAdapter extends RecyclerView.Adapter<SearchResultsListAdapter.ViewHolder> {

    private List<MomentWrapper> mDataSet = new ArrayList<>();

    private int mLastAnimatedItemPosition = -1;
    private OnItemClickListener mItemsOnClickListener;

    public void swapData(List<MomentWrapper> mNewDataSet) {
        mDataSet = mNewDataSet;
        notifyDataSetChanged();
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

        MomentWrapper colorSuggestion = mDataSet.get(position);
        holder.mHashTag.setText(colorSuggestion.getHashtag());
        holder.mContent.setText(colorSuggestion.getContent());

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
        void onClick(MomentWrapper colorWrapper);
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
