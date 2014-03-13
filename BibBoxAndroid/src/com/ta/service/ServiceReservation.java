package com.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.ta.converter.DateNTimeConverter;
import com.ta.converter.ReservationConverter;
import com.ta.pojo.Reservation;
import com.ta.pojo.ReservationState;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe permettant d'appeler les méthodes du web service concernant les réservations
 */
public class ServiceReservation {
	private static final String serviceName = "ServiceReservation";
	
    public List<Reservation> GetAllReservationsByUser(String login)
    {
    	String methodName = "GetAllReservationsByUser";
		SoapObject request = new SoapObject(ServiceUtil.NAMESPACE, methodName);
		request.addProperty("login", login);
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		List<Reservation> reservations = ReservationConverter.instance().convertToListObject(result);
		if(reservations == null){
			reservations = new ArrayList<Reservation>();
		}
		return reservations;
    }

    public boolean ValidateReservationString(String leaderLogin, int idMonoAllocable, int nbPerson, String date, String beginTime, String endTime, String multiAllocables)
    {
    	String methodName = "ValidateReservationString";
		SoapObject request = new SoapObject(ServiceUtil.NAMESPACE, methodName);
		request.addProperty("leaderLogin", leaderLogin);
		request.addProperty("idMonoAllocable", idMonoAllocable);
		request.addProperty("nbPerson", nbPerson);
		request.addProperty("date", DateNTimeConverter.dateToISO8601(date));
		request.addProperty("beginTime", DateNTimeConverter.timeToISO8601(beginTime));
		request.addProperty("endTime", DateNTimeConverter.timeToISO8601(endTime));
		request.addProperty("multiAllocables", multiAllocables);
		
		SoapPrimitive result = (SoapPrimitive)ServiceUtil.callService(serviceName, methodName, request);
		try{
			return Boolean.parseBoolean(result.toString());
		} catch (Exception e){
			return false;
		}
    }

    public Reservation TerminateReservationString(int idReservation, ReservationState state)
    {
    	String methodName = "TerminateReservationString";
		SoapObject request = new SoapObject(ServiceUtil.NAMESPACE, methodName);
		request.addProperty("idReservation", idReservation);
		request.addProperty("state", state.toString());
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		return ReservationConverter.instance().convertToObject(result);
    }
}
