package helpers.management;

import helpers.bean.Group;
import helpers.view.ApplicationButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.*;

import view.AdminGUI;
import view.LoginGUI;
import view.admin.groups.GroupEditor;

public class GroupsManagement implements Runnable,
										 ActionListener,
										 MouseListener {
	//General management
	public AdminGUI adminGUI;
	private GridBagConstraints gbCons = new GridBagConstraints();
	public boolean bDialog = false;
	public String dialogName;
	
	//Objects management
	private JLabel groupsLabel = new JLabel("Groups");
	
	//Groups management
	public JList groupsList;
	public DefaultListModel groupsListModel = new DefaultListModel();
	private JScrollPane listScroller = null;
	
	//Class constructor
	public GroupsManagement(AdminGUI adminGUI) {
		this.adminGUI = adminGUI;
	}
	
	private void addComponents() {
		gbCons.insets.top = 5;
		gbCons.insets.bottom = 5;
		gbCons.insets.left = 5;
		gbCons.insets.right = 5;
		
		gbCons.gridx = 0;
		gbCons.gridy = 0;
		adminGUI.guiPanel.add(groupsLabel, gbCons);
		
		gbCons.gridy++;
		adminGUI.guiPanel.add(listScroller, gbCons);
	}
	
	private void configGroupsList() {
		for(int i=0; i<LoginGUI.groupsList.size(); i++) {
			groupsListModel.addElement(LoginGUI.groupsList.get(i) + "\n");
		}
		Group group = new Group();
		group.setName("Default");
		group.setPointsPerUser(10);
		groupsListModel.addElement(group.getName());
		
		groupsListModel.addElement("Add new..");
		
		groupsList = new JList(groupsListModel);
		groupsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		groupsList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		groupsList.setVisibleRowCount(-1);
		groupsList.setFont(new Font("Verdana", Font.BOLD, 15));
		groupsList.setBackground(Color.DARK_GRAY);
		groupsList.setForeground(new Color(255, 98, 0));
		groupsList.setBorder(BorderFactory.createEmptyBorder());
		groupsList.addMouseListener(this);
		
		listScroller = new JScrollPane(groupsList);
		listScroller.setPreferredSize(new Dimension(300, 200));
		listScroller.setBorder(BorderFactory.createEmptyBorder());
	}
	
	public void run() {
		groupsLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		groupsLabel.setForeground(Color.WHITE);
		
		configGroupsList();
		addComponents();
		adminGUI.repaintGUI();
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
	}

	public void mouseClicked(MouseEvent arg0) {
		if(!bDialog) {
			dialogName = groupsList.getSelectedValue().toString();
			GroupEditor groupEditor = new GroupEditor(this);
			groupEditor.run();
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
