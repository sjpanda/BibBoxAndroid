package com.ta.converter;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.ta.pojo.Allocable;

public class AllocableConverter {
	public Allocable convertToObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		return null;
		
//		try{
//			User u = new User();
//			u.setId(Integer.parseInt(soapObject.getProperty("ID").toString()));
//			u.setFirstName(soapObject.getProperty("FirstName").toString());
//			u.setLastName(soapObject.getProperty("LastName").toString());
//			u.setAddress(soapObject.getProperty("Address").toString());
//			u.setEmail(soapObject.getProperty("Email").toString());
//			u.setLogin(soapObject.getProperty("Login").toString());
//			u.setPassword(soapObject.getProperty("Password").toString());
//			u.setRole(UserRoleConverter.instance().convertToObject(soapObject.getProperty("Role").toString()));
//			return u;
//		} catch(Exception e){
//			return null;
//		}
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
