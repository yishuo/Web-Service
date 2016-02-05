package com.lyu.RESTfulService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.lyu.model.Currency;
import com.lyu.model.Office;
import com.rest.DB.DBClass;
import com.rest.util.ToJSON;

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
	
	
	/**
	 * TD5
	 * Question1
	 * @return
	 * @throws Exception 
	 */	
	@GET
	@Path("offices")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOffices() throws Exception{
		
		ToJSON toJson = new ToJSON();
		JSONArray json = new JSONArray();
		
		DBClass db = new DBClass();
		Connection conn = db.returnConnection();
		PreparedStatement statement;

		try {
			String query = "SELECT * FROM `office`";
			statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			json = toJson.toJSONArray(result);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}
	
	/**
	 * Question2
	 * @param city
	 * @return
	 * @throws Exception
	 */
/*	@GET
	@Path("office")
	@Produces(MediaType.APPLICATION_JSON)
	public String getOffice(@QueryParam ("city") String city) throws Exception{
		ToJSON toJson = new ToJSON();
		JSONArray json = new JSONArray();
		
		DBClass db = new DBClass();
		Connection conn = db.returnConnection();
		PreparedStatement statement;
//		city="paris";

		try {
			String query = "SELECT * FROM `office` WHERE city LIKE '"+city+"'";
			System.out.println(city);
			statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			json = toJson.toJSONArray(result);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.toString();
	}*/
	
	/**
	 * Question3
	 * @param city
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("office")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOffice(@QueryParam ("city") String city) throws Exception{
		ToJSON toJson = new ToJSON();
		JSONArray json = new JSONArray();
		
		DBClass db = new DBClass();
		Connection conn = db.returnConnection();
		PreparedStatement statement;

		try {
			String query = "SELECT * FROM `office` WHERE city LIKE '"+city+"'";
//			System.out.println(city);
			statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			json = toJson.toJSONArray(result);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(200).entity("The offices in the city you inputed are: " + json.toString()).build();
	}
	
	/**
	 * Question4
	 * @param city
	 * @param manager
	 * @param email
	 * @param year_founded
	 * @return
	 * @throws SQLException
	 */
	@POST
	@Path("offices")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addOffice(@FormParam("city") String city,
							@FormParam("manager") String manager,
							@FormParam("email") String email,
							@FormParam("year_founded") int year_founded) throws SQLException
	{
		if(city.trim().length() > 0 && manager.trim().length() > 0 && email.trim().length() > 0){
			DBClass db = new DBClass();
			Connection conn = db.returnConnection();
			Statement statement = conn.createStatement();
			String query ="INSERT INTO `ws_td5`.`office` (`ID`, `city`, `manager_name`, `email`, `year_founded`) VALUES (NULL, "
							+ "'" + city + "'" + "," + "'" + manager + "'" + "," + "'" + email + "'" + "," + "'" + year_founded + "'" + ")";
			statement.executeUpdate(query);			
		}
		
		return "You have successfully added it!";
	}
	
	/**
	 * Question5
	 * @param incomingData
	 * @return
	 * @throws JSONException
	 */
	
	public int InsertOfficeIntoTheDataBase(Office office) 
	{
		Connection connection = DBClass.returnConnection(); 
		PreparedStatement insert;
		try {
			insert = connection.prepareStatement("INSERT INTO office (city, manager_name,email, year_founded)" + "VALUES(?,?,?,?)");
			insert.setString(1, office.getCity());
			insert.setString(2, office.getManager_name());
			insert.setString(3, office.getEmail());
			insert.setInt(4, office.getYear_founded());
			insert.executeUpdate();
			return 200;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 400;
		}
	}
	
	@POST
	@Path("offices_v2")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOffice_v2(String incomingData) throws JSONException
	{
		JSONObject jsonObject = new JSONObject(incomingData);
		String city = jsonObject.getString("city");
		String manager = jsonObject.getString("manager_name");
		String email = jsonObject.getString("email");
		int year_founded = jsonObject.getInt("year_founded");
		
		Office office = new Office(city, manager, email, year_founded);
		int http_code = InsertOfficeIntoTheDataBase(office);
		if (http_code==200) {
			return Response.ok().build();
			}
		else{
			return Response.serverError().build();
			}
	}
	
}