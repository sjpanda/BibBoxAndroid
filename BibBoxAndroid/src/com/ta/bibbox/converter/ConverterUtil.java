package com.ta.bibbox.converter;

public class ConverterUtil {
	public static String convertNullObject(String s){
		if(s.trim().equalsIgnoreCase("anyType{}")){
			return "";
		}
		return s;
	}
}
