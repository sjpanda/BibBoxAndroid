package com.ta.bibbox.converter;

import com.ta.bibbox.pojo.Screen;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet Screen à un POJO Screen
 */
public class ScreenConverter {
	public Screen convertToObject(String screen) {
		if(screen.equalsIgnoreCase("Interactive")){
			return Screen.Interactive;
		}
		if(screen.equalsIgnoreCase("BigClassic")){
			return Screen.BigClassic;
		}
		if(screen.equalsIgnoreCase("MediumClassic")){
			return Screen.MediumClassic;
		}
		if(screen.equalsIgnoreCase("None")){
			return Screen.None;
		}
		return null;
	}

	private static ScreenConverter screenConverter;
	private ScreenConverter() {}

	public static ScreenConverter instance() {
		if(screenConverter == null) {screenConverter = new ScreenConverter(); }
		return screenConverter;
	}
}
