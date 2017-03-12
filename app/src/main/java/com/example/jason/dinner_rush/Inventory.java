package com.example.jason.dinner_rush;

import android.content.Context;

import com.example.jason.dinner_rush.Ingredients.Carrot;
import com.example.jason.dinner_rush.Ingredients.Ingredient;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by byronc on 2/16/17.
 */

public class Inventory {

    private Context mContext;
    protected ArrayList<Ingredient> mOwned = new ArrayList<>();
    protected ArrayList<Ingredient> mForeign = new ArrayList<>();

    private String mForeignName;

    public Inventory(Context context) {
        mContext = context;
    }

    public Ingredient getIngredient(Ingredient ing) {
        if (ing == null) {
            return null;
        }


        if (ing.getName().equals(mForeignName)) {
            mForeignName = null;
            return ing;
        }

        for (int k = 0; k < mOwned.size(); k++) {
            if (ing.getName().equals(mOwned.get(k).getName())) {
                return mOwned.get(k);
            }
        }

        return null;
    }

    public boolean setForeignIngredient(String name) {
        for (int k = 0; k < mForeign.size(); k++) {
            if (mForeign.get(k).getName().equals(name)) {
                mForeignName = name;
                return true;
            }
        }
        return false;
    }
}