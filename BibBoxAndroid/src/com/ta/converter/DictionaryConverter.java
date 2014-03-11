package com.ta.converter;

import java.util.HashMap;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;

public class DictionaryConverter {
	public Map<String, Integer> convertToMapStringIntObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		Map<String, Integer> result = new HashMap<String, Integer>();
		try{
			for(int i=0; i<soapObject.getPropertyCount(); i++){
				try{
					SoapObject entry = (SoapObject)soapObject.getProperty(i);
					result.put(entry.getProperty(0).toString(), Integer.parseInt(entry.getProperty(1).toString()));
				} catch (Exception e){}
			}
		} catch (Exception e){}
		return result;
	}

	private static DictionaryConverter dictionaryConverter;
	private DictionaryConverter() {}

	public static DictionaryConverter instance() {
		if(dictionaryConverter == null) {dictionaryConverter = new DictionaryConverter(); }
		return dictionaryConverter;
	}
}
