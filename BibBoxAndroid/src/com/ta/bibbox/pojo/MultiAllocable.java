package com.ta.bibbox.pojo;

/**
 * @author Jing SHU
 * @date 07/03/2014
 * @copyright TA Copyright
 * @brief Le POJO qui représente un multi-allouable, par exemple un PC portable
 */
public class MultiAllocable extends Allocable {
	private String barCode;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}	
}
