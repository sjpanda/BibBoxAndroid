package com.ta.bibbox.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jing SHU
 * @date 17/03/2014
 * @copyright TA Copyright
 * @brief Les models pour ExpandableListView des mono-allouables
 */
public class AMonoAllocableViewModel {
	public static class MonoAllocableList{
		private String location;
		private String name;
		private List<MonoAllocableDetail> details;
		
		public MonoAllocableList(String location, String name){
			this.location = location;
			this.name = name;
			details = new ArrayList<MonoAllocableDetail>();
		}
		
		public String getLcoation() {
			return location;
		}
		public void setLcoation(String location) {
			this.location = location;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<MonoAllocableDetail> getDetails() {
			return details;
		}
		public void setDetails(List<MonoAllocableDetail> details) {
			this.details = details;
		}	
	}
	
	public static class MonoAllocableDetail{
		private String id;
		private String name;
		private String location;
		private String nbSeat;
	    private String screen;
	    private String description;
	    
	    public MonoAllocableDetail(String id, String name, String location, String nbSeat, String screen, String description){
	    	this.id = id;
	    	this.name = name;
	    	this.location = location;
	    	this.nbSeat = nbSeat;
	    	this.screen = screen;
	    	this.description = description;
	    }

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getNbSeat() {
			return nbSeat;
		}

		public void setNbSeat(String nbSeat) {
			this.nbSeat = nbSeat;
		}

		public String getScreen() {
			return screen;
		}

		public void setScreen(String screen) {
			this.screen = screen;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}
		
	}
}
