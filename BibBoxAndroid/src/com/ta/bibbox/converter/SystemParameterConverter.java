package com.ta.bibbox.converter;

import org.ksoap2.serialization.SoapPrimitive;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet SystemParameter à un POJO SystemParameter
 */
public class SystemParameterConverter {

	public String convertToObject(SoapPrimitive soapPrimitive) {
		if(soapPrimitive == null) { return null; }
		return soapPrimitive.toString();
	}
	
	private static SystemParameterConverter systemParameterConverter;
	private SystemParameterConverter() {}
	
	public static SystemParameterConverter instance() {
		if(systemParameterConverter == null) {systemParameterConverter = new SystemParameterConverter(); }
		return systemParameterConverter;
	}
}
