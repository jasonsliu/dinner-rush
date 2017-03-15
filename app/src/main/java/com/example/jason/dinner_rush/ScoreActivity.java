package com.example.jason.dinner_rush;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    TextView mScoreDisplay;
    TextView mGlobalScoreDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        mScoreDisplay = (TextView) findViewById(R.id.cur_score_display);
        mGlobalScoreDisplay = (TextView) findViewById(R.id.global_score_label_display);
        int cur_score = getIntent().getIntExtra("score", 0);
        mScoreDisplay.setText(String.valueOf(cur_score));
        // Retrieve the global score somehow with server
        retrieveGlobalScore();
    }

    public void returnToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void retrieveGlobalScore() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://52.33.78.1:8080/static/0";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 4 characters of the string
                        mGlobalScoreDisplay.append(response.substring(0,3));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mGlobalScoreDisplay.setText("Global High Score: --");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}
