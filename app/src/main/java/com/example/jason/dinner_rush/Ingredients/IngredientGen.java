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
    ArrayList<String> names = new ArrayList<>();
    Random random = new Random();

    public IngredientGen(Context context) {
        // Add all the ingredients here
        ingredients.add(new Carrot(context));
        ingredients.add(new Lettuce(context));
        ingredients.add(new Tomato(context));
        ingredients.add(new Corn(context));
        ingredients.add(new Avocado(context));
        ingredients.add(new Bacon(context));
        for (int i = 0; i < ingredients.size(); i++) {
            names.add(ingredients.get(i).getName());
        }
    }

    public String getIngredientName() {
        int index = random.nextInt(names.size());
        return names.get(index);
    }

    public class IngredientInfo {
        public String name;
        public int pointValue;
    }

}
