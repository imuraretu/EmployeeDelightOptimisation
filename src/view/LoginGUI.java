package view;

import helpers.bean.*;
import helpers.view.ApplicationButton;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import db.ProductDBHandler;
import db.UserDBHandler;
import xml.XMLReader;

/*
 * !-- LOGIN CLASS --!
 * This class is used for first contact between
 * user and application. In this class will be
 * created the GUI where the user should login
 * for using the application.
 * !-- ----------- --!
 */
public class LoginGUI extends JFrame implements Runnable, 
											 ActionListener {
	private static final long serialVersionUID = 1L;
	
	//General management
	private Dimension minimumDimension = new Dimension(300, 200);

	//Login button images
	private ImageIcon loginImage = new ImageIcon("src/images/submit.png");
	private ImageIcon hLoginImage = new ImageIcon("src/images/submit_h.png");
	
	//Objects attributes
	private ApplicationButton loginButton = 
			new ApplicationButton(loginImage, hLoginImage);
	private JTextField usernameTextField = new JTextField("");
	private JPasswordField passwordField = new JPasswordField("");
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	private JLabel errorLabel = new JLabel("Error!");
	
	//Fonts management
	private Font loginDataFont = new Font("Verdana", Font.BOLD, 12);
	
	//Login management
	private String auxUsername = new String();
	private String auxPassword;
	
	//Layout management attributes
	private GridBagConstraints constraints = new GridBagConstraints();
	
	//List of users (from XML)
	public static ArrayList<User> usersList = new ArrayList<User>();
	public static ArrayList<String> groupsList = new ArrayList<String>();
	public static ArrayList<String> categoryList = new ArrayList<String>();
	public static ArrayList<Product> productsCategory1 = new ArrayList<Product>();
	public static ArrayList<Product> productsCategory2 = new ArrayList<Product>();
	public static ArrayList<Product> productsCategory3 = new ArrayList<Product>();
	public static ArrayList<Integer>pointsList = new ArrayList<Integer>();
	
	//DB Connectors
	public static UserDBHandler userCon;
	public static ProductDBHandler productCon;
	
	
	//Class constructor
	public LoginGUI() {
		setVisible(true);
		setLayout(new GridBagLayout());
		setSize(300, 200);
		setMinimumSize(minimumDimension);
		setTitle("UnifiedPost - Login");
		setResizable(false);
		setLocationRelativeTo(new JFrame());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.DARK_GRAY);
		
		//Applying Windows Look and Feel
		try {
			UIManager.setLookAndFeel
				("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	//Method for adjusting objects properties
	private void adjustObjects() {
		//Adjust labels' fonts
		usernameLabel.setFont(loginDataFont);
		usernameLabel.setForeground(new Color(255, 98, 0));
		passwordLabel.setFont(loginDataFont);
		passwordLabel.setForeground(new Color(255, 98, 0));
		errorLabel.setForeground(Color.RED);
		
		//Adjust password field
		passwordField.setEchoChar('*');
		
		//Adjust button
		loginButton.addActionListener(this);
		loginButton.setOpaque(false);
		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setToolTipText("Find user");
		loginButton.addActionListener(this);
	}
	
	//Method for reading an XML (call for XMLReader)
	private void readXML() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		XMLReader handler = new XMLReader();
		SAXParser parser;
		
		//Parsing xml file
		try {
			parser = factory.newSAXParser();
			parser.parse("src/data/users.xml", handler);
			groupsList = handler.getGroupNames();
		} catch(SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	
	public static void createUsersList() throws SQLException {
		usersList = userCon.tableToArrayList("users");	
	}
	
	public static void createGroupsList() {
		try {
			groupsList = userCon.getGroupName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createCategoryList() {
		try {
			categoryList = productCon.getGroupName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createProductsLists() {
		try {
			productsCategory1 = productCon.tableToArrayList(categoryList.get(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			productsCategory2 = productCon.tableToArrayList(categoryList.get(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			productsCategory3 = productCon.tableToArrayList(categoryList.get(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static boolean bView = true;
	
	//getting data from DB
	public void initConnection() throws SQLException{
		userCon = new UserDBHandler("jdbc:mysql://127.0.0.1:3306/users", "admin", "admin");
		productCon = new ProductDBHandler("jdbc:mysql://127.0.0.1:3306/products", "admin", "admin");
	}
	
	public void createGroupsDB() {
		System.out.println(groupsList.get(0));
		for(int i=0; i<groupsList.size(); i++) {
			try {
				userCon.createTable(groupsList.get(i));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//Run method: here is 'login frame' created and managed
	public void run() {
		adjustObjects();
		
		//Put objects in frame using GridBagConstraints		
		constraints.insets.bottom = 5;
		constraints.insets.top = 5;
		constraints.insets.right = 5;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		getContentPane().add(usernameLabel, constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		getContentPane().add(passwordLabel, constraints);
		
		constraints.insets.right = 0;
		constraints.insets.left = 5;
		constraints.ipadx = 120;
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		getContentPane().add(usernameTextField, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		getContentPane().add(passwordField, constraints);
		
		constraints.insets.top = 10;
		constraints.insets.bottom = 0;
		constraints.insets.left = 0;
		constraints.ipadx = 0;
		constraints.anchor = GridBagConstraints.EAST;
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		getContentPane().add(loginButton, constraints);
		
		//Find the list of users accounts
		//readXML();
		
		//Connect to database
		try {
			initConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//createGroupsDB();
		
		createGroupsList();
		try {
			createUsersList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createCategoryList();
		createProductsLists();
		
		if(bView) {
			setVisible(true);
		} else {
			setVisible(false);
		}
	}

	
	//In this method we will manage the events from 'loginButton'
	public void actionPerformed(ActionEvent event) {
		//We have only one button
		if(event.getSource() == loginButton) {
			//Getting the username and password inserted
			auxUsername = usernameTextField.getText();
			auxPassword = new String(passwordField.getPassword());
			
			//Check if user introduces username and password
			if(!auxUsername.equals("") && !auxPassword.equals("")) {
				//First case: the administrator is logging
				if(auxUsername.equals("admin") 
						&& auxPassword.equals("admin")) {
					setVisible(false);
					
					User auxUser = null;
					for(int i=0 ; i<usersList.size(); i++) {
						if(usersList.get(i).getUsername().equals("admin") && 
									usersList.get(i).getPassword().equals("admin")) {
							auxUser = usersList.get(i);
						}
					}
					AdminGUI adminGUI = new AdminGUI(this, auxUser);
					adminGUI.run();
				} else {
					//Second case: somebody else is logging
					User auxUser = null;
					for(int i=0; i<usersList.size(); i++) {
						if(usersList.get(i).getUsername().equals(auxUsername) &&
									usersList.get(i).getPassword().equals(auxPassword)) {
							auxUser = usersList.get(i);
						}
					}
					
					if(auxUser != null) {
						setVisible(false);
						UserGUI userGUI = new UserGUI(this, auxUser);
						userGUI.run();
					} else {
						JOptionPane.showMessageDialog(null,
								"Username or password incorect! Try again!",
				        		"ERROR!", JOptionPane.ERROR_MESSAGE);
						//Delete text from textfields
						usernameTextField.setText("");
						passwordField.setText("");
					}
				}
				
				//Delete text from textfields
				usernameTextField.setText("");
				passwordField.setText("");
			}
		}
	}
}
