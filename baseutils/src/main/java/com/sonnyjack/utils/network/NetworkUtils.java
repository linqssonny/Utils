package com.sonnyjack.utils.network;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;

/**
 * Created by SonnyJack on 2018/3/13.
 */

public class NetworkUtils {

    private NetworkUtils() {

    }

    /**
     * open the wifi settings activity
     *
     * @param context
     */
    public static void openWifiSettings(Context context) {
        if (null == context) {
            throw new NullPointerException("the context is null");
        }
        Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /***
     * return true when have network
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (null == context) {
            throw new NullPointerException("context must not null");
        }
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        if (null != networkInfo) {
            return networkInfo.isConnected();
        }
        return false;
    }

    /**
     * return true when wifi connected
     *
     * @param context
     * @return
     */
    public static boolean isWifiConnected(Context context) {
        if (null == context) {
            throw new NullPointerException("context must not null");
        }
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        if (networkInfo != null && networkInfo.isConnected()) {
            return networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    /**
     * return network type [-1: unknown; 0: wifi; 2: 2G; 3: 3G; 4: 4G]
     *
     * @param context
     * @return
     */
    @RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE)
    public static int getNetworkType(Context context) {
        int networkType = -1;//unknown
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        if (null == networkInfo || !networkInfo.isAvailable()) {
            return networkType;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            //wifi
            networkType = 0;
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            switch (networkInfo.getSubtype()) {
                case TelephonyManager.NETWORK_TYPE_GSM:
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    //2G
                    networkType = 2;
                    break;
                case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    //3G
                    networkType = 3;
                    break;
                case TelephonyManager.NETWORK_TYPE_IWLAN:
                case TelephonyManager.NETWORK_TYPE_LTE:
                    //4G
                    networkType = 4;
                    break;
                default:
                    String subtypeName = networkInfo.getSubtypeName();
                    if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                            || subtypeName.equalsIgnoreCase("WCDMA")
                            || subtypeName.equalsIgnoreCase("CDMA2000")) {
                        //3G
                        networkType = 3;
                    }
                    break;
            }
        }
        return networkType;
    }

    /**
     * return active network info
     *
     * @param context
     * @return
     */
    public static NetworkInfo getActiveNetworkInfo(Context context) {
        if (null == context) {
            throw new NullPointerException("context must not null");
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null != connectivityManager) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo;
        }
        return null;
    }
}
