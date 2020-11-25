package hotelreservation;

import java.io.IOException;
import java.rmi.RemoteException;

import hotelreservation.HotelReservationStub.CheckHotelsAvailable;
import hotelreservation.HotelReservationStub.Book;
import hotelreservation.HotelReservationStub.Authenticate;
import hotelreservation.HotelReservationStub.Register;
import hotelreservation.HotelReservationStub.CheckExistingReservation;

import com.google.gson.*;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static Customer connexion(Scanner scanner) throws RemoteException{
		
		HotelReservationStub hwp = new HotelReservationStub();
	    Gson gson = new Gson();
		Customer customer = new Customer();

		while( true ) {
			System.out.print( "Enter your email: " );
			String email = scanner.nextLine();
			
			System.out.print( "Enter your password: " );
			String password = scanner.nextLine();
			
			Authenticate a = new Authenticate();
			a.setEmail(email);
			a.setPassword(password);
			String authenticateResult = hwp.authenticate(a).get_return();
			customer = gson.fromJson(authenticateResult, Customer.class);
			
			if ( customer.isNull() ) {
				System.out.println("Connection failed, verify your password and your email adress and try again.");
				System.out.print("\n");
			} else {
				System.out.println("Successful connection !");
				System.out.print("\n");
				break;
			}
	

		}

		
		return customer;
	}

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
	    Gson gson = new Gson();
		HotelReservationStub hwp = new HotelReservationStub();
		Customer customerConnected = new Customer();
		
        try (Scanner scanner = new Scanner(System.in)) {

			while(true){

			
				System.out.print( " Enter 1 to login, 2 to register: " );
				String choix = scanner.nextLine();

				if(choix.equals("1")){

					customerConnected = connexion(scanner);
					break;

				}else if(choix.equals("2")){
					
					System.out.print( "Enter your firstName: " );
					String firstName = scanner.nextLine();

					System.out.print( "Enter your lastName: " );
					String lastName = scanner.nextLine();

					System.out.print( "Enter your email: " );
					String email = scanner.nextLine();

					System.out.print( "Enter your password: " );
					String password = scanner.nextLine();

					System.out.print( "Enter your phone: " );
					String phone = scanner.nextLine();


					Register r = new Register();
					r.setFirstName(firstName);
					r.setLastName(lastName);
					r.setEmail(email);
					r.setPassword(password);
					r.setPhone(phone);

					String registerResult = hwp.register(r).get_return();
					customerConnected = gson.fromJson(registerResult, Customer.class);
					if ( customerConnected.isNull() ) {
						System.out.println("Register failed");
						System.out.print("\n");
					}else{
						System.out.println("Successful register !");
						System.out.print("\n");
						break;
					}
				}else{
					System.out.println("please start again");	
				}
			}

           			
			while(true){
				
	            System.out.print( "Enter a rental date (yyyy-MM-dd): ");
	            String rentalDate = scanner.nextLine();
	            
	            System.out.print( "Enter the number of nights: ");
	            int nbNights =  Integer.parseInt(scanner.nextLine());
	            
	            System.out.print( "Enter the number of rooms: ");
	            int nbRooms =  Integer.parseInt(scanner.nextLine());
	            
	            CheckHotelsAvailable c = new CheckHotelsAvailable();
	    		c.setRentalDate(rentalDate);
	    		c.setNbNights(nbNights);
	    		c.setNbRooms(nbRooms);

				String hotelsAvailableJson = hwp.checkHotelsAvailable(c).get_return();
				ObjectMapper mapper = new ObjectMapper();
				List<Hotel> hotelsAvailable = mapper.readValue(hotelsAvailableJson, new TypeReference<List<Hotel>>(){});
		
				if(hotelsAvailable.size()!=0) {
		    		System.out.print(hotelsAvailableJson);
					System.out.print("\n");
					
					System.out.print( "enter the hotel id you want: ");
					int hotelId =  Integer.parseInt(scanner.nextLine());
					
					System.out.print( "enter the room id you want: ");
		            int roomId =  Integer.parseInt(scanner.nextLine());
		            
					CheckExistingReservation cer = new CheckExistingReservation();
					cer.setIdCustomer(customerConnected.getId());
					cer.setRentalDate(rentalDate);
					cer.setNbNights(nbNights);
					
	
	
					int isAvailable = 0;
					for(Hotel hotel: hotelsAvailable){
						  if(hotel.getId() == hotelId && hotel.getRoomId() == roomId) {
							  isAvailable = 1;
						  } 
					}
	
	
					
	
					String anyExisting = hwp.checkExistingReservation(cer).get_return();
					
	
					if(Integer.parseInt(anyExisting)!=0 && isAvailable==1) {
						String response = "Please answer Y or N";
						while(response.equals("Please answer Y or N")){
							System.out.print( "You already have existing reservation for these dates, do you want to replace it with the new one ? Y/N ");
				            String customerResponse = scanner.nextLine();
		
							Book b = new Book();
							b.setHotelId(hotelId);
							b.setRoomId(roomId);
							b.setIdCustomer(customerConnected.getId());
							b.setRentalDate(rentalDate);
							b.setNbNights(nbNights);
							b.setNbRooms(nbRooms);
							b.setCustomerResponse(customerResponse);
							response = hwp.book(b).get_return();
							System.out.print(response);
							System.out.print("\n");
						}
					} else {
						Book b = new Book();
						b.setHotelId(hotelId);
						b.setRoomId(roomId);
						b.setIdCustomer(customerConnected.getId());
						b.setRentalDate(rentalDate);
						b.setNbNights(nbNights);
						b.setNbRooms(nbRooms);
	
						b.setCustomerResponse(" ");
						System.out.print(hwp.book(b).get_return());
						System.out.print("\n");
	
					}
				} else {
					System.out.print("No hotels available for these dates and this number of room");
					System.out.print("\n");
				}
				
	            System.out.print( "Do you want to book a new room ? Y/N ");
	            String customerResponse = scanner.nextLine();
	            if (customerResponse.equals("N")) {
	            	break;
	            }
				System.out.print("\n");
			}
        }
	}

}
