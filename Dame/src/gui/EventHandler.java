package gui;

import klassen.iBediener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EventHandler implements ActionListener {

	private JFrame frame;
	private StartGui dialogFenster;
	private Spieler1AuswahlDialog spieler1AuswahlDialog;
	private Spieler2AuswahlDialog spieler2AuswahlDialog;
	private GuiSpielbrett guiSpielbrett;
	private MenuDialogLaden menuDialog;

	public EventHandler(StartGui dialogFenster) {
		this.dialogFenster = dialogFenster;
	}

	public EventHandler(Spieler1AuswahlDialog spieler1AuswahlDialog) {
		this.spieler1AuswahlDialog = spieler1AuswahlDialog;
	}

	public EventHandler(Spieler2AuswahlDialog spieler2AuswahlDialog) {
		this.spieler2AuswahlDialog = spieler2AuswahlDialog;
	}

	public EventHandler(GuiSpielbrett guiSpielbrett) {
		this.guiSpielbrett = guiSpielbrett;
	}

	public EventHandler(MenuDialogLaden menuDialog) {
		this.menuDialog = menuDialog;
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

			String name = spieler1AuswahlDialog.getNameEingabe().getText();

			if (name == null || name.length() < 2) {
				JOptionPane.showMessageDialog(spieler1AuswahlDialog,
						"Name muss mindestens 2 Zeichen enthalten!", "ERROR!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				spieler1AuswahlDialog.frame.setVisible(false);
				spieler1AuswahlDialog.frame.dispose();
				new Spieler2AuswahlDialog();

			}

		}

		if (cmd.equals("spielStarten1")) {

			String name = spieler2AuswahlDialog.getNameEingabe().getText();

			if (name == null || name.length() < 2) {
				JOptionPane.showMessageDialog(spieler2AuswahlDialog,
						"Name muss mindestens 2 Zeichen enthalten!", "ERROR!",
						JOptionPane.ERROR_MESSAGE);
			} else {

				spieler2AuswahlDialog.frame.setVisible(false);
				spieler2AuswahlDialog.frame.dispose();
				new GuiSpielbrett();
			}

		}

	}

}
