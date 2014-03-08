package com.ta.pojo;

import java.util.List;

import org.joda.time.DateTime;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief Le POJO qui représente une réservation
 */
public class Reservation {
	private int id;
    private DateTime beginDate;
    private DateTime endDate;
    private int nbUser;
    private ReservationState state;
    private MonoAllocable monoAllocable;
    private List<MultiAllocable> multiAllocables;
    private User leader;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DateTime getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(DateTime beginDate) {
		this.beginDate = beginDate;
	}
	public DateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
	public int getNbUser() {
		return nbUser;
	}
	public void setNbUser(int nbUser) {
		this.nbUser = nbUser;
	}
	public ReservationState getState() {
		return state;
	}
	public void setState(ReservationState state) {
		this.state = state;
	}
	public MonoAllocable getMonoAllocable() {
		return monoAllocable;
	}
	public void setMonoAllocable(MonoAllocable monoAllocable) {
		this.monoAllocable = monoAllocable;
	}
	public List<MultiAllocable> getMultiAllocables() {
		return multiAllocables;
	}
	public void setMultiAllocables(List<MultiAllocable> multiAllocables) {
		this.multiAllocables = multiAllocables;
	}
	public User getLeader() {
		return leader;
	}
	public void setLeader(User leader) {
		this.leader = leader;
	}
}
