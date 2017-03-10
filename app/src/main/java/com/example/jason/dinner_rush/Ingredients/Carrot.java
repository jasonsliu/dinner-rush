package com.example.jason.dinner_rush.Ingredients;

import android.content.Context;

import com.example.jason.dinner_rush.R;

/**
 * Created by jason on 3/8/2017.
 */

public class Carrot extends Ingredient {

    public static final String NAME = "Carrot";
    public static final int HEALTH = 10;
    public static final int RAW_HEIGHT = 50;
    public static final int RAW_WIDTH = 50;
    public static final int RAW_DRAWABLE = R.drawable.carrot;
    public static final int PROCESSED_DRAWABLE = R.drawable.carrot_chopped;


    public Carrot(Context context, IngredientListener listener) {
        super(context, NAME,
                HEALTH, true, RAW_HEIGHT, RAW_WIDTH,
                RAW_DRAWABLE, PROCESSED_DRAWABLE, listener);
        this.setImageResource(mRawDrawable);
    }

}
