package com.ta.converter;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.ta.pojo.UserRole;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet UserRole à un POJO UserRole
 */
public class UserRoleConverter implements IConverter<UserRole> {

	@Override
	public UserRole convertToObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		try{
			String role = soapObject.toString();
			if(role.equalsIgnoreCase("Basic")){
				return UserRole.Basic;
			}
			if(role.equalsIgnoreCase("Teacher")){
				return UserRole.Teacher;
			}
			if(role.equalsIgnoreCase("Agent")){
				return UserRole.Agent;
			}
			return null;
		} catch(Exception e){
			return null;
		}
	}

	@Override
	public List<UserRole> convertToListObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		List<UserRole> userRoles = new ArrayList<UserRole>();
		for(int i=0; i<soapObject.getPropertyCount(); i++){
			userRoles.add(convertToObject((SoapObject)soapObject.getProperty(i)));
		}
		return userRoles;
	}

	private static UserRoleConverter userRoleConverter;
	private UserRoleConverter() {}
	
	public static UserRoleConverter instance() {
		if(userRoleConverter == null) {userRoleConverter = new UserRoleConverter(); }
		return userRoleConverter;
	}
}
