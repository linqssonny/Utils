package com.sonnyjack.utils.security;

import android.text.TextUtils;

import java.security.MessageDigest;

/**
 * Created by SonnyJack on 2018/3/13.
 */

public class SecurityUtils {

    private SecurityUtils() {

    }

    /**
     * MD5 Encryption
     * note: it may be crack when use md5 or sha-1 encryption, so suggest use sha-256 or sha-512 encryption
     *
     * @param value
     * @return
     */
    public static String encryptionMd5(String value) {
        return encryption(value, "MD5");
    }

    /**
     * SHA-256 Encryption
     *
     * @param value
     * @return
     */
    public static String encryptionSHA256(String value) {
        return encryption(value, "SHA-256");
    }

    /**
     * SHA-512 Encryption
     *
     * @param value
     * @return
     */
    public static String encryptionSHA512(String value) {
        return encryption(value, "SHA-512");
    }

    /**
     * Encryption
     *
     * @param value
     * @param encryptionType the type of encryption
     * @return
     */
    public static String encryption(String value, String encryptionType) {
        // 返回值
        String result = null;
        if (TextUtils.isEmpty(value)) {
            return result;
        }
        try {
            // java 加密对象  MessageDigest
            MessageDigest messageDigest = MessageDigest.getInstance(encryptionType);
            // 加密字符串
            messageDigest.update(value.getBytes());
            // 转为bytes
            byte byteBuffer[] = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            // 遍历加密后字节bytes
            for (int i = 0; i < byteBuffer.length; i++) {
                String hex = Integer.toHexString(0xFF & byteBuffer[i]);
                if (hex.length() == 1) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(hex);
            }
            // 得到返回結果
            result = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}