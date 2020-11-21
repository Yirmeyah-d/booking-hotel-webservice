package hotelFiltering;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import com.google.gson.Gson;

public class Register extends ServerResource  {
	
	@Get  
	public String toString() {
		String firstName = (String) getRequestAttributes().get("firstName");
		String lastName = (String) getRequestAttributes().get("lastName");
		String email = (String) getRequestAttributes().get("email");
		String password = (String) getRequestAttributes().get("password");
		int phone = (int) getRequestAttributes().get("phone");

		return "The client request is : " + firstName + " " + lastName + " " + email + " " + password + " " + phone ;
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
        String firstName = form.getFirstValue("firstName");
        String lastName = form.getFirstValue("lastName");
        String email = form.getFirstValue("email");  
        String password = form.getFirstValue("password"); 
        String phone = form.getFirstValue("phone"); 
        Customer customerConnected = conn.register(firstName, lastName, email, password, phone);
        Gson gson = new Gson();    
	    String json = gson.toJson(customerConnected); // converts to json

        
        return json;  
    } 
}
