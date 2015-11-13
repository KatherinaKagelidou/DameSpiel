package gui;

import gui.EventHandler;

import gui.Message;
import gui.Spieler1AuswahlDialog;
import gui.Spieler2AuswahlDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import klassen.Spiel;
import klassen.iBediener;

public class GuiSpielbrett extends JOptionPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame = new JFrame();
	private JLabel imageBrett;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private GuiSpielbrett guiSpielbrett;
	private static iBediener spiel;
	private Spieler1AuswahlDialog spieler1;
	private Spieler2AuswahlDialog spieler2;
	private EventHandler event;

	public GuiSpielbrett() {

		frame.setTitle("Game Dame");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		GuiSpielbrett.spielErstellen();
		this.spieler1 = spieler1;
		this.spieler2 = spieler2;

		createWidgets();
		addWidgets();

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		WindowListener winListener = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int antw = JOptionPane.showConfirmDialog(GuiSpielbrett.this,
						"Möchtst du " + " das Spiel wirklich verlassen?",
						"Spiel verlassen?", JOptionPane.YES_NO_OPTION);
				if (antw == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}

		};
		frame.addWindowListener(winListener);

	}

	private void createWidgets() {

		imageBrett = new JLabel(new ImageIcon("Brett.jpg"));

		textArea = new JTextArea();
		textArea.setFont(textArea.getFont().deriveFont(Font.BOLD + Font.ITALIC,
				15));
		textArea.setEditable(false);

		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(120, 80));
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	}

	private void addWidgets() {

		frame.getContentPane().setLayout(new BorderLayout(15, 15));

		frame.getContentPane().add(BorderLayout.CENTER, imageBrett);
		frame.getContentPane().add(BorderLayout.PAGE_END, scrollPane);

		Message message = new Message(textArea);
		message.redirectOut();
		message.redirectErr(Color.red, null);
		message.setMessageLines(1000);

	}

	public static void spielErstellen() {
		spiel = new Spiel();
	}

	public static iBediener gibSpielZurueck() {
		return spiel;
	}

	public Spieler1AuswahlDialog getSpieler1() {
		return spieler1;
	}

	public Spieler2AuswahlDialog getSpieler2() {
		return spieler2;
	}

	public EventHandler getEvent() {
		return event;
	}

	public iBediener getSpiel() {
		return spiel;
	}

}
