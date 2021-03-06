package com.example.jason.dinner_rush;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.jason.dinner_rush.Ingredients.Ingredient;
import com.example.jason.dinner_rush.Ingredients.IngredientGen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by jason on 3/8/2017.
 */

class Order {
    private static final int MAX_ING_PER_ORDER = 5;
    private static final int MIN_ING_PER_ORDER = 3;

    private final TextView mOrderText;
    private final OrderListener mListener;
    private Animation animBlink;

    private ArrayList<String> originalOrderList = new ArrayList<>();
    private ArrayList<String> orderList = new ArrayList<>();
    private int mOrderPointValue = 0;


    Order(Context context, TextView orderText, OrderListener listener) {
        Random random = new Random();
        mOrderText = orderText;
        mListener = listener;
        animBlink = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.blink);
        IngredientGen ingGen = new IngredientGen(context);

        // Choose number of ingredients in this order
        int mNumIngredients = random.nextInt(MAX_ING_PER_ORDER - MIN_ING_PER_ORDER) + MIN_ING_PER_ORDER;

        // Generate order
        for (int i = 0; i < mNumIngredients; i++) {
            IngredientGen.IngredientInfo ingInfo = ingGen.getRandIngredientInfo();
            orderList.add(ingInfo.name);
            mOrderPointValue += ingInfo.pointValue;
        }

        originalOrderList.addAll(orderList);

        updateOrderText();
        Animation animFadeIn = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.fade_in);
        mOrderText.startAnimation(animFadeIn);
    }

    void addIngredient(Ingredient ing) {
        int index = orderList.indexOf(ing.getName());
        if(index < 0) {
            // Start over since added wrong ingredient
            orderList.clear();
            orderList.addAll(originalOrderList);
            mOrderText.startAnimation(animBlink);
            updateOrderText();
            mListener.botchedOrder(mOrderPointValue / 10);
            return;
        }
        orderList.remove(index);
        updateOrderText();
        if (orderList.size() == 0) {
            mListener.finishedOrder(mOrderPointValue);
        }
    }

    private void updateOrderText() {
        String order = "";
        for (int i = 0; i < orderList.size(); i++) {
            order += orderList.get(i);
            order += "\n";
        }
        mOrderText.setText(order);
    }

    interface OrderListener {
        void finishedOrder(int pointsEarned);
        void botchedOrder(int pointPenalty);
    }

}
