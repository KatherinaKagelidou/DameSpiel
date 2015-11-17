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
import java.util.ArrayList;

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
	private StartGui startGui;
	private Spieler1AuswahlDialog spieler1;
	private Spieler2AuswahlDialog spieler2;
	private EventHandler event;
	private JButton fertig;
	private JLabel figurWeiss;
	private JLabel figurSchwarz;
	private JPanel pnlAdd;
	private JPanel pnlRight;
	private JPanel pnlLeft;
	private JTextField text;

	private ArrayList<JButton> felder;
	private ArrayList<ImageIcon> weiss;
	private ArrayList<ImageIcon> schwarz;

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

	/**
	 * Methode createWidgets 
	 * erstellt alle erforderlingen Komponenten 
	 * und deren Elemente
	 */
	private void createWidgets() {
		pnlRight = new JPanel();
		pnlAdd = new JPanel();
		pnlLeft = new JPanel();
		imageBrett = new JLabel(new ImageIcon("brettNeu.png"));
		figurSchwarz = new JLabel(new ImageIcon("weissStein100.png"));
		figurWeiss = new JLabel(new ImageIcon("schwarzStein100.png"));
		fertig = new JButton("Zug beenden");
		text = new JTextField(" E1 - H2");

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

		// zum ausprobieren
		//liste fuer fie schwarzen figuren um diese in einer schleife
		// auf das Brett zu setzen
		schwarz = new ArrayList<ImageIcon>();
		for (int i = 1; i <= 30; i++) {
			schwarz.add(new ImageIcon("schwarzerStein60.png"));
		}
		//liste fuer fie weissen figuren um diese in einer schleife
		// auf das Brett zu setzen
		weiss = new ArrayList<ImageIcon>();
		for (int i = 1; i <= 30; i++) {
			weiss.add(new ImageIcon("weissStein60.png"));
		}

	}
	
	/**
	 * Methode addWidgets 
	 * fuegt die Komponenten und deren Elemente 
	 * dem Layout hinzu 
	 */
	private void addWidgets() {
	

		frame.getContentPane().setLayout(new BorderLayout(15, 15));
		frame.getContentPane().add(BorderLayout.CENTER, imageBrett);
		frame.getContentPane().add(BorderLayout.SOUTH, scrollPane);
		frame.getContentPane().add(BorderLayout.LINE_END, pnlRight);
		frame.getContentPane().add(BorderLayout.LINE_START, pnlLeft);

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
		System.out.println(" ----Das Spiel beginnt---- ");

		//liste der Buttons durchgegangen und diese nicht sichtbar gemacht
		felder = new ArrayList<JButton>();
		for (int i = 0; i <= 71; i++) {
			
			felder.add(new JButton());
			imageBrett.add(felder.get(i));
			felder.get(i).setBorderPainted(false);
			felder.get(i).setContentAreaFilled(false);
			felder.get(i).addActionListener(new EventHandler(this));
			felder.get(i).setActionCommand("feld");
			if(i<=29){
				felder.get(i).setIcon(schwarz.get(i));
	
				
			}
			
			
		}
		
		for (int i = 0; i <= 71; i++) {

			felder.add(new JButton());
			imageBrett.add(felder.get(i));
			felder.get(i).setBorderPainted(false);
			felder.get(i).setContentAreaFilled(false);
			felder.get(i).addActionListener(new EventHandler(this));
			felder.get(i).setActionCommand("feld");
			 if(i>=42){
				 for (int j = 0; j <= 29; j++) {
					felder.get(i).setIcon(weiss.get(j));
					
				 }
				}
		}
		
         //setzt die buttons auf ihre Position auf dem Spielbrett
		felder.get(0).setBounds(9, 513, 36, 36);
		felder.get(1).setBounds(102, 513, 36, 36);
		felder.get(2).setBounds(194, 513, 36, 36);
		felder.get(3).setBounds(285, 513, 36, 36);
		felder.get(4).setBounds(377, 513, 36, 36);
		felder.get(5).setBounds(469, 513, 36, 36);

		felder.get(6).setBounds(56, 466, 36, 36);
		felder.get(7).setBounds(148, 466, 36, 36);
		felder.get(8).setBounds(240, 466, 36, 36);
		felder.get(9).setBounds(331, 466, 36, 36);
		felder.get(10).setBounds(423, 466, 36, 36);
		felder.get(11).setBounds(515, 466, 36, 36);

		felder.get(12).setBounds(9, 421, 36, 36);
		felder.get(13).setBounds(102, 421, 36, 36);
		felder.get(14).setBounds(194, 421, 36, 36);
		felder.get(15).setBounds(285, 421, 36, 36);
		felder.get(16).setBounds(377, 421, 36, 36);
		felder.get(17).setBounds(469, 421, 36, 36);

		felder.get(18).setBounds(56, 376, 36, 36);
		felder.get(19).setBounds(148, 376, 36, 36);
		felder.get(20).setBounds(240, 376, 36, 36);
		felder.get(21).setBounds(331, 376, 36, 36);
		felder.get(22).setBounds(423, 376, 36, 36);
		felder.get(23).setBounds(515, 376, 36, 36);

		felder.get(24).setBounds(9, 329, 36, 36);
		felder.get(25).setBounds(102, 329, 36, 36);
		felder.get(26).setBounds(194, 329, 36, 36);
		felder.get(27).setBounds(285, 329, 36, 36);
		felder.get(28).setBounds(377, 329, 36, 36);
		felder.get(29).setBounds(469, 329, 36, 36);

		felder.get(30).setBounds(56, 284, 36, 36);
		felder.get(31).setBounds(148, 284, 36, 36);
		felder.get(32).setBounds(240, 284, 36, 36);
		felder.get(33).setBounds(331, 284, 36, 36);
		felder.get(34).setBounds(423, 284, 36, 36);
		felder.get(35).setBounds(515, 284, 36, 36);

		felder.get(36).setBounds(9, 238, 36, 36);
		felder.get(37).setBounds(102, 238, 36, 36);
		felder.get(38).setBounds(194, 238, 36, 36);
		felder.get(39).setBounds(285, 238, 36, 36);
		felder.get(40).setBounds(377, 238, 36, 36);
		felder.get(41).setBounds(469, 238, 36, 36);

		felder.get(42).setBounds(56, 193, 36, 36);
		felder.get(43).setBounds(148, 193, 36, 36);
		felder.get(44).setBounds(240, 193, 36, 36);
		felder.get(45).setBounds(331, 193, 36, 36);
		felder.get(46).setBounds(423, 193, 36, 36);
		felder.get(47).setBounds(515, 193, 36, 36);

		felder.get(48).setBounds(9, 146, 36, 36);
		felder.get(49).setBounds(102, 146, 36, 36);
		felder.get(50).setBounds(194, 146, 36, 36);
		felder.get(51).setBounds(285, 146, 36, 36);
		felder.get(52).setBounds(377, 146, 36, 36);
		felder.get(53).setBounds(469, 146, 36, 36);

		felder.get(54).setBounds(56, 100, 36, 36);
		felder.get(55).setBounds(148, 100, 36, 36);
		felder.get(56).setBounds(240, 100, 36, 36);
		felder.get(57).setBounds(331, 100, 36, 36);
		felder.get(58).setBounds(423, 100, 36, 36);
		felder.get(59).setBounds(515, 100, 36, 36);

		felder.get(60).setBounds(9, 55, 36, 36);
		felder.get(61).setBounds(102, 55, 36, 36);
		felder.get(62).setBounds(194, 55, 36, 36);
		felder.get(63).setBounds(285, 55, 36, 36);
		felder.get(64).setBounds(377, 55, 36, 36);
		felder.get(65).setBounds(469, 55, 36, 36);

		felder.get(66).setBounds(56, 9, 36, 36);
		felder.get(67).setBounds(148, 9, 36, 36);
		felder.get(68).setBounds(240, 9, 36, 36);
		felder.get(69).setBounds(331, 9, 36, 36);
		felder.get(70).setBounds(423, 9, 36, 36);
		felder.get(71).setBounds(515, 9, 36, 36);

//		//setzt die schwarzen figuren auf dem Brett
//		for (int i = 0; i <= 29; i++) {
//		
//			felder.get(i).setIcon(weiss.get(i));
//		}
//		//setzt die weissen figuren auf dem Brett
//		for (int i = 0; i <= 42; i++) {
//			felder.get(i).setIcon(schwarz.get(i));
//
//		}
		
		
	
		nameOfIcon();

	}

	/**
	 * Methode spielErstellen
	 * erstellt ein neues Spiel
	 */
	public static void spielErstellen() {
		spiel = new Spiel();
	}

	/**
	 * Methode getSpieler1
	 * @return spieler1
	 */
	public Spieler1AuswahlDialog getSpieler1() {
		return spieler1;
	}
	/**
	 * Methode getSpieler2
	 * @return spieler2
	 */
	public Spieler2AuswahlDialog getSpieler2() {
		return spieler2;
	}

	/**
	 * Methode getEvent
	 * @return event
	 */
	public EventHandler getEvent() {
		return event;
	}
 
	/**
	 * Methode getSpiel
	 * @return spiel
	 */
	public iBediener getSpiel() {
		return spiel;
	}

	/**
	 * Methode setzt Namen des Spielers auf das jeweilige Icon
	 */
	public void nameOfIcon() {

		if (spieler1 != null) {

			Color farbe1 = spieler1.farbAuswahl((String) spieler1
					.getFarbAuswahl().getSelectedItem());

			if (farbe1 == Color.BLACK) {
				figurSchwarz
						.setToolTipText(spieler1.getNameEingabe().getText());
			} else if (farbe1 == Color.WHITE) {
				figurWeiss.setToolTipText(spieler1.getNameEingabe().getText());

			}

			Color farbe2 = spieler2.farbAuswahl((String) spieler2
					.getFarbAuswahl().getSelectedItem());

			if (farbe2 == Color.BLACK) {
				figurSchwarz
						.setToolTipText(spieler2.getNameEingabe().getText());
			} else if (farbe2 == Color.WHITE) {
				figurWeiss.setToolTipText(spieler2.getNameEingabe().getText());

			}

		}
	}

	/**
	 * Methode getFelder
	 * Liste der Buttons wird zurueckgegeben
	 * @return felder
	 */
	public ArrayList<JButton> getFelder() {
		return felder;

	}

	 public static void main(String[] args) {
	 new GuiSpielbrett(null, null);
	 }

}
