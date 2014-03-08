package com.ta.converter;

import com.ta.pojo.UserRole;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet UserRole à un POJO UserRole
 */
public class UserRoleConverter {

	public UserRole convertToObject(String role) {
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
	}

	private static UserRoleConverter userRoleConverter;
	private UserRoleConverter() {}

	public static UserRoleConverter instance() {
		if(userRoleConverter == null) {userRoleConverter = new UserRoleConverter(); }
		return userRoleConverter;
	}
}
