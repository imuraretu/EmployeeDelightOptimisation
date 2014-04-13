package helpers.management;

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

import javax.swing.*;

import view.LoginGUI;
import view.UserGUI;

public class ProductDialog  extends JDialog implements Runnable,
														ActionListener,
														WindowListener{
	public String productName;
	public String categoryName;
	
	private JLabel gNameLabel = new JLabel("Name");
	private JLabel gVotsLabel = new JLabel("Vots");
	private JLabel nameLabel;
	private JLabel votsLabel;
	
	private JLabel avPoints;
	
	private JLabel gYourAvPoints = new JLabel("Available pts");
	private JLabel yourAvPoints = new JLabel("" + UserGUI.loginInfo.getPoints());
	
	private JTextField yourPoints = new JTextField("");
	private ApplicationButton voteButton = new ApplicationButton("Vote", new Color(255, 98, 0), new Color(255,255,255));
	
	public ProductDialog(String productName, int categoryPosition, JLabel avPoints) {
		this.productName = productName;
		nameLabel = new JLabel(productName);
		
		this.avPoints = avPoints;
		
		switch(categoryPosition) {
		case 0:
			for(int i=0; i<LoginGUI.productsCategory1.size(); i++) {
				if(LoginGUI.productsCategory1.get(i).getProductName().equals(productName)) {
					//--am gasit produsul
					votsLabel = new JLabel(""+LoginGUI.productsCategory1.get(i).getVotes());	
				}
			}
			categoryName = LoginGUI.categoryList.get(0);
			break;
		case 1:
			for(int i=0; i<LoginGUI.productsCategory2.size(); i++) {
				if(LoginGUI.productsCategory2.get(i).getProductName().equals(productName)) {
					//--am gasit produsul
					votsLabel = new JLabel(""+LoginGUI.productsCategory2.get(i).getVotes());	
				}
			}
			categoryName = LoginGUI.categoryList.get(1);
			break;
		case 2:
			for(int i=0; i<LoginGUI.productsCategory3.size(); i++) {
				if(LoginGUI.productsCategory3.get(i).getProductName().equals(productName)) {
					//--am gasit produsul
					votsLabel = new JLabel(""+LoginGUI.productsCategory3.get(i).getVotes());	
				}
			}
			categoryName = LoginGUI.categoryList.get(2);
			break;
		}
		
		setSize(400, 400);
		setLayout(new GridBagLayout());
		setMinimumSize(new Dimension(400, 400));
		setTitle("UnifiedPost - Vote");
		getContentPane().setBackground(Color.DARK_GRAY);
		setVisible(true);
		setLocationRelativeTo(new JFrame());
		
		addWindowListener(this);
	}
	
	public void redrawAll(int avPts, int votes) {
		yourPoints.setText("");
		yourAvPoints.setText("" + avPts);
		
		avPoints.setText("Available points: " + avPts);
		
		votsLabel.setText("" + votes);
		
		revalidate();
		repaint();
	}
	
	public void run() {
		gNameLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		gNameLabel.setForeground(new Color(255, 98, 0));
		
		nameLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		nameLabel.setForeground(new Color(255, 255, 255));
		
		gVotsLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		gVotsLabel.setForeground(new Color(255, 98, 0));
		
		votsLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		votsLabel.setForeground(new Color(255, 255, 255));
		
		gYourAvPoints.setFont(new Font("Verdana", Font.BOLD, 15));
		gYourAvPoints.setForeground(new Color(255, 98, 0));
		
		yourAvPoints.setFont(new Font("Verdana", Font.BOLD, 15));
		yourAvPoints.setForeground(new Color(255, 255, 255));
		
		voteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(yourPoints.getText().equals("")) {
					JOptionPane.showMessageDialog(null,
							"No points inserted!",
			        		"ERROR!", JOptionPane.ERROR_MESSAGE);
				} else {
					if(Integer.parseInt(yourAvPoints.getText()) == 0) {
						JOptionPane.showMessageDialog(null,
								"Not enough points!",
				        		"ERROR!", JOptionPane.ERROR_MESSAGE);
					} else {
						int pts = UserGUI.loginInfo.getPoints() - Integer.parseInt(yourPoints.getText());
						
						try {
							LoginGUI.userCon.updateRecord(pts, UserGUI.loginInfo.getUsername());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						int votes = Integer.parseInt(votsLabel.getText()) +  Integer.parseInt(yourPoints.getText());
						
						try {
							LoginGUI.productCon.updateVots(categoryName, productName, votes);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						redrawAll(pts, votes);
					}
				}
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets.bottom = 8;
		c.insets.left = 8;
		c.insets.right = 8;
		c.insets.top = 8;
		
		c.gridx = 0;
		c.gridy = 0;
		getContentPane().add(gNameLabel, c);
		c.gridx = 1;
		c.gridy = 0;
		getContentPane().add(nameLabel, c);
		c.gridx = 0;
		c.gridy = 1;
		getContentPane().add(gVotsLabel, c);
		c.gridx = 1;
		c.gridy = 1;
		getContentPane().add(votsLabel, c);
		c.gridx = 0;
		c.gridy = 2;
		getContentPane().add(gYourAvPoints, c);
		c.gridx = 1;
		c.gridy = 2;
		getContentPane().add(yourAvPoints, c);
		c.gridx = 1;
		c.gridy = 3;
		getContentPane().add(voteButton, c);
		c.gridx = 0;
		c.gridy = 3;
		c.ipadx = 20;
		getContentPane().add(yourPoints, c);
	}
	
	public void actionPerformed(ActionEvent event) {
		
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
}
