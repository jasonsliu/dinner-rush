package com.example.jason.dinner_rush.Ingredients;

import android.content.Context;
import android.widget.ImageView;

import com.example.jason.dinner_rush.R;

/**
 * Created by byronc on 3/10/17.
 */

public class Bacon extends Ingredient {
    public static final String NAME = "Bacon";
    public static final int HEALTH = 12;
    public static final int POINT_VALUE = 20;
    public static final int RAW_HEIGHT = 100;
    public static final int RAW_WIDTH = 100;
    public static final int RAW_DRAWABLE = R.drawable.bacon;
    public static final int PROCESSED_DRAWABLE = R.drawable.bacon_chopped;

    public Bacon(Context context) {
        super(context, NAME,
                HEALTH, POINT_VALUE,
                RAW_HEIGHT, RAW_WIDTH,
                RAW_DRAWABLE, PROCESSED_DRAWABLE, null, null);
    }

    public Bacon(Context context, ImageView view, IngredientListener listener) {
        super(context, NAME,
                HEALTH, POINT_VALUE,
                RAW_HEIGHT, RAW_WIDTH,
                RAW_DRAWABLE, PROCESSED_DRAWABLE, view, listener);
        this.setImageResource(mRawDrawable);
    }
}
