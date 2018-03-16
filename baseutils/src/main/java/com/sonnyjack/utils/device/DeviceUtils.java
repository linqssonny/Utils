package com.sonnyjack.utils.device;

import android.Manifest;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by SonnyJack on 2018/3/15.
 */

public class DeviceUtils {

    private DeviceUtils() {

    }


    /**
     * return device id, may be return null,but need permission READ_PHONE_STATE
     * telephonyManager.getDeviceId is Deprecated, suggest use getImei
     *
     * @param context
     * @return
     */
    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return null != telephonyManager ? telephonyManager.getDeviceId() : null;
    }

    /**
     * return Imei(International Mobile Equipment Identity), may be return null.
     *
     * @param context
     * @return
     */
    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null == telephonyManager) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return telephonyManager.getImei();
        } else {
            return telephonyManager.getDeviceId();
        }
    }

    /**
     * Returns the MEID (Mobile Equipment Identifier). Return null if MEID is not available.
     *
     * @param context
     * @return
     */
    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    public static String getMeid(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null == telephonyManager) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return telephonyManager.getMeid();
        } else {
            return telephonyManager.getDeviceId();
        }
    }

    /**
     * return android_id
     *
     * @param context
     * @return
     */
    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * return Serial Number, it may be null when Build.getSerial return unknown
     *
     * @return
     */
    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    public static String getSerialNumber() {
        String serialNumber = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            serialNumber = Build.getSerial();
        }
        serialNumber = (serialNumber == Build.UNKNOWN) ? null : serialNumber;
        return serialNumber;
    }

    /**
     * return mac address
     *
     * @param context
     * @return
     */
    @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
    public static String getMacAddress(Context context) {
        String invalidMacAddress = "02:00:00:00:00:00";
        //get from WifiInfo first，return if valid
        WifiManager wifiMan = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = (null == wifiMan ? null : wifiMan.getConnectionInfo());
        if (null != wifiInfo && !invalidMacAddress.equals(wifiInfo.getMacAddress())) {
            return wifiInfo.getMacAddress();
        }

        //get from NetworkInterface second，return if valid
        try {
            List<NetworkInterface> networkInterfaceList = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : networkInterfaceList) {
                if (null == networkInterface || !networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    continue;
                }
                byte[] macBytes = networkInterface.getHardwareAddress();
                if (macBytes != null && macBytes.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : macBytes) {
                        sb.append(String.format("%02x:", b));
                    }
                    return sb.substring(0, sb.length() - 1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get from mac file third，return if valid
        try {
            FileInputStream fin = new FileInputStream(new File("/sys/class/net/wlan0/address"));
            String macAddress = readerStringFromInputStream(fin);
            if (!TextUtils.isEmpty(macAddress)) {
                return macAddress;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return invalidMacAddress;
    }

    private static String readerStringFromInputStream(InputStream inputStream) throws IOException {
        if (inputStream != null) {
            Writer writer = new StringWriter();
            char[] bytes = new char[2048];
            try {
                Reader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                int counter;
                while ((counter = bufferedReader.read(bytes)) != -1) {
                    writer.write(bytes, 0, counter);
                }
            } finally {
                inputStream.close();
            }
            return writer.toString();
        } else {
            return null;
        }
    }
}
