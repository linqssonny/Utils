package com.sonnyjack.utils.string;

import android.text.TextUtils;

/**
 * Created by SonnyJack on 2018/3/15.
 */

public class StringUtils {

    private StringUtils() {

    }

    /**
     * return true when value is null or empty or 'null' or 'NULL',
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return TextUtils.isEmpty(value) || "null".equalsIgnoreCase(value);
    }

    /**
     * return empty char when value is empty
     *
     * @param value
     * @return
     */
    public static String empty2Char(String value) {
        if (isEmpty(value)) {
            return "";
        }
        return value;
    }

    /**
     * return true when value1 equals value2 that ignore case
     *
     * @param value1
     * @param value2
     * @return
     */
    public static boolean equalsIgnoreCase(String value1, String value2) {
        if (TextUtils.equals(value1, value2)) {
            return true;
        }
        if (null != value1) {
            return value1.equalsIgnoreCase(value2);
        }
        return value2.equalsIgnoreCase(value1);
    }

    /**
     * return charSequence length
     *
     * @param charSequence
     * @return
     */
    public static int length(CharSequence charSequence) {
        if (null == charSequence) {
            return 0;
        }
        return charSequence.length();
    }

    /**
     * return trim string
     *
     * @param value
     * @return
     */
    public static String trimString(String value) {
        if (null != value) {
            return value.trim();
        }
        return value;
    }
}
