package view.user;

import helpers.management.ProductDialog;
import helpers.view.ApplicationButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import view.LoginGUI;

public class CategoryGUI extends JFrame implements Runnable,
													ActionListener{
	private static final long serialVersionUID = 1L;

	public int categoryPosition;
	public JLabel avPoints;
	
	public Vector<ApplicationButton> buttons = new Vector<ApplicationButton>();
	
	public CategoryGUI(int categoryPosition, JLabel avPoints) {
		this.categoryPosition = categoryPosition;
		this.avPoints = avPoints;
	}
	
	public void run() {
		JPanel panel = new JPanel();
        
		switch(categoryPosition) {
		case 0:
			for(int i=0; i<LoginGUI.productsCategory1.size(); i++) {
				ApplicationButton auxButton = new ApplicationButton(LoginGUI.productsCategory1.get(i).getProductName(), new Color(255, 98, 0), new Color(255,255,255));
				buttons.addElement(auxButton);
				buttons.elementAt(i).addActionListener(this);
				panel.add(auxButton);
			}
			break;
		case 1:
			for(int i=0; i<LoginGUI.productsCategory2.size(); i++) {
				ApplicationButton auxButton = new ApplicationButton(LoginGUI.productsCategory2.get(i).getProductName(), new Color(255, 98, 0), new Color(255,255,255));
				buttons.addElement(auxButton);
				buttons.elementAt(i).addActionListener(this);
				panel.add(auxButton);
			}
			break;
		case 2:
			for(int i=0; i<LoginGUI.productsCategory3.size(); i++) {
				ApplicationButton auxButton = new ApplicationButton(LoginGUI.productsCategory3.get(i).getProductName(), new Color(255, 98, 0), new Color(255,255,255));
				buttons.addElement(auxButton);
				buttons.elementAt(i).addActionListener(this);
				panel.add(auxButton);
			}
			break;
		}
        
		panel.setBackground(Color.DARK_GRAY);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setForeground(Color.DARK_GRAY);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(true);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 50));
        getContentPane().setBackground(Color.DARK_GRAY);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbCons = new GridBagConstraints();
        
        gbCons.gridx = 0;
        gbCons.gridy = 0;
        scrollPane.setBackground(Color.DARK_GRAY);
        add(scrollPane, gbCons);
        
        setSize(400, 400);
        setMinimumSize(new Dimension(400, 400));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event) {
		for(int i=0; i<buttons.size(); i++) {
			if(event.getSource() == buttons.elementAt(i)) {
				ProductDialog productDialog = new ProductDialog(buttons.elementAt(i).getText(), categoryPosition, avPoints);
				productDialog.run();
			}
		}
	}

	
}
