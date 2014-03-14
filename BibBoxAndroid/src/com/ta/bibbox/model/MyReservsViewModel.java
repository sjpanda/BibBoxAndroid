package com.ta.bibbox.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ta.bibbox.pojo.Reservation;
import com.ta.bibbox.pojo.ReservationState;

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
	public static List<Item> ITEMS = new ArrayList<Item>();

	/**
	 * A map of sample items, by ID.
	 */
	public static Map<String, Item> ITEM_MAP = new HashMap<String, Item>();
	
	private static List<Reservation> beingUsed = new ArrayList<Reservation>();
	private static List<Reservation> waiting = new ArrayList<Reservation>();
	private static List<Reservation> finished = new ArrayList<Reservation>();
	private static List<Reservation> canceled = new ArrayList<Reservation>();
	private static List<Reservation> absent = new ArrayList<Reservation>();
	
	public static void addItems(List<Reservation> reservs) {
		for(Item i : ITEMS) { i.getReservs().clear(); }
		ITEMS.clear();
		ITEM_MAP.clear();
		
		dispatchReservations(reservs);
		
		Item item = new Item("1", "En cours", beingUsed);
		ITEMS.add(item);
		item = new Item("2", "A venir", waiting);
		ITEMS.add(item);
		item = new Item("3", "Terminées", finished);
		ITEMS.add(item);
		item = new Item("4", "Annulées", canceled);
		ITEMS.add(item);
		item = new Item("5", "Abandonnées", absent);
		ITEMS.add(item);
		for(Item i : ITEMS){
			ITEM_MAP.put(String.valueOf(i.id), i);
		}	
	}
	
	/**
	 * A item representing a piece of content.
	 */
	public static class Item {
		private String id;
		private String name;
		private List<Reservation> reservations;
		
		public String getId() { return id; }
		public String getName() { return name; }
		public List<Reservation> getReservs() { return reservations; }

		public Item(String id, String name, List<Reservation> reservs) {
			this.id = id;
			this.name = name;
			this.reservations = reservs;
		}

		@Override
		public String toString() {
			return name;
		}
	}
	
	private static void dispatchReservations(List<Reservation> reservs){
		for(Reservation r : reservs){
			if(r.getState() == ReservationState.BeingUsed){
				beingUsed.add(r);
			}
			if(r.getState() == ReservationState.Waiting){
				waiting.add(r);
			}
			if(r.getState() == ReservationState.Finished){
				finished.add(r);
			}
			if(r.getState() == ReservationState.Canceled){
				canceled.add(r);
			}
			if(r.getState() == ReservationState.Absent){
				absent.add(r);
			}
		}
	}
}
