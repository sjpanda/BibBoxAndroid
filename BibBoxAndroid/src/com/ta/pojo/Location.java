package com.ta.pojo;

import java.util.Date;

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
	private Date beginReservTime;
	private Date endReservTime;
	
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
	public Date getBeginReservTime() {
		return beginReservTime;
	}
	public void setBeginReservTime(Date beginReservTime) {
		this.beginReservTime = beginReservTime;
	}
	public Date getEndReservTime() {
		return endReservTime;
	}
	public void setEndReservTime(Date endReservTime) {
		this.endReservTime = endReservTime;
	}
	
	
}
