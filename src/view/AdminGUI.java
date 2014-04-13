package view;

import helpers.bean.User;
import helpers.management.CategoryManagement;
import helpers.management.GroupsManagement;
import helpers.management.ProductManagement;
import helpers.management.UsersManagement;
import helpers.view.MenuAdminGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

/*
 * !-- AdminGUI Class --!
 * This class is used to manage the administrator GUI
 */
public class AdminGUI extends JFrame implements Runnable,
												ActionListener, ComponentListener {
	private static final long serialVersionUID = 1L;
	
	//General management
	private LoginGUI loginGUI;
	private MenuAdminGUI menuAdminGUI = new MenuAdminGUI(this);
	private GridBagConstraints gbCons = new GridBagConstraints();
	private Dimension minimumDimension = new Dimension(400, 400);
	private boolean bComponentsToPanel = false;
	
	//Menu option management
	private boolean isUsers = false;
	private boolean isGroups = false;
	private boolean isCategory = false;
	private boolean isProducts = false;
	
	private boolean bLogo = true;
	
	//Panels manager
	private JPanel loggedPanel = new JPanel();
	public JPanel guiPanel = new JPanel();
	
	//Objects
	private JLabel loggedLabel;
	private Font loggedFont = new Font("Verdana", Font.ITALIC, 10);
	private JLabel upLogo = new JLabel(new ImageIcon("src/images/upLogo.png"));

	//Class constructor
	public AdminGUI(LoginGUI loginGUI, User loginInfo) {
		this.loginGUI = loginGUI;
		
		//Make logged label
		loggedLabel = new JLabel("Logged in as: " + loginInfo.getFirstName() 
								+ " " + loginInfo.getLastName() 
								+ " (" + loginInfo.getUsername() + ")");
		
		//Set GUI configuration
		setVisible(true);
		setLayout(new GridBagLayout());
		setSize(400, 400);
		setMinimumSize(minimumDimension);
		setTitle("UnifiedPost - Administration");
		getRootPane().addComponentListener(this);
		setLocationRelativeTo(new JFrame());
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void adjustObjects() {
		//Welcome panel components
		loggedLabel.setFont(loggedFont);
		loggedLabel.setForeground(new Color(255, 98, 0));
		
		//Panels
		loggedPanel.setLayout(new BorderLayout());
		loggedPanel.setOpaque(false);
		guiPanel.setLayout(new GridBagLayout());
		guiPanel.setOpaque(false);
	}
	
	private void addComponentsToPanels() {
		//Adding objects to GUI
		loggedPanel.add(loggedLabel, BorderLayout.EAST);
		if(bLogo) {
			gbCons.gridx = 0;
			gbCons.gridy = 0;
			guiPanel.add(upLogo, gbCons);
		}
	}
	
	private void addPanels() {
		if(!bComponentsToPanel) {
			addComponentsToPanels();
		} else {
			bComponentsToPanel = true;
		}
		
		gbCons = new GridBagConstraints();
		gbCons.fill = GridBagConstraints.BOTH;
		gbCons.anchor = GridBagConstraints.NORTH;
		gbCons.weightx = 100;
		gbCons.weighty = 12;
		gbCons.ipadx = getSize().width;
		
		gbCons.gridx = 0;
		gbCons.gridy = 0;
		gbCons.ipady = 50;
		add(loggedPanel, gbCons);
		
		gbCons.gridx = 0;
		gbCons.gridy = 1;
		gbCons.ipady = getSize().height - 50;
		gbCons.weighty = 88;
		add(guiPanel, gbCons);
	}
	
	public void repaintGUI() {
		revalidate();
		repaint();
	}
	
	public void removeComponentsFromPanel() {
		guiPanel.removeAll();
		repaintGUI();
	}
	
	public void run() {		
		//Creating the menu (via MenuAdminGUI)
		menuAdminGUI.run();
		setJMenuBar(menuAdminGUI.getMenuBar());
		
		//Adjust objects
		adjustObjects();
		
		addPanels();
		repaintGUI();
	}
	
	
	
	public void componentResized(ComponentEvent e) {
        // This is only called when the user releases the mouse button.
		//addPanels();
		//repaintGUI();
    } 

	public void actionPerformed(ActionEvent event) {		
		if(event.getSource() == menuAdminGUI.genReportMenuItem) {
			// -- generate the report			
		}
		
		if(event.getSource() == menuAdminGUI.logoutMenuItem) {
			// -- close this GUI and open loginGUI
			setVisible(false);
			loginGUI.setLocationRelativeTo(new JFrame());
			loginGUI.setVisible(true);
		}
		
		if(event.getSource() == menuAdminGUI.usersMenuItem) {
			bLogo = false;
			// -- manage users
			if(!isUsers) {
				isGroups = false;
				isCategory = false;
				isProducts = false;
				
				removeComponentsFromPanel();
				isUsers = true;
				UsersManagement usersManagement = new UsersManagement(this);
				usersManagement.run();
			}
		}
		
		if(event.getSource() == menuAdminGUI.groupsMenuItem) {
			// -- manage groups
			if(!isGroups) {
				isUsers = false;
				isCategory = false;
				isProducts = false;
				
				removeComponentsFromPanel();
				GroupsManagement groupsManagement = new GroupsManagement(this);
				groupsManagement.run();
				isGroups = true;
			}
			
		}
		
		if(event.getSource() == menuAdminGUI.categoryMenuItem) {
			// -- manage categories
			if(!isCategory) {
				isGroups = false;
				isUsers = false;
				isProducts = false;
				
				removeComponentsFromPanel();
				CategoryManagement categoryManagement = new CategoryManagement(this);
				categoryManagement.run();
				isCategory = true;
			}
		}
		
		if(event.getSource() == menuAdminGUI.productsMenuItem) {
			// -- manage products
			if(!isProducts) {
				isUsers = false;
				isGroups = false;
				isCategory = false;
				
				removeComponentsFromPanel();
				ProductManagement productManagement = new ProductManagement(this);
				productManagement.run();
				isProducts = true;
			}
		}
		
		if(event.getSource() == menuAdminGUI.welcomeMessageMenuItem) {
			// -- manage welcome message
			
		}
	}

	public void componentHidden(ComponentEvent e) {
		
	}

	public void componentMoved(ComponentEvent e) {
		
	}

	public void componentShown(ComponentEvent e) {
		
	}
}
