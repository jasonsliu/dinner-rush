package com.example.jason.dinner_rush;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;

import com.example.jason.dinner_rush.Ingredients.Ingredient;

import java.util.Hashtable;

public class TradeActivity extends FragmentActivity {

    // Assuming somehow we get this from the other play activity
    Hashtable<String,Ingredient> ingredients = new Hashtable<String,Ingredient>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        // TODO: get ingredient's current hashtable from other activities
        // ingredients = savedInstanceState.blahblahblah

//         FRAGMENT STUFF
//        android.app.FragmentManager fm = getFragmentManager();
//        addShowHideListener(R.id.openInventoryButton, fm.findFragmentById(R.id.inventoryFragment));
    }

    void addShowHideListener(int buttonId, final Fragment fragment) {
        final Button button = (Button)findViewById(buttonId);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.setCustomAnimations(android.R.animator.fade_in,
//                        android.R.animator.fade_out);
//                if (fragment.isHidden()) {
//                    ft.show(fragment);
//                    button.setText("Hide");
//                } else {
//                    ft.hide(fragment);
//                    button.setText("Show");
//                }
//                ft.commit();
//            }
//        });
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

        if (cur_ig == null || !cur_ig.haveIngredient()) {
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
            cur_ig.useIngredient();

//            if (cur_ig.getQuantity() < 1) {
//                // This is an error. It should never be less than 0.
//                // TODO: report this error somehow.
//            } else {
//                cur_ig.setQuantity(cur_ig.getQuantity() - 1);
//            }
        }
    }

    // Call this when a message has been received,
    // Increases that ingredient's count by 1
    void receiveIngredient(String receivedMessage) {
        // receive string (example: get "carrot")

        // Current ingredient
        Ingredient cur_ig = ingredients.get(receivedMessage);
        if (cur_ig != null) {
//            cur_ig.setQuantity(cur_ig.getQuantity() + 1);
        }

        // After it is received, send a quick ACK saying it was received.
    }

    // THESE FUNCTIONS ARE CALLED BY THE BUTTONS IN DISPLAY XML

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
