package com.srit.ecs.phone.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Utils {
    public static String encode(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        char[] chars = password.toCharArray();
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            bytes[i] = (byte) chars[i];
        }
        byte[] md5Bytes = messageDigest.digest(bytes);
        StringBuffer hexValue = new StringBuffer();
        for (byte md5Byte : md5Bytes) {
            int value = ((int) md5Byte) & 0xff;
            if (value < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(value));
        }
        return hexValue.toString();
    }
}
