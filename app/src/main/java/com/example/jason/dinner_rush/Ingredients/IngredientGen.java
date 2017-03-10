package com.example.jason.dinner_rush.Ingredients;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

/**
 * Needs to be updated with every new ingredient.
 * Used to get a random ingredient class.
 * Created by jason on 3/10/2017.
 */

public class IngredientGen {

    ArrayList<Ingredient> ingredients = new ArrayList<>();
    ArrayList<IngredientInfo> info = new ArrayList<>();
    Random random = new Random();

    public IngredientGen(Context context) {
        // Add all the ingredients here
        ingredients.add(new Carrot(context));

        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ing = ingredients.get(i);
            info.add(new IngredientInfo(ing.getName(), ing.getPointValue()));
        }
    }

    public IngredientInfo getRandIngredientInfo() {
        int index = random.nextInt(info.size());
        return info.get(index);
    }

    public class IngredientInfo {
        IngredientInfo(String name, int pointValue) {
            this.name = name;
            this.pointValue = pointValue;
        }
        public String name;
        public int pointValue;
    }

}
