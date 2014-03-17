package com.ta.bibbox.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.ksoap2.serialization.SoapObject;

import com.ta.bibbox.pojo.Location;

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
			l.setName(ConverterUtil.convertNullObject(soapObject.getProperty("Name").toString()));
			l.setAddress(ConverterUtil.convertNullObject(soapObject.getProperty("Address").toString()));
			l.setBeginReservTime(parseLastModified(soapObject.getProperty("BeginReservTime").toString()));
			l.setBeginReservTime(DateNTimeConverter.ISO8601toTime(soapObject.getProperty("BeginReservTime").toString()));
			l.setEndReservTime(DateNTimeConverter.ISO8601toTime(soapObject.getProperty("EndReservTime").toString()));
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
	
	private Date parseLastModified(String lastModified) {
		SimpleDateFormat httpHeaderDateFormatter = new SimpleDateFormat("hh:mm:ss", Locale.FRANCE);
	    Date date = null;
	    if (lastModified != null && lastModified.length() > 0) {
	        try {
	            date = httpHeaderDateFormatter.parse(lastModified);
	        } catch (ParseException e) {
	            // otherwise we just leave it empty
	        }
	    }
	    return date;
	}
}
