package com.example.himanshujain.taranpanth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment implements YouTubeListAdapter.YoutubeListClickListener {

    public static final String TAG = "VideoFragment";

    private View mRootView;
    private Activity mActivity;
    List<YoutubeItems> mVideoListItems = new ArrayList<>();
    LayoutInflater mInflater;
    public static final String URL = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyAU9s7aHjvJj4mVlJGcZoGKTvlpWzL2hxY&channelId=UCpEqIbBt-f4V261KD4K3pVg&part=snippet,id&order=date&maxResults=20";

    public VideoFragment() {
        // empty
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_youtube_video, container, false);
        mInflater = inflater;
        mActivity = getActivity();
        setVideoListAdapter();
        mRootView.findViewById(R.id.circularProgressbar).setVisibility(View.VISIBLE);

        makeVideoRequest();

        return mRootView;
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    YouTubeListAdapter mYouTubeListAdapter;

    public void setVideoListAdapter() {

        mYouTubeListAdapter = new YouTubeListAdapter(getActivity(), mVideoListItems, mInflater, this);
        ((RecyclerView) mRootView.findViewById(R.id.videoListview)).setAdapter(mYouTubeListAdapter);
        ((RecyclerView) mRootView.findViewById(R.id.videoListview)).setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    public void makeVideoRequest() {

        JsonObjectRequest req = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
mRootView.findViewById(R.id.circularProgressbar).setVisibility(View.GONE);
                            JSONArray itemArray = response.getJSONArray("items");

                            for (int i = 0; i < itemArray.length(); i++) {
                                String videoId = "";
                                String imageUrl;
                                String channelID;
                                String description;
                                String title;
                                String publishAt;
                                JSONObject itemObject = itemArray.getJSONObject(i);
                                if (itemObject != null && itemObject.has("id")) {
                                    JSONObject IdObject = itemObject.getJSONObject("id");

                                    if (IdObject != null && IdObject.has("videoId")) {
                                        videoId = IdObject.getString("videoId");


                                        JSONObject snippetObject = itemObject.getJSONObject("snippet");
                                        title = snippetObject.getString("title");
                                        channelID = snippetObject.getString("channelId");
                                        publishAt = snippetObject.getString("publishedAt");
                                        description = snippetObject.getString("description");

                                        JSONObject imageObject = snippetObject.getJSONObject("thumbnails");

                                        JSONObject mediumObject = imageObject.getJSONObject("medium");
                                        imageUrl = mediumObject.getString("url");
                                        YoutubeItems youtubeItems = new YoutubeItems(videoId, imageUrl, channelID, description, title, publishAt);
                                        mVideoListItems.add(youtubeItems);
                                    }
                                }


                            }
                            mRootView.findViewById(R.id.circularProgressbar).setVisibility(View.GONE);

                            mYouTubeListAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                mRootView.findViewById(R.id.circularProgressbar).setVisibility(View.GONE);
            }
        });

        // Adding request to request queue
        TaranPanthApp.getInstance().addToRequestQueue(req);
    }

    @Override
    public void onClick(YoutubeItems youtubeItems) {
        Intent intent = new Intent(getActivity(), YoutubePlayerActivity.class);
        intent.putExtra("VIDEO_ID", youtubeItems.getVideoId());
        startActivity(intent);
    }


}


