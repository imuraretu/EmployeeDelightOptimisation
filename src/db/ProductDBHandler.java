package db;

import helpers.bean.Product;
import helpers.bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.DatabaseMetaData;

public class ProductDBHandler implements DBHandler {
	
	private String url;
    private String user;
    private String password;
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
	
	public ProductDBHandler(String _url, String _user, String _password) throws SQLException {
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

	public void createTable(String s) throws SQLException {
		st = con.createStatement();
        String sql = "CREATE TABLE " + s +
                      "(productName VARCHAR(255) not NULL, " + 
                      " productPrice VARCHAR(255) not NULL, " +
                      " votes INT(6) not NULL, " +
                      " PRIMARY KEY ( productName ))"; 
        
        st.executeUpdate(sql);
		
	}
	
	public void updateVots(String _tableName, String _productName, int _productVotes) throws SQLException {
    	st = con.createStatement();
    	String sql = "UPDATE " + _tableName + " SET votes = " + _productVotes
                	+ " WHERE productName = '" +_productName+ "'";
    	st.executeUpdate(sql);
    }

	public void insertRecord(String _table, String _firstName,
			String _lastName, String _username, String _password, int _points)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void insertRecord(String _table, String _productName,
			int _productPrice, int votes) throws SQLException {
		st = con.createStatement();
    	String sql = "INSERT INTO " + _table + " (productName, productPrice, votes) " +
                "VALUES ('"+ _productName + "', '" + _productPrice +"', '" + votes +"')";
        st.executeUpdate(sql);
		
	}

	public void deleteRecord(String _table, String _productName)
			throws SQLException {
		st = con.createStatement();
    	String sql = "DELETE FROM "+ _table  + " WHERE productName = '"+ _productName + "' ";
    	st.executeUpdate(sql);
		
	}

	public void updateRecord(int _productPrice, String _productName) throws SQLException {
		st = con.createStatement();
    	String sql = "UPDATE produse SET productPrice = " + _productPrice
                	+ " WHERE productName = '" +_productName+ "'";
    	st.executeUpdate(sql);
		
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

	public ArrayList<Product> tableToArrayList(String tabel) throws SQLException {
		ArrayList<Product> productsList = new ArrayList<Product>();
		productsList.clear();
		st = con.createStatement();
        String sql = "SELECT productName, productPrice, votes FROM " + tabel;
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            //Retrieve by column name
            String prodName = rs.getString("productName");
            int prodPrice = rs.getInt("productPrice");
            int vts = rs.getInt("votes");

            Product pr = new Product(prodName, prodPrice, vts);
            productsList.add(pr);          
         }
         rs.close();
		return productsList;
	}
	
	public void changeGroupName(String name1, String name2) throws SQLException {
    	st = con.createStatement();
    	String sql = "RENAME TABLE " + name1 + " TO " + name2;
    	st.executeUpdate(sql);
	}
	
}
