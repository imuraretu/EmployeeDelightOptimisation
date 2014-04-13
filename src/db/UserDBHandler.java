package db;

import helpers.bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import view.LoginGUI;

import com.mysql.jdbc.DatabaseMetaData;



public class UserDBHandler implements DBHandler {
	
	
	 private String url;
     private String user;
     private String password;
     private Connection con = null;
     private Statement st = null;
     private ResultSet rs = null;
     private ArrayList<User> usersList = new ArrayList<User>();
     
     
    public UserDBHandler(String _url, String _user, String _password) throws SQLException {
  
    	url      	= _url;
    	user     	= _user;
    	password 	= _password;
    	
    	
  
        try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        con = DriverManager.getConnection(url, user, password);
        st = con.createStatement();
        rs = st.executeQuery("SELECT VERSION()");
    }
    
    /**
     * Create a table to store users
     * @param table  name of the table wich will be created
     * @throws SQLException
     */
    public void createTable(String table) throws SQLException {
    	
    	
    	st = con.createStatement();
        
        String sql = "CREATE TABLE " + table +
                      "(firstName VARCHAR(255) not NULL, " + 
                      " lastName VARCHAR(255) not NULL, " + 
                      " username VARCHAR(255) not NULL, " +
                      " password VARCHAR(255) not NULL, " +
                      " points INTEGER not NULL, " + 
                      " PRIMARY KEY ( username ))"; 

        st.executeUpdate(sql);
    }
    
    
    
    public void insertRecord(String _table, String _firstName, String _lastName, 
    		String _username, String _password, int _points) throws SQLException {
    	
    	st = con.createStatement();
    	String sql = "INSERT INTO " + _table + " (firstName, lastName, username, password, points) " +
                "VALUES ('"+ _firstName + "', '" + _lastName +"', '" + _username + "', '"
    					+ _password + "', '" + _points +"' )";
        st.executeUpdate(sql);
    }
    
    public void deleteRecord(String _table, String _username) throws SQLException {
    	st = con.createStatement();
    	String sql = "DELETE FROM "+ _table  + " WHERE username = '"+ _username + "'";
    	System.out.println(sql);
    	st.executeUpdate(sql);
    }
    
    public void updateRecord(int _points, String _username) throws SQLException {
    	st = con.createStatement();
    	String sql = "UPDATE users SET points = " + _points
                	+ " WHERE username = '" +_username+ "'";
    	st.executeUpdate(sql);
    }
    
    public String getGroupNameByUsername(String username) {
		String groupName = null;
		//ArrayList<String>
		
		for(int i=0; i<LoginGUI.groupsList.size(); i++) {
			
		}
				
    	return groupName;
    }
    
    public ArrayList<User> tableToArrayList(String tabel) throws SQLException {
    	st = con.createStatement();
        String sql = "SELECT firstName, lastName, username, password, points FROM " + tabel;
        ResultSet rs = st.executeQuery(sql);
        usersList.clear();
        while(rs.next()){
            //Retrieve by column name
            String first = rs.getString("firstName");
            String last = rs.getString("lastName");
            String usr = rs.getString("username");
            String password = rs.getString("password");
            int points = rs.getInt("points");

            User _user = new User(usr, password, first, last, points);
  
            usersList.add(_user);
        }
        rs.close();
		return usersList;
    }

	public void insertRecord(String _table,  String _productName,
			int _productPrice, int votes) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<String> getGroupName() throws SQLException{
		
		ArrayList<String> groupName = new ArrayList<String>();
		
		DatabaseMetaData dbm = (DatabaseMetaData) con.getMetaData();
		String[] types = {"TABLE"};
		ResultSet rs = dbm.getTables(null,null,"%",types);
		while (rs.next()){
			String table = rs.getString("TABLE_NAME");
			if(!table.equals("users")) {
				groupName.add(table);
			}
		}
		return groupName;
	}
	

	public void searchGroup(String user, ArrayList<String> groups) throws SQLException{
		for(int i = 0; i < groups.size(); i++){
			System.out.println(tableToArrayList(groups.get(i)).get(0).getUsername());
		}
	}
	
	 public void changeGroupName(String name1, String name2) throws SQLException {
	    	st = con.createStatement();
	    	String sql = "RENAME TABLE " + name1 + " TO " + name2;
	    	st.executeUpdate(sql);
	 }
}