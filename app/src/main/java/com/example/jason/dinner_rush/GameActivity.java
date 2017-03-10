package com.example.jason.dinner_rush;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jason.dinner_rush.Ingredients.Avocado;
import com.example.jason.dinner_rush.Ingredients.Bacon;
import com.example.jason.dinner_rush.Ingredients.Carrot;
import com.example.jason.dinner_rush.Ingredients.Tomato;
import com.example.jason.dinner_rush.Ingredients.Lettuce;
import com.example.jason.dinner_rush.Ingredients.Corn;
import com.example.jason.dinner_rush.Ingredients.Ingredient;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    public static final long SECONDS_PER_GAME = 60;

    private ViewGroup mContentView;
    CountDownTimer mTimer;
    TextView timeDisplay;
    TextView scoreDisplay;
    ImageView INGREDIENT_PLACEHOLDER;
    TextView mOrderTextView;

    Ingredient.IngredientListener mIngredientListener;
    Order.OrderListener mOrderListener;

    private Ingredient mCurrIngredient;
    private Order mCurrOrder;
    private int mScore = 0;

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

        LoadViews();
        InitListeners();
        startGame();
    }

    private void InitListeners() {
        mIngredientListener = new Ingredient.IngredientListener() {
            @Override
            public void isFinished(Ingredient ingredient) {
                Log.d("oh", "Chopped!");
                mCurrOrder.addIngredient(ingredient);
                Random random = new Random();

                switch(random.nextInt()%6) {
                    case 0:
                        putIngredient(new Carrot(GameActivity.this, INGREDIENT_PLACEHOLDER, mIngredientListener));
                        break;
                    case 1:
                        putIngredient(new Tomato(GameActivity.this, INGREDIENT_PLACEHOLDER, mIngredientListener));
                        break;
                    case 2:
                        putIngredient(new Lettuce(GameActivity.this, INGREDIENT_PLACEHOLDER, mIngredientListener));
                        break;
                    case 3:
                        putIngredient(new Corn(GameActivity.this, INGREDIENT_PLACEHOLDER, mIngredientListener));
                        break;
                    case 4:
                        putIngredient(new Avocado(GameActivity.this, INGREDIENT_PLACEHOLDER, mIngredientListener));
                        break;
                    case 5:
                        putIngredient(new Bacon(GameActivity.this, INGREDIENT_PLACEHOLDER, mIngredientListener));
                        break;
                }

            }
        };

        mOrderListener = new Order.OrderListener() {
            @Override
            public void finishedOrder(int pointsEarned) {
                updateScore(pointsEarned);
                mCurrOrder = new Order(GameActivity.this, mOrderTextView, mOrderListener);
            }
        };
    }

    private void LoadViews() {
        timeDisplay = (TextView) findViewById(R.id.time_display);
        scoreDisplay = (TextView) findViewById(R.id.score_display);
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
        mOrderTextView = (TextView) findViewById(R.id.order);
    }

    private void putIngredient(Ingredient ing) {
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
        mCurrOrder = new Order(GameActivity.this, mOrderTextView, mOrderListener);
        scoreDisplay.setText("0");
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

    private void updateScore(int numPointsToAdd) {
        mScore += numPointsToAdd;
        scoreDisplay.setText(String.valueOf(mScore));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }
}
