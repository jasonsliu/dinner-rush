package com.example.jason.dinner_rush;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;
//
//class Ingredient {
//    Ingredient(String name, int quantity) {
//        name_ = name; quantity_ = quantity;
//    }
//
//
//    String name_;
//    int quantity_;
//}


public class PlayActivity extends AppCompatActivity {

    Inventory inventory_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        inventory_ = new Inventory();
//        updateCount();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
    }

    protected void updateCount() {
        TextView display = (TextView) findViewById(R.id.displayIngredients);
        String displayText = "Potatoes: " + Integer.toString(inventory_.getIngredientCount("potato"))
                + "; Fries: " + Integer.toString(inventory_.getIngredientCount("fries"));
        display.setText(displayText);
    }

    protected void processIngredient(View view) {
        inventory_.processIngredient("potato");
        updateCount();
    }



}
