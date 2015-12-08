package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

public class MyWindowListener extends WindowAdapter  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void windowClosing(WindowEvent e) {

		System.out.println("Möchtest du das Spiel verlassen?");
		System.exit(0);

	}

}
