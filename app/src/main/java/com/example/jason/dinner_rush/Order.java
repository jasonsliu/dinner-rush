package com.example.jason.dinner_rush;

import android.content.Context;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by jason on 3/8/2017.
 */

public class Order {

    public static final int MAX_ING_PER_ORDER = 6;
    public static final int MIN_ING_PER_ORDER = 3;

    private int mNumIngredients;
    private TextView[] recipeText;

    public Order(Context context) {
        Random random = new Random();

        // choose number of ingredients in this order
        mNumIngredients = random.nextInt(MAX_ING_PER_ORDER - MIN_ING_PER_ORDER) + MIN_ING_PER_ORDER;

        recipeText = new TextView[mNumIngredients];
        for (int i = 0; i < mNumIngredients; i++) {
            TextView ingredientText = new TextView(context);
            // TODO: 3/8/2017  choose some random ingredient
            // ingredientText.setText(randomIng.getName());


        }
    }

}
