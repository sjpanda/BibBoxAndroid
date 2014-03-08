package com.ta.pojo;

import java.util.List;

import org.joda.time.DateTime;

public class Penalty {
	private int id;
    private DateTime creationDate;
    private User user;
    private List<Allocable> allocables;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DateTime getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(DateTime creationDate) {
		this.creationDate = creationDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Allocable> getAllocables() {
		return allocables;
	}
	public void setAllocables(List<Allocable> allocables) {
		this.allocables = allocables;
	}
    
    
}
