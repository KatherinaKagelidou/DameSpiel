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
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
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

import klassen.FarbEnum;
import klassen.Spiel;
import klassen.Spielbrett;
import klassen.Spielfeld;
import klassen.Spielfigur;
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
	private Spiel spiel;
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

	private int[] wert;
	private ArrayList<String>posZiel=new ArrayList<String>();
	private ArrayList<JButton>posZielButton=new ArrayList<JButton>();
	
	private Spielbrett brett;

	/**
	 * Konstruktor der Klasse GuiSpielbrett
	 * Methodenaufrufe und Eigenschaften des Frames werden hier uebergeben
	 * @param spieler1
	 * @param spieler2
	 */
	public GuiSpielbrett(Spieler1AuswahlDialog spieler1,
			Spieler2AuswahlDialog spieler2) {

		frame.setTitle("Game Dame");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.spieler1 = spieler1;
		this.spieler2 = spieler2;
		event = new EventHandler(this);
		createWidgets();
		addWidgets();
		addListener();
		this.spielErstellen();
		wert = new int[1];

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		WindowListener winListener = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int antw = JOptionPane.showConfirmDialog(GuiSpielbrett.this,
						"Möchtest du " + " das Spiel wirklich verlassen?",
						"Spiel verlassen?", JOptionPane.YES_NO_OPTION);
				if (antw == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}

		};
		frame.addWindowListener(winListener);

	}

	/**
	 * Methode createWidgets erstellt alle erforderlingen Komponenten und deren
	 * Elemente
	 */
	private void createWidgets() {
		pnlRight = new JPanel();
		pnlAdd = new JPanel();
		pnlLeft = new JPanel();
		imageBrett = new JLabel(new ImageIcon("brett-akt.png"));
		figurSchwarz = new JLabel(new ImageIcon("schwarzStein100.png"));
		figurWeiss = new JLabel(new ImageIcon("weissStein100.png"));
		fertig = new JButton("Zug beenden");
		text = new JTextField();

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
		// liste fuer fie schwarzen figuren um diese in einer schleife
		// auf das Brett zu setzen
		schwarz = new ArrayList<ImageIcon>();
		for (int i = 1; i <= 30; i++) {
			schwarz.add(new ImageIcon("schwarzerStein60.png"));
		}
		// liste fuer fie weissen figuren um diese in einer schleife
		// auf das Brett zu setzen

		weiss = new ArrayList<ImageIcon>();
		for (int i = 1; i <= 30; i++) {
			weiss.add(new ImageIcon("weissStein60.png"));

		}

	}

	/**
	 * Methode addWidgets fuegt die Komponenten und deren Elemente dem Layout
	 * hinzu
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

//		Message message = new Message(textArea);
//		message.redirectOut();
//		message.redirectErr(Color.red, null);
//		message.setMessageLines(1000);

		// this.getSpiel().starteSpiel();
		
		

		// liste der Buttons durchgegangen und diese nicht sichtbar gemacht
		felder = new ArrayList<JButton>();
		for (int i = 0; i <= 71; i++) {

			felder.add(new JButton());
			imageBrett.add(felder.get(i));
			felder.get(i).setBorderPainted(false);
			felder.get(i).setContentAreaFilled(false);
			felder.get(i).addActionListener(new EventHandler(this));
			felder.get(i).setActionCommand("feld");
			
		}
		
		for(int i = 0; i <= 29; i++) {
			felder.get(i).setIcon(schwarz.get(i));			
		}
		
		int count=0;
		for(int i = 42; i <=71; i++)  {		
			felder.get(i).setIcon(weiss.get(count));
			count++;
		}
//		felder.get(42).setIcon(weiss.get(0));
//		felder.get(43).setIcon(weiss.get(1));
//		felder.get(44).setIcon(weiss.get(2));

		// setzt die buttons auf ihre Position auf dem Spielbrett
		felder.get(0).setBounds(41, 501, 60, 60);
		felder.get(0).setText("A1");
		felder.get(0).setText(null);
		felder.get(1).setBounds(133, 501, 60, 60);
		felder.get(1).setText("A3");
		felder.get(1).setText(null);
		felder.get(2).setBounds(224, 501, 60, 60);
		felder.get(2).setText("A5");
		felder.get(2).setText(null);
		felder.get(3).setBounds(315, 501, 60, 60);
		felder.get(3).setText("A7");
		felder.get(3).setText(null);
		felder.get(4).setBounds(408, 501, 60, 60);
		felder.get(4).setText("A9");
		felder.get(4).setText(null);
		felder.get(5).setBounds(499, 501, 60, 60);
		felder.get(5).setText("A11");
		felder.get(5).setText(null);

		felder.get(6).setBounds(86, 454, 60, 60);
		felder.get(6).setText("B2");
		felder.get(6).setText(null);
		felder.get(7).setBounds(178, 454, 60, 60);
		felder.get(7).setText("B4");
		felder.get(7).setText(null);
		felder.get(8).setBounds(270, 454, 60, 60);
		felder.get(8).setText("B6");
		felder.get(8).setText(null);
		felder.get(9).setBounds(360, 454, 60, 60);
		felder.get(9).setText("B8");
		felder.get(9).setText(null);
		felder.get(10).setBounds(452, 454, 60, 60);
		felder.get(10).setText("B10");
		felder.get(10).setText(null);
		felder.get(11).setBounds(545, 454, 60, 60);
		felder.get(11).setText("B12");
		felder.get(11).setText(null);

		felder.get(12).setBounds(41, 410, 60, 60);
		felder.get(12).setText("C1");
		felder.get(12).setText(null);
		felder.get(13).setBounds(133, 410, 60, 60);
		felder.get(13).setText("C3");
		felder.get(13).setText(null);
		felder.get(14).setBounds(224, 410, 60, 60);
		felder.get(14).setText("C5");
		felder.get(14).setText(null);
		felder.get(15).setBounds(315, 410, 60, 60);
		felder.get(15).setText("C7");
		felder.get(15).setText(null);
		felder.get(16).setBounds(408, 410, 60, 60);
		felder.get(16).setText("C9");
		felder.get(16).setText(null);
		felder.get(17).setBounds(499, 410, 60, 60);
		felder.get(17).setText("C11");
		felder.get(17).setText(null);

		felder.get(18).setBounds(86, 364, 60, 60);
		felder.get(18).setText("D2");
		felder.get(18).setText(null);
		felder.get(19).setBounds(178, 364, 60, 60);
		felder.get(19).setText("D4");
		felder.get(19).setText(null);
		felder.get(20).setBounds(270, 364, 60, 60);
		felder.get(20).setText("D6");
		felder.get(20).setText(null);
		felder.get(21).setBounds(360, 364, 60, 60);
		felder.get(21).setText("D8");
		felder.get(21).setText(null);
		felder.get(22).setBounds(452, 364, 60, 60);
		felder.get(22).setText("D10");
		felder.get(22).setText(null);
		felder.get(23).setBounds(545, 364, 60, 60);
		felder.get(23).setText("D12");
		felder.get(23).setText(null);

		felder.get(24).setBounds(41, 318, 60, 60);
		felder.get(24).setText("E1");
		felder.get(24).setText(null);
		felder.get(25).setBounds(133, 318, 60, 60);
		felder.get(25).setText("E3");
		felder.get(25).setText(null);
		felder.get(26).setBounds(224, 318, 60, 60);
		felder.get(26).setText("E5");
		felder.get(26).setText(null);
		felder.get(27).setBounds(315, 318, 60, 60);
		felder.get(27).setText("E7");
		felder.get(27).setText(null);
		felder.get(28).setBounds(408, 318, 60, 60);
		felder.get(28).setText("E9");
		felder.get(28).setText(null);
		felder.get(29).setBounds(499, 318, 60, 60);
		felder.get(29).setText("E11");
		felder.get(29).setText(null);

		felder.get(30).setBounds(86, 274, 60, 60);
		felder.get(30).setText("F2");
		felder.get(30).setText(null);
		felder.get(31).setBounds(178, 274, 60, 60);
		felder.get(31).setText("F4");
		felder.get(31).setText(null);
		felder.get(32).setBounds(270, 274, 60, 60);
		felder.get(32).setText("F6");
		felder.get(32).setText(null);
		felder.get(33).setBounds(360, 274, 60, 60);
		felder.get(33).setText("F8");
		felder.get(33).setText(null);
		felder.get(34).setBounds(452, 274, 60, 60);
		felder.get(34).setText("F10");
		felder.get(34).setText(null);
		felder.get(35).setBounds(545, 274, 60, 60);
		felder.get(35).setText("F12");
		felder.get(35).setText(null);

		felder.get(36).setBounds(41, 228, 60, 60);
		felder.get(36).setText("G1");
		felder.get(36).setText(null);
		felder.get(37).setBounds(133, 228, 60, 60);
		felder.get(37).setText("G3");
		felder.get(37).setText(null);
		felder.get(38).setBounds(224, 228, 60, 60);
		felder.get(38).setText("G5");
		felder.get(38).setText(null);
		felder.get(39).setBounds(315, 228, 60, 60);
		felder.get(39).setText("G7");
		felder.get(39).setText(null);
		felder.get(40).setBounds(408, 228, 60, 60);
		felder.get(40).setText("G9");
		felder.get(40).setText(null);
		felder.get(41).setBounds(499, 228, 60, 60);
		felder.get(41).setText("G11");
		felder.get(41).setText(null);

		felder.get(42).setBounds(86, 182, 60, 60);
		felder.get(42).setText("H2");
		felder.get(42).setText(null);
		felder.get(43).setBounds(178, 182, 60, 60);
		felder.get(43).setText("H4");
		felder.get(43).setText(null);
		felder.get(44).setBounds(270, 182, 60, 60);
		felder.get(44).setText("H6");
		felder.get(44).setText(null);
		felder.get(45).setBounds(360, 182, 60, 60);
		felder.get(45).setText("H8");
		felder.get(45).setText(null);
		felder.get(46).setBounds(452, 182, 60, 60);
		felder.get(46).setText("H10");
		felder.get(46).setText(null);
		felder.get(47).setBounds(545, 182, 60, 60);
		felder.get(47).setText("H12");
		felder.get(47).setText(null);

		felder.get(48).setBounds(41, 136, 60, 60);
		felder.get(48).setText("I1");
		felder.get(48).setText(null);
		felder.get(49).setBounds(133, 136, 60, 60);
		felder.get(49).setText("I3");
		felder.get(49).setText(null);
		felder.get(50).setBounds(224, 136, 60, 60);
		felder.get(50).setText("I5");
		felder.get(50).setText(null);
		felder.get(51).setBounds(315, 136, 60, 60);
		felder.get(51).setText("I7");
		felder.get(51).setText(null);
		felder.get(52).setBounds(408, 136, 60, 60);
		felder.get(52).setText("I9");
		felder.get(52).setText(null);
		felder.get(53).setBounds(499, 136, 60, 60);
		felder.get(53).setText("I11");
		felder.get(53).setText(null);

		felder.get(54).setBounds(86, 90, 60, 60);
		felder.get(54).setText("J2");
		felder.get(54).setText(null);
		felder.get(55).setBounds(178, 90, 60, 60);
		felder.get(55).setText("J4");
		felder.get(55).setText(null);
		felder.get(56).setBounds(270, 90, 60, 60);
		felder.get(56).setText("J6");
		felder.get(56).setText(null);
		felder.get(57).setBounds(360, 90, 60, 60);
		felder.get(57).setText("J8");
		felder.get(57).setText(null);
		felder.get(58).setBounds(452, 90, 60, 60);
		felder.get(58).setText("J10");
		felder.get(58).setText(null);
		felder.get(59).setBounds(545, 90, 60, 60);
		felder.get(59).setText("J12");
		felder.get(59).setText(null);

		felder.get(60).setBounds(41, 45, 60, 60);
		felder.get(60).setText("K1");
		felder.get(60).setText(null);
		felder.get(61).setBounds(133, 45, 60, 60);
		felder.get(61).setText("K3");
		felder.get(61).setText(null);
		felder.get(62).setBounds(224, 45, 60, 60);
		felder.get(62).setText("K5");
		felder.get(62).setText(null);
		felder.get(63).setBounds(315, 45, 60, 60);
		felder.get(63).setText("K7");
		felder.get(63).setText(null);
		felder.get(64).setBounds(408, 45, 60, 60);
		felder.get(64).setText("K9");
		felder.get(64).setText(null);
		felder.get(65).setBounds(499, 45, 60, 60);
		felder.get(65).setText("K11");
		felder.get(65).setText(null);

		felder.get(66).setBounds(86, -1, 60, 60);
		felder.get(66).setText("L2");
		felder.get(66).setText(null);
		felder.get(67).setBounds(178, -1, 60, 60);
		felder.get(67).setText("L4");
		felder.get(67).setText(null);
		felder.get(68).setBounds(270, -1, 60, 60);
		felder.get(68).setText("L6");
		felder.get(68).setText(null);
		felder.get(69).setBounds(360, -1, 60, 60);
		felder.get(69).setText("L8");
		felder.get(69).setText(null);
		felder.get(70).setBounds(452, -1, 60, 60);
		felder.get(70).setText("L10");
		felder.get(70).setText(null);
		felder.get(71).setBounds(545, -1, 60, 60);
		felder.get(71).setText("L12");
		felder.get(71).setText(null);

		// //setzt die schwarzen figuren auf dem Brett
		// for (int i = 0; i <= 29; i++) {
		//
		// felder.get(i).setIcon(weiss.get(i));
		// }
		// //setzt die weissen figuren auf dem Brett
		// for (int i = 0; i <= 42; i++) {
		// felder.get(i).setIcon(schwarz.get(i));
		//
		// }

		nameOfIcon();
		//

	}

	/**
	 * Methode spielErstellen erstellt ein neues Spiel
	 */
	public void spielErstellen() {
		spiel = new Spiel();
		this.spieler();
		spiel.starteSpiel();
	}

	/**
	 * Methode getSpieler1
	 * 
	 * @return spieler1
	 */
	public Spieler1AuswahlDialog getSpieler1() {
		return spieler1;
	}

	/**
	 * Methode getSpieler2
	 * 
	 * @return spieler2
	 */
	public Spieler2AuswahlDialog getSpieler2() {
		return spieler2;
	}

	/**
	 * Methode getEvent
	 * 
	 * @return event
	 */
	public EventHandler getEvent() {
		return event;
	}

	/**
	 * Methode getSpiel
	 * 
	 * @return spiel
	 */
	public iBediener getSpiel() {
		return spiel;
	}

	/**
	 * Methode setzt Namen des Spielers auf das jeweilige Icon
	 */
	public void nameOfIcon() {

		if (this != null) {

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

	private void addListener() {
//		for (int i = 0; i < felder.size(); i++) {
//			felder.get(i).addActionListener(event);
//			felder.get(i).setActionCommand(felder.get(i).getText());
//		}
		fertig.addActionListener(event);
		fertig.setActionCommand("ziehen");

	}

	/**
	 * Methode getFelder Liste der Buttons wird zurueckgegeben
	 * 
	 * @return felder
	 */
	public ArrayList<JButton> getFelder() {
		return felder;

	}

	public void spieler() {

		String name1 = spieler1.getNameEingabe().getText();
		String farbe1 = (String) spieler1.getFarbAuswahl().getSelectedItem();

		// String ki1 = (String)spieler1.getArtAuswahl().getSelectedItem();

		String name2 = spieler2.getNameEingabe().getText();
		String farbe2 = (String) spieler2.getFarbAuswahl().getSelectedItem();
		// String ki1 = (String)spieler1.getArtAuswahl().getSelectedItem();

		spiel.addSpieler(name1, spieler1.gibFarbe(farbe1), null);
		spiel.addSpieler(name2, spieler2.gibFarbe(farbe2), null);

	}

	public boolean hatIcon(ActionEvent e) {

		JButton feld = (JButton) e.getSource();

		if (feld.getIcon() != null) {
			return true;
		}
		return false;
	}



	public ArrayList<ImageIcon> getWeiss() {
		return weiss;
	}

	public void setWeiss(ArrayList<ImageIcon> weiss) {
		this.weiss = weiss;
	}

	public ArrayList<ImageIcon> getSchwarz() {
		return schwarz;
	}

	public void setSchwarz(ArrayList<ImageIcon> schwarz) {
		this.schwarz = schwarz;
	}
	/**
	 * prueft ob die Figuren gleich sind
	 * @param i1
	 * @param i2
	 * @return gibt den Wahrheitswert zurueck
	 */
	public boolean figurGleich(ImageIcon i1, Icon i2){
		
		
		for(ImageIcon b:weiss){
			if(b==i1){
				for(Icon c:weiss){
					if(c==i2){
						return true;
					}
				}
			}
		}
		
		for(ImageIcon b:schwarz){
			if(b==i1){
				for(Icon c:schwarz){
					if(c==i2){
						return true;
					}
				}
			}
		}
		
		
		
		return false;
	}
	
	
	public void laufText() {
		String []parts=text.getText().split("-");
		String pos=parts[0];
		String ziel=parts[1];
		Spielfeld aktFeld = spiel.gebeFeld(pos);
		Spielfeld zielFeld = spiel.gebeFeld(ziel);
		Spielfigur fig=aktFeld.getFigur();
		
		
		if(fig!=null){
			if(spiel.farbePlayer().startsWith("W")){
				if(spiel.getSpielerAmZug().getFarbe()==fig.getFarbe()){
					ImageIcon figur=(ImageIcon)this.farbeIcon(spiel.farbePlayer()).get(this.figurStringWeiss(fig.getId()));
					spiel.laufen(pos,ziel);
					
					if(spiel.istFigurDrin(fig.getId())==false){
							this.getFelder().get(this.feld(pos)).setIcon(null);
							parts=null;
							
					}else if(zielFeld.getFigur()!=null&& zielFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
						parts=null;
					}else{
						this.getFelder().get(this.feld(pos)).setIcon(null);
						this.getFelder().get(this.feld(ziel)).setIcon(null);
						this.getFelder().get(this.feld(spiel.gibFeld(fig.getId()).getId())).setIcon(figur);
						parts=null;
					}	
				}else{
					System.out.println("Du bist nicht dran !");
				}
				
			}else if(spiel.farbePlayer().startsWith("S")){
				if(spiel.getSpielerAmZug().getFarbe()==fig.getFarbe()){
					ImageIcon figur=(ImageIcon)this.farbeIcon(spiel.farbePlayer()).get(this.figurStringSchwarz(fig.getId()));
					spiel.laufen(pos,ziel);
					
					// die abfrage is da wenn die figur gepustet wird
					if(spiel.istFigurDrin(fig.getId())==false){
						this.getFelder().get(this.feld(pos)).setIcon(null);
						parts=null;
												
					}else 
						// falls die figur die eigene figur schlagen will
						if(zielFeld.getFigur()!=null&& zielFeld.getFigur().getFarbe()==spiel.getSpielerAmZug().getFarbe()){
						parts=null;
						
					}else{
						// normal laufen und schlagen
						this.getFelder().get(this.feld(pos)).setIcon(null);
						this.getFelder().get(this.feld(ziel)).setIcon(null);
						this.getFelder().get(this.feld(spiel.gibFeld(fig.getId()).getId())).setIcon(figur);
						parts=null;
					}
				}else{
					System.out.println("Du bist nicht dran !");
				}
			}
			
		}else{
			System.out.println("Da ist keine Figur drauf !");
		}
	}
	
	
	
	
	
	
	
	
	

	/**
	 * Die Methode laufen fuer die Icons anstatt der Figur vom Backend
	 * @param e
	 */
	public void lauf(ActionEvent e) {
		ImageIcon figur = null;
		ImageIcon figur2=null;
		JButton feld = (JButton) e.getSource();
		
		for (JButton button : this.getFelder()) {
			if (button==feld) {
				
				if (button.getIcon() != null) {
					this.getWert()[0]=this.farbeIcon(spiel.farbePlayer()).indexOf(button.getIcon());
					System.out.println("WERT: "+this.getWert()[0]);
				}
				

				
				if(posZielButton.size()<2){
					posZiel.add(button.getText());
					posZielButton.add(feld);
//					System.out.println("SIZE---->"+posZiel.size());
//					System.out.println(posZiel);
					if(posZielButton.size()==2){	
						if(posZielButton.get(0).getIcon()!=null&&posZielButton.get(1).getIcon()==null){
							System.out.println("ein icon");
							
							System.out.println("POSITION: "+posZiel.get(0));
							System.out.println("ZIEL: "+posZiel.get(1));
							System.out.println("FIGUR: "+this.farbeFigur(spiel.farbePlayer())+this.zahlFigur2(this.getWert()[0]));
//-----------------------weiss button --------------------------------							
							if(spiel.farbePlayer().startsWith("W")){
							System.out.println(spiel.farbePlayer());
							figur=(ImageIcon)this.farbeIcon(spiel.farbePlayer()).get(this.getWert()[0]);
//							spiel.laufen(posZiel.get(0),posZiel.get(1),this.farbeFigur(spiel.farbePlayer())+this.zahlFigur2(this.getWert()[0]));
							System.out.println("ZAHL: "+this.getFelder().indexOf(button));
							this.getFelder().get(this.feld(posZiel.get(0))).setIcon(null);
							this.getFelder().get(this.feld(posZiel.get(1))).setIcon(figur);
							posZielButton.clear();
							posZiel.clear();
							
// ---------------------------------schwarz button------------------------							
							}else if(spiel.farbePlayer().startsWith("S")){
							//zum testen fuer den schwarzen spieler
							System.out.println(spiel.farbePlayer());
							figur2=(ImageIcon)this.farbeIcon(spiel.farbePlayer()).get(this.getWert()[0]);
//							spiel.laufen(posZiel.get(0),posZiel.get(1),this.farbeFigur(spiel.farbePlayer())+this.zahlFigur(this.getWert()[0]));
							System.out.println("ZAHL: "+this.getFelder().indexOf(button));
							this.getFelder().get(this.feld(posZiel.get(0))).setIcon(null);
							this.getFelder().get(this.feld(posZiel.get(1))).setIcon(figur2);
							posZielButton.clear();
							posZiel.clear();
							
							}
						}
						else if(posZielButton.get(0).getIcon()!=null&&posZielButton.get(1).getIcon()!=null){
							System.out.println("beide icon");
							posZielButton.remove(1);
							posZiel.remove(1);
						}
						else{
							System.out.println("ohne icon");
							posZielButton.clear();
							posZiel.clear();
						}
						
						
						
					}
					
					
				}	
			}
		}
	}
	
	/**
	 * Methode Feld gibt uns fuer jeden String einen int Wert zurueck
	 * @param ziel
	 * @return a , einen int Wert
	 */
	public int feld(String ziel){
		int a=0;
		switch(ziel){
			case "A1":
				return a=0;
			case "A3":
				return a=1;
			case "A5":
				return a=2;
			case "A7":
				return a=3;
			case "A9":
				return a=4;
			case "A11":
				return a=5;
			case "B2":
				return a=6;
			case "B4":
				return a=7;
			case "B6":
				return a=8;
			case "B8":
				return a=9;
			case "B10":
				return a=10;
			case "B12":
				return a=11;
			case "C1":
				return a=12;
			case "C3":
				return a=13;
			case "C5":
				return a=14;
			case "C7":
				return a=15;
			case "C9":
				return a=16;
			case "C11":
				return a=17;
			case "D2":
				return a=18;
			case "D4":
				return a=19;
			case "D6":
				return a=20;
			case "D8":
				return a=21;
			case "D10":
				return a=22;
			case "D12":
				return a=23;
			case "E1":
				return a=24;
			case "E3":
				return a=25;
			case "E5":
				return a=26;
			case "E7":
				return a=27;
			case "E9":
				return a=28;
			case "E11":
				return a=29;
			case "F2":
				return a=30;
			case "F4":
				return a=31;
			case "F6":
				return a=32;
			case "F8":
				return a=33;
			case "F10":
				return a=34;
			case "F12":
				return a=35;
			case "G1":
				return a=36;
			case "G3":
				return a=37;
			case "G5":
				return a=38;
			case "G7":
				return a=39;
			case "G9":
				return a=40;
			case "G11":
				return a=41;
			case "H2":
				return a=42;
			case "H4":
				return a=43;
			case "H6":
				return a=44;
			case "H8":
				return a=45;
			case "H10":
				return a=46;
			case "H12":
				return a=47;
			case "I1":
				return a=48;
			case "I3":
				return a=49;
			case "I5":
				return a=50;
			case "I7":
				return a=51;
			case "I9":
				return a=52;
			case "I11":
				return a=53;
			case "J2":
				return a=54;
			case "J4":
				return a=55;
			case "J6":
				return a=56;
			case "J8":
				return a=57;
			case "J10":
				return a=58;
			case "J12":
				return a=59;
			case "K1":
				return a=60;
			case "K3":
				return a=61;
			case "K5":
				return a=61;
			case "K7":
				return a=62;
			case "K9":
				return a=63;
			case "K11":
				return a=64;
			case "L2":
				return a=65;
			case "L4":
				return a=66;
			case "L6":
				return a=67;
			case "L8":
				return a=68;
			case "L10":
				return a=69;
			case "L12":
				return a=70;
		}
		return a;
		
	}

	
	/**
	 * Methode zahlFigur gibt der FigurenID einen String 
	 * Das ist jetzt fuer die schwarzen Figuren
	 * @param zahl
	 * @return a , einen String
	 */
	public String zahlFigur(int zahl){
		String a=null;
		switch(zahl){
			case 0:
				return a="1";
			case 1:
				return a="2";
			case 2:
				return a="3";
			case 3:
				return a="4";
			case 4:
				return a="5";
			case 5:
				return a="6";
			case 6:
				return a="7";
			case 7:
				return a="8";
			case 8:
				return a="9";
			case 9:
				return a="10";
			case 10:
				return a="11";
			case 11:
				return a="12";
			case 12:
				return a="13";
			case 13:
				return a="14";
			case 14:
				return a="15";
			case 15:
				return a="16";
			case 16:
				return a="17";
			case 17:
				return a="18";
			case 18:
				return a="19";
			case 19:
				return a="20";
			case 20:
				return a="21";
			case 21:
				return a="22";
			case 22:
				return a="23";
			case 23:
				return a="24";
			case 24:
				return a="25";
			case 25:
				return a="26";
			case 26:
				return a="27";
			case 27:
				return a="28";
			case 28:
				return a="29";
			case 29:
				return a="30";			
			
		}
		return a;
	}
	
	/**
	 * Methode zahlFigur gibt der FigurenID einen String 
	 * Das ist jetzt fuer die weissen Figuren
	 * @param zahl
	 * @return a , einen String
	 */
	public String zahlFigur2(int zahl){
		String a=null;
		switch(zahl){
			case 0:
				return a="30";
			case 1:
				return a="29";
			case 2:
				return a="28";
			case 3:
				return a="27";
			case 4:
				return a="26";
			case 5:
				return a="25";
			case 6:
				return a="24";
			case 7:
				return a="23";
			case 8:
				return a="22";
			case 9:
				return a="21";
			case 10:
				return a="20";
			case 11:
				return a="19";
			case 12:
				return a="18";
			case 13:
				return a="17";
			case 14:
				return a="16";
			case 15:
				return a="15";
			case 16:
				return a="14";
			case 17:
				return a="13";
			case 18:
				return a="12";
			case 19:
				return a="11";
			case 20:
				return a="10";
			case 21:
				return a="9";
			case 22:
				return a="8";
			case 23:
				return a="7";
			case 24:
				return a="6";
			case 25:
				return a="5";
			case 26:
				return a="4";
			case 27:
				return a="3";
			case 28:
				return a="2";
			case 29:
				return a="1";			
			
		}
		return a;
	}
	
	public String farbeFigur(String farbe){
		String l=null;
		switch(farbe){
		case "Weiss":
			l="white ";
			return l;
		case "Schwarz":
			l="black ";
			return l;
		}
		return l;	
	}
	
	/**
	 * prueft ob der wert enthalten ist
	 * @param liste 
	 * @param e
	 * @return gibt den Wahrheitswert zurueck
	 */
	public boolean istSourceDrin(ArrayList<JButton> liste,ActionEvent e){
		JButton feld = (JButton)e.getSource();
		for(JButton b:liste){
			if(b==feld){
				return true;
			}
		}
		return false;
	}
	/**
	 * die Methode gibt die Farbe zurueck die man gewaehlt hat
	 * @param farbe
	 * @return
	 */
	public ArrayList<ImageIcon>farbeIcon(String farbe){
		ArrayList<ImageIcon>l=null;
		switch(farbe){
		case "Weiss":
			l=weiss;
			return l;
		case "Schwarz":
			l=schwarz;
			return l;
		}
		
		return l;
		
	}
	
	public int[] getWert() {
		return wert;
	}
	
	
	
	/**
	 * Methode zahlFigur gibt der FigurenID einen String 
	 * Das ist jetzt fuer die schwarzen Figuren
	 * @param zahl
	 * @return a , einen String
	 */
	public int figurStringWeiss(String zahl){
		int a=0;
		switch(zahl){
			case "white 30":
				return a=0;
			case "white 29":
				return a=1;
			case "white 28":
				return a=2;
			case "white 27":
				return a=3;
			case "white 26":
				return a=4;
			case "white 25":
				return a=5;
			case "white 24":
				return a=6;
			case "white 23":
				return a=7;
			case "white 22":
				return a=8;
			case "white 21":
				return a=9;
			case "white 20":
				return a=10;
			case "white 19":
				return a=11;
			case "white 18":
				return a=12;
			case "white 17":
				return a=13;
			case "white 16":
				return a=14;
			case "white 15":
				return a=15;
			case "white 14":
				return a=16;
			case "white 13":
				return a=17;
			case "white 12":
				return a=18;
			case "white 11":
				return a=19;
			case "white 10":
				return a=20;
			case "white 9":
				return a=21;
			case "white 8":
				return a=22;
			case "white 7":
				return a=23;
			case "white 6":
				return a=24;
			case "white 5":
				return a=25;
			case "white 4":
				return a=26;
			case "white 3":
				return a=27;
			case "white 2":
				return a=28;
			case "white 1":
				return a=29;			
			
		}
		return a;
	}
	
	public int figurStringSchwarz(String zahl){
		int a=0;
		switch(zahl){
			case "black 30":
				return a=29;
			case "black 29":
				return a=28;
			case "black 28":
				return a=27;
			case "black 27":
				return a=26;
			case "black 26":
				return a=25;
			case "black 25":
				return a=24;
			case "black 24":
				return a=23;
			case "black 23":
				return a=22;
			case "black 22":
				return a=21;
			case "black 21":
				return a=20;
			case "black 20":
				return a=19;
			case "black 19":
				return a=18;
			case "black 18":
				return a=17;
			case "black 17":
				return a=16;
			case "black 16":
				return a=15;
			case "black 15":
				return a=14;
			case "black 14":
				return a=13;
			case "black 13":
				return a=12;
			case "black 12":
				return a=11;
			case "black 11":
				return a=10;
			case "black 10":
				return a=9;
			case "black 9":
				return a=8;
			case "black 8":
				return a=7;
			case "black 7":
				return a=6;
			case "black 6":
				return a=5;
			case "black 5":
				return a=4;
			case "black 4":
				return a=3;
			case "black 3":
				return a=2;
			case "black 2":
				return a=1;
			case "black 1":
				return a=0;			
			
		}
		return a;
	}

}












