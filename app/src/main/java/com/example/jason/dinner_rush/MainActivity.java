package com.example.jason.dinner_rush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

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
}
