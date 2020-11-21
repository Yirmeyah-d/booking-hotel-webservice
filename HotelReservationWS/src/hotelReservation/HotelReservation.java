package hotelReservation;


import org.restlet.Client;
import org.restlet.Context;
import org.restlet.data.Form;
import org.restlet.data.Protocol;
import org.restlet.resource.ClientResource;

public class HotelReservation {
	 

	public String restCallHotelsAvailable(String rentalDate, int nbNights,int nbRooms){
		
		Client client = new Client(new Context(), Protocol.HTTP);
		// Create the client resource  
		ClientResource resource = new ClientResource("http://localhost:8182/checkHotelsAvailable");
	    resource.setNext(client);	

		Form form = new Form();  
        form.add("rentalDate", rentalDate);  
        form.add("nbNights", Integer.toString(nbNights));  
        form.add("nbRooms", Integer.toString(nbRooms));  
		
        // Write the response entity on the console
		try {
 
			return resource.post(form).getText();
 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "error";
		}
		
	}

	

	
	public String checkHotelsAvailable(String rentalDate, int nbNights, int nbRooms){
		return restCallHotelsAvailable(rentalDate, nbNights, nbRooms);            
    }    
	
	public String restCallBooking(int hotelId , int roomId, int idCustomer, String rentalDate, int nbNights, String customerResponse){
		
		Client client = new Client(new Context(), Protocol.HTTP);
		// Create the client resource  
		ClientResource resource = new ClientResource("http://localhost:8182/book");
	    resource.setNext(client);	

		Form form = new Form();   
        form.add("hotelId", Integer.toString(hotelId));  
        form.add("roomId", Integer.toString(roomId));  
        form.add("idCustomer", Integer.toString(idCustomer));  
        form.add("rentalDate", rentalDate);  
        form.add("nbNights", Integer.toString(nbNights));  
        form.add("customerResponse", customerResponse);  


        // Write the response entity on the console
		try {
 
			return resource.post(form).getText();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "error";
		}
		
	}
	
	public String book(int hotelId , int roomId, int idCustomer, String rentalDate, int nbNights, String customerResponse){
		return restCallBooking(hotelId,roomId,idCustomer,rentalDate,nbNights,customerResponse);
	} 
	
	
	public String restCallAuthenticate(String email , String password){
		
		Client client = new Client(new Context(), Protocol.HTTP);
		// Create the client resource  
		ClientResource resource = new ClientResource("http://localhost:8182/authentication");
	    resource.setNext(client);	

		Form form = new Form();   
        form.add("email", email);  
        form.add("password", password);  
		
        // Write the response entity on the console
		try {
 
			return resource.post(form).getText();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "error";
		}
		
	}

	public String authenticate(String email, String password){
        return restCallAuthenticate(email, password);
    }
        
	
	public String restCallRegister(String firstName, String lastName, String email, String password, String phone){
		
		Client client = new Client(new Context(), Protocol.HTTP);
		// Create the client resource  
		ClientResource resource = new ClientResource("http://localhost:8182/register");
	    resource.setNext(client);	

		Form form = new Form(); 
		form.add("firstName", firstName); 
		form.add("lastName", lastName); 
        form.add("email", email);  
        form.add("password", password); 
        form.add("phone", phone); 
		
        // Write the response entity on the console
		try {
 
			return resource.post(form).getText();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "error";
		}
		
	}
	
	
	public String register(String firstName, String lastName, String email, String password, String phone){
  
		return restCallRegister(firstName, lastName, email, password, phone);            

	} 
	
	
	public String restCallCheckExistingReservation(int idCustomer, String rentalDate, int nbNights){
		
		Client client = new Client(new Context(), Protocol.HTTP);
		// Create the client resource  
		ClientResource resource = new ClientResource("http://localhost:8182/checkExistingReservation");
	    resource.setNext(client);	

		Form form = new Form();  
		form.add("idCustomer", Integer.toString(idCustomer));
        form.add("rentalDate", rentalDate);  
        form.add("nbNights", Integer.toString(nbNights));  
		
        // Write the response entity on the console
		try {
 
			return resource.post(form).getText();
 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "error";
		}
		
	}
	
	public String checkExistingReservation(int idCustomer, String rentalDate, int nbNights){

		return restCallCheckExistingReservation(idCustomer, rentalDate, nbNights);            
        
	}
	
}
