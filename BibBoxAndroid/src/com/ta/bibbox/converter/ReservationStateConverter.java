package com.ta.bibbox.converter;

import com.ta.bibbox.pojo.ReservationState;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet ReservationState à un POJO ReservationState
 */
public class ReservationStateConverter {
	public ReservationState convertToObject(String reservationState) {
		if(reservationState.equalsIgnoreCase("Absent")){
			return ReservationState.Absent;
		}
		if(reservationState.equalsIgnoreCase("BeingUsed")){
			return ReservationState.BeingUsed;
		}
		if(reservationState.equalsIgnoreCase("Canceled")){
			return ReservationState.Canceled;
		}
		if(reservationState.equalsIgnoreCase("Finished")){
			return ReservationState.Finished;
		}
		if(reservationState.equalsIgnoreCase("Waiting")){
			return ReservationState.Waiting;
		}
		return null;
	}

	private static ReservationStateConverter reservationStateConverter;
	private ReservationStateConverter() {}

	public static ReservationStateConverter instance() {
		if(reservationStateConverter == null) {reservationStateConverter = new ReservationStateConverter(); }
		return reservationStateConverter;
	}
}
