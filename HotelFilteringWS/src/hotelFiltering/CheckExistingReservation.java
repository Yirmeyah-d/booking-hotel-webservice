package hotelFiltering;

import java.text.ParseException;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class CheckExistingReservation  extends ServerResource {

	@Get  
	public String toString() {

		int idCustomer = (int) getRequestAttributes().get("idCustomer");
		String rentalDate = (String) getRequestAttributes().get("rentalDate");
		int nbNights = (int) getRequestAttributes().get("nbNights");
		return "The client request is : " + idCustomer + " " + rentalDate + " " + nbNights;
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
        String idCustomer = form.getFirstValue("idCustomer");
        String rentalDate = form.getFirstValue("rentalDate");
        String nbNights = form.getFirstValue("nbNights");
        
        String result = conn.checkExistingReservation(Integer.parseInt(idCustomer),rentalDate,Integer.parseInt(nbNights));
        
        
        return result;  
    }
    
}
