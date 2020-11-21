package hotelFiltering;

public class Customer {
	
	public Customer(int id, String firstName, String lastName, String email, String password, int phone) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int phone;
	
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public int getPhone() {
		return phone;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}


	public boolean isNull() {
		boolean isNull = false;
		if(this.id == 0 && this.firstName == null && this.lastName == null && this.email == null && this.password == null && this.phone == 0) {
			 isNull = true;
		}
		return isNull;
	}
	
}