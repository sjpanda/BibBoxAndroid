package com.ta.converter;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.ta.pojo.Allocable;
import com.ta.pojo.BoxType;
import com.ta.pojo.MonoAllocable;
import com.ta.pojo.MultiAllocable;
import com.ta.pojo.Type;

/**
 * @author Jing SHU
 * @date 10/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet Allocable à un POJO Allocable
 */
public class AllocableConverter {
	public Allocable convertToObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }			
		try{	
			Allocable a;
			Type type = TypeConverter.instance().convertToObject((SoapObject)soapObject.getProperty("Type"));
			if(type instanceof BoxType){
				a = new MonoAllocable();
			} else {
				a = new MultiAllocable();
				((MultiAllocable)a).setBarCode(soapObject.getProperty("BarCode").toString());
				
			}
			a.setId(Integer.parseInt(soapObject.getProperty("ID").toString()));
			a.setName(soapObject.getProperty("Name").toString());
			a.setDescription(soapObject.getProperty("Description").toString());
			a.setLocation(LocationConverter.instance().convertToObject((SoapObject)soapObject.getProperty("Location")));
			a.setType(type);
			a.setState(AllocableStateConverter.instance().convertToObject(soapObject.getProperty("State").toString()));
			return a;
		} catch(Exception e){
			return null;
		}
	}

	public List<Allocable> convertToListObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		List<Allocable> allocables = new ArrayList<Allocable>();
		for(int i=0; i<soapObject.getPropertyCount(); i++){
			allocables.add(convertToObject((SoapObject)soapObject.getProperty(i)));
		}
		return allocables;
	}
	
	private static AllocableConverter allocableConverter;
	private AllocableConverter() {}
	
	public static AllocableConverter instance() {
		if(allocableConverter == null) {allocableConverter = new AllocableConverter(); }
		return allocableConverter;
	}
}
