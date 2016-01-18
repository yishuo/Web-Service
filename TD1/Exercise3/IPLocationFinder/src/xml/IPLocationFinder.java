package xml;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import net.webservicex.GeoIPServiceSoap;


public class IPLocationFinder {

	public static void main(String[] args) {
		
		String ip= "8.8.8.8"; //ip de google (U.S.)
		//String ip= "202.108.22.5"; // ip de http://www.baidu.com/(Chinese website)
		//String ip = "195.51.51.87"; // ip de FNAC (European Union)
		locate(ip);
		
	}
	private static void locate(String ip) {
		
		GeoIPService ws = new GeoIPService();
		GeoIPServiceSoap service =  ws.getGeoIPServiceSoap(); 
		GeoIP resultat = service.getGeoIP(ip);
		
		System.out.println("Le pays correspodant ид l'Adresse IP '"+ip+"' est : "+resultat.getCountryName());
		
	}

}

