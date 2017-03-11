package com.example.jason.dinner_rush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        InventoryFragment frag = new InventoryFragment();

        getFragmentManager().beginTransaction()
                .add(R.id.inventoryContainer, frag)
                .commit();
    }



}
