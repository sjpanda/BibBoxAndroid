package com.ta.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import com.ta.converter.AllocableConverter;
import com.ta.converter.DateNTimeConverter;
import com.ta.converter.DictionaryConverter;
import com.ta.converter.LocationConverter;
import com.ta.converter.TypeConverter;
import com.ta.pojo.Allocable;
import com.ta.pojo.Location;
import com.ta.pojo.MonoAllocable;
import com.ta.pojo.Type;


/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe permettant d'appeler les méthodes du web service concernant les allouables
 */
public class ServiceAllocable {
	private static String NAMESPACE = "http://tempuri.org/";
	private static final String serviceName = "ServiceAllocable";

	public List<MonoAllocable> SearchMonoAllocables(int nbPerson, String equip, String place, String date, String beginTime, String endTime)
	{
		String methodName = "SearchMonoAllocables";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		request.addProperty("nbPerson", nbPerson);
		request.addProperty("equip", equip);
		request.addProperty("place", place);
		request.addProperty("date", DateNTimeConverter.dateToISO8601(date));
		request.addProperty("beginTime", DateNTimeConverter.timeToISO8601(beginTime));
		request.addProperty("endTime", DateNTimeConverter.timeToISO8601(endTime));
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		List<Allocable> allocables = AllocableConverter.instance().convertToListObject(result);
		List<MonoAllocable> monoAllocables = new ArrayList<MonoAllocable>();
		if(allocables != null){
			for(Allocable a : allocables){
				monoAllocables.add((MonoAllocable)a);
			}
		}
		return monoAllocables;
	}

	public List<MonoAllocable> SearchMonoAllocablesForTeacher(int nbPerson, String equip, String place, String date, String beginTime, String endTime)
	{
		String methodName = "SearchMonoAllocablesForTeacher";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		request.addProperty("nbPerson", nbPerson);
		request.addProperty("equip", equip);
		request.addProperty("place", place);
		request.addProperty("date", DateNTimeConverter.dateToISO8601(date));
		request.addProperty("beginTime", DateNTimeConverter.timeToISO8601(beginTime));
		request.addProperty("endTime", DateNTimeConverter.timeToISO8601(endTime));
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		List<Allocable> allocables = AllocableConverter.instance().convertToListObject(result);
		List<MonoAllocable> monoAllocables = new ArrayList<MonoAllocable>();
		if(allocables != null){
			for(Allocable a : allocables){
				monoAllocables.add((MonoAllocable)a);
			}
		}
		return monoAllocables;
	}

	public List<Integer> GetMonoAllocablePossibleNbSeat()
	{
		String methodName = "GetMonoAllocablePossibleNbSeat";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		List<Integer> nbSeat = new ArrayList<Integer>();
		if(result != null){
			for(int i=0; i<result.getPropertyCount(); i++){
				String nb = result.getProperty(i).toString();
				try{
					nbSeat.add(Integer.parseInt(nb));
				} catch (Exception e) {}
			}
		}
		return nbSeat;
	}

	public List<String> GetMonoAllocableEquips()
	{
		String methodName = "GetMonoAllocableEquips";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		List<String> equips = new ArrayList<String>();
		if(result != null){
			for(int i=0; i<result.getPropertyCount(); i++){
				equips.add(result.getProperty(i).toString());
			}
		}
		return equips;
	}

	public List<Location> GetAllLocations()
	{
		String methodName = "GetAllLocations";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		List<Location> locations = LocationConverter.instance().convertToListObject(result);
		if(locations == null){
			locations = new ArrayList<Location>();
		}
		return locations;
	}

	public Date GetBeginReservTime()
	{
		String methodName = "GetBeginReservTime";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		SoapPrimitive result = (SoapPrimitive)ServiceUtil.callService(serviceName, methodName, request);
		return DateNTimeConverter.ISO8601toTime(result.toString());
	}

	public Date GetEndReservTime()
	{
		String methodName = "GetEndReservTime";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		SoapPrimitive result = (SoapPrimitive)ServiceUtil.callService(serviceName, methodName, request);
		return DateNTimeConverter.ISO8601toTime(result.toString());
	}

	public Map<String, Integer> GetMultiAllocablesByMonoAllocable(int idMonoAllocable, String date, String beginTime, String endTime)
	{
		String methodName = "GetMultiAllocablesByMonoAllocable";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		request.addProperty("idMonoAllocable", idMonoAllocable);
		request.addProperty("date", DateNTimeConverter.dateToISO8601(date));
		request.addProperty("beginTime", DateNTimeConverter.timeToISO8601(beginTime));
		request.addProperty("endTime", DateNTimeConverter.timeToISO8601(endTime));
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		Map<String, Integer> multiAllocables = DictionaryConverter.instance().convertToMapStringIntObject(result);
		return multiAllocables;
	}


	public List<Type> GetAllTypesAllocable()
	{
		String methodName = "GetAllTypesAllocable";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		List<Type> types = TypeConverter.instance().convertToListObject(result);
		if(types == null){
			types = new ArrayList<Type>();
		}
		return types;
	}


	//	public boolean InsertAllocable(String barCode, String name, String description, int idType, int idLocation)
	//	{
	//		String methodName = "InsertAllocable";
	//		SoapObject request = new SoapObject(NAMESPACE, methodName);
	//		request.addProperty("barCode", barCode);
	//		request.addProperty("name", name);
	//		request.addProperty("description", description);
	//		request.addProperty("idType", idType);
	//		request.addProperty("idLocation", idLocation);
	//		SoapPrimitive result = (SoapPrimitive)ServiceUtil.callService(serviceName, methodName, request);
	//		try{
	//			return Boolean.parseBoolean(result.toString());
	//		} catch (Exception e){
	//			return false;
	//		}
	//	}
	//
	//	public List<Allocable> GetAllAllocable()
	//	{
	//		String methodName = "GetAllAllocable";
	//		SoapObject request = new SoapObject(NAMESPACE, methodName);
	//		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
	//		List<Allocable> allocables = AllocableConverter.instance().convertToListObject(result);
	//		if(allocables == null){
	//			allocables = new ArrayList<Allocable>();
	//		}
	//		return allocables;
	//	}
}
