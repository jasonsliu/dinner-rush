package com.example.jason.dinner_rush.Ingredients;

import android.animation.ValueAnimator;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.jason.dinner_rush.R;
import com.example.jason.dinner_rush.utils.PixelHelper;

/**
 * Extend this class to add new ingredients.
 * When creating new ingredients, register it with {@link IngredientGen}.
 * Created by byronc on 3/8/17.
 */

public class Ingredient extends AppCompatImageView implements ValueAnimator.AnimatorUpdateListener {

    protected Context mContext;
    private String mName;
    private int mHealth;
    private int mPointValue;
    private int mRawHeight;
    private int mRawWidth;
    protected int mRawDrawable;
    protected int mProcessedDrawable;
    private IngredientListener mListener;

    boolean mIsActive = true;
    private ValueAnimator mAnimator;
    private MediaPlayer mChopMediaPlayer;

    public Ingredient(Context context, String name,
                      int health, int pointValue,
                      int rawHeight, int rawWidth,
                      int rawImage, int processedImage,
                      @Nullable ImageView placeholder, @Nullable IngredientListener listener) {
        super(context);

        mContext = context;
        mName = name;
        mHealth = health;
        mRawHeight = rawHeight;
        mRawWidth = rawWidth;
        mPointValue = pointValue;
        mRawDrawable = rawImage;
        mProcessedDrawable = processedImage;
        mListener = listener;

        mChopMediaPlayer = MediaPlayer.create(mContext, R.raw.knife_chop);

        if (placeholder != null) {
            setLocation(placeholder);
        }
    }

    public void setUpForUse(ImageView placeholder, IngredientListener listener) {
        mListener = listener;
        setLocation(placeholder);
    }

    // set up location for image display
    private void setLocation(ImageView placeholder) {
        if (placeholder != null) {
            int dpHeight = PixelHelper.pixelsToDp(mRawHeight, mContext);
            int dpWidth = PixelHelper.pixelsToDp(mRawWidth, mContext);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth, dpHeight);
            setLayoutParams(params);
            this.setX(placeholder.getX() - (dpHeight / 2));
            this.setY(placeholder.getY() - (dpWidth / 2));
        }
    }

    private void process() {
        mHealth--;
        if (mHealth > 0) {
            this.setImageResource(mRawDrawable);
        } else {
            mChopMediaPlayer.start(); // Plays the chop sound
            this.setImageResource(mProcessedDrawable); // Switch to chopped ingredient
        }
        if (mHealth <= -1) {
            mListener.isFinished(this);
        }
    }

    public String getName() { return mName; }
    public Integer getPointValue() { return mPointValue; }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mIsActive && event.getAction() == MotionEvent.ACTION_DOWN) {
            process();
            doBounce();
        }
        return super.onTouchEvent(event);
    }

    private void doBounce() {
        mAnimator = new ValueAnimator();
        mAnimator.setDuration(70);
        mAnimator.setFloatValues(getY(), getY() - 20f, getY());
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setTarget(this);
        mAnimator.addUpdateListener(this);
        mAnimator.start();
    }
    
    public void setInactive() {
        mIsActive = false;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        this.setY((float) mAnimator.getAnimatedValue());
    }

    public interface IngredientListener {
        // call this when the user touches again after first processed
        void isFinished(Ingredient ingredient);
    }
}
