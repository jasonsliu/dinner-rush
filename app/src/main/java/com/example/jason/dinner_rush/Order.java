package com.example.jason.dinner_rush;

import android.content.Context;
import android.widget.TextView;

import com.example.jason.dinner_rush.Ingredients.Ingredient;
import com.example.jason.dinner_rush.Ingredients.IngredientGen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jason on 3/8/2017.
 */

public class Order {
    public static final int MAX_ING_PER_ORDER = 6;
    public static final int MIN_ING_PER_ORDER = 3;

    private final TextView mOrderText;
    private final OrderListener mListener;
    private ArrayList<String> orderList = new ArrayList<>();
    private int mOrderPointValue = 0;


    public Order(Context context, TextView orderText, OrderListener listener) {
        Random random = new Random();
        mOrderText = orderText;
        mListener = listener;
        IngredientGen ingGen = new IngredientGen(context);

        // Choose number of ingredients in this order
        int mNumIngredients = random.nextInt(MAX_ING_PER_ORDER - MIN_ING_PER_ORDER) + MIN_ING_PER_ORDER;

        // Generate order
        for (int i = 0; i < mNumIngredients; i++) {
            IngredientGen.IngredientInfo ingInfo = ingGen.getRandIngredientInfo();
            orderList.add(ingInfo.name);
            mOrderPointValue += ingInfo.pointValue;
        }
        setOrderText();
    }

    public void addIngredient(Ingredient ing) {
        int index = orderList.indexOf(ing.getName());
        if(index < 0) return; // TODO: 3/10/2017 Punish for incorrect ingredient
        orderList.remove(index);
        setOrderText();
        if (orderList.size() == 0) {
            mListener.finishedOrder(mOrderPointValue);
        }
    }

    private void setOrderText() {
        String order = "";
        for (int i = 0; i < orderList.size(); i++) {
            order += orderList.get(i);
            order += "\n";
        }
        mOrderText.setText(order);
    }

    public interface OrderListener {
        void finishedOrder(int pointsEarned);
    }

}
