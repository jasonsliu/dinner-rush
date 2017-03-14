package com.example.jason.dinner_rush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreDisplay = (TextView) findViewById(R.id.time_display);
    }
}
