package com.ta.bibbox.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ta.bibbox.pojo.MonoAllocable;
import com.ta.bibbox.pojo.MultiAllocable;
import com.ta.bibbox.pojo.ReservationState;
import com.ta.bibbox.pojo.User;

/**
 * @author Jing SHU
 * @date 13/03/2014
 * @copyright TA Copyright
 * @brief Le model pour les vues de "mes réservations"
 */
public class MyReservsViewModel {

	/**
	 * An array of reservation items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

	static {
		// Add 3 sample items.
		addItem(new DummyItem());
		addItem(new DummyItem());
		addItem(new DummyItem());
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(String.valueOf(item.id), item);
	}
	
	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public String content;
		
		
		private int id;
	    private Date beginDate;
	    private Date endDate;
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
		public Date getBeginDate() {
			return beginDate;
		}
		public void setBeginDate(Date beginDate) {
			this.beginDate = beginDate;
		}
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
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
		

//		public DummyItem(String id, String content) {
//			this.id = id;
//			this.content = content;
//		}

		@Override
		public String toString() {
			return content;
		}
	}
}
