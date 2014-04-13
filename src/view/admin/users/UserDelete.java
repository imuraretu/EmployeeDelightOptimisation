package view.admin.users;

import helpers.management.UsersManagement;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.*;

import view.LoginGUI;

public class UserDelete extends JDialog implements Runnable,
												   WindowListener,
												   MouseListener{
	private static final long serialVersionUID = 1L;
	
	//General management
	private UsersManagement usersManagement;
	private Dimension minimumDimension = new Dimension(400, 400);
	private GridBagConstraints gbCons = new GridBagConstraints();
	
	//Object management
	private JList usersList = new JList();
	private DefaultListModel usersListModel = new DefaultListModel();
	private JScrollPane listScroller = null;
	private JLabel deleteLabel = new JLabel("Delete user");
	
	
	public UserDelete(UsersManagement usersManagement) {
		super(usersManagement.adminGUI);
		this.usersManagement = usersManagement;
		setSize(400, 400);
		setLayout(new GridBagLayout());
		setMinimumSize(minimumDimension);
		setTitle("UnifiedPost - Delete user");
		getContentPane().setBackground(Color.DARK_GRAY);
		setVisible(true);
		setLocationRelativeTo(new JFrame());
		
		addWindowListener(this);
	}
	
	private void configDeleteLabel() {
		deleteLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		deleteLabel.setForeground(Color.WHITE);
	}
	
	private void configUsersList() {	
		if(usersListModel.getSize() != 0) {
			usersListModel.removeAllElements();
		}
		
		for(int i=0; i<LoginGUI.usersList.size(); i++) {
			if(!LoginGUI.usersList.get(i).getUsername().equals("admin")) {
				usersListModel.addElement(LoginGUI.usersList.get(i).getUsername());
			}
		}
		
		usersList = new JList(usersListModel);
		usersList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		usersList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		usersList.setVisibleRowCount(-1);
		usersList.setFont(new Font("Verdana", Font.BOLD, 15));
		usersList.setBackground(Color.DARK_GRAY);
		usersList.setForeground(new Color(255, 98, 0));
		usersList.setBorder(BorderFactory.createEmptyBorder());
		usersList.addMouseListener(this);

		listScroller = new JScrollPane(usersList);
		listScroller.setPreferredSize(new Dimension(100, 100));
		listScroller.setBorder(BorderFactory.createEmptyBorder());
	}
	
	private void drawObjects() {
		getContentPane().removeAll();
		gbCons.insets.bottom = 5;
		gbCons.insets.top = 5;
		gbCons.insets.left = 5;
		gbCons.insets.right = 5;
		gbCons.gridx = 0;
		gbCons.gridy = 0;
		getContentPane().add(deleteLabel, gbCons);
		
		gbCons.gridy++;
		getContentPane().add(listScroller, gbCons);
		
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	public void run() {
		configDeleteLabel();
		configUsersList();
		drawObjects();
	}
	
	public void windowActivated(WindowEvent e) {
		
	}

	public void windowClosed(WindowEvent e) {
		
	}

	public void windowClosing(WindowEvent e) {
		usersManagement.bDeleteDialog = false;
	}

	public void windowDeactivated(WindowEvent e) {
		
	}

	public void windowDeiconified(WindowEvent e) {
		
	}

	public void windowIconified(WindowEvent e) {
		
	}

	public void windowOpened(WindowEvent e) {
		usersManagement.bDeleteDialog = true;
	}

	public void mouseClicked(MouseEvent event) {
		//Show YES/NO dialog
		int index = usersList.getSelectedIndex(); // Just for test
		String username = (String) usersListModel.getElementAt(index);
		username = username.substring(0, username.length()-1);
		
		int reply = JOptionPane.showConfirmDialog(null, 
								("Delete " + username + "Are you sure?"), 
								"Delete user", 
								JOptionPane.YES_NO_OPTION);
        
		if (reply == JOptionPane.YES_OPTION) {			
			//Delete user from database
				try {
					LoginGUI.userCon.deleteRecord("users", username);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			try {
				LoginGUI.createUsersList();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			usersList.clearSelection();
			configUsersList();
			usersManagement.adminGUI.repaintGUI();
        }
	}

	public void mouseEntered(MouseEvent event) {
		
	}

	public void mouseExited(MouseEvent event) {
		
	}

	public void mousePressed(MouseEvent event) {
		
	}

	public void mouseReleased(MouseEvent event) {
		
	}
}
