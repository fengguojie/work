package com.srit.ecs.phone.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtils extends org.springframework.beans.BeanUtils {
    public static <T> T getInstantiation(HttpServletRequest request, Class<T> clz) {
        T instantiation;
        try {
            instantiation = getInstantiation(clz);
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String propertyName = parameterNames.nextElement();
                if (propertyNameExisted(clz, propertyName)) {
                    String propertyValue = request.getParameter(propertyName);
                    setPropertyValue(instantiation, propertyName, propertyValue);
                }
            }
        } catch (Exception e) {
            instantiation = null;
            e.printStackTrace();
        }
        return instantiation;
    }

    public static void copyNullProperties(Object source, Object target) {
        copyProperties(source, target, getNotNullPropertyNames(target));
    }

    public static void setCreateProperties(Object instantiation) {
        try {
            setPropertyValue(instantiation, Constants.BEAN_PROPERTY_CREATE_BY, "admin");
            setPropertyValue(instantiation, Constants.BEAN_PROPERTY_CREATE_TIME, DateUtils.getCurrentTime());
            setPropertyValue(instantiation, Constants.BEAN_PROPERTY_STATE, Constants.BEAN_PROPERTY_STATE_TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void setCreatePropertiesComp(Object instantiation) {
        try {
            setPropertyValue(instantiation, "dataStatus", "1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setUpdateProperties(Object instantiation) {
        try {
            setPropertyValue(instantiation, Constants.BEAN_PROPERTY_UPDATE_BY, "admin");
            setPropertyValue(instantiation, Constants.BEAN_PROPERTY_UPDATE_TIME, DateUtils.getCurrentTime());
            setPropertyValue(instantiation, Constants.BEAN_PROPERTY_STATE, Constants.BEAN_PROPERTY_STATE_TRUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static <T> T getInstantiation(Class<T> clz) throws IllegalAccessException, InstantiationException {
        T instantiation = clz.newInstance();
        return instantiation;
    }

    private static boolean propertyNameExisted(Class<?> clz, String propertyName) {
        boolean existed = false;
        try {
            if (clz.getDeclaredField(propertyName) != null) {
                existed = true;
            }
        } catch (NoSuchFieldException e) {
        }
        return existed;
    }

    private static void setPropertyValue(Object instantiation, String propertyName, String propertyValue) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, instantiation.getClass());
        Method writeMethod = propertyDescriptor.getWriteMethod();
        Class<?> propertyType = propertyDescriptor.getReadMethod().getReturnType();
        if (propertyType == Integer.class) {
            writeMethod.invoke(instantiation, Integer.valueOf(propertyValue));
        } else if (propertyType == Double.class) {
            writeMethod.invoke(instantiation, Double.valueOf(propertyValue));
        } else if (propertyType == Boolean.class) {
            writeMethod.invoke(instantiation, Boolean.valueOf(propertyValue));
        } else if (propertyType == Long.class) {
            writeMethod.invoke(instantiation, Long.valueOf(propertyValue));
        } else if (propertyType == Timestamp.class) {
            writeMethod.invoke(instantiation, Timestamp.valueOf(propertyValue));
        } else {
            writeMethod.invoke(instantiation, propertyValue);
        }
    }

    private static String[] getNotNullPropertyNames(Object instantiation) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(instantiation);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        Set<String> notNullPropertyNames = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Object propertyValue = beanWrapper.getPropertyValue(propertyDescriptor.getName());
            if (propertyValue != null) {
                notNullPropertyNames.add(propertyDescriptor.getName());
            }
        }
        return notNullPropertyNames.toArray(new String[notNullPropertyNames.size()]);
    }
}
