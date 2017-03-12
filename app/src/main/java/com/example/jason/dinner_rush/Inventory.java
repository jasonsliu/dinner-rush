package com.example.jason.dinner_rush;

import com.example.jason.dinner_rush.Ingredients.Ingredient;

import java.util.Hashtable;

/**
 * Created by byronc on 2/16/17.
 */

public class Inventory {

    public Inventory() {
//        Ingredient potato = new Ingredient("potato", false, true);
//        ingredients_.put(potato.getName(), potato);
//        Ingredient carrot = new Ingredient("carrot", false, true);
//        ingredients_.put(carrot.getName(), carrot);
    }

//    public boolean haveIngredient(String name) {
//        Ingredient i = ingredients_.get(name);
//        return i.haveIngredient();
//    }
//
//	public void receiveIngredient(String name) {
//        Ingredient i = ingredients_.get(name);
//        i.getIngredient();
//    }
//
//    public boolean useIngredient(String name) {
//        Ingredient i = ingredients_.get(name);
//        return i.useIngredient();
//    }

	private Hashtable<String, Ingredient> ingredients_ = new Hashtable<String, Ingredient>();
}