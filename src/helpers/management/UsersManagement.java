package helpers.management;

import helpers.view.ApplicationButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import view.AdminGUI;
import view.admin.users.*;

public class UsersManagement implements Runnable,
										ActionListener{
	//General management
	public AdminGUI adminGUI;
	private GridBagConstraints gbCons = new GridBagConstraints();
	private UserCreator userCreator = null;
	private UserDelete userDelete = null;
	
	//Dialog management
	public boolean bCreateDialog = false;
	public boolean bAssign = false;
	public boolean bDeleteDialog = false;
	
	//Object attributes
	private JLabel userListLabel = new JLabel("Users management");
	
	//Buttons images
	private ImageIcon newUserImage = 
			new ImageIcon("src/images/newButton.png");
	private ImageIcon overNewUserImage = 
			new ImageIcon("src/images/newButton_h.png");
	private ImageIcon deleteImage = 
			new ImageIcon("src/images/deleteButton.png");
	private ImageIcon overDeleteImage = 
			new ImageIcon("src/images/deleteButton_h.png");
	private ImageIcon findImage = 
			new ImageIcon("src/images/searchButton.png");
	private ImageIcon overFindImage = 
			new ImageIcon("src/images/searchButton_h.png");
	
	//Object buttons
	private ApplicationButton newUserButton = 
			new ApplicationButton(newUserImage, overNewUserImage);
	private ApplicationButton deleteButton = 
			new ApplicationButton(deleteImage, overDeleteImage);
	private ApplicationButton findButton = 
			new ApplicationButton(findImage, overFindImage);
	
	
	public UsersManagement(AdminGUI adminGUI) {
		this.adminGUI = adminGUI;
		userListLabel.setForeground(new Color(255, 255, 255));
		Font font = new Font("Verdana", Font.BOLD, 25);
		userListLabel.setFont(font);
	
		newUserButton.setOpaque(false);
		newUserButton.setBorderPainted(false);
		newUserButton.setContentAreaFilled(false);
		newUserButton.setToolTipText("Add new user");
		newUserButton.addActionListener(this);
		
		deleteButton.setOpaque(false);
		deleteButton.setBorderPainted(false);
		deleteButton.setContentAreaFilled(false);
		deleteButton.setToolTipText("Delete user");
		deleteButton.addActionListener(this);
		
		findButton.setOpaque(false);
		findButton.setBorderPainted(false);
		findButton.setContentAreaFilled(false);
		findButton.setToolTipText("Assign to group");
		findButton.addActionListener(this);
	}
	
	public void run() {	    
	    gbCons.insets.bottom = 20;
	    gbCons.gridx = 0;
	    gbCons.gridy = 0;
	    gbCons.gridwidth = 3;
	    adminGUI.guiPanel.add(userListLabel, gbCons);
	    
	    gbCons.insets.bottom = 0;
	    gbCons.insets.top = 20;
	    gbCons.insets.right = 5;
	    gbCons.gridy = 1;
	    gbCons.gridwidth = 1;
	    adminGUI.guiPanel.add(newUserButton, gbCons);
	    
	    gbCons.insets.left = 5;
	    gbCons.gridx = 1;
	    adminGUI.guiPanel.add(deleteButton, gbCons);
	    
	    gbCons.insets.right = 0;
	    gbCons.gridx = 2;
	    adminGUI.guiPanel.add(findButton, gbCons);
	    
	    adminGUI.repaintGUI();
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == newUserButton) {
			//Open dialog
			if(!bCreateDialog && !bAssign && !bDeleteDialog) {
				userCreator = new UserCreator(this);
				userCreator.run();
			}
		}
		
		if(event.getSource() == deleteButton) {
			if(!bCreateDialog && !bAssign && !bDeleteDialog) {
				userDelete = new UserDelete(this);
				userDelete.run();
			}
		}
		
		if(event.getSource() == findButton) {
			UserAssign userAssign = new UserAssign(this);
			userAssign.run();
		}
	}

}
