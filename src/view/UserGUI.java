package view;

import helpers.bean.User;
import helpers.view.ApplicationButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.util.Vector;

import javax.swing.*;

import view.admin.products.CategoryEditor;
import view.user.CategoryGUI;

public class UserGUI extends JFrame implements Runnable,
											   ActionListener, ComponentListener{
	private static final long serialVersionUID = 1L;
	
	public LoginGUI loginGUI;
	public static User loginInfo;
	public Dimension minimumDimension = new Dimension(400, 400);
	public JLabel loggedLabel;
	public static JLabel pointsLabel;
	private JLabel categoryLabel = new JLabel("Choose");
	
	//Images for buttons
	private Vector<ImageIcon> nImages = new Vector<ImageIcon>();
	private Vector<ImageIcon> hImages = new Vector<ImageIcon>();
	private File nFile;
	private File hFile;
	private Vector<ApplicationButton> buttons = new Vector<ApplicationButton>();
	
	private Font loggedFont = new Font("Verdana", Font.BOLD, 10);
	private Font pointsFont = new Font("Verdana", Font.BOLD, 10);
	private boolean bComponentsToPanel = false;
	
	public GridBagConstraints gbConsLP = new GridBagConstraints();
	public GridBagConstraints gbConsGP = new GridBagConstraints();
	public GridBagConstraints gbCons = new GridBagConstraints();
	
	//Panels manager
	private JPanel loggedPanel = new JPanel();
	public JPanel guiPanel = new JPanel();
	
	public UserGUI(LoginGUI loginGUI, User loginInfo) {
		this.loginGUI = loginGUI;
		this.loginInfo = loginInfo;
		
		//Make logged label
		loggedLabel = new JLabel("Logged in as: " + loginInfo.getFirstName() 
								+ " " + loginInfo.getLastName() 
								+ " (" + loginInfo.getUsername() + ")");
		pointsLabel = new JLabel("Available points: " + loginInfo.getPoints());
		
		//Set GUI configuration
		setVisible(true);
		setLayout(new GridBagLayout());
		setSize(400, 400);
		setMinimumSize(minimumDimension);
		setTitle("UnifiedPost - Administration");
		getRootPane().addComponentListener(this);
		setLocationRelativeTo(new JFrame());
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		gbConsGP.insets.top = 5;
		gbConsGP.insets.bottom = 5;
		gbConsGP.insets.left = 5;
		gbConsGP.insets.right = 5;
		
		gbConsGP.gridx = 0;
		gbConsGP.gridy = 0;
		gbConsGP.gridwidth = 3;
		categoryLabel.setFont(new Font("Verdana", Font.BOLD, 25));
		categoryLabel.setForeground(Color.WHITE);
		guiPanel.add(categoryLabel, gbConsGP);
		
		gbConsGP.gridwidth = 1;
		gbConsGP.gridx = 0;
		gbConsGP.gridy = 1;
		
		int i = 0;
		do {			
			guiPanel.add(buttons.elementAt(i), gbConsGP);
			if(gbConsGP.gridx == 2) {
				gbConsGP.gridx = 0;
				gbConsGP.gridy++;
			} else {
				gbConsGP.gridx++;
			}
			
			i++;
		} while(i < LoginGUI.categoryList.size());
		repaintGUI();
	}
	
	private void adjustObjects() {
		//Welcome panel components
		loggedLabel.setFont(loggedFont);
		loggedLabel.setForeground(new Color(255, 98, 0));
		
		pointsLabel.setFont(pointsFont);
		pointsLabel.setForeground(new Color(255, 98, 0));
		
		//Panels
		loggedPanel.setLayout(new GridBagLayout());
		loggedPanel.setOpaque(false);
		guiPanel.setLayout(new GridBagLayout());
		guiPanel.setOpaque(false);
	}
	
	private void addComponentsToPanels() {
		//Adding objects to GUI
		gbConsLP.gridy = 0;
		gbConsLP.anchor = GridBagConstraints.CENTER;
		loggedPanel.add(loggedLabel, gbConsLP);
		gbConsLP.gridy = 1;
		gbConsLP.anchor = GridBagConstraints.CENTER;
		loggedPanel.add(pointsLabel, gbConsLP);
	}
	
	private void addPanels() {
		if(!bComponentsToPanel) {
			addComponentsToPanels();
		} else {
			bComponentsToPanel = true;
		}
		
		gbCons = new GridBagConstraints();
		gbCons.fill = GridBagConstraints.BOTH;
		gbCons.anchor = GridBagConstraints.NORTH;
		gbCons.weightx = 100;
		gbCons.weighty = 12;
		gbCons.ipadx = getSize().width;
		
		gbCons.gridx = 0;
		gbCons.gridy = 0;
		gbCons.ipady = 50;
		add(loggedPanel, gbCons);
		
		gbCons.gridx = 0;
		gbCons.gridy = 1;
		gbCons.ipady = getSize().height - 50;
		gbCons.weighty = 88;
		add(guiPanel, gbCons);
	}
	
	public void repaintGUI() {
		revalidate();
		repaint();
	}
	
	public void removeComponentsFromPanel() {
		guiPanel.removeAll();
		repaintGUI();
	}

	public void run() {		
		//Adjust objects
		adjustObjects();
		
		addPanels();
		
		loadImages();
		configButtons();
		drawObjects();
		
		repaintGUI();
	}
	
	public void actionPerformed(ActionEvent event) {
		for(int i=0; i<LoginGUI.categoryList.size(); i++) {
			if(event.getSource() == buttons.elementAt(i)) {
				// -- new frame for categoryList.get(i) Category
				CategoryGUI categoryGUI = new CategoryGUI(i, pointsLabel);
				categoryGUI.run();
			}
		}
	}

	public void componentResized(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
