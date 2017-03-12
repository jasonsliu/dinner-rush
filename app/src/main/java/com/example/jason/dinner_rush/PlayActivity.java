package com.example.jason.dinner_rush;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

public class PlayActivity extends AppCompatActivity {

    P1InventoryFragment mFrag = new P1InventoryFragment();
    private ViewGroup mContentView;
    boolean flip;
    long lastTIme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mContentView = (ViewGroup) findViewById(R.id.activity_play);
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long thisTime = System.currentTimeMillis();
                if (thisTime - lastTIme > 1000) {
//                    displayFrag(flip);
                    lastTIme = thisTime;
                }
            }
        });
    }

//    private void displayFrag(boolean set) {
//        if (set) {
//            getFragmentManager().beginTransaction()
//                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                    .replace(R.id.inventoryContainer, mFrag)
//                    .commit();
//        } else {
////            EmptyFragment e = new EmptyFragment();
//            getFragmentManager().beginTransaction()
//                    .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
////                    .replace(R.id.inventoryContainer, e)
//                    .remove(mFrag)
//                    .commit();
////            getFragmentManager().beginTransaction()
////                    .remove(e)
////                    .commit();
//        }
//        flip = !flip;
//
//    }

}
