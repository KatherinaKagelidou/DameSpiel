package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyWindowListener extends WindowAdapter {

	@Override
	public void windowClosing(WindowEvent e) {

		System.out.println("Möchtest du das Spiel verlassen?");
		System.exit(0);

	}

}
