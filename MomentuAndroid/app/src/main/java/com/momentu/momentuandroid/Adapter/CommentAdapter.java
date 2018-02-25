package com.momentu.momentuandroid.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.momentu.momentuandroid.Model.CommentRow;
import com.momentu.momentuandroid.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fahad on 2/20/18.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_LOADER = 2;

    private final List<CommentRow> commentRow = new ArrayList<>();

    public static Context context;

    private boolean showLoadingView = false;

    public CommentAdapter(Context context) {
        this.context = context;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DEFAULT) {
            View view = LayoutInflater.from(context).inflate(R.layout.comment_row, parent, false);
            CommentAdapter.CellCommentViewHolder cellCommentViewHolder = new CommentAdapter.CellCommentViewHolder(view);
            return cellCommentViewHolder;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((CommentAdapter.CellCommentViewHolder) viewHolder).bindView(commentRow.get(position));
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
        return commentRow.size();
    }

    public void updateItems(boolean animated) {
//        commentRow.clear();
//        Log.d("before data", "ok!");
//        commentRow.addAll(Arrays.asList(
//                //TODO: Hard Coded feed item
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment"),
//                new CommentRow("falharbi88","This is a nice comment")));
//        Log.d("After data", "ok!");
        if (animated) {
            notifyItemRangeInserted(0, commentRow.size());
        } else {
            notifyDataSetChanged();
        }
    }

    public void showLoadingView() {
        showLoadingView = true;
        notifyItemChanged(0);
    }


    public static class CellCommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.username)
        TextView username;

        @BindView(R.id.commentText)
        TextView commentText;

        CommentRow commentRow;

        public CellCommentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindView(CommentRow commentRow) {
            this.commentRow = commentRow;
            int adapterPosition = getAdapterPosition();
            username.setText(commentRow.getUsername());
            commentText.setText(commentRow.getComment());
        }
        public CommentRow getCommentRow() {
            return commentRow;
        }
    }

    public void addComment(CommentRow myComment){
        commentRow.add(myComment);
    }

    public void addCommentAtFirst(CommentRow myComment){
        commentRow.add(0,myComment);
    }
}
