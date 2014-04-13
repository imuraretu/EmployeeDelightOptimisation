package helpers.management;

import helpers.view.ApplicationButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import view.AdminGUI;
import view.LoginGUI;
import view.admin.products.CategoryEditor;
import view.admin.users.UserDelete;

public class CategoryManagement implements Runnable, ActionListener, WindowListener{
	
	//General management
	public AdminGUI adminGUI;
	private GridBagConstraints gbCons = new GridBagConstraints();
	private JLabel categoryLabel = new JLabel("Categories");
	public boolean isDialog = false;
	
	//Images for buttons
	private Vector<ImageIcon> nImages = new Vector<ImageIcon>();
	private Vector<ImageIcon> hImages = new Vector<ImageIcon>();
	private File nFile;
	private File hFile;
	private Vector<ApplicationButton> buttons = new Vector<ApplicationButton>();
	
	//Class constructor
	public CategoryManagement(AdminGUI adminGUI) {
		this.adminGUI = adminGUI;
	}
	
	public void loadImages() {
		for(int i=0; i<LoginGUI.categoryList.size(); i++) {
			String nPath = "src/images/" + LoginGUI.categoryList.get(i) + ".png";
			String hPath = "src/images/" + LoginGUI.categoryList.get(i) + "_h.png";
			
			nFile = new File(nPath);
			hFile = new File(hPath);
			
			if(nFile.exists() && hFile.exists() && !nFile.isDirectory() && !hFile.isDirectory()) {
				// -- assign image
				nImages.addElement(new ImageIcon(nPath));
				hImages.addElement(new ImageIcon(hPath));
			} else {
				// -- assign default image
				nImages.addElement(new ImageIcon("src/images/default.png"));
				hImages.addElement(new ImageIcon("src/images/default_h.png"));
			}
		}
	}
	
	public void configButtons() {
		for(int i=0; i<LoginGUI.categoryList.size(); i++) {
			buttons.addElement(new ApplicationButton(nImages.elementAt(i), hImages.elementAt(i)));
			buttons.elementAt(i).setOpaque(false);
			buttons.elementAt(i).setBorderPainted(false);
			buttons.elementAt(i).setContentAreaFilled(false);
			buttons.elementAt(i).setToolTipText("Find user");
			buttons.elementAt(i).addActionListener(this);
		}
	}
	
	public void drawObjects() {
		gbCons.insets.top = 5;
		gbCons.insets.bottom = 5;
		gbCons.insets.left = 5;
		gbCons.insets.right = 5;
		
		gbCons.gridx = 0;
		gbCons.gridy = 0;
		gbCons.gridwidth = 3;
		categoryLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		categoryLabel.setForeground(Color.WHITE);
		adminGUI.guiPanel.add(categoryLabel, gbCons);
		
		gbCons.gridwidth = 1;
		gbCons.gridx = 0;
		gbCons.gridy = 1;
		
		int i = 0;
		do {			
			System.out.println(gbCons.gridx + "," + gbCons.gridy);
			adminGUI.guiPanel.add(buttons.elementAt(i), gbCons);
			
			if(gbCons.gridx == 2) {
				gbCons.gridx = 0;
				gbCons.gridy++;
			} else {
				gbCons.gridx++;
			}
			
			i++;
		} while(i < LoginGUI.categoryList.size());
		adminGUI.repaintGUI();
	}
	
	public void run() {
		loadImages();
		configButtons();
		drawObjects();
	}

	public void actionPerformed(ActionEvent event) {
		for(int i=0; i<LoginGUI.categoryList.size(); i++) {
			if(event.getSource() == buttons.elementAt(i)) {
				if(!isDialog) {
					CategoryEditor categoryEditor = new CategoryEditor(this, i);
					categoryEditor.run();
				}
			}
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

}
