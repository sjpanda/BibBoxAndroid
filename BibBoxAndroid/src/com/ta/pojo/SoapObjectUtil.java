package com.ta.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.ksoap2.serialization.SoapObject;

/** 
 * soapObject->javaBean
 * @author zjf 
 * 
 */  
public class SoapObjectUtil {  
    /** 
     * soap -> javabean 
     * @param <T> 
     * @param clazz 
     * @param soapObject 
     * @return 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     * @throws IllegalAccessException 
     * @throws InvocationTargetException 
     * @throws NoSuchMethodException 
     * @throws InstantiationException 
     */  
    public static <T> T soapToPojo(Class<T> clazz, SoapObject soapObject)  
            throws IllegalArgumentException, SecurityException,  
            IllegalAccessException, InvocationTargetException,  
            NoSuchMethodException, InstantiationException {  
          
        Field[] fields = clazz.getDeclaredFields();  
        Object obj = clazz.newInstance();  
        for (Field f : fields) {  
            String method = "set" + f.getName().substring(0, 1).toUpperCase()  
                    + f.getName().substring(1);  
            if (hasMethod(method, clazz.getMethods())) {  
                clazz.getMethod(method, new Class[] { f.getType() }).invoke(  
                        obj,  
                        new Object[] { soapObject.getProperty(f.getName()) });  
            }  
        }  
        return (T) obj;  
    }  
  
    private static boolean hasMethod(String methodName, Method[] method) {  
        for (Method m : method) {  
            if (methodName.equals(m.getName())) {  
                return true;  
            }  
        }  
        return false;  
    }  
} 
