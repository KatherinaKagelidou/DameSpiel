package gui;

import klassen.Spiel;
import klassen.iBediener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EventHandler implements ActionListener {

	private JFrame frame;
	private StartGui startGui;
	private Spieler1AuswahlDialog spieler1AuswahlDialog;
	private Spieler2AuswahlDialog spieler2AuswahlDialog;
	private GuiSpielbrett guiSpielbrett;
	private MenuDialogLaden menuDialog;

	private iBediener i;
	private Spiel spiel;

	public EventHandler(StartGui startGui) {
		this.startGui = startGui;
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
		System.out.println(cmd);

		// ------------Start--------------
		if (cmd.equals("button")) {
			startGui.fra.setVisible(false);
			startGui.fra.dispose();
			new Spieler1AuswahlDialog(startGui);
			
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
				new Spieler2AuswahlDialog(spieler1AuswahlDialog);
			

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
				new GuiSpielbrett(spieler2AuswahlDialog.getSpieler1(), spieler2AuswahlDialog);
			}

		}

		if(guiSpielbrett!=null){
//		for (JButton b:guiSpielbrett.getFelder()) {
////			if (cmd.equals(b.getText())&& guiSpielbrett.hatIcon(e)==true) {
//			if (cmd.equals(b.getText())) {
//				System.out.println("Das Feld mit der ID "
//						+ b.getText());
//				
//				guiSpielbrett.lauf(e);
//			}
//		}
			if (cmd.equals("ziehen")) {
				guiSpielbrett.laufText();
			}
		}
		}
//	}

}
