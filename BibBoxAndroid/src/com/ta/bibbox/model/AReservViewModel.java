package com.ta.bibbox.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jing SHU
 * @date 14/03/2014
 * @copyright TA Copyright
 * @brief Les models pour ExpandableListView des réservations
 */
public class AReservViewModel {
	public static class ReservList{
		private String location;
		private String monoAllocableName;
		private List<ReservDetail> details;
		
		public ReservList(String location, String monoAllocableName){
			this.location = location;
			this.monoAllocableName = monoAllocableName;
			details = new ArrayList<ReservDetail>();
		}
		
		public String getLcoation() {
			return location;
		}
		public void setLcoation(String location) {
			this.location = location;
		}
		public String getMonoAllocableName() {
			return monoAllocableName;
		}
		public void setMonoAllocableName(String monoAllocableName) {
			this.monoAllocableName = monoAllocableName;
		}
		public List<ReservDetail> getDetails() {
			return details;
		}
		public void setDetails(List<ReservDetail> details) {
			this.details = details;
		}	
	}
	
	public static class ReservDetail{
		private String name;
	    private String value;
	    
	    public ReservDetail(String name, String value){
	    	this.name = name;
	    	this.value = value;
	    }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
