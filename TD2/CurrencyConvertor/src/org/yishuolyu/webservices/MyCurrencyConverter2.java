package org.yishuolyu.webservices;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MyCurrencyConverter2 {
	
	List<Currency> currencyConvertor = new ArrayList<Currency>();
	
	public List<Currency> getCurrenciesList(){
		initializeList();
		return currencyConvertor;		
	}
	
//	@WebMethod(exclude=true)
	private List<Currency> initializeList(){
		currencyConvertor.clear();
		Currency c1 = new Currency();
		c1.setName("EURO");
		c1.setCountry("EU");
		c1.setYearAdopted(1999);
		Currency c2 = new Currency();
		c2.setName("YEN");
		c2.setCountry("China");
		c2.setYearAdopted(1949);
		currencyConvertor.add(c1);
//		currencyConvertor.add("Dollar");
//		currencyConvertor.add("Euro");
//		currencyConvertor.add("Yen");
		return currencyConvertor;
	}
	@WebMethod(exclude=true)
	public double convert ( String source, String destination, double amount ){
		double result = 0;
		if(source.equals("Dollar")){
			if(destination.equals("Euro")){
				result = amount*0.922;
			}
			else if(destination.equals("Yen")){
				result = amount*6.58;
			}
			else{
				System.out.println("This is error.");
			}
		}
		else if(source.equals("Euro")){
			if(destination.equals("Dollar")){
				result = amount*1.08;
			}
			else if(destination.equals("Yen")){
				result = amount*7.14;
			}
			else{
				System.out.println("This is error.");
			}
		}
		else if(source.equals("Yen")){
			if(destination.equals("Euro")){
				result = amount*0.14;
			}
			else if(destination.equals("Dollar")){
				result = amount* 0.15;
			}
			else{
				System.out.println("This is error.");
			}
		}
		else{
			System.out.println("This is error.");
		}
		
		
		return result;
		
	}
	
	@WebMethod(exclude=true)
	public List<Currency> addCurrency(String name){
		List<Currency> currencyList = this.initializeList();
//		currencyList.add(name);
		return currencyConvertor;
		
	}
	
	public static void main(String args[]){
		
	}

}
