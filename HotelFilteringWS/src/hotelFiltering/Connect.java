package hotelFiltering;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hotelFiltering.Hotel;
import hotelFiltering.Customer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  

public class Connect {
	/**
	 * Connect to a sample database
	 */
	public Connection connect() {

		Connection conn = null;
		try {
			// db parameters
			String url = "jdbc:sqlite:/Users/jeremydormevil/ENSIIE/2A/SIA/Projet/workspace-projet_webservice/bookingHotel.db";
			// create a connection to the database
			conn = DriverManager.getConnection(url);

			System.out.println("Connection to SQLite has been established.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		return conn;
	}



	public void selectAllHotelTest(){
		String sql = "SELECT * FROM Hotel";

		try (Connection conn = this.connect();
				Statement stmt  = conn.createStatement();
				ResultSet rs    = stmt.executeQuery(sql)){

			// loop through the result set
			while (rs.next()) {
				System.out.println(rs.getInt("idHotel") +  "\t" + 
						rs.getString("hotelName") + "\t" +
						rs.getString("hotelAdress"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
	}


	public List<Hotel> getHotelsAvailable(String rentalDate, int nbNights, int nbRooms) throws ParseException{
		List<Hotel> hotels = new ArrayList<Hotel>();

		String sql = "SELECT DISTINCT  Hotel.idHotel, Hotel.hotelName, Hotel.hotelAdress, Room.idRoom " +
				"FROM Room, Hotel " + 
				"WHERE Room.idRoom NOT IN(SELECT roomId FROM Reservation WHERE (? BETWEEN rentalDate  AND endDate) OR (? BETWEEN rentalDate  AND endDate)) AND Hotel.idHotel == Room.hotelId AND Hotel.idHotel IN ( SELECT DISTINCT Hotel.idHotel From Room,Hotel WHERE Room.idRoom NOT IN (SELECT roomId FROM Reservation WHERE ( ? BETWEEN rentalDate  AND endDate) OR ( ? BETWEEN rentalDate  AND endDate) ) AND Hotel.idHotel == Room.hotelId GROUP BY  Hotel.idHotel HAVING COUNT(Hotel.idHotel) >= ? ) ";


	    Date rentalDateConverted =new SimpleDateFormat("yyyy-MM-dd").parse(rentalDate);  
        Calendar c = Calendar.getInstance();
        c.setTime(rentalDateConverted);
	   	c.add(Calendar.DAY_OF_MONTH, nbNights);  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String endDate = dateFormat.format(c.getTime());  
        
        
		try (Connection conn = this.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql);
				){

			// set the value
			pstmt.setString(1,rentalDate);
			pstmt.setString(2,endDate);
			pstmt.setString(3,rentalDate);
			pstmt.setString(4,endDate);
			pstmt.setInt(5,nbRooms);
			//pstmt.setInt(3,nbRooms);

			try(ResultSet rs  = pstmt.executeQuery()){

				// loop through the result set
				while (rs.next()) {

					System.out.println(rs.getInt("idHotel") +  "\t" + 
							rs.getString("hotelName") + "\t" +
							rs.getString("hotelAdress") + "\t" +
							rs.getInt("idRoom"));

					Hotel hotel = new Hotel();
					hotel.setId(rs.getInt("idHotel"));
					hotel.setHotelName(rs.getString("hotelName"));
					hotel.setHotelAdress(rs.getString("hotelAdress"));
					hotel.setRoomId(rs.getInt("idRoom"));
					hotels.add(hotel);

				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return hotels;
	}

	public String checkExistingReservation(int idCustomer, String rentalDate, int nbNights) throws ParseException {
		
		int count = 0;
		String sql = "SELECT COUNT(*) AS  count  From Reservation WHERE customerId = ? AND ((? BETWEEN rentalDate AND endDate) OR (? BETWEEN rentalDate AND endDate)) ";
		
	    Date rentalDateConverted =new SimpleDateFormat("yyyy-MM-dd").parse(rentalDate);  
        Calendar c = Calendar.getInstance();
        c.setTime(rentalDateConverted);
	   	c.add(Calendar.DAY_OF_MONTH, nbNights);  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String endDate = dateFormat.format(c.getTime());
        
        System.out.print(endDate);
		try (Connection conn = this.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql);
				){
			// set the value
			pstmt.setInt(1,idCustomer);
			pstmt.setString(2,rentalDate);
			pstmt.setString(3,endDate);

			try(ResultSet rs  = pstmt.executeQuery()){
		        // username does exist, now check the password
				if (rs.next()) {
					 count = rs.getInt("count");
				}
				
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		
		return  String.valueOf(count);

	}
	

	public String insertReservation(int hotelId , int roomId, int idCustomer, String rentalDate, int nbNights, int nbRooms, String customerResponse) throws ParseException{
		// if hotelid and rommId not in select(list available hotels) then return false else return true
		// if true insert.
		List<Hotel> hotelsAvailable = this.getHotelsAvailable(rentalDate, nbNights, nbRooms);		
		String result = "The hotel "+ hotelId +" and the room " + roomId + " is not available";
		for(Hotel hotel: hotelsAvailable){
			  if(hotel.getId() == hotelId && hotel.getRoomId() == roomId) {
				  result = "Reservation successful";
			  }
		}
		
		
        String existingReservation = this.checkExistingReservation(idCustomer,rentalDate,nbNights);
        
	    Date rentalDateConverted =new SimpleDateFormat("yyyy-MM-dd").parse(rentalDate);  
        Calendar c = Calendar.getInstance();
        c.setTime(rentalDateConverted);
	   	c.add(Calendar.DAY_OF_MONTH, nbNights);  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
        String endDate = dateFormat.format(c.getTime());  
        



		if(Integer.parseInt(existingReservation)!=0 && customerResponse.equals("Y")){
			String sql = "UPDATE Reservation SET hotelId = ?, roomId = ?, rentalDate = ? , endDate = ? WHERE customerId = ? AND ((? BETWEEN rentalDate AND endDate) OR (? BETWEEN rentalDate AND endDate)) ";
			System.out.println("DAAAAAAAAAA !");


			
			try (Connection conn = this.connect();
					PreparedStatement pstmt  = conn.prepareStatement(sql)){
	
				// set the value
				pstmt.setInt(1,hotelId);
				pstmt.setInt(2,roomId);
				pstmt.setString(3,rentalDate);
				pstmt.setString(4,endDate);
				pstmt.setInt(5,idCustomer);
				pstmt.setString(6,rentalDate);
				pstmt.setString(7,endDate);
	
				pstmt.executeUpdate();
	
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else if (Integer.parseInt(existingReservation)!=0 && customerResponse.equals("N"))  {
			result = "We keep your last reservation, Reservation successful";
		} else if (Integer.parseInt(existingReservation)!=0 && (!customerResponse.equals("N") || !customerResponse.equals("Y")) )  {
			result = "Please answer Y or N";
		} else {
		
			String sql = "INSERT INTO Reservation (hotelId, roomId, customerId, rentalDate, endDate) VALUES (?, ?, ?, ?, ?)";
	
	
			if(result == "Reservation successful"){
				try (Connection conn = this.connect();
						PreparedStatement pstmt  = conn.prepareStatement(sql)){
	
					// set the value
					pstmt.setInt(1,hotelId);
					pstmt.setInt(2,roomId);
					pstmt.setInt(3,idCustomer);
					pstmt.setString(4,rentalDate);
					pstmt.setString(5, endDate);
	
					pstmt.executeUpdate();
	
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		

		return result ;
	}


	public Customer authenticate(String email ,String pass)
	{
		Customer customer = new Customer();
		String sql = "SELECT * FROM Client WHERE email = ? and password = ?";

		try (Connection conn = this.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql);
				){
			// set the value
			pstmt.setString(1,email);
			pstmt.setString(2,pass);

			try(ResultSet rs  = pstmt.executeQuery()){
		        // username does exist, now check the password
				if (rs.next()) {
					customer.setId(rs.getInt("idClient"));
					customer.setFirstName(rs.getString("firstName"));
					customer.setLastName(rs.getString("lastName"));
					customer.setEmail(rs.getString("email"));
					customer.setPassword(rs.getString("password"));
					customer.setPhone(rs.getInt("phone"));
				}
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}

		return customer;


	}
	
	public Customer register(String firstName, String lastName, String email ,String password, String phone)
	{
		int count = 0;
		String sql = "SELECT count(*) AS  count FROM Client WHERE  email = ?";
		
		try (Connection conn = this.connect();
				PreparedStatement pstmt  = conn.prepareStatement(sql);
				){
			// set the value
			pstmt.setString(1,firstName);
			pstmt.setString(2,lastName);
			pstmt.setString(3,email);
			pstmt.setString(4,password);
			pstmt.setInt(5,Integer.parseInt(phone));

			try(ResultSet rs  = pstmt.executeQuery()){
		        // username does exist, now check the password
				if (rs.next()) {
					 count = rs.getInt("count");
				}
				
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		
		Customer customer = new Customer();

		if(count == 0) {
			
			sql = "INSERT INTO Client (firstName, lastName, email, password, phone) VALUES (?, ?, ?, ?, ?)";
	
			try (Connection conn = this.connect();
					PreparedStatement pstmt  = conn.prepareStatement(sql)){
	
				// set the value
				pstmt.setString(1,firstName);
				pstmt.setString(2,lastName);
				pstmt.setString(3,email);
				pstmt.setString(4,password);
				pstmt.setInt(5,Integer.parseInt(phone));
	
				pstmt.executeUpdate();
	
	
	
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			sql = "SELECT * FROM Client WHERE firstName= ? and lastName = ? and email = ? and password = ? and phone = ?";
	
			
			try (Connection conn = this.connect();
					PreparedStatement pstmt  = conn.prepareStatement(sql);
					){
				// set the value
				pstmt.setString(1,firstName);
				pstmt.setString(2,lastName);
				pstmt.setString(3,email);
				pstmt.setString(4,password);
				pstmt.setInt(5,Integer.parseInt(phone));
	
				try(ResultSet rs  = pstmt.executeQuery()){
			        // username does exist, now check the password
					if (rs.next()) {
						customer.setId(rs.getInt("idClient"));
						customer.setFirstName(rs.getString("firstName"));
						customer.setLastName(rs.getString("lastName"));
						customer.setEmail(rs.getString("email"));
						customer.setPassword(rs.getString("password"));
						customer.setPhone(rs.getInt("phone"));
					}
				}
	
			} catch (SQLException e) {
				System.out.println(e.getMessage());
	
			}
		}
		
		return customer;


	}



	public static void main(String[] args) throws Exception{

		try {

			Class.forName("org.sqlite.JDBC");

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}
		Connect app = new Connect();
		System.out.print(app.checkExistingReservation(2,"2020-06-21",3));
        
		
		/*
		Customer customer = app.register("Vivi", "JALLON","vivijallon@gmail.com","123456","0775342178");
		System.out.print(customer.getFirstName());


		/*
		List<Hotel> hotelsAvailable = app.getHotelsAvailable("2020-11-09", 4);
		for(Hotel hotel: hotelsAvailable){
			System.out.print("hotelId: " + hotel.getId() + " roomId: " +hotel.getRoomId() );
			System.out.print("\n");
		}
		*/
		
		
		 
		
		/*
		String res  = app.insertReservation(1, 1, 2, "01-04-2021", 4);
		System.out.print(res);
	    /*


		/*
	    String rentalDateStr ="15-06-2020";  
	    Date rentalDate =new SimpleDateFormat("dd-MM-yyyy").parse(rentalDateStr);  
	    System.out.println(rentalDateStr+"\t"+rentalDate);
        Calendar c = Calendar.getInstance();
        c.setTime(rentalDate);
	   	c.add(Calendar.DAY_OF_MONTH, 5);  
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
        String endDateStr = dateFormat.format(c.getTime());  
        System.out.println("endDate: " + endDateStr);  
        */

	}
}