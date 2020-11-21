package hotelFiltering;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
 
public class RouterApplication extends Application{
	/**
	 * Creates a root Restlet that will receive all incoming calls.
	 */
	@Override
	public synchronized Restlet createInboundRoot() {
		// Create a router Restlet that routes each call to a new respective instance of resource.
		Router router = new Router(getContext());
		// Defines only three routes
		router.attach("/checkHotelsAvailable", CheckHotelsAvailable.class);
		router.attach("/authentication", Authentication.class);
		router.attach("/book", Book.class);
		router.attach("/register", Register.class);
		router.attach("/checkExistingReservation", CheckExistingReservation.class);

		return router;
	}
}