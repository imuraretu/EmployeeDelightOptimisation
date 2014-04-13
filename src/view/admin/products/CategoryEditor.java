package view.admin.products;

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
import helpers.management.CategoryManagement;
import helpers.view.ApplicationButton;

public class CategoryEditor extends JDialog implements Runnable,
													   WindowListener,
													   MouseListener,
													   ActionListener {
	//General management
	public CategoryManagement categoryManagement;
	private Dimension minimumDimension = new Dimension(400, 400);
	private GridBagConstraints gbCons = new GridBagConstraints();
	private int categoryPosition;
	
	//Object management
	private JLabel dialogName = new JLabel("Customize");
	private JLabel categoryName = new JLabel("Name");
	private JTextField categoryTextField;
	private ApplicationButton saveButton = new ApplicationButton(new ImageIcon("src/images/submit.png"), 
													new ImageIcon("src/images/submit_h.png"));
	
	//Class constructor
	public CategoryEditor(CategoryManagement categoryManagement, int categoryPosition) {
		super(categoryManagement.adminGUI);
		this.categoryManagement = categoryManagement;
		this.categoryPosition = categoryPosition;
		setSize(400, 400);
		setLayout(new GridBagLayout());
		setMinimumSize(minimumDimension);
		setTitle("UnifiedPost - Edit category");
		getContentPane().setBackground(Color.DARK_GRAY);
		setVisible(true);
		setLocationRelativeTo(new JFrame());
		
		addWindowListener(this);
	}
	
	private void configObjects() {
		dialogName.setFont(new Font("Verdana", Font.BOLD, 25));
		dialogName.setForeground(new Color(255, 98, 0));
		
		categoryName.setFont(new Font("Verdana", Font.BOLD, 15));
		categoryName.setForeground(Color.WHITE);
		
		categoryTextField = new JTextField(LoginGUI.categoryList.get(categoryPosition));
		
		saveButton.setOpaque(false);
		saveButton.setBorderPainted(false);
		saveButton.setContentAreaFilled(false);
		saveButton.setToolTipText("Find user");
		saveButton.addActionListener(this);
	}
	

	private void drawObjects() {
		gbCons.anchor = GridBagConstraints.CENTER;
		gbCons.insets.bottom = 5;
		gbCons.insets.top = 5;
		gbCons.insets.left = 5;
		gbCons.insets.right = 5;
		
		gbCons.gridx = 0;
		gbCons.gridy = 0;
		gbCons.gridwidth = 2;
		getContentPane().add(dialogName, gbCons);
		
		gbCons.gridy = 1;
		gbCons.gridwidth = 1;
		getContentPane().add(categoryName, gbCons);
		
		gbCons.gridx++;
		gbCons.ipadx = 50;
		getContentPane().add(categoryTextField, gbCons);
		
		gbCons.anchor = GridBagConstraints.EAST;
		gbCons.ipadx = 0;
		gbCons.gridx = 1;
		gbCons.gridy = 2;
		getContentPane().add(saveButton, gbCons);
	}
	
	public void run() {
		configObjects();
		drawObjects();
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == saveButton) {
			if(!LoginGUI.categoryList.get(categoryPosition).equals(categoryTextField.getText())) {
				// --change category name
				JOptionPane.showMessageDialog(null,
						"Category name was succesfully changed.",
			       		"TASK COMPLETE", JOptionPane.INFORMATION_MESSAGE);
				
				try {
					LoginGUI.productCon.changeGroupName(LoginGUI.categoryList.get(categoryPosition), 
							categoryTextField.getText());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// --update images
				categoryManagement.loadImages();
				categoryManagement.configButtons();
				categoryManagement.drawObjects();
			}
			
			categoryManagement.isDialog = false;
			dispose();
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
		categoryManagement.isDialog = false;
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
		categoryManagement.isDialog = true;
	}
}
