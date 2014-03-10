package com.ta.service;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe qui appelle effectivement les web services
 */
public class ServiceUtil {
	private static String URL = "";
	private static String SOAP_ACTION = "";
	
	private static void init(String serviceName, String methodName){
		URL = "http://132.227.69.163:81/" + serviceName + ".svc";
		SOAP_ACTION = "http://tempuri.org/I" + serviceName + "/" + methodName;
	}
	
	public static Object callService(String serviceName, String methodName, SoapObject request){
		init(serviceName, methodName);
		
		System.out.println("tata1 : URL : " + URL);
		System.out.println("tata2 : SOAP_ACTION : " + SOAP_ACTION);
		
		HttpTransportSE androidHttpTransport = null;
		System.out.println("tata3");
		try {
			System.out.println("tata4");
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			System.out.println("tata5");
			envelope.dotNet = true;
			System.out.println("tata6");
			envelope.setOutputSoapObject(request);
			System.out.println("tata7");

			androidHttpTransport = new HttpTransportSE(URL);
			System.out.println("tata8");
			androidHttpTransport.debug = true;
			System.out.println("tata9");
			androidHttpTransport.call(SOAP_ACTION, envelope);
			System.out.println("tata10");
			
			System.out.println("tata10 bis : " + envelope.getResponse().toString());
			return envelope.getResponse();
		} catch (Exception e) {
			System.out.println("tata11");
			System.out.println("Error:\n" + e.getClass().getName() + "\n" + e.getMessage() + "\n\nRequest : \n" + androidHttpTransport.requestDump + "\nResponse : \n" + androidHttpTransport.responseDump + "\n");
			System.out.println("tata12");
			return null;
		}
	}
}
