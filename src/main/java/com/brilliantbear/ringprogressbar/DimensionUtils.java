package com.brilliantbear.ringprogressbar;

import android.content.res.Resources;

/**
 * Created by Bear on 2016/1/12.
 */
public class DimensionUtils {
    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }
}
