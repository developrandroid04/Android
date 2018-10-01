package com.example.himanshujain.taranpanth;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubePlayerActivity extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    Button button;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    String videoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        videoID = getIntent().getStringExtra("VIDEO_ID");
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);


        onInitializedListener = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.loadVideo(videoID);

                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        showVideo();



    }

    public void showVideo() {
        youTubePlayerView.initialize("AIzaSyAC0yw2mxXKZJRBE4xiabFnN2-MG-anOd8", onInitializedListener);

    }

}
