package com.example.jason.dinner_rush.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by jason on 3/8/2017.
 */

public class PixelHelper {

    public static int pixelsToDp(int px, Context context) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, px,
                context.getResources().getDisplayMetrics()
        );
    }
}
