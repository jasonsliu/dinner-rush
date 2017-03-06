package com.example.jason.dinner_rush;

import java.util.Hashtable;

/**
 * Created by byronc on 2/16/17.
 */


class Ingredient {

    Ingredient(String p, int q){
        processed = p;
        quantity = q;
    }

    String processed;
    int quantity;
};

public class Inventory {

    Inventory() {
        ingredients_ = new Hashtable<String,Ingredient>();
        Ingredient potato = new Ingredient("fries", 100);
        ingredients_.put("potato", potato);
        Ingredient fries = new Ingredient("", 0);
        ingredients_.put("fries", fries);
    }

    int getIngredientCount(String name) {
        Ingredient i = ingredients_.get(name);
        int result = (i == null) ? -1 : i.quantity;
        return result;
    }

	int processIngredient(String name) {
		Ingredient first = ingredients_.get(name);
		Ingredient processed = ingredients_.get(first.processed);

        // no ingredients to process
        if (first.quantity == 0) return -1;

		first.quantity -= 1;
		processed.quantity += 1;

		return processed.quantity;
	}

	private Hashtable<String, Ingredient> ingredients_;
}