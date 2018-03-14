package com.sonnyjack.utils.sp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by SonnyJack on 2018/3/13.
 */
public class SPUtils {

    private SharedPreferences mSharedPreferences;
    private String mName;

    private Context mContext;

    private SPUtils() {
    }

    private static class SPUtilsHolder {
        private static SPUtils sInstance = new SPUtils();
    }

    public static SPUtils getInstance() {
        return SPUtilsHolder.sInstance;
    }

    public void init(Context context) {
        init(context, null);
    }

    public void init(Context context, String name) {
        if (null == context) {
            throw new NullPointerException("context is null");
        }
        mContext = context.getApplicationContext();
        mName = name;
        if (TextUtils.isEmpty(mName)) {
            mName = context.getPackageName();
        }
        mSharedPreferences = mContext.getSharedPreferences(mName, Activity.MODE_PRIVATE);
    }

    private SharedPreferences getSharedPreferences() {
        if (null == mSharedPreferences) {
            synchronized (SPUtils.class) {
                if (null == mSharedPreferences) {
                    if (TextUtils.isEmpty(mName)) {
                        mName = mContext.getPackageName();
                    }
                    mSharedPreferences = mContext.getSharedPreferences(mName, Activity.MODE_PRIVATE);
                }
            }
        }
        return mSharedPreferences;
    }

    public void reset(String name) {
        if (TextUtils.isEmpty(name)) {
            name = mContext.getPackageName();
        }
        mSharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
    }

    public boolean put(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public boolean put(String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public int getInt(String key) {
        return getInt(key, -1);
    }

    public int getInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    public boolean put(String key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    public boolean put(String key, float value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public float getFloat(String key) {
        return getFloat(key, -1.0f);
    }

    public float getFloat(String key, float defaultValue) {
        return getSharedPreferences().getFloat(key, defaultValue);
    }

    public boolean put(String key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public long getLong(String key) {
        return getLong(key, -1L);
    }

    public long getLong(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    public boolean remove(String key) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove(key);
        return editor.commit();
    }

    public boolean removeAll() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        return editor.commit();
    }

    /***********************************************************************************************
     * 指定SharedPreferences文件名  ----->  start
     ***********************************************************************************************/

    public boolean put(String name, String key, String value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public String getString2(String name, String key) {
        return getString2(name, key, null);
    }

    public String getString2(String name, String key, String defaultValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    public boolean put(String name, String key, int value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public int getInt(String name, String key) {
        return getInt(name, key, 0);
    }

    public int getInt(String name, String key, int defaultValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }

    public boolean put(String name, String key, boolean value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public boolean getBoolean(String name, String key) {
        return getBoolean(name, key, false);
    }

    public boolean getBoolean(String name, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public boolean put(String name, String key, float value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public float getFloat(String name, String key) {
        return getFloat(name, key, 0.0f);
    }

    public float getFloat(String name, String key, float defaultValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public boolean put(String name, String key, long value) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public long getLong(String name, String key) {
        return getLong(name, key, 0L);
    }

    public long getLong(String name, String key, long defaultValue) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defaultValue);
    }

    public boolean remove(String name, String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        return editor.commit();
    }

    public boolean removeAll(String name) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        return editor.commit();
    }

    /***********************************************************************************************
     * 指定SharedPreferences文件名  ----->  end
     ***********************************************************************************************/
}