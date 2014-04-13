package helpers.bean;

public class User {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	int points;
	private String group;
	
	public User(String username, String password, 
						String firstName, String lastName, String group) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.group = group;
		points = 0;
	}
	
	public User(String username, String password, 
			String firstName, String lastName, int points) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.points = points;

}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	//Setters for attributes
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	//Getters for attributes
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getGroup() {
		return group;
	}
	public int getPoints() {
		return points;
	}
	
}
