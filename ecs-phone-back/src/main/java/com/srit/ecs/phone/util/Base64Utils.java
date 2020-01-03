package com.srit.ecs.phone.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Utils {
    public static String encode(String password) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
    }
}
