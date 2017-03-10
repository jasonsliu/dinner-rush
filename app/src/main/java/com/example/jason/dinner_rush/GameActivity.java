package com.example.jason.dinner_rush;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jason.dinner_rush.Ingredients.Carrot;
import com.example.jason.dinner_rush.Ingredients.Ingredient;

public class GameActivity extends AppCompatActivity {

    private ViewGroup mContentView;
    CountDownTimer mTimer;
    public static final long SECONDS_PER_GAME = 60;
    TextView timeDisplay;
    public static ImageView INGREDIENT_PLACEHOLDER;
    Ingredient.IngredientListener mIngredientListener;

    Ingredient mCurrIngredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mContentView = (ViewGroup) findViewById(R.id.activity_game);

        // Return to fullscreen with tap
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToFullScreen();
            }
        });

        timeDisplay = (TextView) findViewById(R.id.time_display);
        mTimer = new CountDownTimer(SECONDS_PER_GAME*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeDisplay.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                timeDisplay.setText(String.valueOf(0));
                gameOver();
            }
        };

        INGREDIENT_PLACEHOLDER = (ImageView) findViewById(R.id.ingredient_placeholder);

        mIngredientListener = new Ingredient.IngredientListener() {
            @Override
            public void isFinished(Ingredient ingredient) {
                Log.d("oh", "Chopped!");
                Carrot c = new Carrot(GameActivity.this, INGREDIENT_PLACEHOLDER, mIngredientListener);
                putIngredient(c);
            }
        };

        mContentView.setOnTouchListener(new View.OnTouchListener() {
            boolean touched;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Carrot c = new Carrot(GameActivity.this, INGREDIENT_PLACEHOLDER, mIngredientListener);
                    Log.d("test", String.valueOf(event.getX()));
                    Log.d("test", String.valueOf(event.getY()));

                    if(!touched) {
                        mContentView.addView(c);
                        mCurrIngredient = c;
                    }
                    touched = true;
                }
                return false;
            }
        });

        startGame();
    }

    public void putIngredient(Ingredient ing) {
        if (mCurrIngredient != null) {
            // replace old ingredient
            mContentView.removeView(mCurrIngredient);
            mCurrIngredient.setInactive();
        }
        mCurrIngredient = ing;
        mContentView.addView(ing);
    }

    private void startGame() {
        // TODO: 3/8/2017 Implement this.
        mTimer.start();
    }

    private void gameOver() {
        // TODO: 3/8/2017 Implement this.
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
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }
}
