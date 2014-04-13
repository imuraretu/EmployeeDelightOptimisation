import javax.swing.UnsupportedLookAndFeelException;

import view.LoginGUI;


public class StartUp {

	/**
	 * @param args
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) {
		//Create GUI
		LoginGUI loginGUI = new LoginGUI();
		loginGUI.run();
	}
}
