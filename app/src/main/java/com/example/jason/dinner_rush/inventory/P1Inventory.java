package com.example.jason.dinner_rush.inventory;

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

public class P1Inventory extends Inventory {
    public P1Inventory(Context context) {
        super(context);

        ArrayList<Ingredient> owned = new ArrayList<>();
        owned.add(new Carrot(context));
        owned.add(new Avocado(context));
        owned.add(new Bacon(context));
        mOwned = owned;

        ArrayList<Ingredient> foreign = new ArrayList<>();
        foreign.add(new Corn(context));
        foreign.add(new Lettuce(context));
        foreign.add(new Tomato(context));
        mForeign = foreign;
    }
}
