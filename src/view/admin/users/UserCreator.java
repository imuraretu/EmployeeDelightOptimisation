package view.admin.users;

import helpers.bean.User;
import helpers.management.UsersManagement;
import helpers.view.ApplicationButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;

import view.LoginGUI;

/*
 * !-----------------------------------------------!
 * !------------- UserGenerator Class -------------!
 * !-----------------------------------------------!
 * This class is used to add a new user to database.
 * Only the administrator could add a new user.
 */
public class UserCreator extends JDialog implements Runnable,
														ActionListener,
														WindowListener{
	private static final long serialVersionUID = 1L;
	
	//Management attributes
	private Dimension minimumDimension = new Dimension(400, 400);
	private GridBagConstraints gbCons = new GridBagConstraints();
	public boolean isOpen = false;
	private UsersManagement usersManagement;
	private Vector<String> groups = new Vector<String>();
	private boolean isSubmiting = false;
	
	//Object attributes
	private JLabel createLabel = new JLabel("Create new user");
	private JLabel personalDataLabel = new JLabel("Personal data");
	private JLabel loginDataLabel = new JLabel("Login data");
	private JLabel firstNameLabel = new JLabel("First name");
	private JLabel lastNameLabel = new JLabel("Last name");
	private JLabel groupLabel = new JLabel("Group");
	private JLabel usernameLabel = new JLabel("Username");
	private JLabel passwordLabel = new JLabel("Password");
	
	private JTextField firstNameTextField = new JTextField("");
	private JTextField lastNameTextField = new JTextField("");
	private JComboBox groupComboBox;
	private JTextField usernameTextField = new JTextField("");
	private JTextField passwordTextField = new JTextField("");
	
	private Font createFont = new Font("Verdana", Font.BOLD, 25);
	private Font dataFont = new Font("Verdana", Font.BOLD, 15);
	private Font propertiesFont = new Font("Verdana", Font.BOLD, 12);
	
	//Buttons images
	private ImageIcon submitImage = new ImageIcon("src/images/submituser.png");
	private ImageIcon hSubmitImage = new ImageIcon("src/images/submituser_h.png");
	private ImageIcon cancelImage = new ImageIcon("src/images/cancel.png");
	private ImageIcon hCancelImage = new ImageIcon("src/images/cancel_h.png");
	
	//Buttons management
	private ApplicationButton submitButton = 
			new ApplicationButton(submitImage, hSubmitImage);
	private ApplicationButton cancelButton = 
			new ApplicationButton(cancelImage, hCancelImage);
	
	//Class constructor
	public UserCreator(UsersManagement usersManagement) {
		super(usersManagement.adminGUI);
		this.usersManagement = usersManagement;
		setSize(400, 400);
		setLayout(new GridBagLayout());
		setMinimumSize(minimumDimension);
		setTitle("UnifiedPost - Create new user");
		getContentPane().setBackground(Color.DARK_GRAY);
		setVisible(true);
		setLocationRelativeTo(new JFrame());
		
		addWindowListener(this);
		
		//get groups
		for(int i=0; i<LoginGUI.groupsList.size(); i++) {
			groups.addElement(LoginGUI.groupsList.get(i));
		}
		
		groupComboBox = new JComboBox(groups);
	}
	
	private void configObjects() {
		createLabel.setFont(createFont);
		createLabel.setForeground(Color.WHITE);
		
		personalDataLabel.setFont(dataFont);
		personalDataLabel.setForeground(Color.WHITE);
		
		loginDataLabel.setFont(dataFont);
		loginDataLabel.setForeground(Color.WHITE);
		
		firstNameLabel.setFont(propertiesFont);
		firstNameLabel.setForeground(new Color(255, 98, 0));
		
		lastNameLabel.setFont(propertiesFont);
		lastNameLabel.setForeground(new Color(255, 98, 0));
		
		groupLabel.setFont(propertiesFont);
		groupLabel.setForeground(new Color(255, 98, 0));
		
		usernameLabel.setFont(propertiesFont);
		usernameLabel.setForeground(new Color(255, 98, 0));
		
		passwordLabel.setFont(propertiesFont);
		passwordLabel.setForeground(new Color(255, 98, 0));
		
		groupComboBox.setSelectedIndex(0);
		groupComboBox.addActionListener(this);
		
		submitButton.setOpaque(false);
		submitButton.setBorderPainted(false);
		submitButton.setContentAreaFilled(false);
		submitButton.setToolTipText("Submit");
		submitButton.addActionListener(this);
		
		cancelButton.setOpaque(false);
		cancelButton.setBorderPainted(false);
		cancelButton.setContentAreaFilled(false);
		cancelButton.setToolTipText("Cancel");
		cancelButton.addActionListener(this);
	}
	
	private void addObjects() {
		gbCons.insets.bottom = 8;
		gbCons.insets.left = 8;
		gbCons.insets.right = 8;
		gbCons.insets.top = 8;
		
		gbCons.gridx = 0;
		gbCons.gridy = 0;
		gbCons.gridwidth = 2;
		getContentPane().add(createLabel, gbCons);
		
		gbCons.anchor = GridBagConstraints.WEST;
		gbCons.gridwidth = 1;
		gbCons.gridy++;
		getContentPane().add(loginDataLabel, gbCons);
		
		gbCons.gridy++;
		getContentPane().add(usernameLabel, gbCons);
		
		gbCons.gridy++;
		getContentPane().add(passwordLabel, gbCons);
		
		gbCons.gridy++;
		getContentPane().add(personalDataLabel, gbCons);
		
		gbCons.gridy++;
		getContentPane().add(firstNameLabel, gbCons);
		
		gbCons.gridy++;
		getContentPane().add(lastNameLabel, gbCons);
		
		gbCons.gridy++;
		getContentPane().add(groupLabel, gbCons);
		
		gbCons.ipadx = 100;
		gbCons.gridx = 1;
		gbCons.gridy = 2;
		getContentPane().add(usernameTextField, gbCons);
		
		gbCons.gridy = 3;
		getContentPane().add(passwordTextField, gbCons);
		
		gbCons.gridy = 5;
		getContentPane().add(firstNameTextField, gbCons);
		
		gbCons.gridy = 6;
		getContentPane().add(lastNameTextField, gbCons);
		
		gbCons.ipadx = 32;
		gbCons.gridy = 7;
		getContentPane().add(groupComboBox, gbCons);
		
		gbCons.anchor = GridBagConstraints.EAST;
		gbCons.ipadx = 0;
		gbCons.gridx = 0;
		gbCons.gridy = 8;
		getContentPane().add(submitButton, gbCons);
		
		gbCons.anchor = GridBagConstraints.WEST;
		gbCons.ipadx = 0;
		gbCons.gridx = 1;
		gbCons.gridy = 8;
		getContentPane().add(cancelButton, gbCons);
	}

	public void run() {
		configObjects();
		addObjects();
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == cancelButton) {
			usersManagement.bCreateDialog = false;
			dispose();
		}
		
		if(event.getSource() == submitButton) {
			String username = usernameTextField.getText();
			String password = passwordTextField.getText();
			String firstName = firstNameTextField.getText();
			String lastName = lastNameTextField.getText();
			String group = groupComboBox.getSelectedItem().toString();
			
			if(username.equals("") || password.equals("") ||
					firstName.equals("") || lastName.equals("")) {
				JOptionPane.showMessageDialog(null,
						"You must complete all fields.",
		        		"ERROR!", JOptionPane.ERROR_MESSAGE);
			} else {
				isSubmiting = true;
				for(int i=0; i<LoginGUI.usersList.size(); i++) {
					String auxUsername = LoginGUI.usersList.get(i).getUsername();
					if(username.equals(auxUsername)) {
						JOptionPane.showMessageDialog(null,
								"Username already exists.",
				        		"ERROR!", JOptionPane.ERROR_MESSAGE);
						isSubmiting = false;
					}
				}
				if(isSubmiting) {
					//Create new user
					User user = new User(username, password, 
										 firstName, lastName, group);
					
					try {
						LoginGUI.userCon.insertRecord(group, user.getFirstName(), user.getLastName(), user.getUsername(),
														user.getPassword(), user.getPoints());
						JOptionPane.showMessageDialog(null,
								"User was succesfully created.",
					       		"TASK COMPLETE", JOptionPane.INFORMATION_MESSAGE);
						LoginGUI.createUsersList();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,
								"Username already exists.",
					       		"ERROR!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	public void windowActivated(WindowEvent event) {
		
	}

	public void windowClosed(WindowEvent event) {

	}

	public void windowClosing(WindowEvent event) {
		usersManagement.bCreateDialog = false;
	}

	public void windowDeactivated(WindowEvent event) {
		
	}

	public void windowDeiconified(WindowEvent event) {
		
	}

	public void windowIconified(WindowEvent event) {
		
	}
	

	public void windowOpened(WindowEvent event) {
			usersManagement.bCreateDialog = true;
	}
}
