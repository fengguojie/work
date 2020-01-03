package com.srit.ecs.phone.util;

import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static HttpSession getSession() {
        return RequestUtils.getRequest().getSession();
    }

    public static Object getAttribute(String key) {
        return getSession().getAttribute(key);
    }

    public static void setAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static void removeAttribute(String key) {
        getSession().removeAttribute(key);
    }
}
