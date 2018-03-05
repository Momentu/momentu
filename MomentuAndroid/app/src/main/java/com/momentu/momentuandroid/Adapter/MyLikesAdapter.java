package com.momentu.momentuandroid.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.momentu.momentuandroid.Model.MyPostsItem;
import com.momentu.momentuandroid.Model.PostsYouHaveLikedItem;
import com.momentu.momentuandroid.PostsYouHaveLikedActivity;
import com.momentu.momentuandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Fahad on 2/25/18.
 */

public class MyLikesAdapter extends BaseAdapter {

    private LayoutInflater layoutinflater;
    private List<MyPostsItem> listOfMyLikes;
    private Context context;

    public MyLikesAdapter(Context context, List<MyPostsItem> customizedListView) {
        this.context = context;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listOfMyLikes = customizedListView;
    }


    @Override
    public int getCount() {
        return listOfMyLikes.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyLikesAdapter.ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new MyLikesAdapter.ViewHolder();
            convertView = layoutinflater.inflate(R.layout.posts_you_have_like_item, parent, false);
            listViewHolder.textInListView = (TextView)convertView.findViewById(R.id.hashtagLabel);
            listViewHolder.imageInListView = (ImageView)convertView.findViewById(R.id.imageView);
            listViewHolder.imageButtonChat = (ImageButton) convertView.findViewById(R.id.imageButtonChat);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (MyLikesAdapter.ViewHolder)convertView.getTag();
        }
        listViewHolder.textInListView.setText(listOfMyLikes.get(position).getHashtagLabel());

        Picasso.with(context)
                .load(Uri.parse(listOfMyLikes.get(position).getThumbnailUrl()))
                .into(listViewHolder.imageInListView);

        setupClickableViews(convertView, listViewHolder, listOfMyLikes.get(position), position);
        return convertView;
    }


    private void setupClickableViews(final View view, final ViewHolder listViewHolder, final MyPostsItem postsYouHaveLikedItem, final int position) {

        listViewHolder.imageInListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof PostsYouHaveLikedActivity) {
                    ((PostsYouHaveLikedActivity) context).goToMediaActivity(postsYouHaveLikedItem.getMediaType(),postsYouHaveLikedItem.getOriginalUrl());
                }
            }
        });

        listViewHolder.textInListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof PostsYouHaveLikedActivity) {
                    ((PostsYouHaveLikedActivity) context).goToFeedActivity(postsYouHaveLikedItem.getState(),postsYouHaveLikedItem.getCity(),postsYouHaveLikedItem.getHashtagLabel());
                }
            }
        });

        listViewHolder.imageButtonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof PostsYouHaveLikedActivity) {
                    ((PostsYouHaveLikedActivity) context).goToCommentsActivity(position, postsYouHaveLikedItem.getOriginalUrl(),postsYouHaveLikedItem.getThumbnailUrl(),postsYouHaveLikedItem.getMediaType(),postsYouHaveLikedItem.getMediaId(),postsYouHaveLikedItem.getLikesCount(), postsYouHaveLikedItem.getHashtagLabel());
                }
            }
        });
    }

    static class ViewHolder{
        TextView textInListView;
        ImageView imageInListView;
        ImageButton imageButtonChat;
    }
}
