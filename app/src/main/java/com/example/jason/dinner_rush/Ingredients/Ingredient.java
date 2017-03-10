package com.example.jason.dinner_rush.Ingredients;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.jason.dinner_rush.utils.PixelHelper;

/**
 * Created by byronc on 3/8/17.
 */

public class Ingredient extends AppCompatImageView implements ValueAnimator.AnimatorUpdateListener {

    protected Context mContext;
    private String mName;
    private boolean m_isMine;
    private boolean m_haveIt;
    int mHealth;
    int mRawDrawable;
    int mProcessedDrawable;
    IngredientListener mListener;
    boolean mIsActive = true;
    private ValueAnimator mAnimator;

    public Ingredient(Context context) {
        super(context);
    }

    public Ingredient(Context context, String name,
                      int health, boolean mine, int rawHeight, int rawWidth,
                      int rawImage, int processedImage,
                      ImageView placeholder, IngredientListener listener) {
        super(context);

        // set up location for image display
        int dpHeight = PixelHelper.pixelsToDp(rawHeight, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth, context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth, dpHeight);
        setLayoutParams(params);
        this.setX(placeholder.getX() - (dpHeight / 2));
        this.setY(placeholder.getY() - (dpWidth / 2));

        mContext = context;
        mName = name;
        mHealth = health;
        m_isMine = mine;
        m_haveIt = m_isMine;
        mRawDrawable = rawImage;
        mProcessedDrawable = processedImage;
        mListener = listener;
    }

    public void setLocation(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    private void process() {
        mHealth--;
        if (mHealth > 0) {
            this.setImageResource(mRawDrawable);
        } else {
            this.setImageResource(mProcessedDrawable);
        }
        if (mHealth <= -1) {
            mListener.isFinished(this);
        }
    }

    public String getName() { return mName; }

    public boolean useIngredient() {
        if (m_isMine) return true;
        if (m_haveIt) {
            m_haveIt = false;
            return true;
        }

        return false;
    }

    public boolean getIngredient() { return m_haveIt; }
    public boolean haveIngredient() { return m_haveIt; }


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
