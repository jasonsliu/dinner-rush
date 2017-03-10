package com.example.jason.dinner_rush.Ingredients;

import android.content.Context;
import android.widget.ImageView;

import com.example.jason.dinner_rush.R;

/**
 * Created by byronc on 3/10/17.
 */

public class Corn extends Ingredient {
    public static final String NAME = "Corn";
    public static final int HEALTH = 7;
    public static final int POINT_VALUE = 7;
    public static final int RAW_HEIGHT = 100;
    public static final int RAW_WIDTH = 100;
    public static final int RAW_DRAWABLE = R.drawable.corn;
    public static final int PROCESSED_DRAWABLE = R.drawable.corn_chopped;

    public Corn(Context context) {
        super(context, NAME, POINT_VALUE);
    }

    public Corn(Context context, ImageView view, IngredientListener listener) {
        super(context, NAME,
                HEALTH, POINT_VALUE, true,
                RAW_HEIGHT, RAW_WIDTH,
                RAW_DRAWABLE, PROCESSED_DRAWABLE, view, listener);
        this.setImageResource(mRawDrawable);
    }
}
