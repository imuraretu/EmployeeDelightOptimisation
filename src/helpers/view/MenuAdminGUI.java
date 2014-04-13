package helpers.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

import view.AdminGUI;

public class MenuAdminGUI implements Runnable {
	private AdminGUI adminGUI;
	
	//Menu management
	public MenuBar menuBar = new MenuBar();
	public JMenu fileMenu = new JMenu("File");
	public JMenuItem genReportMenuItem = new JMenuItem("Generate report");
	public JMenuItem logoutMenuItem = new JMenuItem("Logout");
	public JMenu settingsMenu = new JMenu("Settings");
	public JMenuItem usersMenuItem = new JMenuItem("Users");
	public JMenuItem groupsMenuItem = new JMenuItem("Groups");
	public JMenu customizeMenu = new JMenu("Customize");
	public JMenuItem categoryMenuItem = new JMenuItem("Category");
	public JMenuItem productsMenuItem = new JMenuItem("Products");
	public JMenuItem welcomeMessageMenuItem = new JMenuItem("Welcome message");
	
	public MenuAdminGUI(AdminGUI adminGUI) {
		this.adminGUI = adminGUI;
		UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder());
		UIManager.put("PopupMenu.selection", Color.ORANGE);
		menuBar.setBorder(BorderFactory.createEmptyBorder());
	}
	
	public MenuBar getMenuBar() {
		return menuBar;
	}

	public void run() {
		//File menu
		genReportMenuItem.setOpaque(true);
		genReportMenuItem.setBackground(Color.DARK_GRAY);
		genReportMenuItem.setForeground(Color.WHITE);
		genReportMenuItem.addActionListener(adminGUI);
		fileMenu.add(genReportMenuItem);
		logoutMenuItem.setOpaque(true);
		logoutMenuItem.setBackground(Color.DARK_GRAY);
		logoutMenuItem.setForeground(Color.WHITE);
		logoutMenuItem.addActionListener(adminGUI);
		fileMenu.add(logoutMenuItem);
				
		fileMenu.setForeground(Color.WHITE);
			
		//Settings menu
		usersMenuItem.setOpaque(true);
		usersMenuItem.setBackground(Color.DARK_GRAY);
		usersMenuItem.setForeground(Color.WHITE);
		usersMenuItem.addActionListener(adminGUI);
		settingsMenu.add(usersMenuItem);
				
		groupsMenuItem.setOpaque(true);
		groupsMenuItem.setBackground(Color.DARK_GRAY);
		groupsMenuItem.setForeground(Color.WHITE);
		groupsMenuItem.addActionListener(adminGUI);
		settingsMenu.add(groupsMenuItem);
		
		//Customize menu
		customizeMenu.setOpaque(true);
		customizeMenu.setBackground(Color.DARK_GRAY);
		customizeMenu.setForeground(Color.WHITE);
		customizeMenu.addActionListener(adminGUI);
		
		categoryMenuItem.setOpaque(true);
		categoryMenuItem.setBackground(Color.DARK_GRAY);
		categoryMenuItem.setForeground(Color.WHITE);
		categoryMenuItem.addActionListener(adminGUI);
		customizeMenu.add(categoryMenuItem);
		
		productsMenuItem.setOpaque(true);
		productsMenuItem.setBackground(Color.DARK_GRAY);
		productsMenuItem.setForeground(Color.WHITE);
		productsMenuItem.addActionListener(adminGUI);
		customizeMenu.add(productsMenuItem);
		
		welcomeMessageMenuItem.setOpaque(true);
		welcomeMessageMenuItem.setBackground(Color.DARK_GRAY);
		welcomeMessageMenuItem.setForeground(Color.WHITE);
		welcomeMessageMenuItem.addActionListener(adminGUI);
		customizeMenu.add(welcomeMessageMenuItem);
				
		settingsMenu.setForeground(Color.WHITE);
				
		//Menu bar
		menuBar.add(fileMenu);
		menuBar.add(settingsMenu);
		menuBar.add(customizeMenu);
	}

}
