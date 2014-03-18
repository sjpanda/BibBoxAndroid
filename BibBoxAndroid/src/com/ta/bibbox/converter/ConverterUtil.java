package com.ta.bibbox.converter;

/**
 * @author Jing SHU
 * @date 17/03/2014
 * @copyright TA Copyright
 * @brief Ksoap2 renvoie "anytype{}" pour les valeurs null
 */
public class ConverterUtil {
	public static String convertNullObject(String s){
		if(s.trim().equalsIgnoreCase("anyType{}")){
			return "";
		}
		return s;
	}
}
