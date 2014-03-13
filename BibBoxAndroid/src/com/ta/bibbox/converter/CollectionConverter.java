package com.ta.bibbox.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.ksoap2.serialization.SoapObject;

import com.ta.bibbox.service.ServiceUtil;

public class CollectionConverter {
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
	
	public String mapStringIntToCustomString(Map<String, Integer> keyValues, String name){
		StringBuffer s = new StringBuffer();
		for(Entry<String, Integer> entry : keyValues.entrySet()){
			s.append(entry.getKey() + ":" + entry.getValue() + ";");
		}
		return s.toString();
	}
	
	private static CollectionConverter collectionConverter;
	private CollectionConverter() {}

	public static CollectionConverter instance() {
		if(collectionConverter == null) {collectionConverter = new CollectionConverter(); }
		return collectionConverter;
	}
}
