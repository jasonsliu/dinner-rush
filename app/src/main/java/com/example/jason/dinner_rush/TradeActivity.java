package com.example.jason.dinner_rush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Hashtable;

public class TradeActivity extends AppCompatActivity {

    // Assuming somehow we get this from the other play activity
    Hashtable<String,Ingredient> ingredients = new Hashtable<String,Ingredient>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        // TODO: get ingredient's current hashtable from other activities
        // ingredients = savedInstanceState.blahblahblah
    }

    void receiveMessage() {
        // TODO: Listener for sent ingredients and acks
        // If it's an ingredient, calls receiveIngredient() when it gets one.
        // If it's an ACK, decrease that ingredient's amount by 1 in AckSentIngredient();
    }

    void sendIngredient(String name) {
        // Send string (example: send "carrot")

        // Current ingredient
        Ingredient cur_ig = ingredients.get(name);

        if (cur_ig == null || cur_ig.quantity <= 0) {
            // Doesn't exist in the hashtable, so silent return.
            return;
        } else {
            // TODO: SEND INGREDIENT USING CONNECTION
            // SEND WILL LOOK LIKE "carrot" <-- that means 1 carrot sent
        }
    }

    void AckSentIngredient(String ing_name) {
        // Reduces OWN inventory by one, since you got confirmation that the ingredient sent.
        Ingredient cur_ig = ingredients.get(ing_name);
        if (cur_ig != null) {
            if (cur_ig.quantity < 1) {
                // This is an error. It should never be less than 0.
                // TODO: report this error somehow.
            } else {
                cur_ig.quantity -= 1;
            }
        }
    }

    // Call this when a message has been received,
    // Increases that ingredient's count by 1
    void receiveIngredient(String receivedMessage) {
        // receive string (example: get "carrot")

        // Current ingredient
        Ingredient cur_ig = ingredients.get(receivedMessage);
        if (cur_ig != null) {
            cur_ig.quantity += 1;
        }

        // After it is received, send a quick ACK saying it was received.
    }

    // THESE FUNCTIONS ARE CALLED BY THE BUTTONS IN DISPLAY XML

    // Connected to goBack button
    void goBack() {
        // Return to main screen.
    }

    // Connected to sendCarrotView button
    void sendCarrot() {
        sendIngredient("carrot");
    }

    // Connected to sendPotatoView button
    void sendPotato() {
        sendIngredient("potato");
    }

    // Connected to sendOnionView button
    void sendOnion() {
        sendIngredient("onion");
    }

    // Connected to sendCeleryView button
    void sendCelery() {
        sendIngredient("celery");
    }
}
