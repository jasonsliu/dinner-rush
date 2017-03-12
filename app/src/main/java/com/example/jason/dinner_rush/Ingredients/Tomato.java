package com.example.jason.dinner_rush.Ingredients;

import android.content.Context;
import android.widget.ImageView;

import com.example.jason.dinner_rush.R;

/**
 * Created by byronc on 3/10/17.
 */

public class Tomato extends Ingredient {
    public static final String NAME = "Tomato";
    public static final int HEALTH = 4;
    public static final int POINT_VALUE = 20;
    public static final int RAW_HEIGHT = 100;
    public static final int RAW_WIDTH = 100;
    public static final int RAW_DRAWABLE = R.drawable.tomato;
    public static final int PROCESSED_DRAWABLE = R.drawable.tomato_chopped;

    public Tomato(Context context) {
        super(context, NAME, POINT_VALUE);
    }

    public Tomato(Context context, ImageView view, IngredientListener listener) {
        super(context, NAME,
                HEALTH, POINT_VALUE,
                RAW_HEIGHT, RAW_WIDTH,
                RAW_DRAWABLE, PROCESSED_DRAWABLE, view, listener);
        this.setImageResource(mRawDrawable);
    }
}
