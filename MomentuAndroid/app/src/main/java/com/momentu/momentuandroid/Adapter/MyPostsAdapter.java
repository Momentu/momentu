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
import com.momentu.momentuandroid.PostsOfYouActivity;
import com.momentu.momentuandroid.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Fahad on 2/25/18.
 */

public class MyPostsAdapter extends BaseAdapter {

    private LayoutInflater layoutinflater;
    private List<MyPostsItem> listOfMyPost;
    private Context context;

    public MyPostsAdapter(Context context, List<MyPostsItem> customizedListView) {
        this.context = context;
        layoutinflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listOfMyPost = customizedListView;
    }


    @Override
    public int getCount() {
        return listOfMyPost.size();
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
        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutinflater.inflate(R.layout.my_post_item, parent, false);
            listViewHolder.textInListView = (TextView)convertView.findViewById(R.id.hashtagLabel);
            listViewHolder.imageInListView = (ImageView)convertView.findViewById(R.id.imageView);
            listViewHolder.imageButtonDelete = (ImageButton) convertView.findViewById(R.id.imageButtonDelete);
            listViewHolder.imageButtonChat = (ImageButton) convertView.findViewById(R.id.imageButtonChat);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.textInListView.setText(listOfMyPost.get(position).getHashtagLabel());
        Picasso.with(context)
                .load(Uri.parse(listOfMyPost.get(position).getThumbnailUrl()))
                .into(listViewHolder.imageInListView);

        setupClickableViews(convertView,listViewHolder,listOfMyPost.get(position),position);
        return convertView;
    }

    private void setupClickableViews(final View view, final ViewHolder listViewHolder, final MyPostsItem myPostsItem, final int position) {

        listViewHolder.imageInListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof PostsOfYouActivity) {
                    ((PostsOfYouActivity) context).goToMediaActivity(myPostsItem.getMediaType(),myPostsItem.getOriginalUrl());
                }
            }
        });

        listViewHolder.textInListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof PostsOfYouActivity) {
                    ((PostsOfYouActivity) context).goToFeedActivity(myPostsItem.getState(),myPostsItem.getCity(),myPostsItem.getHashtagLabel());
                }
            }
        });

        listViewHolder.imageButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof PostsOfYouActivity) {
                    ((PostsOfYouActivity) context).deletePost(position,myPostsItem.getMediaId());
                }
            }
        });

        listViewHolder.imageButtonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof PostsOfYouActivity) {
                    ((PostsOfYouActivity) context).goToCommentsActivity(position, myPostsItem.getOriginalUrl(),myPostsItem.getThumbnailUrl(),myPostsItem.getMediaType(),myPostsItem.getMediaId(),myPostsItem.getLikesCount(), myPostsItem.getHashtagLabel());
                }
            }
        });
    }


    static class ViewHolder{
        TextView textInListView;
        ImageView imageInListView;
        ImageButton imageButtonDelete;
        ImageButton imageButtonChat;
    }
}
