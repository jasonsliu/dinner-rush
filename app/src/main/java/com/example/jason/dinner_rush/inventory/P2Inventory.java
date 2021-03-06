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

public class P2Inventory extends Inventory {
    public P2Inventory(Context context) {
        super(context);

        ArrayList<Ingredient> foreign = new ArrayList<>();
        foreign.add(new Carrot(context));
        foreign.add(new Avocado(context));
        foreign.add(new Bacon(context));
        mForeign = foreign;

        ArrayList<Ingredient> owned = new ArrayList<>();
        owned.add(new Corn(context));
        owned.add(new Lettuce(context));
        owned.add(new Tomato(context));
        mOwned = owned;
    }
}
