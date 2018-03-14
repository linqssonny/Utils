package com.sonnyjack.utils.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by SonnyJack on 2018/3/13.
 */

public class NetUtils {

    /***
     * return true when have network
     *
     * @param context
     * @return
     */
    public boolean isNetworkConnected(Context context) {
        if (null == context) {
            throw new NullPointerException("context must not null");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (null != networkInfo) {
                return networkInfo.isConnected();
            }
        }
        return false;
    }

    /**
     * return true when wifi connected
     *
     * @param context
     * @return
     */
    public boolean isWifiConnected(Context context) {
        if (null == context) {
            throw new NullPointerException("context must not null");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
            }
        }
        return false;
    }
}
