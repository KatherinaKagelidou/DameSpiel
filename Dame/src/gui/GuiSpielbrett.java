package gui;

import gui.EventHandler;
import gui.Message;
import gui.Spieler1AuswahlDialog;
import gui.Spieler2AuswahlDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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
	private JButton fertig;
	private JLabel figurWeiss;
	private JLabel figurSchwarz;
	private JPanel pnlAdd;
	private JPanel pnlRight;
	private JPanel pnlText;
	private JTextField text;

	public GuiSpielbrett(Spieler1AuswahlDialog spieler1,
			Spieler2AuswahlDialog spieler2) {

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
		pnlRight = new JPanel();
		pnlAdd = new JPanel();
		pnlText = new JPanel();
		imageBrett = new JLabel(new ImageIcon("Brett.jpg"));
		figurSchwarz = new JLabel(new ImageIcon("schwarzFigur.png"));
		figurWeiss = new JLabel(new ImageIcon("weissFigur.png"));
		fertig = new JButton("Zug beenden");
		text = new JTextField(" z.B E1 - H2");

		textArea = new JTextArea();
		textArea.setFont(textArea.getFont().deriveFont(Font.BOLD + Font.ITALIC,
				15));
		textArea.setEditable(false);

		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(120, 80));
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		fertig.setAlignmentX(frame.LEFT_ALIGNMENT);
		pnlAdd.setLayout(new GridLayout(0, 1, 5, 5));
		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.PAGE_AXIS));
		pnlRight.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		text.setForeground(Color.MAGENTA);

	}

	private void addWidgets() {

		frame.getContentPane().setLayout(new BorderLayout(15, 15));

		frame.getContentPane().add(BorderLayout.CENTER, imageBrett);
		frame.getContentPane().add(BorderLayout.SOUTH, scrollPane);
		frame.getContentPane().add(BorderLayout.LINE_END, pnlRight);

		pnlAdd.add(figurSchwarz);
		pnlAdd.add(figurWeiss);

		pnlAdd.add(Box.createVerticalGlue());

		pnlAdd.setMaximumSize(pnlAdd.getPreferredSize());
		pnlAdd.setAlignmentX(frame.LEFT_ALIGNMENT);
		pnlRight.add(pnlAdd);

		pnlRight.add(Box.createVerticalGlue());

		pnlAdd.add(text);
		pnlRight.add(fertig);

		Message message = new Message(textArea);
		message.redirectOut();
		message.redirectErr(Color.red, null);
		message.setMessageLines(1000);
		
		nameOfIcon();

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

	/**
	 * Methode setzt Namen des Spielers auf das jeweilige Icon
	 */
	public void nameOfIcon() {

		if (spieler2 != null) {
			Color farbe1 = spieler2.farbAuswahl((String) spieler2
					.getFarbAuswahl().getSelectedItem());

			if (farbe1 == Color.BLACK) {
				figurSchwarz
						.setToolTipText(spieler2.getNameEingabe().getText());
			} else if (farbe1 == Color.WHITE) {
				figurWeiss.setToolTipText(spieler2.getNameEingabe().getText());

			}
		}
//		if (spieler2 != null) {
//			Color farbe2 = spieler2.farbAuswahl((String) spieler2
//					.getFarbAuswahl().getSelectedItem());
//
//			if (farbe2 == Color.BLACK) {
//				figurSchwarz
//						.setToolTipText(spieler2.getNameEingabe().getText());
//			} else if (farbe2 == Color.WHITE) {
//				figurWeiss.setToolTipText(spieler2.getNameEingabe().getText());
//
//			}
//		}
	}

}
