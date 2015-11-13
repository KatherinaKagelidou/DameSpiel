package gui;

import klassen.iBediener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EventHandler implements ActionListener {

	JFrame frame;
	StartGui dialogFenster;
	Spieler1AuswahlDialog spieler1AuswahlDialog;
	Spieler2AuswahlDialog spieler2AuswahlDialog;

	public EventHandler(StartGui dialogFenster) {
		this.dialogFenster = dialogFenster;
	}

	public EventHandler(Spieler1AuswahlDialog spieler1AuswahlDialog) {
		this.spieler1AuswahlDialog = spieler1AuswahlDialog;
	}

	public EventHandler(Spieler2AuswahlDialog spieler2AuswahlDialog) {
		this.spieler2AuswahlDialog = spieler2AuswahlDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		// ------------Start--------------
		if (cmd.equals("button")) {
			dialogFenster.fra.setVisible(false);
			dialogFenster.fra.dispose();
			new Spieler1AuswahlDialog();
		}
		if (cmd.equals("spielStarten")) {
			spieler1AuswahlDialog.frame.setVisible(false);
			spieler1AuswahlDialog.frame.dispose();
			new Spieler2AuswahlDialog();
		}
		

		
	}

}
