package com.ta.bibbox.service;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.ta.bibbox.converter.SystemParameterConverter;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe permettant d'appeler les méthodes du web service concernant les paramètres du système
 */
public class ServiceSystemParameter {
	private static final String serviceName = "ServiceSystemParameter";
	
	public int GetReservationMinInterval()
    {
		String methodName = "GetReservationMinInterval";
		SoapObject request = new SoapObject(ServiceUtil.NAMESPACE, methodName);
		SoapPrimitive result = (SoapPrimitive)ServiceUtil.callService(serviceName, methodName, request);
		String s = SystemParameterConverter.instance().convertToObject(result);
		try{
			return Integer.parseInt(s);
		} catch (Exception e)
		{
			return -1;
		}
    }

    public int GetMaxReservDays()
    {
    	String methodName = "GetMaxReservDays";
		SoapObject request = new SoapObject(ServiceUtil.NAMESPACE, methodName);
		SoapPrimitive result = (SoapPrimitive)ServiceUtil.callService(serviceName, methodName, request);
		String s = SystemParameterConverter.instance().convertToObject(result);
		try{
			return Integer.parseInt(s);
		} catch (Exception e)
		{
			return -1;
		}
    }
}
