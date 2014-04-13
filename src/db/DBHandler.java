package db;

import helpers.bean.Product;
import helpers.bean.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DBHandler {
	public void createTable(String s) throws SQLException;
	public void insertRecord(String _table, String _firstName, String _lastName, 
    		String _username, String _password, int _points) throws SQLException;
	public void insertRecord(String _table,  String _productName, int _productPrice, int votes) throws SQLException;
	public void deleteRecord(String _table, String _username) throws SQLException;
	public void updateRecord(int _points, String _username) throws SQLException;
	public ArrayList<?> tableToArrayList(String tabel) throws SQLException;
	
}
