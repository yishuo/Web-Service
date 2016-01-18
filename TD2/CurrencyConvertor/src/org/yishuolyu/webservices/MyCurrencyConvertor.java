package org.yishuolyu.webservices;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class MyCurrencyConvertor {
	
	List<String> currencyConvertor = new ArrayList<String>();
	
	public Collection getCurrenciesList(){
		return currencyConvertor;		
	}
	
	public List<String> initializeList(){
		currencyConvertor.clear();
		currencyConvertor.add("Dollar");
		currencyConvertor.add("Euro");
		currencyConvertor.add("Yen");
		return currencyConvertor;
	}
	
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
	public List<String> addCurrency(String name){
		List<String> currencyList = this.initializeList();
		currencyList.add(name);
		return currencyConvertor;
		
	}
	
	public static void main(String args[]){
		
	}

}
