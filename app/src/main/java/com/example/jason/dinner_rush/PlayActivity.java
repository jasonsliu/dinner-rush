package com.example.jason.dinner_rush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

class Ingredient {
    Ingredient(String name, int quantity) {
        name_ = name; quantity_ = quantity;
    }


    String name_;
    int quantity_;
}


public class PlayActivity extends AppCompatActivity {

    Ingredient potato;
    Ingredient fries;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        potato = new Ingredient("potato", 100);
        fries = new Ingredient("fries", 0);
        updateCount();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    protected void updateCount() {
        TextView display = (TextView) findViewById(R.id.displayIngredients);
        String displayText = "Potatoes: " + Integer.toString(potato.quantity_)
                + "; Fries: " + Integer.toString(fries.quantity_);
        display.setText(displayText);
    }

    protected void processIngredient(View view) {
        potato.quantity_ -= 1;
        fries.quantity_ += 1;
        updateCount();
    }



}
