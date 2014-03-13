package com.ta.bibbox.converter;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.ta.bibbox.pojo.Allocable;
import com.ta.bibbox.pojo.MonoAllocable;
import com.ta.bibbox.pojo.MultiAllocable;
import com.ta.bibbox.pojo.Reservation;

/**
 * @author Jing SHU
 * @date 10/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet Reservation à un POJO Reservation
 */
public class ReservationConverter {
	public Reservation convertToObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }			
		try{	
			Reservation r = new Reservation();
			r.setId(Integer.parseInt(soapObject.getProperty("ID").toString()));
			r.setBeginDate(DateNTimeConverter.ISO8601toDate(soapObject.getProperty("BeginDate").toString()));
			r.setEndDate(DateNTimeConverter.ISO8601toDate(soapObject.getProperty("EndDate").toString()));
			r.setLeader(UserConverter.instance().convertToObject((SoapObject)soapObject.getProperty("Leader")));
			r.setMonoAllocable((MonoAllocable)AllocableConverter.instance().convertToObject((SoapObject)soapObject.getProperty("MonoAllocable")));
			r.setNbUser(Integer.parseInt(soapObject.getProperty("NbUser").toString()));
			r.setState(ReservationStateConverter.instance().convertToObject(soapObject.getProperty("State").toString()));
			List<Allocable> allocables = AllocableConverter.instance().convertToListObject((SoapObject)soapObject.getProperty("MultiAllocables"));
			List<MultiAllocable> multiAllocables = new ArrayList<MultiAllocable>();
			for(Allocable a : allocables){
				multiAllocables.add((MultiAllocable)a);
			}
			r.setMultiAllocables(multiAllocables);
			return r;
		} catch(Exception e){
			return null;
		}
	}

	public List<Reservation> convertToListObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		List<Reservation> reservations = new ArrayList<Reservation>();
		for(int i=0; i<soapObject.getPropertyCount(); i++){
			reservations.add(convertToObject((SoapObject)soapObject.getProperty(i)));
		}
		return reservations;
	}
	
	private static ReservationConverter reservationConverter;
	private ReservationConverter() {}
	
	public static ReservationConverter instance() {
		if(reservationConverter == null) {reservationConverter = new ReservationConverter(); }
		return reservationConverter;
	}
}
