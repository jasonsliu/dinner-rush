package com.example.jason.dinner_rush;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.net.nsd.NsdServiceInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jason.dinner_rush.Ingredients.Avocado;
import com.example.jason.dinner_rush.Ingredients.Bacon;
import com.example.jason.dinner_rush.Ingredients.Carrot;
import com.example.jason.dinner_rush.Ingredients.Corn;
import com.example.jason.dinner_rush.Ingredients.Ingredient;
import com.example.jason.dinner_rush.Ingredients.Lettuce;
import com.example.jason.dinner_rush.Ingredients.Tomato;
import com.example.jason.dinner_rush.inventory.Inventory;
import com.example.jason.dinner_rush.inventory.P1Inventory;
import com.example.jason.dinner_rush.inventory.P1InventoryFragment;
import com.example.jason.dinner_rush.inventory.P2Inventory;
import com.example.jason.dinner_rush.inventory.P2InventoryFragment;
import com.example.jason.dinner_rush.utils.NsdHelper;
import com.example.jason.dinner_rush.utils.SoundHelper;

public class GameActivity extends AppCompatActivity {

    public static final int SECONDS_PER_GAME = 120;
    public static final int POINTS_PER_CUT = 1;
    public static final String TAG = "GameActivity";
    public static final String START_MSG = "start";

    private ViewGroup mContentView;
    private MediaPlayer mDingPlayer;
    CountDownTimer mTimer;
    TextView timeDisplay;
    TextView scoreDisplay;
    ImageView INGREDIENT_PLACEHOLDER;
    TextView orderTextView;
    Fragment mFrag;

    Ingredient.IngredientListener mIngredientListener;
    Order.OrderListener mOrderListener;
    DataConnection.ConnectionListener mConnectionListener;
    private static Handler mUpdateHandler;
    private Handler mAutoConnectHandler = new Handler();

    DataConnection mConnection;
    NsdHelper mNsdHelper;
    SoundHelper mSoundHelper = new SoundHelper();

    private Ingredient mCurrIngredient;
    private Order mCurrOrder;
    private int mScore = 0;
    private boolean isPlayer1;
    private boolean gameRunning;
    private Inventory mInventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mContentView = (ViewGroup) findViewById(R.id.activity_game);
        mDingPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ding);

        // Return to fullscreen with tap
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToFullScreen();
            }
        });

        mSoundHelper.preparePlayer(this);
        LoadViews();
        InitListeners();
    }

    private void countDown() {
        final int numTicks = 4;

        if (gameRunning) {
            return;
        }

        if (isPlayer1) {
            mSoundHelper.playMusic();
        }

        CountDownTimer t = new CountDownTimer(numTicks*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                switch ((int)millisUntilFinished / 1000) {
                    case 3:
                        orderTextView.setText("Ready...");
                        break;
                    case 2:
                        orderTextView.setText("Set...");
                        break;
                    case 1:
                        orderTextView.setText("Go!");
                        break;
                }
            }

            @Override
            public void onFinish() {
                startGame();
            }
        };
        t.start();
    }

    private void startGame() {
        if (!gameRunning) {
            Log.e(TAG, "STARTING GAME");

            mTimer.start();
            if (isPlayer1) {
                mInventory = new P1Inventory(GameActivity.this);
                mFrag = new P1InventoryFragment();
            } else {
                mInventory = new P2Inventory(GameActivity.this);
                mFrag = new P2InventoryFragment();
            }
            mCurrOrder = new Order(GameActivity.this, orderTextView, mOrderListener);
            scoreDisplay.setText("0");
            gameRunning = true;
        }
    }

    private void endGame() {
        Log.e(TAG, "Game ended!");
        gameRunning = false;
        if (mFrag.isVisible()) {
            getFragmentManager().beginTransaction()
                    .remove(mFrag)
                    .commit();
        }
        orderTextView.setText("");
        putIngredient(null);
        mSoundHelper.pauseMusic();
        Toast toast = Toast.makeText(getApplicationContext(), "Game over! Score: " + mScore, Toast.LENGTH_LONG);
        toast.show();
    }

    private void putIngredient(Ingredient ing) {
        if (mCurrIngredient != null) {
            // replace old ingredient
            mContentView.removeView(mCurrIngredient);
            mCurrIngredient.setInactive();
        }

        mCurrIngredient = ing;
        if (ing != null) {
            ing.setUpForUse(INGREDIENT_PLACEHOLDER, mIngredientListener);
            mContentView.addView(ing);
        }
    }

    private void updateScore(int numPointsToAdd) {
        mScore += numPointsToAdd;
        scoreDisplay.setText(String.valueOf(mScore));
        mConnection.sendMessage(String.valueOf(numPointsToAdd)); // send score to other team
    }

    public void inventoryButtonPress(View view) {
        // do nothing if game is not running
        if (!gameRunning) return;

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
                getThis = new Avocado(this);
                break;
            case R.id.getBaconButton:
                getThis = new Bacon(this);
                break;
            case R.id.getCornButton:
                getThis = new Corn(this);
                break;
            case R.id.getLettuceButton:
                getThis = new Lettuce(this);
                break;
            case R.id.getTomatoButton:
                getThis = new Tomato(this);
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
            putIngredient(received);
            inventoryButtonPress(null);
        }
    }

    public void sendIngredientButtonPress(View view) {
        Ingredient ing = null;
        switch(view.getId()) {
            case R.id.sendCarrotButton:
                ing = new Carrot(this);
                break;
            case R.id.sendAvocadoButton:
                ing = new Avocado(this);
                break;
            case R.id.sendBaconButton:
                ing = new Bacon(this);
                break;
            case R.id.sendCornButton:
                ing = new Corn(this);
                break;
            case R.id.sendLettuceButton:
                ing = new Lettuce(this);
                break;
            case R.id.sendTomatoButton:
                ing = new Tomato(this);
                break;
        }
        if (ing != null) {
            mConnection.sendMessage(ing.getName());
            inventoryButtonPress(null);
        }
    }

    private void autoConnect() {
        // Register service
        if(mConnection.getLocalPort() > -1) {
            mNsdHelper.registerService(mConnection.getLocalPort());
        } else {
            Log.d(TAG, "ServerSocket isn't bound.");
        }

        mAutoConnectHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                NsdServiceInfo service = mNsdHelper.getChosenServiceInfo();
                if(service == null) {
                    mNsdHelper.discoverServices();
                    mAutoConnectHandler.postDelayed(this, 3000);
                } else {
                    Log.d(TAG, "Connecting.");
                    mConnection.connectToServer(service.getHost(),
                            service.getPort());
                    isPlayer1 = true;
                    Log.e(TAG, "I am player 1 !");
                }
            }
        }, 1);
    }

    private void InitListeners() {
        mIngredientListener = new Ingredient.IngredientListener() {
            @Override
            public void isFinished(Ingredient ingredient) {
                mCurrOrder.addIngredient(ingredient);
                updateScore(POINTS_PER_CUT);
                putIngredient(null);
            }
        };

        mOrderListener = new Order.OrderListener() {
            @Override
            public void finishedOrder(int pointsEarned) {
                updateScore(pointsEarned);
                mCurrOrder = new Order(GameActivity.this, orderTextView, mOrderListener);
            }

            @Override
            public void botchedOrder(int pointPenalty) {
                updateScore(-pointPenalty);
            }
        };

        mConnectionListener = new DataConnection.ConnectionListener() {
            @Override
            public void stopAutoDiscovery() {
                Log.d(TAG, "Stopping auto-discovery.");
                mAutoConnectHandler.removeCallbacksAndMessages(null);
                Log.e(TAG, "I am player 2 !");
                isPlayer1 = false;
                mConnection.sendMessage(START_MSG);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        countDown();
                    }
                });
            }
        };

        mUpdateHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String chatLine = msg.getData().getString("msg");
                Log.e(TAG, "Received " + chatLine + " from friend!");

                if (chatLine.equals(START_MSG)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            countDown();
                        }
                    });
                } else {
                    if (mInventory.setForeignIngredient(chatLine)) {
                        mDingPlayer.start();
                    } else {
                        // not an ingredient name, must be score
                        try {
                            mScore += Integer.parseInt(chatLine);
                            scoreDisplay.setText(String.valueOf(mScore));
                        } catch (NumberFormatException e) {
                            Log.e(TAG, "Received unknown message: " + chatLine);
                        }
                    }
                }
            }
        };
    }

    private void setToFullScreen() {
        mContentView.setSystemUiVisibility((View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION));
    }

    private void LoadViews() {
        timeDisplay = (TextView) findViewById(R.id.time_display);
        scoreDisplay = (TextView) findViewById(R.id.score_display);
        scoreDisplay.setText("0");
        mTimer = new CountDownTimer(SECONDS_PER_GAME*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeDisplay.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                timeDisplay.setText(String.valueOf(0));
                endGame();
            }
        };
        INGREDIENT_PLACEHOLDER = (ImageView) findViewById(R.id.ingredient_placeholder);
        orderTextView = (TextView) findViewById(R.id.order);
        orderTextView.setText("Connecting...");
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Starting.");
        mConnection = new DataConnection(mUpdateHandler, mConnectionListener);

        mNsdHelper = new NsdHelper(this);
        mNsdHelper.initializeNsd();

        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Resuming.");
        super.onResume();
        setToFullScreen();
        if (mNsdHelper != null) {
            mNsdHelper.discoverServices();
        }
        autoConnect();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Pausing.");
        mAutoConnectHandler.removeCallbacksAndMessages(null);
        if (mNsdHelper != null) {
            mNsdHelper.stopDiscovery();
        }
        mSoundHelper.pauseMusic();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Being stopped.");
        mNsdHelper.tearDown();
        mConnection.tearDown();
        mNsdHelper = null;
        mConnection = null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Being destroyed.");
        super.onDestroy();
    }
}
