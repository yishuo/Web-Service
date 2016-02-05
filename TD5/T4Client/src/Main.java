import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8080/TD4/v1/converterApp/currencyConverter/offices_v2");

			String input = "{\"city\":\"Lyon\",\"manager_name\":\"manager3\",\"email\":\"Lyon@rest.fr\",\"year_founded\":\"2007\"}";


			ClientResponse response = webResource.type("application/json")
			   .post(ClientResponse.class, input);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }

	}

}
