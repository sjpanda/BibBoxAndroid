package com.ta.converter;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.ta.pojo.Location;

/**
 * @author Jing SHU
 * @date 05/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet Location à un POJO Location
 */
public class LocationConverter{

	public Location convertToObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		try{
			Location l = new Location();
			l.setId(Integer.parseInt(soapObject.getProperty("ID").toString()));
			l.setName(soapObject.getProperty("Name").toString());
			l.setAddress(soapObject.getProperty("Address").toString());
			//l.setBeginReservTime(UserRoleConverter.instance().convertToObject(soapObject.getProperty("BeginReservTime").toString()));
			//l.setEndReservTime(UserRoleConverter.instance().convertToObject(soapObject.getProperty("EndReservTime").toString()));
			return l;
		} catch(Exception e){
			return null;
		}
	}

	public List<Location> convertToListObject(SoapObject soapObject) {
		if(soapObject == null) { return null; }
		List<Location> locations = new ArrayList<Location>();
		for(int i=0; i<soapObject.getPropertyCount(); i++){
			locations.add(convertToObject((SoapObject)soapObject.getProperty(i)));
		}
		return locations;
	}

	private static LocationConverter locationConverter;
	private LocationConverter() {}
	
	public static LocationConverter instance() {
		if(locationConverter == null) {locationConverter = new LocationConverter(); }
		return locationConverter;
	}
}
