package com.ta.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.ksoap2.serialization.SoapObject;

import com.ta.service.ServiceUtil;

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
	
	public SoapObject mapStringIntToSoapObject(Map<String, Integer> keyValues, String name){
		SoapObject o = new SoapObject(ServiceUtil.NAMESPACE, name);
		for(Entry<String, Integer> entry : keyValues.entrySet()){
			o.addProperty(entry.getKey(), entry.getValue());
		}
		return o;
	}
	
	private static CollectionConverter collectionConverter;
	private CollectionConverter() {}

	public static CollectionConverter instance() {
		if(collectionConverter == null) {collectionConverter = new CollectionConverter(); }
		return collectionConverter;
	}
}