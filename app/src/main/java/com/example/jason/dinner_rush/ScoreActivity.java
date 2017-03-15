package com.example.jason.dinner_rush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ScoreActivity extends AppCompatActivity {

    public static final String TAG = "ScoreActivity";
    public static final String BASE_URL = "http://52.33.78.1:8080/static/";
    TextView mScoreDisplay;
    TextView mGlobalScoreDisplay;
    int myScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        myScore = getIntent().getIntExtra("score", 0);

        mScoreDisplay = (TextView) findViewById(R.id.cur_score_display);
        mScoreDisplay.setText(String.valueOf(myScore));
        mGlobalScoreDisplay = (TextView) findViewById(R.id.global_score_label_display);

        retrieveGlobalScore();
    }

    public void retrieveGlobalScore() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = BASE_URL + String.valueOf(myScore);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Server response: " + response);
                        mGlobalScoreDisplay.setText("GLOBAL HIGH SCORE: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "ERROR FOR VOLLEY RESPONSE");
                Log.d(TAG, error.getMessage());
                mGlobalScoreDisplay.setText("Global High Score: --");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void returnToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
