package hotelreservation;

public class Hotel {

	public Hotel(int id, String hotelName, String hotelAdress, int roomId) {
		super();
		this.id = id;
		this.hotelName = hotelName;
		this.hotelAdress = hotelAdress;
		this.roomId = roomId;
	}
	

	
	public Hotel() {
		// TODO Auto-generated constructor stub
	}



	private int id;
	private String hotelName;
	private String hotelAdress;
	private int roomId;
	
	public int getId() {
		return id;
	}

	public String getHotelName() {
		return hotelName;
	}

	public String getHotelAdress() {
		return hotelAdress;
	}
	
	public int getRoomId() {
		return roomId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public void setHotelAdress(String hotelAdress) {
		this.hotelAdress = hotelAdress;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}



	
}
