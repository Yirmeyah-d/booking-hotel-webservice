package hotelFiltering;

import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.text.ParseException;
import java.util.List;
import org.restlet.data.Form;
import org.restlet.representation.Representation;
import hotelFiltering.Connect;
import java.util.stream.*;
import com.google.gson.*;


public class CheckHotelsAvailable extends ServerResource {  	
	@Get  
	public String toString() {
		String rentalDate = (String) getRequestAttributes().get("rentalDate");
		String nbNights = (String) getRequestAttributes().get("nbNights");

		return "The client request is : " + rentalDate + " " + nbNights;
	}  
	
	
    @Post
    public String acceptItem(Representation entity) throws NumberFormatException, ParseException {  
    	
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}
		
		Connect conn = new Connect();

        // Parse the given representation and retrieve data
        Form form = new Form(entity);  
        String rentalDate = form.getFirstValue("rentalDate");  
        String nbNights = form.getFirstValue("nbNights");  
        //List<Hotel> hotelsAvailable = conn.getHotelsAvailable(rentalDate, Integer.parseInt(nbNights),Integer.parseInt(nbRooms));

        List<Hotel> hotelsAvailable = conn.getHotelsAvailable(rentalDate, Integer.parseInt(nbNights));
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    String json = gson.toJson(hotelsAvailable); // converts to json
		
        return json;  
    } 
    
    
    
    
}