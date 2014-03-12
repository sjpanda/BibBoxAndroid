package com.ta.activity;

import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ta.converter.DateNTimeConverter;
import com.ta.pojo.Location;
import com.ta.pojo.MonoAllocable;
import com.ta.pojo.Reservation;
import com.ta.pojo.ReservationState;
import com.ta.pojo.User;
import com.ta.service.ServiceAllocable;
import com.ta.service.ServiceAuthentification;
import com.ta.service.ServiceReservation;
import com.ta.service.ServiceSystemParameter;

public class ExamplesWCFCall {
	public static String testWCFCall(){
		StringBuilder sb = new StringBuilder();
		ServiceAuthentification auth = new ServiceAuthentification();
		User u = auth.login("Basic1", "password1");
		if(u != null){
			sb.append("Hello " + u.getLogin() + "\n"); 
		} else {
			sb.append("Oops\n"); 
		}
		
		ServiceSystemParameter sysParam = new ServiceSystemParameter();
		int reservationMinInterval = sysParam.GetReservationMinInterval();
		sb.append("reservationMinInterval : " + reservationMinInterval + "\n"); 
		int maxReservDays = sysParam.GetMaxReservDays();
		sb.append("maxReservDays : " + maxReservDays + "\n"); 
		
		ServiceAllocable alloc = new ServiceAllocable();
		List<Integer> monoAllocablePossibleNbSeat = alloc.GetMonoAllocablePossibleNbSeat();
		for(int nbSeat : monoAllocablePossibleNbSeat){
			sb.append("nbSeat : " + nbSeat + "\n"); 
		}
		
		List<String> monoAllocableEquips = alloc.GetMonoAllocableEquips();
		for(String equip : monoAllocableEquips){
			sb.append("equip : " + equip + "\n"); 
		}
		
		List<String> locations = alloc.GetAllLocations();
		for(String location : locations){
			sb.append("location : " + location + "\n"); 
		}
		
//		boolean inserted = alloc.InsertAllocable("", "Box Android", "", 1, 1);
//		sb.append("Box Android inserted ? " + inserted + "\n"); 
//		
//		List<Type> types = alloc.GetAllTypesAllocable();
//		for(Type type : types){
//			sb.append("type : " + type.getClass().getName() + "\n"); 
//		}
//		
//		List<Allocable> allocables = alloc.GetAllAllocable();
//		for(Allocable a : allocables){
//			sb.append("allocable : " + a.getName() + "\n"); 
//		}
		
		sb.append("For students : \n");
		List<MonoAllocable> monoAllocables = alloc.SearchMonoAllocables(1, "Indifferent", "Indifferent", "2014-05-12 00:00:00", "10:00:00", "13:00:00");
		for(MonoAllocable m : monoAllocables){
			sb.append("mono allocable : " + m.getName() + "\n");
		}
		
//		sb.append("For teachers : \n");
//		List<MonoAllocable> monoAllocablesT = alloc.SearchMonoAllocablesForTeacher(1, "Indifferent", "Indifferent", "2014-05-12 00:00:00", "10:00:00", "13:00:00");
//		for(MonoAllocable m : monoAllocablesT){
//			sb.append("mono allocable : " + m.getName() + "\n");
//		}
		
		Map<String, Integer> multiAllocables = alloc.GetMultiAllocablesByMonoAllocable(1, "2014-05-12 00:00:00", "10:00:00", "13:00:00");
		for(Entry<String, Integer> e : multiAllocables.entrySet()){
			sb.append("nb de " + e.getKey() + " : " + e.getValue() + "\n");
		}
		
		Date beginReservTime = alloc.GetBeginReservTime();
		sb.append("beginReservTime : " + DateNTimeConverter.dateToTime(beginReservTime) + "\n");
		
		Date endReservTime = alloc.GetEndReservTime();
		sb.append("endReservTime : " + DateNTimeConverter.dateToTime(endReservTime) + "\n");
		
		ServiceReservation reserv = new ServiceReservation();
		List<Reservation> reservations = reserv.GetAllReservationsByUser("Basic1");
		for(Reservation r : reservations){
			sb.append("reservation of " + r.getLeader().getLogin() + " from " + r.getBeginDate().toString() + " to " + r.getEndDate().toString() + "\n");
		}
		
		Dictionary<String, Integer> multiAllocablesSelected = new Hashtable<String, Integer>();
		multiAllocablesSelected.put("PC portable", 1);
		multiAllocablesSelected.put("Casque audio", 0);
		boolean validated = reserv.ValidateReservationString("Basic1", 5, 1, "2014-04-03 00:00:00", "09:00:00", "11:30:00", "PC portable:1;Casque audio:0");
		sb.append("new reservation of Basic1 from 2014-04-03 9:00:00 to 2014-04-03 11:30:00 validated ? " + validated + "\n");
		
		Reservation canceled = reserv.TerminateReservationString(9, ReservationState.Canceled);
		if(canceled != null){
			sb.append("reservation of Basic1 from " + canceled.getBeginDate().toString() + " to " + canceled.getEndDate().toString() + " canceled ? true\n");
		} else {
			sb.append("failed to cancel the reservation number 9\n");
		}
		return sb.toString();
	}
}
