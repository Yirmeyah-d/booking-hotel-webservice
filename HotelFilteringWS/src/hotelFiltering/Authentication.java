package hotelFiltering;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import hotelFiltering.Connect;
import com.google.gson.*;

public class Authentication extends ServerResource {
	
	@Get  
	public String toString() {
		String email = (String) getRequestAttributes().get("email");
		String password = (String) getRequestAttributes().get("password");

		return "The client request is : " + email + " " + password ;
	}  
	
	
    @Post
    public String acceptItem(Representation entity) {  
    	
		try {

			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}
		
		Connect conn = new Connect();

        // Parse the given representation and retrieve data
        Form form = new Form(entity);  
        String email = form.getFirstValue("email");  
        String password = form.getFirstValue("password");  
        Customer customerConnected = conn.authenticate(email,password);
        Gson gson = new Gson();    
	    String json = gson.toJson(customerConnected); // converts to json

        
        return json;  
    } 
    
}
