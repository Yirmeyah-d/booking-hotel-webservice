package hotelFiltering;
import java.text.ParseException;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class Book extends ServerResource {
	
	@Get  
	public String toString() {
		int hotelId = (int) getRequestAttributes().get("hotelId");
		int roomId = (int) getRequestAttributes().get("roomId");
		int idCustomer = (int) getRequestAttributes().get("idCustomer");
		String rentalDate = (String) getRequestAttributes().get("rentalDate");
		int nbNights = (int) getRequestAttributes().get("nbNights");
		String customerResponse = (String) getRequestAttributes().get("customerResponse");
		return "The client request is : " + hotelId + " " +  roomId + " " + idCustomer + " " + rentalDate + " " + nbNights + " " + customerResponse;
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
        String hotelId = form.getFirstValue("hotelId");  
        String roomId = form.getFirstValue("roomId"); 
        String idCustomer = form.getFirstValue("idCustomer");
        String rentalDate = form.getFirstValue("rentalDate");
        String nbNights = form.getFirstValue("nbNights");
        String customerResponse = form.getFirstValue("customerResponse");
        
        String result = conn.insertReservation(Integer.parseInt(hotelId),Integer.parseInt(roomId),Integer.parseInt(idCustomer),rentalDate,Integer.parseInt(nbNights), customerResponse);
        

        
        return result;  
    } 
}
