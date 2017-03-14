package com.example.jason.dinner_rush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Need to keep track of the countdown
    private static int mCountdown = 4;
    ViewGroup mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContentView = (ViewGroup) findViewById(R.id.activity_main);

        // Return to fullscreen with tap
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToFullScreen();
            }
        });
    }

    public void playButtonPress(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void aboutButtonPress(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void connectCountdown(View view) {
        final TextView helloWorld = (TextView) findViewById(R.id.header);
        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switch (mCountdown) {
                                    case 4:
                                        helloWorld.setText("Connecting...");
                                        mCountdown--;
                                        break;
                                    case 3:
                                        helloWorld.setText("Ready...");
                                        mCountdown--;
                                        break;
                                    case 2:
                                        helloWorld.setText("Set...");
                                        mCountdown--;
                                        break;
                                    case 1:
                                        helloWorld.setText("Go!");
                                        mCountdown--;
                                        break;
                                    case 0:
                                        helloWorld.setText("Place the order here");
                                        break;
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();

    }

    private void setToFullScreen() {
        mContentView.setSystemUiVisibility((View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION));
    }

    @Override
    public void onResume() {
        super.onResume();
        setToFullScreen();
    }
}
