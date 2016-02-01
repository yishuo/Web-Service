package com.lyu.RESTfulService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.lyu.model.Currency;

@Path("currencyConverter")
public class CurrencyConverter {
	private String version = "1.0";
	private static List<Currency> currencyList = new ArrayList<Currency>();
	
	@GET
	@Path("version")
	public String version()
	{
		return "The current version is " +version;
	}
	
	private static void initializeCurrencies()
	{
		currencyList.add(new Currency("Japan", "Yen", 1945, 3));
		currencyList.add(new Currency("EU","Euro", 2000, 2));
		currencyList.add(new Currency("USA", "Dollar", 1800, 1));
	}
	
	/**
	 * 
	 * Question 1
	 * @param ID
	 * @return
	 */
	@GET
	@Path("currency/{id}")
	public Response currency(@PathParam("id") int ID)
	{
		String name = null;
		initializeCurrencies();
		for(int i=0; i<currencyList.size(); i++){
			if(currencyList.get(i).getID() == ID){
				name = currencyList.get(i).getName();
			}
		}
		return Response.status(200).entity("The currency name choosed is: " + name).build();
	}
	
	/**
	 * Question 2.1
	 * @param source
	 * @param destination
	 * @param amount
	 * @return
	 */
	@GET
	@Path("conversion/{source}/{destination}/{amount}")
	public Response conversion (
			@PathParam("source") String source,
			@PathParam("destination") String destination, 
			@PathParam("amount") double amount){
		double result = 0;
		if(source.equals("D")){
			if(destination.equals("E")){
				result = amount*0.922;
			}
			else if(destination.equals("Y")){
				result = amount*6.58;
			}
			else{
				System.out.println("This is error.");
			}
		}
		else if(source.equals("E")){
			if(destination.equals("D")){
				result = amount*1.08;
			}
			else if(destination.equals("Y")){
				result = amount*7.14;
			}
			else{
				System.out.println("This is error.");
			}
		}
		else if(source.equals("Y")){
			if(destination.equals("E")){
				result = amount*0.14;
			}
			else if(destination.equals("D")){
				result = amount* 0.15;
			}
			else{
				System.out.println("This is error.");
			}
		}
		else{
			System.out.println("This is error.");
		}
		
		return Response.status(200).entity("The result of conversion: " + result).build();
	}
	
	/**
	 * 
	 * Question 3.1 xml
	 * @return
	 */

	@GET
	@Path("currencies")
	@Produces(MediaType.TEXT_XML)
	public List<Currency> getCurrenciesXML(@QueryParam("sortedYN") String sortedYN){
		initializeCurrencies();
		if(sortedYN.endsWith("y")){
			List<Currency> currencyList_sorted = new ArrayList<Currency>();			
			String[] currencyListName = new String[]{"Yen","Dollar","Euro"};
			Arrays.sort(currencyListName);
			for(int i=0; i<currencyListName.length; i++){
				for(int j=0; j<currencyList.size();j++){
					if(currencyList.get(j).getName().equals(currencyListName[i])){
						currencyList_sorted.add(currencyList.get(j));
					}
				}
				
			}
			return currencyList_sorted;
		}
		else{
			return currencyList;	
		}
		
	}
	
	/**
	 * Question 3.2 json
	 * @return
	 */
	@GET
	@Path("currencies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Currency> getCurrenciesJSON(@QueryParam("sortedYN") String sortedYN){
		initializeCurrencies();
		if(sortedYN.endsWith("y")){
			List<Currency> currencyList_sorted = new ArrayList<Currency>();			
			String[] currencyListName = new String[]{"Yen","Dollar","Euro"};
			Arrays.sort(currencyListName);
			for(int i=0; i<currencyListName.length; i++){
				for(int j=0; j<currencyList.size();j++){
					if(currencyList.get(j).getName().equals(currencyListName[i])){
						currencyList_sorted.add(currencyList.get(j));
					}
				}
				
			}
			return currencyList_sorted;
		}
		else{
			return currencyList;	
		}		
	}
	
	
	
}
