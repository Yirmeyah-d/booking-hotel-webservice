package hotelFiltering;

import org.restlet.Component;
import org.restlet.data.Protocol;
 
public class RESTDistributor {
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// Create a new Restlet component and add a HTTP server connector to it  
		Component component = new Component();  
		component.getServers().add(Protocol.HTTP, 8182); 
 
		// Attach the application to the component and start it  
		component.getDefaultHost().attach(new RouterApplication());  
		component.start();  
	}	 
}
