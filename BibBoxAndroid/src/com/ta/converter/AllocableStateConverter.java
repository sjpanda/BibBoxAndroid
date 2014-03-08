package com.ta.converter;

import com.ta.pojo.AllocableState;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet AllocableState à un POJO AllocableState
 */
public class AllocableStateConverter {
	public AllocableState convertToObject(String allocableState) {
		if(allocableState.equalsIgnoreCase("Available")){
			return AllocableState.Available;
		}
		if(allocableState.equalsIgnoreCase("HasReservation")){
			return AllocableState.HasReservation;
		}
		if(allocableState.equalsIgnoreCase("BeingUsed")){
			return AllocableState.BeingUsed;
		}
		return null;
	}

	private static AllocableStateConverter allocableStateConverter;
	private AllocableStateConverter() {}

	public static AllocableStateConverter instance() {
		if(allocableStateConverter == null) {allocableStateConverter = new AllocableStateConverter(); }
		return allocableStateConverter;
	}
}
