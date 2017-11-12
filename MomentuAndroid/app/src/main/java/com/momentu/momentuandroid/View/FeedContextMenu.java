package com.momentu.momentuandroid.View;

/**
 * Created by Jane on 11/11/2017.
 */

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.momentu.momentuandroid.R;
import com.momentu.momentuandroid.Utility.DeviceParameterTools;

import butterknife.ButterKnife;

public class FeedContextMenu extends LinearLayout {
    private static final int CONTEXT_MENU_WIDTH = DeviceParameterTools.dpToPx(240);

    private int feedItem = -1;

    private OnFeedContextMenuItemClickListener onItemClickListener;

    public FeedContextMenu(Context context) {
        super(context);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.bg_container_shadow);
        setOrientation(VERTICAL);
        setLayoutParams(new LayoutParams(CONTEXT_MENU_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void bindToItem(int feedItem) {
        this.feedItem = feedItem;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ButterKnife.bind(this);
    }

    public void dismiss() {
        ((ViewGroup) getParent()).removeView(FeedContextMenu.this);
    }

    public void setOnFeedMenuItemClickListener(OnFeedContextMenuItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnFeedContextMenuItemClickListener {
        //TODO: Preserved listener for the next quarter: we can put more items in this menu, for example, report this feed, copy link of this feed, share this feed...
    }
}