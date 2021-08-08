package com.selfLearn.SELF_LEARN;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


import android.os.Bundle;

public class VideoPlayerActivity extends AppCompatActivity {
    String video_id = "vG2PNdI8axo";

    AbstractYouTubePlayerListener youtubePlayerListener;
    YouTubePlayerView youTubePlayerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_video_player);
        // below line of code is
        // to hide our action bar.
        getSupportActionBar().hide();
        video_id = getIntent().getStringExtra("videoId");
        Log.d("Video ID", "Video to play is : \t" + video_id);
        // below two lines are used to set our
        // screen orientation in landscape mode.

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);





        // declaring variable for youtubeplayer view
         youTubePlayerView = findViewById(R.id.videoPlayer);


        // here we are adding observer to our youtubeplayerview.
        getLifecycle().addObserver(youTubePlayerView);


        youTubePlayerView.getPlayerUiController();


        youTubePlayerView.enterFullScreen();




        youTubePlayerView.addYouTubePlayerListener( new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                // loading the selected video into the YouTube Player
                youTubePlayer.loadVideo(video_id, 0);
            }

            @Override
            public void onStateChange(@NonNull YouTubePlayer youTubePlayer,
                                      @NonNull PlayerConstants.PlayerState state) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state);
            }
        });

//        youtubePlayerListener  = new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                // loading the selected video into the YouTube Player
//                youTubePlayer.loadVideo(video_id, 0);
//            }
//
//            @Override
//            public void onStateChange(@NonNull YouTubePlayer youTubePlayer,
//                                      @NonNull PlayerConstants.PlayerState state) {
//                // this method is called if video has ended,
//                super.onStateChange(youTubePlayer, state);
//            }
//        };

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(youTubePlayerView);


    }
}