package com.ta.pojo;

import java.util.Date;
import java.util.List;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief Le POJO qui représente une pénalité
 */
public class Penalty {
	private int id;
    private Date creationDate;
    private User user;
    private List<Allocable> allocables;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
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
