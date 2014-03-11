package com.ta.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.text.format.Time;

public class DateNTimeConverter {
	public static String dateToTime(Date date){
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(date);
	}

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

	public static Date ISO8601toDate(String iso8601Date){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date d = sdf.parse(iso8601Date);
			return d;
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}		
	}

	public static Date ISO8601toTime(String iso8601Time){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("'PT'ss'S'");
			Date d = sdf.parse(iso8601Time);
			return d;
		} catch (ParseException e) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("'PT'HH'H'mm'M'");
				Date d = sdf.parse(iso8601Time);
				return d;
			} catch (ParseException e1) {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("'PT'HH'H'");
					Date d = sdf.parse(iso8601Time);
					return d;
				} catch (ParseException e2) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("'PT'mm'M'ss'S'");
						Date d = sdf.parse(iso8601Time);
						return d;
					} catch (ParseException e3) {
						try {
							SimpleDateFormat sdf = new SimpleDateFormat("'PT'HH'H'mm'M'ss'S'");
							Date d = sdf.parse(iso8601Time);
							return d;
						} catch (ParseException e4) {
							try {
								SimpleDateFormat sdf = new SimpleDateFormat("'PT'HH'H'ss'S'");
								Date d = sdf.parse(iso8601Time);
								return d;
							} catch (ParseException e5) {
								try {
									SimpleDateFormat sdf = new SimpleDateFormat("'PT'mm'M'");
									Date d = sdf.parse(iso8601Time);
									return d;
								} catch (ParseException e6) {
									e.printStackTrace();
									return null;
								}	
							}	
						}	
					}	
				}	
			}	
		}		
	}
}
