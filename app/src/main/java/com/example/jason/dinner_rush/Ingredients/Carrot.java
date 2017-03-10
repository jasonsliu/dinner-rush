package com.example.jason.dinner_rush.Ingredients;

import android.content.Context;
import android.widget.ImageView;

import com.example.jason.dinner_rush.R;

/**
 * Created by jason on 3/8/2017.
 */

public class Carrot extends Ingredient {

    public static final String NAME = "Carrot";
    public static final int HEALTH = 6;
    public static final int POINT_VALUE = 10;
    public static final int RAW_HEIGHT = 100;
    public static final int RAW_WIDTH = 100;
    public static final int RAW_DRAWABLE = R.drawable.carrot;
    public static final int PROCESSED_DRAWABLE = R.drawable.carrot_chopped;

    public Carrot(Context context) {
        super(context, NAME, POINT_VALUE);
    }

    public Carrot(Context context, ImageView view, IngredientListener listener) {
        super(context, NAME,
                HEALTH, POINT_VALUE, true,
                RAW_HEIGHT, RAW_WIDTH,
                RAW_DRAWABLE, PROCESSED_DRAWABLE, view, listener);
        this.setImageResource(mRawDrawable);
    }

}
