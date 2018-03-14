package com.sonnyjack.utils.density;

import android.content.Context;

/**
 * Created by SonnyJack on 2018/3/13.
 */
public class DensityUtils {

    /***
     * px convert dp
     *
     * @param context
     * @param value
     * @return
     */
    public static int px2dp(Context context, float value) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value / scale + 0.5f);
    }

    /***
     * dp convert px
     *
     * @param context
     * @param value
     * @return
     */
    public static int dp2px(Context context, float value) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    /***
     * px convert sp
     *
     * @param context
     * @param value
     * @return
     */
    public static int px2sp(Context context, float value) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value / scale + 0.5f);
    }

    /***
     * sp convert px
     *
     * @param context
     * @param value
     * @return
     */
    public static int sp2px(Context context, float value) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (value * scale + 0.5f);
    }
}
