package com.ta.service;

import org.ksoap2.serialization.SoapObject;

import com.ta.converter.UserConverter;
import com.ta.pojo.User;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe permettant d'appeler les méthodes du web service concernant l'authentification
 */
public class ServiceAuthentification {
	private static final String serviceName = "ServiceAuthentification";
	
	public User login(String login, String password)
    {
		String methodName = "Login";
		SoapObject request = new SoapObject(ServiceUtil.NAMESPACE, methodName);
		request.addProperty("login", login);
		request.addProperty("password", password);
        SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		return UserConverter.instance().convertToObject(result);
    }
}
