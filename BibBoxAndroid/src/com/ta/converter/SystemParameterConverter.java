package com.ta.converter;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.ta.pojo.SystemParameter;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet SystemParameter à un POJO SystemParameter
 */
public class SystemParameterConverter implements IConverter<SystemParameter> {

	@Override
	public SystemParameter convertToObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		try{
			SystemParameter s = new SystemParameter();
			s.setId(Integer.parseInt(soapObject.getProperty("ID").toString()));
			s.setName(soapObject.getProperty("Name").toString());
			s.setValue(soapObject.getProperty("Value").toString());
			return s;
		} catch(Exception e){
			return null;
		}
	}

	@Override
	public List<SystemParameter> convertToListObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		List<SystemParameter> systemParameters = new ArrayList<SystemParameter>();
		for(int i=0; i<soapObject.getPropertyCount(); i++){
			systemParameters.add(convertToObject((SoapObject)soapObject.getProperty(i)));
		}
		return systemParameters;
	}
	
	private static SystemParameterConverter systemParameterConverter;
	private SystemParameterConverter() {}
	
	public static SystemParameterConverter instance() {
		if(systemParameterConverter == null) {systemParameterConverter = new SystemParameterConverter(); }
		return systemParameterConverter;
	}
}
