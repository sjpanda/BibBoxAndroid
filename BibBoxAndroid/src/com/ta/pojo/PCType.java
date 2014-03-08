package com.ta.pojo;

/**
 * @author Jing SHU
 * @date 04/03/2014
 * @copyright TA Copyright
 * @brief Le POJO qui représente un type de pc
 */
public class PCType {
	private int ID;
	private String Brand;
	private String CPU;
	private String DiskSize;
	private String RAM;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public String getCPU() {
		return CPU;
	}
	public void setCPU(String cPU) {
		CPU = cPU;
	}
	public String getDiskSize() {
		return DiskSize;
	}
	public void setDiskSize(String diskSize) {
		DiskSize = diskSize;
	}
	public String getRAM() {
		return RAM;
	}
	public void setRAM(String rAM) {
		RAM = rAM;
	}
	
	
}
