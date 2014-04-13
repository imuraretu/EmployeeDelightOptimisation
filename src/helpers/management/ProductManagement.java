package helpers.management;

import helpers.view.ApplicationButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.*;

import view.AdminGUI;
import view.LoginGUI;
import view.admin.products.ProductAdder;

public class ProductManagement implements Runnable, ActionListener, WindowListener, MouseListener{
	//General management
	private AdminGUI adminGUI;
	private GridBagConstraints gbCons = new GridBagConstraints();
	public ProductAdder productAdder;
	public boolean isDialog = false;
	
	private JLabel labelCategory1 = new JLabel(LoginGUI.categoryList.get(0));
	private JLabel labelCategory2 = new JLabel(LoginGUI.categoryList.get(1));
	private JLabel labelCategory3 = new JLabel(LoginGUI.categoryList.get(2));
	
	//Objects management
	private Vector<JLabel> productCategory1 = new Vector<JLabel>();
	private Vector<JLabel> productCategory2 = new Vector<JLabel>();
	private Vector<JLabel> productCategory3 = new Vector<JLabel>();
	
	private Vector<JTextField> pricesCategory1 = new Vector<JTextField>();
	private Vector<JTextField> pricesCategory2 = new Vector<JTextField>();
	private Vector<JTextField> pricesCategory3 = new Vector<JTextField>();
	
	private ImageIcon nImage = new ImageIcon("src/images/add.png");
	private ImageIcon hImage = new ImageIcon("src/images/add_h.png");
	
	private ApplicationButton category1Button = new ApplicationButton(nImage, hImage);
	private ApplicationButton category2Button = new ApplicationButton(nImage, hImage);
	private ApplicationButton category3Button = new ApplicationButton(nImage, hImage);

	public ProductManagement(AdminGUI adminGUI) {
		this.adminGUI = adminGUI;
	}
	
	public void getProducts() {
		for(int i=0; i<LoginGUI.productsCategory1.size(); i++) {
			JLabel auxLabel = new JLabel(LoginGUI.productsCategory1.get(i).getProductName());
			auxLabel.setFont(new Font("Verdana", Font.BOLD, 15));
			auxLabel.setForeground(new Color(255, 98, 0));
			productCategory1.add(auxLabel);
			JTextField auxTextField = new JTextField(""+LoginGUI.productsCategory1.get(i).getPointsPrice());
			pricesCategory1.add(auxTextField);
		}
		
		for(int i=0; i<LoginGUI.productsCategory2.size(); i++) {
			JLabel auxLabel = new JLabel(LoginGUI.productsCategory2.get(i).getProductName());
			auxLabel.setFont(new Font("Verdana", Font.BOLD, 15));
			auxLabel.setForeground(new Color(255, 98, 0));
			productCategory2.add(auxLabel);
			JTextField auxTextField = new JTextField(""+LoginGUI.productsCategory2.get(i).getPointsPrice());
			pricesCategory2.add(auxTextField);
		}
		
		for(int i=0; i<LoginGUI.productsCategory3.size(); i++) {
			JLabel auxLabel = new JLabel(LoginGUI.productsCategory3.get(i).getProductName());
			auxLabel.setFont(new Font("Verdana", Font.BOLD, 15));
			auxLabel.setForeground(new Color(255, 98, 0));
			productCategory3.add(auxLabel);
			JTextField auxTextField = new JTextField(""+LoginGUI.productsCategory3.get(i).getPointsPrice());
			pricesCategory3.add(auxTextField);
		}
	}
	
	
	public void run() {
		labelCategory1.setFont(new Font("Verdana", Font.BOLD, 25));
		labelCategory1.setForeground(Color.WHITE);
		
		labelCategory2.setFont(new Font("Verdana", Font.BOLD, 25));
		labelCategory2.setForeground(Color.WHITE);
		
		labelCategory3.setFont(new Font("Verdana", Font.BOLD, 25));
		labelCategory3.setForeground(Color.WHITE);
		
		category1Button.setOpaque(false);
		category1Button.setBorderPainted(false);
		category1Button.setContentAreaFilled(false);
		category1Button.setToolTipText("Add product to category 1");
		category1Button.addActionListener(this);
		
		category2Button.setOpaque(false);
		category2Button.setBorderPainted(false);
		category2Button.setContentAreaFilled(false);
		category2Button.setToolTipText("Add product to category 2");
		category2Button.addActionListener(this);
		
		category3Button.setOpaque(false);
		category3Button.setBorderPainted(false);
		category3Button.setContentAreaFilled(false);
		category3Button.setToolTipText("Add product to category 3");
		category3Button.addActionListener(this);
		
		getProducts();
		
		int maxGridY = 0;
		gbCons.insets.top = 5;
		gbCons.insets.bottom = 5;
		gbCons.insets.right = 5;
		gbCons.insets.left = 5;
		
		gbCons.gridx = 0;
		gbCons.gridy = 0;
		gbCons.gridwidth = 2;
		adminGUI.guiPanel.add(labelCategory1, gbCons);
		
		gbCons.gridx = 2;
		gbCons.gridy = 0;
		gbCons.gridwidth = 2;
		adminGUI.guiPanel.add(labelCategory2, gbCons);
		
		gbCons.gridx = 4;
		gbCons.gridy = 0;
		gbCons.gridwidth = 2;
		adminGUI.guiPanel.add(labelCategory3, gbCons);
		
		gbCons.gridwidth = 1;
		gbCons.gridx = 0;
		gbCons.gridy = 1;
		for(int i=0; i<productCategory1.size(); i++) {
			adminGUI.guiPanel.add(productCategory1.elementAt(i), gbCons);
			gbCons.gridy++;
			if(gbCons.gridy > maxGridY) {
				maxGridY = gbCons.gridy;
			}
		}
		
		gbCons.gridx = 2;
		gbCons.gridy = 1;
		for(int i=0; i<productCategory2.size(); i++) {
			adminGUI.guiPanel.add(productCategory2.elementAt(i), gbCons);
			gbCons.gridy++;
			if(gbCons.gridy > maxGridY) {
				maxGridY = gbCons.gridy;
			}
		}
		
		gbCons.gridx = 4;
		gbCons.gridy = 1;
		for(int i=0; i<productCategory3.size(); i++) {
			adminGUI.guiPanel.add(productCategory3.elementAt(i), gbCons);
			gbCons.gridy++;
			if(gbCons.gridy > maxGridY) {
				maxGridY = gbCons.gridy;
			}
		}
		
		gbCons.ipadx = 20;
		gbCons.gridx = 1;
		gbCons.gridy = 1;
		for(int i=0; i<pricesCategory1.size(); i++) {
			adminGUI.guiPanel.add(pricesCategory1.elementAt(i), gbCons);
			gbCons.gridy++;
		}
		
		gbCons.gridx = 3;
		gbCons.gridy = 1;
		for(int i=0; i<pricesCategory2.size(); i++) {
			adminGUI.guiPanel.add(pricesCategory2.elementAt(i), gbCons);
			gbCons.gridy++;
		}
		
		gbCons.gridx = 5;
		gbCons.gridy = 1;
		for(int i=0; i<pricesCategory3.size(); i++) {
			adminGUI.guiPanel.add(pricesCategory3.elementAt(i), gbCons);
			gbCons.gridy++;
		}
		
		gbCons.ipadx = 0;
		gbCons.ipady = 0;
		gbCons.gridx = 0;
		gbCons.gridy = maxGridY + 1; 
		gbCons.gridwidth = 2;
		adminGUI.guiPanel.add(category1Button, gbCons);
		gbCons.gridx = 2;
		adminGUI.guiPanel.add(category2Button, gbCons);
		gbCons.gridx = 4;
		adminGUI.guiPanel.add(category3Button, gbCons);
		
		adminGUI.repaintGUI();
	}
	
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == category1Button && !isDialog) {
			productAdder = new ProductAdder(this, LoginGUI.categoryList.get(0));
			productAdder.run();
		}
		
		if(event.getSource() == category2Button && !isDialog) {
			productAdder = new ProductAdder(this, LoginGUI.categoryList.get(1));
			productAdder.run();
		}
		
		if(event.getSource() == category3Button && !isDialog) {
			productAdder = new ProductAdder(this, LoginGUI.categoryList.get(2));
			productAdder.run();
		}
	}

	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
}
