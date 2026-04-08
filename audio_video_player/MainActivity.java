package com.example.audiovideofile;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.MediaController;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loadAudio = findViewById(R.id.loadAudio);
        Button playVideo = findViewById(R.id.playVideo);
        Button play = findViewById(R.id.play);
        Button pause = findViewById(R.id.pause);
        Button stop = findViewById(R.id.stop);
        Button restart = findViewById(R.id.restart);
        EditText urlInput = findViewById(R.id.urlInput);
        videoView = findViewById(R.id.videoView);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);

        loadAudio.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            mediaPlayer = MediaPlayer.create(this, R.raw.song);
            if (mediaPlayer != null) {
                mediaPlayer.start();
                Toast.makeText(this, "Playing Audio", Toast.LENGTH_SHORT).show();
            }
        });

        playVideo.setOnClickListener(v -> {
            String url = urlInput.getText().toString().trim();

            if (url.isEmpty()) {
                Toast.makeText(this, "Enter URL", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri uri = Uri.parse(url);
            videoView.setVideoURI(uri);

            videoView.setOnPreparedListener(mp -> videoView.start());

            videoView.setOnErrorListener((mp, what, extra) -> {
                Toast.makeText(this, "Video can't play", Toast.LENGTH_SHORT).show();
                return true;
            });
        });

        play.setOnClickListener(v -> {
            if (mediaPlayer != null) mediaPlayer.start();
            if (!videoView.isPlaying()) videoView.start();
        });

        pause.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) mediaPlayer.pause();
            if (videoView.isPlaying()) videoView.pause();
        });

        stop.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(this, R.raw.song);
            }
            if (videoView.isPlaying()) {
                videoView.stopPlayback();
            }
        });

        restart.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
            if (videoView != null) {
                videoView.seekTo(0);
                videoView.start();
            }
        });
    }
}