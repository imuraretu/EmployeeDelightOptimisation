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
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;

import view.LoginGUI;

public class UserAssign extends JDialog implements Runnable,
													ActionListener,
													WindowListener{
	private static final long serialVersionUID = 1L;
	
	public UsersManagement usersManagement;
	private Vector<String> groups = new Vector<String>();
	private ArrayList<User> users = new ArrayList<User>();
	private Vector<String> usersString = new Vector<String>();
	private GridBagConstraints c = new GridBagConstraints();
	private JComboBox groupComboBox;
	private JComboBox usersComboBox;
	public User auxUser;
	
	private ApplicationButton assignButton = new ApplicationButton("Vote", new Color(255, 98, 0), new Color(255,255,255));
	
	private JLabel usersLabel = new JLabel("Username");
	private JLabel cGroupLabel = new JLabel("Current group");
	private JLabel nGroupLabel = new JLabel("nGroupLabel");
	
	private JLabel assignLabel = new JLabel("Assign users to groups");
	
	public UserAssign(UsersManagement usersManagement) {
		super(usersManagement.adminGUI);
		this.usersManagement = usersManagement;
		setSize(400, 400);
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(400, 400));
		setTitle("UnifiedPost -Assign users..");
		getContentPane().setBackground(Color.DARK_GRAY);
		setVisible(true);
		setLocationRelativeTo(new JFrame());
		
		addWindowListener(this);
		
		//get groups
		for(int i=0; i<LoginGUI.groupsList.size(); i++) {
			groups.addElement(LoginGUI.groupsList.get(i));
		}
		groupComboBox = new JComboBox(groups);
		
		users = LoginGUI.usersList;
		for(int i=0; i<users.size(); i++) {
			usersString.addElement(users.get(i).getUsername());
		}
		usersComboBox = new JComboBox(usersString);
	}
	
	public void initBoxes() {
		groupComboBox.setSelectedIndex(0);
		groupComboBox.addActionListener(this);
		
		usersComboBox.setSelectedIndex(0);
		usersComboBox.addActionListener(this);
	}
	
	public void drawObjects() {
		c.insets.top = 8;
		c.insets.bottom = 8;
		c.insets.right = 8;
		c.insets.left = 8;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		getContentPane().add(assignLabel, c);
		
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		getContentPane().add(usersLabel, c);
		
		c.gridx = 1;
		getContentPane().add(nGroupLabel, c);
		
		c.gridx = 0;
		c.gridy = 2;
		getContentPane().add(usersComboBox, c);
		
		c.gridx = 1;
		c.gridy = 2;
		getContentPane().add(groupComboBox, c);
		
		assignButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String username = usersComboBox.getSelectedItem().toString();
				String newGroup = groupComboBox.getSelectedItem().toString();
				
				for(int i=0; i<LoginGUI.usersList.size(); i++) {
					if(LoginGUI.usersList.get(i).getUsername().equals(username)) {
						auxUser = new User(LoginGUI.usersList.get(i).getUsername(),
											LoginGUI.usersList.get(i).getPassword(), 
											LoginGUI.usersList.get(i).getFirstName(),
											LoginGUI.usersList.get(i).getLastName(), 0);
					}
				}
				
				//get group points / table from group-points list (xml first)
				
				try {
					System.out.println(auxUser.getFirstName() + " " +  auxUser.getLastName() + " " + auxUser.getUsername() + " " + auxUser.getPassword());
					LoginGUI.userCon.insertRecord(newGroup, auxUser.getFirstName(), auxUser.getLastName(), auxUser.getUsername(), auxUser.getPassword(), 0);
					JOptionPane.showMessageDialog(null,
							"User was succesfully asigned to new group.",
				       		"TASK COMPLETE", JOptionPane.INFORMATION_MESSAGE);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,
							"User already exist in this group.",
			        		"ERROR!", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		
		c.gridx = 2;
		c.gridy = 2;
		getContentPane().add(assignButton, c);
		
		revalidate();
		repaint();
	}
	
	public String getGroupName(String username) {
		String groupName = null;
		
		return groupName;
	}

	public void run() {
		assignLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		assignLabel.setForeground(Color.WHITE);
		
		usersLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		usersLabel.setForeground(Color.WHITE);
		
		cGroupLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		cGroupLabel.setForeground(Color.WHITE);
		
		nGroupLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		nGroupLabel.setForeground(Color.WHITE);
		
		initBoxes();
		drawObjects();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent arg0) {
		usersManagement.bAssign = false;
	}

	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent arg0) {
		usersManagement.bAssign = true;
	}

}
