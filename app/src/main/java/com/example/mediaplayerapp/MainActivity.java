package com.example.mediaplayerapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_AUDIO_REQUEST = 1;
    private MediaPlayer mediaPlayer;
    private Uri currentAudioUri;

    private TextView trackName, timeDisplay;
    private SeekBar seekBar;
    private Button buttonPick, buttonPlay, buttonPause, buttonLoop, buttonShuffle;

    private boolean isLooping = false;
    private boolean isShuffle = false;
    private final Handler handler = new Handler();
    private final Runnable updateSeekbar = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                timeDisplay.setText(formatTime(mediaPlayer.getCurrentPosition()) +
                        " / " + formatTime(mediaPlayer.getDuration()));
                handler.postDelayed(this, 500);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI References
        trackName = findViewById(R.id.trackName);
        timeDisplay = findViewById(R.id.timeDisplay);
        seekBar = findViewById(R.id.seekBar);
        buttonPick = findViewById(R.id.buttonPick);
        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPause = findViewById(R.id.buttonPause);
        buttonLoop = findViewById(R.id.buttonLoop);
        buttonShuffle = findViewById(R.id.buttonShuffle);

        buttonPick.setOnClickListener(v -> pickAudioFile());

        buttonPlay.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.start();
                handler.post(updateSeekbar);
            }
        });

        buttonPause.setOnClickListener(v -> {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        });

        buttonLoop.setOnClickListener(v -> {
            isLooping = !isLooping;
            if (mediaPlayer != null) {
                mediaPlayer.setLooping(isLooping);
            }
            buttonLoop.setText(isLooping ? "Loop ON" : "Loop OFF");
        });

        buttonShuffle.setOnClickListener(v -> {
            isShuffle = !isShuffle;
            buttonShuffle.setText(isShuffle ? "Shuffle ON" : "Shuffle OFF");
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer != null) {
                    mediaPlayer.seekTo(progress);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void pickAudioFile() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("audio/*");
        startActivityForResult(intent, PICK_AUDIO_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri audioUri = data.getData();
            if (audioUri != null) {
                currentAudioUri = audioUri;
                playAudio(audioUri);
            }
        }
    }

    private void playAudio(Uri uri) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(this, uri);
            mediaPlayer.prepare();
            mediaPlayer.start();

            seekBar.setMax(mediaPlayer.getDuration());
            trackName.setText("Now Playing: " + uri.getLastPathSegment());
            handler.post(updateSeekbar);

            mediaPlayer.setOnCompletionListener(mp -> {
                if (isShuffle) {
                    pickAudioFile(); // simulate shuffle
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error loading audio", Toast.LENGTH_SHORT).show();
        }
    }

    private String formatTime(int millis) {
        int mins = (millis / 1000) / 60;
        int secs = (millis / 1000) % 60;
        return String.format("%02d:%02d", mins, secs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            handler.removeCallbacks(updateSeekbar);
            mediaPlayer.release();
        }
    }
}
