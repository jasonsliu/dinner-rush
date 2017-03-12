package com.example.jason.dinner_rush;

import android.content.Context;

import com.example.jason.dinner_rush.Ingredients.Avocado;
import com.example.jason.dinner_rush.Ingredients.Bacon;
import com.example.jason.dinner_rush.Ingredients.Carrot;
import com.example.jason.dinner_rush.Ingredients.Corn;
import com.example.jason.dinner_rush.Ingredients.Ingredient;
import com.example.jason.dinner_rush.Ingredients.Lettuce;
import com.example.jason.dinner_rush.Ingredients.Tomato;

import java.util.ArrayList;

/**
 * Created by byronc on 3/12/17.
 */

public class p1Inventory extends Inventory {
    public p1Inventory(Context context) {
        super(context);

        ArrayList<Ingredient> a = new ArrayList<>();
        Ingredient i = new Carrot(context);
        a.add(i);
        i = new Avocado(context);
        a.add(i);
        i = new Bacon(context);
        a.add(i);

        ArrayList<Ingredient> b = new ArrayList<>();
        i = new Corn(context);
        a.add(i);
        i = new Lettuce(context);
        a.add(i);
        i = new Tomato(context);
        a.add(i);

        mOwned = a;
        mForeign = b;
    }
}
