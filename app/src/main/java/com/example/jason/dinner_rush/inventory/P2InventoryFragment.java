package com.example.jason.dinner_rush.inventory;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jason.dinner_rush.R;

/**
 * Created by jason on 3/11/2017.
 */

public class P2InventoryFragment extends Fragment {

    public P2InventoryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.p2_inventory_fragment, container, false);
    }
}
