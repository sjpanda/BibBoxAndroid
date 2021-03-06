package com.ta.bibbox.converter;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.ta.bibbox.pojo.User;

/**
 * @author Jing SHU
 * @date 05/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet User � un POJO User
 */
public class UserConverter {
	public User convertToObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		try{		
			User u = new User();
			u.setId(Integer.parseInt(soapObject.getProperty("ID").toString()));
			u.setFirstName(ConverterUtil.convertNullObject(soapObject.getProperty("FirstName").toString()));
			u.setLastName(ConverterUtil.convertNullObject(soapObject.getProperty("LastName").toString()));
			u.setAddress(ConverterUtil.convertNullObject(soapObject.getProperty("Address").toString()));
			u.setEmail(ConverterUtil.convertNullObject(soapObject.getProperty("Email").toString()));
			u.setLogin(ConverterUtil.convertNullObject(soapObject.getProperty("Login").toString()));
			u.setPassword(ConverterUtil.convertNullObject(soapObject.getProperty("Password").toString()));
			u.setRole(UserRoleConverter.instance().convertToObject(soapObject.getProperty("Role").toString()));
			return u;
		} catch(Exception e){
			return null;
		}
	}

	public List<User> convertToListObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		List<User> users = new ArrayList<User>();
		for(int i=0; i<soapObject.getPropertyCount(); i++){
			users.add(convertToObject((SoapObject)soapObject.getProperty(i)));
		}
		return users;
	}
	
	private static UserConverter userConverter;
	private UserConverter() {}
	
	public static UserConverter instance() {
		if(userConverter == null) {userConverter = new UserConverter(); }
		return userConverter;
	}
}
