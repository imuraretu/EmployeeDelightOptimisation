package view.admin.groups;

import helpers.bean.User;
import helpers.management.GroupsManagement;
import helpers.view.ApplicationButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.*;

import view.LoginGUI;

public class GroupEditor extends JDialog implements Runnable,
													WindowListener,
													MouseListener,
													ActionListener {
	private static final long serialVersionUID = 1L;
	
	//General management
	private GroupsManagement groupsManagement;
	private Dimension minimumDimension = new Dimension(400, 400);
	private GridBagConstraints gbCons = new GridBagConstraints();
	private boolean isSubmiting = true;

	//View group objects
	private JLabel addNewGroupLabel = new JLabel("Add new group");
	private JLabel groupName;
	private JLabel name = new JLabel("Name");
	private JLabel points = new JLabel("Points");
	
	private JTextField nameTextField = new JTextField("");
	private JTextField pointsTextField = new JTextField("");
	
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
	
	public GroupEditor(GroupsManagement groupsManagement) {
		super(groupsManagement.adminGUI);
		this.groupsManagement = groupsManagement;
		setSize(400, 400);
		setLayout(new GridBagLayout());
		setMinimumSize(minimumDimension);
		setTitle("UnifiedPost - " + groupsManagement.dialogName);
		getContentPane().setBackground(Color.DARK_GRAY);
		setVisible(true);
		setLocationRelativeTo(new JFrame());
		
		addWindowListener(this);
	}
	
	private void configObjects() {
		addNewGroupLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		addNewGroupLabel.setForeground(Color.WHITE);
		
		name.setFont(new Font("Verdana", Font.BOLD, 15));
		name.setForeground(new Color(255, 98, 0));
		
		points.setFont(new Font("Verdana", Font.BOLD, 15));
		points.setForeground(new Color(255, 98, 0));
		
		submitButton.setOpaque(false);
		submitButton.setBorderPainted(false);
		submitButton.setContentAreaFilled(false);
		submitButton.setToolTipText("Find user");
		submitButton.addActionListener(this);
		
		cancelButton.setOpaque(false);
		cancelButton.setBorderPainted(false);
		cancelButton.setContentAreaFilled(false);
		cancelButton.setToolTipText("Find user");
		cancelButton.addActionListener(this);
	}
	
	private void drawGUI() {
		gbCons.insets.bottom = 5;
		gbCons.insets.top = 5;
		gbCons.insets.left = 5;
		gbCons.insets.right = 5;
		
		gbCons.gridwidth = 4;
		gbCons.gridx = 0;
		gbCons.gridy = 0;
		getContentPane().add(addNewGroupLabel, gbCons);
		
		gbCons.gridwidth = 1;
		gbCons.gridx = 0;
		gbCons.gridy = 1;
		getContentPane().add(name, gbCons);
		
		gbCons.gridx = 0;
		gbCons.gridy = 2;
		getContentPane().add(points, gbCons);
		
		gbCons.ipadx = 150;
		gbCons.gridx = 1;
		gbCons.gridy = 1;
		gbCons.gridwidth = 3;
		getContentPane().add(nameTextField, gbCons);
		
		gbCons.gridy++;
		getContentPane().add(pointsTextField, gbCons);
		
		gbCons.gridwidth = 1;
		gbCons.ipadx = 0;
		gbCons.anchor = GridBagConstraints.EAST;
		gbCons.gridx = 1;
		gbCons.gridy = 3;
		getContentPane().add(submitButton, gbCons);
		
		gbCons.anchor = GridBagConstraints.WEST;
		gbCons.gridx = 2;
		gbCons.gridy = 3;
		getContentPane().add(cancelButton, gbCons);
	}
	
	private void addNewGroup() {
		configObjects();
		drawGUI();
	}
	
	private void viewGroup() {
		groupName.setText(groupsManagement.dialogName);
		name.setText(groupsManagement.dialogName);
		//pointsTextField.setText( GROUP POINTS PER USER );
	}
	
	public void run() {
		if(groupsManagement.dialogName.equals("Add new..")) {
			addNewGroup();
		} else {
			viewGroup();
		}
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent arg0) {
		groupsManagement.bDialog = false;
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
		revalidate();
		repaint();
		groupsManagement.bDialog = true;
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == cancelButton) {
			groupsManagement.bDialog = false;
			revalidate();
			repaint();
			dispose();
		}
		
		if(event.getSource() == submitButton) {
			String auxGroupName = nameTextField.getText();
			String auxPoints = pointsTextField.getText();

			if(auxGroupName.equals("") || auxPoints.equals("")) {
				JOptionPane.showMessageDialog(null,
						"You must complete all fields.",
		        		"ERROR!", JOptionPane.ERROR_MESSAGE);
			} else {
				isSubmiting = true;
				for(int i=0; i<LoginGUI.groupsList.size(); i++) {
					String auxName = LoginGUI.groupsList.get(i);
					if(auxName.equals(auxGroupName)) {
						JOptionPane.showMessageDialog(null,
								"Group already exists.",
				        		"ERROR!", JOptionPane.ERROR_MESSAGE);
						isSubmiting = false;
					}
				}
				if(isSubmiting) {
					//Create new user
					try {
						//Add table to database
						LoginGUI.userCon.createTable(auxGroupName);
						
						JOptionPane.showMessageDialog(null,
								"Group was succesfully created.",
					       		"TASK COMPLETE", JOptionPane.INFORMATION_MESSAGE);
						
						groupsManagement.groupsListModel.add(groupsManagement.groupsListModel.getSize()-1, auxGroupName);
						groupsManagement.groupsList.clearSelection();
						
						LoginGUI.createGroupsList();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,
								"Group already exists.",
					       		"ERROR!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
}
