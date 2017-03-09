package com.example.jason.dinner_rush.Ingredients;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jason.dinner_rush.utils.PixelHelper;

/**
 * Created by byronc on 3/8/17.
 */

public class Ingredient extends ImageView {

    protected Context mContext;
    private String mName;
    protected boolean mProcessed;
    private boolean m_isMine;
    private boolean m_haveIt;
    int mHealth;
    int mRawDrawable;
    int mProcessedDrawable;

    public Ingredient(Context context) {
        super(context);
    }

    public Ingredient(Context context, String name,
                      int health, boolean mine, int rawHeight, int rawWidth,
                      int raw, int processed) {
        super(context);

        // convert from px to dp
        int dpHeight = PixelHelper.pixelsToDp(rawHeight, context);
        int dpWidth = PixelHelper.pixelsToDp(rawWidth, context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(dpWidth, dpHeight);
        setLayoutParams(params);

        mContext = context;
        mName = name;
        mHealth = health;
        m_isMine = mine;
        m_haveIt = m_isMine;
        mRawDrawable = raw;
        mProcessedDrawable = processed;
    }

    public void setLocation(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public void process() {
        mHealth--;
        if (mHealth <= 0) {
            setProcessed(true);
        }
    }

    public boolean getProcessed() { return mProcessed; }
    private void setProcessed(Boolean p) {
        mProcessed = p;
        if (p) {
            this.setImageResource(mProcessedDrawable);
        } else {
            this.setImageResource(mRawDrawable);
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

}
