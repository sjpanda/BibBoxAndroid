package com.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.serialization.SoapObject;

import com.ta.converter.AllocableConverter;
import com.ta.converter.LocationConverter;
import com.ta.converter.TypeConverter;
import com.ta.pojo.Allocable;
import com.ta.pojo.Location;
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

	//	public List<DBMonoAllocable> SearchMonoAllocables(int nbPerson, string equip, string place, DateTime date, TimeSpan beginTime, TimeSpan endTime)
	//    {
	//        return ManagementFactory.CreateAllocableManagement().SearchMonoAllocables(nbPerson, equip, place, date, beginTime, endTime);
	//    }
	//
	//    public List<DBMonoAllocable> SearchMonoAllocablesForTeacher(int? nbPerson, string equip, string place, DateTime date, TimeSpan beginTime, TimeSpan endTime)
	//    {
	//        return ManagementFactory.CreateAllocableManagement().SearchMonoAllocablesForTeacher(nbPerson, equip, place, date, beginTime, endTime);
	//    }

	public List<Integer> GetMonoAllocablePossibleNbSeat()
	{
		String methodName = "GetMonoAllocablePossibleNbSeat";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		List<Integer> nbSeat = new ArrayList<Integer>();
		for(int i=0; i<result.getPropertyCount(); i++){
			String nb = result.getProperty(i).toString();
			try{
				nbSeat.add(Integer.parseInt(nb));
			} catch (Exception e) {}
		}
		return nbSeat;
	}

	public List<String> GetMonoAllocableEquips()
	{
		String methodName = "GetMonoAllocableEquips";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		List<String> equips = new ArrayList<String>();
		for(int i=0; i<result.getPropertyCount(); i++){
			equips.add(result.getProperty(i).toString());
		}
		return equips;
	}

	public List<Location> GetAllLocations()
	{
		String methodName = "GetAllLocations";
		SoapObject request = new SoapObject(NAMESPACE, methodName);
		SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
		List<Location> locations = LocationConverter.instance().convertToListObject(result);
		return locations;
	}

	//    public TimeSpan GetBeginReservTime()
	//    {
	//        return ManagementFactory.CreateAllocableManagement().GetBeginReservTime();
	//    }
	//
	//    public TimeSpan GetEndReservTime()
	//    {
	//        return ManagementFactory.CreateAllocableManagement().GetEndReservTime();
	//    }

	//	    public Dictionary<String, Integer> GetMultiAllocablesByMonoAllocable(int idMonoAllocable, DateTime date, TimeSpan beginTime, TimeSpan endTime)
	//	    {
	//	        // S'il y a un prob de serialisation de Dictionary, alors changer le type de retour à List<KeyValuePair<string, int>> puis faire .ToList() sur Dictionary
	//	        return ManagementFactory.CreateAllocableManagement().GetMultiAllocablesByMonoAllocable(idMonoAllocable, date, beginTime, endTime);
	//	    }


	    public List<Type> GetAllTypesAllocable()
	    {
	    	String methodName = "GetAllTypesAllocable";
			SoapObject request = new SoapObject(NAMESPACE, methodName);
			SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
			List<Type> types = TypeConverter.instance().convertToListObject(result);
			return types;
	    }
	
	
//	    public boolean InsertAllocable(String barCode, String name, String description, int idType, int idLocation)
//	    {
//	    	String methodName = "InsertAllocable";
//			SoapObject request = new SoapObject(NAMESPACE, methodName);
//			request.addProperty("barCode", barCode);
//			request.addProperty("name", name);
//			request.addProperty("description", description);
//			request.addProperty("idType", idType);
//			request.addProperty("idLocation", idLocation);
//			SoapPrimitive result = (SoapPrimitive)ServiceUtil.callService(serviceName, methodName, request);
//			try{
//				return Boolean.parseBoolean(result.toString());
//			} catch (Exception e){
//				return false;
//			}
//	    }
	    
	    public List<Allocable> GetAllAllocable()
        {
	    	String methodName = "GetAllAllocable";
			SoapObject request = new SoapObject(NAMESPACE, methodName);
			SoapObject result = (SoapObject)ServiceUtil.callService(serviceName, methodName, request);
			List<Allocable> allocables = AllocableConverter.instance().convertToListObject(result);
			return allocables;
        }
}
