package com.ta.pojo;

import com.ta.datentime.TimeSpan;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief Le POJO qui représente un lieu
 */
public class Location {
	private int id;
	private String name;
	private String address;
	private TimeSpan beginReservTime;
	private TimeSpan endReservTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public TimeSpan getBeginReservTime() {
		return beginReservTime;
	}
	public void setBeginReservTime(TimeSpan beginReservTime) {
		this.beginReservTime = beginReservTime;
	}
	public TimeSpan getEndReservTime() {
		return endReservTime;
	}
	public void setEndReservTime(TimeSpan endReservTime) {
		this.endReservTime = endReservTime;
	}
	
	
}
