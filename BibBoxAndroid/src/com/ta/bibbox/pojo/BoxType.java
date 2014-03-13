package com.ta.bibbox.pojo;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief Le POJO qui représente un type de box
 */
public class BoxType extends Type {
	private int nbSeat;
    private Screen screen;
    
	public int getNbSeat() {
		return nbSeat;
	}
	public void setNbSeat(int nbSeat) {
		this.nbSeat = nbSeat;
	}
	public Screen getScreen() {
		return screen;
	}
	public void setScreen(Screen screen) {
		this.screen = screen;
	}  
}
