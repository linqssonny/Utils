package com.sonnyjack.utils.regex;

import android.text.TextUtils;

import java.util.regex.Pattern;

/**
 * Created by SonnyJack on 2018/3/15.
 */

public class RegexUtils {

    private RegexUtils() {

    }

    /**
     * return true if the CharSequence is phone number
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneNumber(CharSequence phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        String regex = "^[1]\\d{10}$";
        return isMatches(regex, phone);
    }

    /**
     * return true if the CharSequence is email
     *
     * @param email
     * @return
     */
    public static boolean isEmail(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        return isMatches(regex, email);
    }

    /**
     * return true if the CharSequence is Chinese
     *
     * @param chinese
     * @return
     */
    public static boolean isChinese(CharSequence chinese) {
        if (TextUtils.isEmpty(chinese)) {
            return false;
        }
        String regex = "^[\u4e00-\u9fa5],{0,}$";
        return isMatches(regex, chinese);
    }

    /**
     * return true if the CharSequence is ip
     *
     * @param ip
     * @return
     */
    public static boolean isIP(CharSequence ip) {
        if (TextUtils.isEmpty(ip)) {
            return false;
        }
        String regex = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
        return isMatches(regex, ip);
    }

    /**
     * return true if the CharSequence is web url
     *
     * @param webUrl
     * @return
     */
    public static boolean isWebUrl(CharSequence webUrl) {
        if (TextUtils.isEmpty(webUrl)) {
            return false;
        }
        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return isMatches(regex, webUrl);
    }

    /**
     * return true if the CharSequence is IDCard what length is 15
     *
     * @param cardId
     * @return
     */
    public static boolean isIDCardOf15(CharSequence cardId) {
        if (TextUtils.isEmpty(cardId)) {
            return false;
        }
        String regex = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        return isMatches(regex, cardId);
    }

    /**
     * return true if the CharSequence is IDCard what length is 18
     *
     * @param cardId
     * @return
     */
    public static boolean isIDCardOf18(CharSequence cardId) {
        if (TextUtils.isEmpty(cardId)) {
            return false;
        }
        String regex = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
        return isMatches(regex, cardId);
    }

    /**
     * return whether input matches the regex.
     *
     * @param regex
     * @param input
     * @return
     */
    public static boolean isMatches(String regex, CharSequence input) {
        return !TextUtils.isEmpty(input) && Pattern.matches(regex, input);
    }
}
