package com.example.jason.dinner_rush.Ingredients;

import android.content.Context;
import android.widget.ImageView;

import com.example.jason.dinner_rush.R;

/**
 * Created by byronc on 3/10/17.
 */

public class Lettuce extends Ingredient {
    public static final String NAME = "Lettuce";
    public static final int HEALTH = 5;
    public static final int POINT_VALUE = 10;
    public static final int RAW_HEIGHT = 100;
    public static final int RAW_WIDTH = 100;
    public static final int RAW_DRAWABLE = R.drawable.lettuce;
    public static final int PROCESSED_DRAWABLE = R.drawable.lettuce_chopped;

    public Lettuce(Context context) {
        super(context, NAME, POINT_VALUE);
    }

    public Lettuce(Context context, ImageView view, IngredientListener listener) {
        super(context, NAME,
                HEALTH, POINT_VALUE, true,
                RAW_HEIGHT, RAW_WIDTH,
                RAW_DRAWABLE, PROCESSED_DRAWABLE, view, listener);
        this.setImageResource(mRawDrawable);
    }
}
