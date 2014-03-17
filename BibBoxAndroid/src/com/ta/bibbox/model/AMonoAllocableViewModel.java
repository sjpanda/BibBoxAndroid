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
		private String name;
	    private String value;
	    
	    public MonoAllocableDetail(String name, String value){
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
