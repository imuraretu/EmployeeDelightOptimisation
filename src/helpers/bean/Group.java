package helpers.bean;

import java.util.ArrayList;

public class Group {
	//General information
	private String name; //db table name
	private ArrayList<User> usersList = new ArrayList<User>(); //getting user info and create an 'User'
	private int pointsPerUser; // table points per user
	
	//Class constructor
	public Group() {
		
	}
	
	//Class constructor
	public Group(String name, ArrayList<User> usersList, int pointsPerUser) {
		this.name = name;
		this.usersList = usersList;
		this.pointsPerUser = pointsPerUser;
	}
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUsersList(ArrayList<User> usersList) {
		this.usersList = usersList;
	}
	
	public void setPointsPerUser(int pointsPerUser) {
		this.pointsPerUser = pointsPerUser;
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public ArrayList<User> getUsersList() {
		return usersList;
	}
	
	public int getPointsPerUser() {
		return pointsPerUser;
	}
	
	//Add new user to group
	public void addNewUser(User user) {
		usersList.add(user);
	}
}
