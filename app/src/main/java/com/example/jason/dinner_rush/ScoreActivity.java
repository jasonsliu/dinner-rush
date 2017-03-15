package com.example.jason.dinner_rush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    public static final String TAG = "ScoreActivity";
    TextView scoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        scoreDisplay = (TextView) findViewById(R.id.cur_score_display);
        int cur_score = getIntent().getIntExtra("score", 0);
        scoreDisplay.setText(String.valueOf(cur_score));
        // Retrieve the global score somehow with server
    }

    public void returnToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
