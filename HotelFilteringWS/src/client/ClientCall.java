package client;
 
import java.io.IOException;
 
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;
 
public class ClientCall {
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 
		// Create the client resource  
		ClientResource resource = new ClientResource("http://localhost:8182");  
 
		// Write the response entity on the console
		try {
			resource.get().write(System.out);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
 
}