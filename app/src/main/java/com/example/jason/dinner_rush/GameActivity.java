package com.example.jason.dinner_rush;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jason.dinner_rush.Ingredients.Avocado;
import com.example.jason.dinner_rush.Ingredients.Bacon;
import com.example.jason.dinner_rush.Ingredients.Carrot;
import com.example.jason.dinner_rush.Ingredients.Corn;
import com.example.jason.dinner_rush.Ingredients.Ingredient;
import com.example.jason.dinner_rush.Ingredients.Lettuce;
import com.example.jason.dinner_rush.Ingredients.Tomato;

public class GameActivity extends AppCompatActivity {

    public static final long SECONDS_PER_GAME = 60;
    public static final String TAG = "GameActivity";

    private ViewGroup mContentView;
    CountDownTimer mTimer;
    TextView timeDisplay;
    TextView scoreDisplay;
    ImageView INGREDIENT_PLACEHOLDER;
    TextView mOrderTextView;
    P1InventoryFragment mFrag = new P1InventoryFragment();

    Ingredient.IngredientListener mIngredientListener;
    Order.OrderListener mOrderListener;

    private Ingredient mCurrIngredient;
    private Order mCurrOrder;
    private int mScore = 0;
    private Inventory mInventory;

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

        LoadViews();
        InitListeners();
        startGame();
    }

    private void InitListeners() {
        mIngredientListener = new Ingredient.IngredientListener() {
            @Override
            public void isFinished(Ingredient ingredient) {
                mCurrOrder.addIngredient(ingredient);

                putIngredient(null);
            }
        };

        mOrderListener = new Order.OrderListener() {
            @Override
            public void finishedOrder(int pointsEarned) {
                updateScore(pointsEarned);
                mCurrOrder = new Order(GameActivity.this, mOrderTextView, mOrderListener);
            }

            @Override
            public void botchedOrder(int pointPenalty) {
                updateScore(-pointPenalty);
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
            Log.d(TAG, "Replacing old ingredient!");
            mContentView.removeView(mCurrIngredient);
            mCurrIngredient.setInactive();
        }

        mCurrIngredient = ing;
        if (ing != null) {
            mContentView.addView(ing);
        }
    }

    private void startGame() {
        mTimer.start();
        mInventory = new p1Inventory(GameActivity.this); // TODO: 3/12/2017 Choose appropriate inventory based on player 
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

    public void inventoryButtonPress(View view) {
        if (!mFrag.isVisible()) {
            getFragmentManager().beginTransaction()
                    .add(R.id.inventory_container, mFrag)
                    .commit();
            findViewById(R.id.inventory_container).bringToFront();
        } else {
            getFragmentManager().beginTransaction()
                    .remove(mFrag)
                    .commit();
        }
    }

    public void getIngredientButtonPress(View view) {
        Ingredient getThis = null;

        switch(view.getId()) {
            case R.id.getCarrotButton:
                getThis = new Carrot(GameActivity.this);
                break;
            case R.id.getAvocadoButton:
                getThis = new Avocado(GameActivity.this);
                break;
            case R.id.getBaconButton:
                getThis = new Bacon(GameActivity.this);
                break;
            case R.id.getCornButton:
                getThis = new Corn(GameActivity.this);
                break;
            case R.id.getLettuceButton:
                getThis = new Lettuce(GameActivity.this);
                break;
            case R.id.getTomatoButton:
                getThis = new Tomato(GameActivity.this);
                break;
        }

        if (getThis == null) {
            // this should never happen
            Exception e = new Exception();
            e.printStackTrace();
        }

        Ingredient received = mInventory.getIngredient(getThis);
        if (received != null) {
            Log.d(TAG, "Got a " + received.getName());
            received.setUpForUse(INGREDIENT_PLACEHOLDER, mIngredientListener);
            putIngredient(received);
            inventoryButtonPress(null);
        }
    }

    public void sendIngredientButtonPress(View view) {
        switch(view.getId()) {
            case R.id.sendCarrotButton:
                // inventory.getIngredient(INGREDIENT.CLASS)
                break;
            case R.id.sendAvocadoButton:
                break;
            case R.id.sendBaconButton:
                Log.d(TAG, "bancn");
                break;
            case R.id.sendCornButton:
                break;
            case R.id.sendLettuceButton:
                break;
            case R.id.sendTomatoButton:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setToFullScreen();
    }
}
