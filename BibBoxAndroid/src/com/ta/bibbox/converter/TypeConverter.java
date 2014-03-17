package com.ta.bibbox.converter;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import com.ta.bibbox.pojo.BoxType;
import com.ta.bibbox.pojo.HeadPhoneType;
import com.ta.bibbox.pojo.PCType;
import com.ta.bibbox.pojo.Type;

/**
 * @author Jing SHU
 * @date 10/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet Type à un POJO Type
 */
public class TypeConverter {
	public Type convertToObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		try{
			Type type;
			List<String> allAttributesNames = getAllAttributesNames(soapObject);
			if(allAttributesNames.contains("NbSeat")){
				type = new BoxType();
				((BoxType)type).setNbSeat(Integer.parseInt(soapObject.getProperty("NbSeat").toString()));
				((BoxType)type).setScreen(ScreenConverter.instance().convertToObject(soapObject.getProperty("Screen").toString()));
			} else {
				if(allAttributesNames.contains("CPU")){
					type = new PCType();
					((PCType)type).setBrand(ConverterUtil.convertNullObject(soapObject.getProperty("Brand").toString()));
					((PCType)type).setCpu(ConverterUtil.convertNullObject(soapObject.getProperty("CPU").toString()));
					((PCType)type).setDiskSize(ConverterUtil.convertNullObject(soapObject.getProperty("DiskSize").toString()));
					((PCType)type).setRam(ConverterUtil.convertNullObject(soapObject.getProperty("RAM").toString()));
				} else {
					type = new HeadPhoneType();
					((HeadPhoneType)type).setBrand(ConverterUtil.convertNullObject(soapObject.getProperty("Brand").toString()));
				}
			}
			type.setId(Integer.parseInt(soapObject.getProperty("ID").toString()));

			return type;
		} catch (Exception e){
			return null;
		}
	}

	public List<Type> convertToListObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		List<Type> types = new ArrayList<Type>();
		for(int i=0; i<soapObject.getPropertyCount(); i++){
			types.add(convertToObject((SoapObject)soapObject.getProperty(i)));
		}
		return types;
	}

	private static TypeConverter typeConverter;
	private TypeConverter() {}

	public static TypeConverter instance() {
		if(typeConverter == null) {typeConverter = new TypeConverter(); }
		return typeConverter;
	}

	private List<String> getAllAttributesNames(SoapObject soapObject){
		List<String> allAttributesNames = new ArrayList<String>();
		PropertyInfo pi = new PropertyInfo();
		for(int i = 0; i < soapObject.getPropertyCount(); i++){
			soapObject.getPropertyInfo(i, null, pi);
			allAttributesNames.add(pi.name);
		}
		return allAttributesNames;
	}
}
