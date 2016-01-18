package org.yishuolyu.webservices;

public class Currency {
	private String name;
	private String country;
	private int yearAdopted;
	
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

}
