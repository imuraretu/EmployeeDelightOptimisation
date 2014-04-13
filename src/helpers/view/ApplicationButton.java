package helpers.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
 
 
public class ApplicationButton extends JButton implements MouseListener { 
	private static final long serialVersionUID = 1L;
	
	//Instance attributes
	private Color defaultColor;
	private Color mouseOverColor;
	private ImageIcon normalImage;
	private ImageIcon mouseOverImage;
	private int controller;
	 
	//Class constructor (for text buttons)
	public ApplicationButton(String text, Color defaultColor, Color mouseOverColor) { 	 
		super(text);
		setContentAreaFilled(false);
        setOpaque(true);
        setBorderPainted(true);
		setBackground(defaultColor);

		this.defaultColor = defaultColor;		 
		this.mouseOverColor = mouseOverColor;
		
		controller = 0;

		addMouseListener(this); 
	}
	
	//Class constructor (for image buttons)
	public ApplicationButton(ImageIcon normalImage, ImageIcon mouseOverImage) { 	 
		super();
		 
		this.normalImage = normalImage;
		this.mouseOverImage = mouseOverImage;
		
		setIcon(normalImage);
		
		controller = 1;
		
		addMouseListener(this); 
	}
	 
	public void mouseEntered(MouseEvent e) {  
		if(e.getSource() == this) { 
			if(controller == 0) {
				this.setBackground(this.mouseOverColor);
			} else {
				this.setIcon(mouseOverImage);
			}
		}
	 
	}
	
	public void mouseExited(MouseEvent e) { 
		if(e.getSource()==this) {
			if(controller == 0) {
				this.setBackground(this.defaultColor);
			} else {
				this.setIcon(normalImage);
			}
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
}