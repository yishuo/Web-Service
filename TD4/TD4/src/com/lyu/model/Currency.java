package com.lyu.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="currency")
@XmlAccessorType(XmlAccessType.FIELD)
public class Currency {
	private int ID;
	
	private String name;
	private String country;
	private int yearAdopted;
	
	public Currency(){
		
	}
	
	public Currency(String country, String name, int yearAdopted, int ID) {
		// TODO Auto-generated constructor stub
		this.country = country;
		this.name = name;
		this.yearAdopted = yearAdopted;
		this.ID = ID;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public String getCountry(){
		return this.country;
	}
	
	public void setYearAdopted(int yearAdopted){
		this.yearAdopted = yearAdopted;
	}
	
	public int getYearAdopted(){
		return this.yearAdopted;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

}
