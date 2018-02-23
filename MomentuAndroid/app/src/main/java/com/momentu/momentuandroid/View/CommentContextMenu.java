package com.momentu.momentuandroid.View;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.momentu.momentuandroid.R;
import com.momentu.momentuandroid.Utility.DeviceParameterTools;

import butterknife.ButterKnife;

import static android.graphics.drawable.ClipDrawable.VERTICAL;

/**
 * Created by Fahad on 2/20/18.
 */

public class CommentContextMenu extends LinearLayout {

    private static final int CONTEXT_MENU_WIDTH = DeviceParameterTools.dpToPx(240);

    private int commentRow = -1;

    private CommentContextMenu.OnCommentContextMenuItemClickListener onCommentClickListener;

    public CommentContextMenu(Context context) {
        super(context);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.bg_container_shadow);
        setOrientation(VERTICAL);
        setLayoutParams(new LinearLayout.LayoutParams(CONTEXT_MENU_WIDTH, ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void bindToItem(int commentRow) {
        this.commentRow = commentRow;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ButterKnife.bind(this);
    }

    public void dismiss() {
        ((ViewGroup) getParent()).removeView(CommentContextMenu.this);
    }

    public void setOnFeedMenuItemClickListener(CommentContextMenu.OnCommentContextMenuItemClickListener onCommentClickListener) {
        this.onCommentClickListener = onCommentClickListener;
    }

    public interface OnCommentContextMenuItemClickListener {
        //TODO: Preserved listener for the next quarter: we can put more items in this menu, for example, report this feed, copy link of this feed, share this feed...
    }
}
