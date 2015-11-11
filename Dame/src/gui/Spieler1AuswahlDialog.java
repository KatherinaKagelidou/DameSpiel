package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import klassen.FarbEnum;
import klassen.Spieler;

public class Spieler1AuswahlDialog {

	JFrame frame;
	JButton spielStarten;
	JLabel spielTypFrage;
	JLabel name;
	JLabel farben;
	TextField nameEingabe;
	JComboBox artAuswahl;
	JComboBox farbAuswahl;
	JLabel spieloptionen;
	JPanel panel = new JPanel();
	JLabel spielerText;

	private Spieler spieler;

	private StartGui startGui;
	private EventHandler event;

	String[] art = { "Mensch", "KI" };
	String[] farbe = { "Weiss", "Schwarz" };

	public Spieler1AuswahlDialog() {

		frame = new JFrame("Game Dame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 290);

		this.startGui = startGui;
		event = new EventHandler(this);
		erstelle();
		hinzufuegen();
		addListener();

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		// frame.pack();
		frame.setVisible(true);
	}

	private void erstelle() {

		spieloptionen = new JLabel("Spieloptionen");
		spieloptionen.setBounds(180, 0, 250, 30);
		spieloptionen.setFont(spieloptionen.getFont().deriveFont(
				Font.BOLD + Font.ITALIC, 20));
		spieloptionen.setForeground(Color.black);

		spielerText = new JLabel("Spieler 1");
		spielerText.setBounds(30, 0, 250, 30);
		spielerText.setForeground(Color.magenta);
		spielerText.setFont(spielerText.getFont().deriveFont(
				Font.ROMAN_BASELINE + Font.ITALIC, 15));
		//

		spielTypFrage = new JLabel("Wähle eine Art");
		spielTypFrage.setBounds(50, 70, 250, 20);

		artAuswahl = new JComboBox(art);
		artAuswahl.setBackground(Color.WHITE);
		artAuswahl.setBounds(300, 70, 120, 25);

		name = new JLabel();
		name.setText("Gib deinen Namen ein");
		name.setBounds(50, 80, 250, 100);

		nameEingabe = new TextField();
		nameEingabe.setBounds(300, 120, 120, 20);

		farben = new JLabel("Wähle eine Farbe");
		farben.setBounds(50, 170, 250, 20);

		farbAuswahl = new JComboBox(farbe);
		farbAuswahl.setBackground(Color.WHITE);
		farbAuswahl.setBounds(300, 170, 120, 25);

		spielStarten = new JButton("Spiel starten");
		spielStarten.setFont(new Font("Arial", Font.BOLD, 12));
		spielStarten.setBackground(Color.WHITE);
		spielStarten.setBounds(300, 220, 120, 30);

	}

	private void hinzufuegen() {

		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panel.setLayout(null);

		panel.add(spielTypFrage);
		panel.add(artAuswahl);

		panel.add(name);
		panel.add(nameEingabe);

		panel.add(farben);
		panel.add(farbAuswahl);

		panel.add(spielStarten);

		panel.add(spieloptionen);

		panel.add(spielerText);

		frame.add(panel);
	}

	public FarbEnum gibFarbe(String farbe) {
		FarbEnum getFarbe = null;
		switch (farbe) {
		case "Weiss":
			getFarbe = FarbEnum.WEISS;
			break;
		case "Schwarz":
			getFarbe = FarbEnum.SCHWARZ;
			break;

		}
		return getFarbe;
	}

	public Color farbAuswahl(String farbe) {
		Color getFarbe = null;
		switch (farbe) {
		case "Weiss":
			getFarbe = Color.WHITE;
			break;
		case "Schwarz":
			getFarbe = Color.BLACK;

		}
		return getFarbe;
	}

	private void addListener() {
		spielStarten.addActionListener(event);
		spielStarten.setActionCommand("spielStarten");
	}

	public JButton getSpielStarten() {
		return spielStarten;
	}

	public JComboBox getArtAuswahl() {
		return artAuswahl;
	}

	public JComboBox getFarbAuswahl() {
		return farbAuswahl;
	}

	public TextField getNameEingabe() {
		return nameEingabe;
	}

	public String[] getFarbe() {
		return farbe;
	}

	public String[] getArt() {
		return art;
	}

	public EventHandler getEvent() {
		return event;
	}

}
