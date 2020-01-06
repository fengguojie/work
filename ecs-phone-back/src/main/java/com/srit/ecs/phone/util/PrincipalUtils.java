package com.srit.ecs.phone.util;

import java.util.List;
import java.util.Map;

public class PrincipalUtils {
	
    @SuppressWarnings("unchecked")
	public static Map<String, Object> getPrincipal() {
        return (Map<String, Object>) SessionUtils.getAttribute(Constants.PRINCIPAL);
    }

    public static void setPrincipal(Map<String, Object> principal) {
        SessionUtils.setAttribute(Constants.PRINCIPAL, principal);
    }

    public static void removePrincipal() {
        SessionUtils.removeAttribute(Constants.PRINCIPAL);
    }

    public static Boolean isPrincipalAdmin() {
    	//为了测试现将下面代码注释掉，为了绕过登录，做完后再打开
        return (Boolean) getPrincipal().get(Constants.PRINCIPAL_IS_ADMIN);
        //return true;
    }

    public static String getPrincipalUserId() {
        return (String) getPrincipal().get(Constants.PRINCIPAL_USER_ID);
    }

    public static String getPrincipalUserFullName() {
        return (String) getPrincipal().get(Constants.PRINCIPAL_USER_FULL_NAME);
    }

    public static String getPrincipalDeptId() {
        return (String) getPrincipal().get(Constants.PRINCIPAL_DEPT_ID);
    }

    @SuppressWarnings("unchecked")
	public static List<String> getPrincipalFuncs() {
        return (List<String>) getPrincipal().get("funcs");
    }

    @SuppressWarnings("unchecked")
	public static List<String> getPrincipalRoutes() {
        return (List<String>) getPrincipal().get("msrvs");
    }

    @SuppressWarnings("unchecked")
	public static List<String> getPrincipalEqpts() {
        return (List<String>) getPrincipal().get("eqpts");
    }

    @SuppressWarnings("unchecked")
	public static List<String> getPrincipalMenus() {
        return (List<String>) getPrincipal().get("menus");
    }
}
