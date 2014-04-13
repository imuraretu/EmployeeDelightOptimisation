package view.admin.products;

import helpers.bean.Product;
import helpers.bean.User;
import helpers.management.ProductManagement;
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

import view.AdminGUI;
import view.LoginGUI;

public class ProductAdder extends JDialog implements Runnable,
													 WindowListener,
													 MouseListener,
													 ActionListener {
	public ProductManagement productManagement;
	public String currentCategory;
	public Dimension minimumDimension = new Dimension(400, 400);
	public GridBagConstraints gbCons = new GridBagConstraints();
	private boolean isSubmiting = true;
	private boolean bAdded = false;
	
	public String auxName;
	public String auxPoints;
	
	public JLabel category;
	public JLabel name = new JLabel("Name");
	public JLabel points = new JLabel("Points");
	public JTextField nameTextField = new JTextField("");
	public JTextField pointsTextField = new JTextField("");
	
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
	
	public ProductAdder(ProductManagement productManagement, String currentCategory) {
		this.productManagement = productManagement;
		this.currentCategory = currentCategory;
		
		setSize(400, 400);
		setLayout(new GridBagLayout());
		setMinimumSize(minimumDimension);
		setTitle("UnifiedPost - Add product");
		getContentPane().setBackground(Color.DARK_GRAY);
		setVisible(true);
		setLocationRelativeTo(new JFrame());
		
		addWindowListener(this);
	}
	
	public void run() {
		submitButton.setOpaque(false);
		submitButton.setBorderPainted(false);
		submitButton.setContentAreaFilled(false);
		submitButton.setToolTipText("Submit product");
		submitButton.addActionListener(this);
		
		cancelButton.setOpaque(false);
		cancelButton.setBorderPainted(false);
		cancelButton.setContentAreaFilled(false);
		cancelButton.setToolTipText("Cancel");
		cancelButton.addActionListener(this);
		
		 gbCons.insets.top = 5;
		 gbCons.insets.bottom = 5;
		 gbCons.insets.right = 5;
		 gbCons.insets.left = 5;
		
		 category = new JLabel(currentCategory);
		 category.setFont(new Font("Verdana", Font.BOLD, 25));
		 category.setForeground(Color.WHITE);
		 
		 name.setFont(new Font("Verdana", Font.BOLD, 15));
		 name.setForeground(new Color(255, 98, 0));
		 
		 points.setFont(new Font("Verdana", Font.BOLD, 15));
		 points.setForeground(new Color(255, 98, 0));
		 
		 gbCons.gridx = 0;
		 gbCons.gridy = 0;
		 gbCons.gridwidth = 2;
		 getContentPane().add(category, gbCons);
		 
		 gbCons.gridwidth = 1;
		 gbCons.gridx = 0;
		 gbCons.gridy = 1;
		 getContentPane().add(name, gbCons);
		 
		 gbCons.gridx = 0;
		 gbCons.gridy = 2;
		 getContentPane().add(points, gbCons);
		 
		 gbCons.ipadx = 80;
		 gbCons.gridx = 1;
		 gbCons.gridy = 1;
		 getContentPane().add(nameTextField, gbCons);
		 
		 gbCons.gridx = 1;
		 gbCons.gridy = 2;
		 getContentPane().add(pointsTextField, gbCons);
		 
		 gbCons.anchor = GridBagConstraints.EAST;
		 gbCons.ipadx = 0;
		 gbCons.gridx = 0;
		 gbCons.gridy = 3;
		 getContentPane().add(submitButton, gbCons);
			
		 gbCons.anchor = GridBagConstraints.WEST;
		 gbCons.ipadx = 0;
		 gbCons.gridx = 1;
		 gbCons.gridy = 3;
		 getContentPane().add(cancelButton, gbCons);
	}
	
	public void generalAdding() {
		if(isSubmiting && !bAdded) {
			//Create new user
			Product product = new Product(auxName, Integer.parseInt(auxPoints), 0);
			
			try {
				LoginGUI.productCon.insertRecord(currentCategory, product.getProductName(), product.getPointsPrice(), 
													product.getVotes());
				JOptionPane.showMessageDialog(null,
						"Product was succesfully added.",
			       		"TASK COMPLETE", JOptionPane.INFORMATION_MESSAGE);
				LoginGUI.createProductsLists();
				
				bAdded = true;
				
				productManagement.getProducts();
				getContentPane().revalidate();
				getContentPane().repaint();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null,
						"Product already exists.",
			       		"ERROR!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void addInCategory1() {
		for(int i=0; i<LoginGUI.productsCategory1.size(); i++) {
			if(LoginGUI.productsCategory1.get(i).getProductName().equals(auxName)) {
				JOptionPane.showMessageDialog(null,
						"Product already exists.",
		        		"ERROR!", JOptionPane.ERROR_MESSAGE);
				isSubmiting = false;				
			}
			generalAdding();
		}
	}
	
	public void addInCategory2() {
		for(int i=0; i<LoginGUI.productsCategory2.size(); i++) {
			if(LoginGUI.productsCategory2.get(i).getProductName().equals(auxName)) {
				JOptionPane.showMessageDialog(null,
						"Product already exists.",
		        		"ERROR!", JOptionPane.ERROR_MESSAGE);
				isSubmiting = false;				
			}
			generalAdding();
		}
	}
	
	public void addInCategory3() {
		for(int i=0; i<LoginGUI.productsCategory3.size(); i++) {
			if(LoginGUI.productsCategory3.get(i).getProductName().equals(auxName)) {
				JOptionPane.showMessageDialog(null,
						"Product already exists.",
		        		"ERROR!", JOptionPane.ERROR_MESSAGE);
				isSubmiting = false;				
			}
			generalAdding();
		}
	}
	
	public void actionPerformed(ActionEvent event) {		
		if(event.getSource() == cancelButton) {
			productManagement.isDialog = false;
			dispose();
		}
		
		if(event.getSource() == submitButton) {
			auxName = nameTextField.getText();
			auxPoints = pointsTextField.getText();
			
			if(auxName.equals("") || auxPoints.equals("")) {
				JOptionPane.showMessageDialog(null,
						"You must complete all fields.",
		        		"ERROR!", JOptionPane.ERROR_MESSAGE);
			} else {
				isSubmiting = true;
				if(currentCategory.equals(LoginGUI.categoryList.get(0))) {
					if(!bAdded)
					addInCategory1();
				} else {
					if(currentCategory.equals(LoginGUI.categoryList.get(1))) {
						if(!bAdded)
						addInCategory2();
					} else {
						if(currentCategory.equals(LoginGUI.categoryList.get(2))) {
							if(!bAdded)
							addInCategory3();
						}
					}
				}
			}
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
		productManagement.isDialog = false;
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
		productManagement.isDialog = true;
	}

}
