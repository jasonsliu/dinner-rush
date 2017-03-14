package com.example.jason.dinner_rush.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.jason.dinner_rush.R;

/**
 * Created by jason on 3/14/2017.
 */

public class SoundHelper {

    private MediaPlayer mPlayer;

    public void preparePlayer(Context context) {
        mPlayer = MediaPlayer.create(context.getApplicationContext(), R.raw.crossing);
        mPlayer.setVolume(0.5f, 0.5f);
        mPlayer.setLooping(true);
    }

    public void playMusic() {
        if (mPlayer != null) {
            mPlayer.start();
        }
    }

    public void pauseMusic() {
        if (mPlayer != null && mPlayer.isPlaying()) {
            mPlayer.pause();
        }
    }
}
