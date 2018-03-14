package com.sonnyjack.utils.screen;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by SonnyJack on 2018/3/13.
 */
public class ScreenUtils {

    /**
     * return screen width
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (null == context) {
            throw new NullPointerException("context must not null");
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        return screenWidth;
    }

    /***
     * return screen height
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (null == context) {
            throw new NullPointerException("context must not null");
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.heightPixels;
        return screenWidth;
    }
}
