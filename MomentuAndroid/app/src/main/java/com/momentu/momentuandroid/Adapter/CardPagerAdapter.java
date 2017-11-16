package com.momentu.momentuandroid.Adapter;

/**
 * Created by Jane on 11/4/2017.
 */

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.momentu.momentuandroid.Data.DataHelper;
import com.momentu.momentuandroid.Data.MomentWrapper;
import com.momentu.momentuandroid.Model.TrendHashTagCard;
import com.momentu.momentuandroid.R;

import java.util.ArrayList;
import java.util.List;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<TrendHashTagCard> mData;
    private float mBaseElevation;
    private final int[] btns = {R.id.bTag1, R.id.bTag2, R.id.bTag3, R.id.bTag4, R.id.bTag5, R.id.bTag6};

    public CardPagerAdapter() {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
    }

    public void addCardItem(TrendHashTagCard item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d("Position", Integer.toString(position));
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.card_view_adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(TrendHashTagCard item, View view) {
        ArrayList<Button> buttonArrayList = new ArrayList<>();
//        buttonArrayList.add((Button) view.findViewById(R.id.bTag1));
//        buttonArrayList.add((Button) view.findViewById(R.id.bTag2));
//        buttonArrayList.add((Button) view.findViewById(R.id.bTag3));
//        buttonArrayList.add((Button) view.findViewById(R.id.bTag4));
//        buttonArrayList.add((Button) view.findViewById(R.id.bTag5));
//        buttonArrayList.add((Button) view.findViewById(R.id.bTag6));

        //Dynamic button creation
        for(int i = 0; i < item.HashTagCount(); i++){
            buttonArrayList.add((Button) view.findViewById(btns[i]));
            String hashTag = item.getTrendHashTags()[i];
            int hashTagLen = hashTag.length();
            // self-adjust font size
            int hashTagFontSize = Math.min(15, (int) (45/Math.sqrt(hashTagLen)));
            buttonArrayList.get(i).setVisibility(View.VISIBLE);
            buttonArrayList.get(i).setTextSize(TypedValue.COMPLEX_UNIT_DIP, hashTagFontSize);
            buttonArrayList.get(i).setText(hashTag);
        }
    }
}

