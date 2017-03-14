package com.example.jason.dinner_rush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Need to keep track of the countdown
    private static int mCountdown = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void connectButtonPress(View view) {
        Intent intent = new Intent(this, ConnectActivity.class);
        startActivity(intent);
    }

    public void playButtonPress(View view) {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

    public void tradeButtonPress(View view) {
        Intent intent = new Intent(this, GameActivity.class);
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

}
