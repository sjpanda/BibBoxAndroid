package com.ta.datentime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateNTimeUtil {
	public static Date stringToDate(String date){
	    try {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        return sdf.parse(date);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	
	public static Date stringToTime(String time){
	    try {
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	        return sdf.parse(time);
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}
	
	public static String dateToISO8601(String date){
		TimeZone tz = TimeZone.getTimeZone("PT");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		df.setTimeZone(tz);
		return df.format(stringToDate(date));
	}
	
	public static String timeToISO8601(String time){
		TimeZone tz = TimeZone.getTimeZone("PT");
		DateFormat df = new SimpleDateFormat("'PT'HH'H'mm'M'ss'S'");
		df.setTimeZone(tz);
		return df.format(stringToTime(time));
	}
}
