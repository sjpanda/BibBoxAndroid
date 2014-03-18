package com.ta.bibbox.service;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

/**
 * @author Jing SHU
 * @date 18/03/2014
 * @copyright TA Copyright
 * @brief La classe permettant d'appeler les méthodes du web service concernant la pénalité
 */
public class ServicePenalty {
	private static final String serviceName = "ServicePenalty";

	public boolean hasPenalty(String login)
    {
		String methodName = "HasPenalty";
		SoapObject request = new SoapObject(ServiceUtil.NAMESPACE, methodName);
		request.addProperty("login", login);
        SoapPrimitive result = (SoapPrimitive)ServiceUtil.callService(serviceName, methodName, request);
        try{
			return Boolean.parseBoolean(result.toString());
		} catch (Exception e){
			return false;
		}
    }
}
