package com.example.himanshujain.taranpanth;

/**
 * Created by himanshu jain on 29/07/15.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


import java.util.Collections;
import java.util.List;


public class YouTubeListAdapter extends RecyclerView.Adapter<YouTubeListAdapter.ViewHolder> {
    List<YoutubeItems> mVideoListItems = Collections.emptyList();
    private LayoutInflater inflater;
    private Context mContext;
    private YoutubeListClickListener mListener;
    ImageLoader imageLoader = TaranPanthApp.getInstance().getImageLoader();

    public YouTubeListAdapter(Context context, List<YoutubeItems> data, LayoutInflater inflater, YoutubeListClickListener cartItemClickListener) {
        this.mContext = context;
        this.inflater = inflater;
        this.mVideoListItems = data;
        mListener = cartItemClickListener;

    }

    public void delete(int position) {
        mVideoListItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.youtube_video_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final YoutubeItems youtubeItems = mVideoListItems.get(position);
        holder.videoName.setText(youtubeItems.getTitle());

        int index = youtubeItems.getPublishingDate().indexOf("T");
        int difference = Utils.getDaysDifference(youtubeItems.getPublishingDate().substring(0, index));
        holder.videoDate.setText(difference + " Days ago");
        holder.description.setText(youtubeItems.getDescription());
        if (imageLoader == null)
            imageLoader = TaranPanthApp.getInstance().getImageLoader();
        holder.mImageView.setImageUrl(youtubeItems.getImageUrl(), imageLoader);
        holder.containerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(youtubeItems);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mVideoListItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView videoName;
        TextView videoDate;
        TextView description;
        NetworkImageView mImageView;
        LinearLayout containerLayout;


        public ViewHolder(View itemView) {
            super(itemView);
            videoName = (TextView) itemView.findViewById(R.id.tv_video_name);
            videoDate = (TextView) itemView.findViewById(R.id.tv_date);
            description = (TextView) itemView.findViewById(R.id.tv_description);
            mImageView = (NetworkImageView) itemView.findViewById(R.id.networkImageView);
            containerLayout = (LinearLayout) itemView.findViewById(R.id.container_layout);

        }
    }


    public interface YoutubeListClickListener {
          void onClick(YoutubeItems youtubeItems);
    }

    public void hideKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
