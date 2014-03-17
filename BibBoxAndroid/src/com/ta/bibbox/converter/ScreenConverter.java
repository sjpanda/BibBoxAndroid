package com.ta.bibbox.converter;

import com.ta.bibbox.pojo.Screen;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief La classe de converteur qui convert un soap objet Screen � un POJO Screen
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
	
	public String convertToString(Screen screen) {
		if(screen == Screen.Interactive){
			return "Un �cran interactif";
		}
		if(screen == Screen.BigClassic){
			return "Un �cran mural classique de taille grande";
		}
		if(screen == Screen.MediumClassic){
			return "Un �cran mural classique de taille moyenne";
		}
		if(screen == Screen.None){
			return "Pas d'�cran";
		}
		return "Non d�fini";
	}

	private static ScreenConverter screenConverter;
	private ScreenConverter() {}

	public static ScreenConverter instance() {
		if(screenConverter == null) {screenConverter = new ScreenConverter(); }
		return screenConverter;
	}
}
