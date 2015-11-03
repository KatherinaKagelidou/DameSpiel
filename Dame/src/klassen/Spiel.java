package klassen;

import java.util.ArrayList;

public class Spiel implements iBediener {

	private int spielerAnz = 0;
	private final static int spielerMax = 2;
	private Spieler[] Spieler= new Spieler[spielerMax] ;
	// private Spieler sp1;
	// private Spieler sp2;
	private static int sCounter = 0;
	// private Spielfigur spielfigur;
	private Spielbrett spielbrett;
	// private Spielfeld spielfeld;
	private Spieler sp;//greift nicht auf die Spielerklasse zu
//	private int spielerAmZug;
	private boolean istDame = false;
	private Spieler spielerAmZug;
	private Spielfigur f;
	private Spielfigur [] figurWeiss = new Spielfigur[30];
	private Spielfigur [] figurSchwarz= new Spielfigur[30];
	private ArrayList<Spielfigur> figurenListe = new ArrayList<Spielfigur>();

	private  int counter = 0;

	public Spiel() {

	}

	/**
	 * 
	 * Methode addSpieler()
	 * 
	 * Fuegt die jeweiligen Spieler ins Spiel hinzu. Der erste Spieler waehlt
	 * eine Farbei, wobei der zweite Spieler die Farbe automatisch bekommt die
	 * der erste Spieler nicht ausgewaehlt hat
	 * 
	 * @param name
	 *            Name des Spielers.
	 * @param farbe
	 *            Farbe des Spielers
	 */

	@Override
	public void addSpieler(String name, FarbEnum farbe, KI ki) {
	
		Spieler sp = new Spieler(name, farbe, ki);
	
	
	
	
	
		if (spielerAnz==2) {
			
			System.out.println("Es kann kein Spieler mehr hinzugefügt werden");
			return;
		}
		
			// wenn spieler keine farbe gewählt hat
			if (sp.getFarbe() == null) {

				System.out.println("Bitte wähle eine Farbe aus du Schmock!!");
				return;

			}
			if(spielerAnz==1) {
				// wenn spieler die gleiche farbe gewählt hat
			//	for (int i=0;i<Spieler.length;i++) {
				
//						if (Spieler[i].getFarbe().equals(sp.getFarbe())) {
//			
//							System.out.println(sp);
//							System.out.println(Spieler[0]);
				
//							System.out
//									.println(sp.getName()
//											+ "Die Farbe"
//											+ sp.getFarbe()
//											+ "wurde leider schon gewählt! Bitte wähle eine andere!");
//							
//						} else {
							if (sp.getFarbe().equals(FarbEnum.SCHWARZ)) {
								this.Spieler[1]=sp;
								spielerAnz++;
							} else if (sp.getFarbe().equals(FarbEnum.WEISS)) {
								this.Spieler[0]=sp; 
								spielerAnz++;
							}
							System.out.println("Spieler " + sp + " wurde hinzugefügt " + sp.getFarbe());
						}
			if (spielerAnz==0) {
				if (sp.getFarbe().equals(FarbEnum.WEISS)) {
					this.Spieler[0]=sp;
					spielerAnz++;
				} else if (sp.getFarbe().equals(FarbEnum.SCHWARZ)) {
					this.Spieler[1]=sp;
					spielerAnz++;
				}
				
				System.out.println("Spieler " + sp + " wurde hinzugefügt " + sp.getFarbe());
			}
		}

	

	

	private int getSpielerAnz() {
		return spielerAnz;
	}
	
	@Override
	public void figurSetzen() {

		SpielerAmZug(Spieler[0]);
		
		char ch = 'L';
		char ch1 = 'A';
		int x = 1;
		 String str = null;
		 boolean schwarz = false;
			int count = 0;

		for (int i = 0; i < spielbrett.getFelder().length; i++) {

			for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {
				str = "" + ch + x;
				x++;

//				felder[i][j].setFarbeFeld(FarbEnum.SCHWARZ);
			
		
//				spielbrett.getFelder()[i][j]=new Spielfeld(spielbrett, str, schwarz);
				
			System.out.println(spielbrett.getFelder()[i][j].toString());
				schwarz = !schwarz;

				if (x > 12) {
					x = 1;
					ch--;
					if (ch == ch1) {
						str = "" + ch1 + x;
						schwarz = !schwarz;

						break;

					}
					schwarz = !schwarz;

				}
//				if (this.spielbrett.getFelder()[i][j].getFarbeFeld().equals(FarbEnum.WEISS)) {
					

//					if (spielFigur.getFarbe().equals(FarbEnum.SCHWARZ)) {
						if (this.spielbrett.getFelder()[i][j].getId()
								.startsWith("A")
								|| this.spielbrett.getFelder()[i][j].getId()
										.startsWith("B")
								|| this.spielbrett.getFelder()[i][j].getId()
										.startsWith("C")
								|| this.spielbrett.getFelder()[i][j].getId()
										.startsWith("D")
								|| this.spielbrett.getFelder()[i][j].getId()
										.startsWith("E")) {
							if(this.spielbrett.getFelder()[i][j].getFarbeFeld().equals(FarbEnum.SCHWARZ)){
//							this.spielbrett.getFelder()[i][j].setFigur(spielFigur);
//							this.Spieler[0].getFigurArray()[i].setSpielfeld(this.spielbrett.getFelder()[i][j]);
//							spielbrett.getFelder()[i][j].setFigur(this.Spieler[1].getFigurArray()[v]);
//							v++;
							f = new Spielfigur(spielbrett.getFeld(str), FarbEnum.SCHWARZ);
							figurSchwarz[count] = f;
							count++;
							
							System.out.println(f);

							}
					} else {
//						if (spielFigur.getFarbe().equals(FarbEnum.WEISS)) {
							if (spielbrett.getFelder()[i][j].getId()
									.startsWith("H")
									|| spielbrett.getFelder()[i][j].getId()
											.startsWith("I")
									|| spielbrett.getFelder()[i][j].getId()
											.startsWith("J")
									|| spielbrett.getFelder()[i][j].getId()
											.startsWith("K")
									|| spielbrett.getFelder()[i][j].getId()
											.startsWith("L")) {
								if(this.spielbrett.getFelder()[i][j].getFarbeFeld().equals(FarbEnum.SCHWARZ)){

//								this.spielbrett.getFelder()[i][j].setFigur(spielFigur);
//								this.Spieler[0].getFigurArray()[i].setSpielfeld(this.spielbrett.getFelder()[i][j]);
//								spielbrett.getFelder()[i][j].setFigur(this.Spieler[0].getFigurArray()[v]);
								f = new Spielfigur(spielbrett.getFeld(str), FarbEnum.WEISS);
								figurWeiss[count] = f;
								count++;
								
								System.out.println(f);
								}
							}
						}

					}// else schließen

				}// 1. if
				
			}
			
//		}
		
		
//		int v=0;
//		for (int i = 0; i < this.spielbrett.getFelder().length; i++) {
//			
//			for (int j = 0; j < this.spielbrett.getFelder()[i].length; j++) {
//				
//				
//				
//			}// for 2.
//		}// for 1.
	
	
	public void SpielerAmZug(Spieler spieler) {
		if (spieler != null) {
			this.spielerAmZug = spieler;
		}
	}


	// letzte

//	public Spielfigur bekommeFigur() {
//		Spielfigur sf = null;
//
//		for (int f = 0; f < this.Spieler[0].getFigurArray().length; f++) {
//			sf = this.Spieler[0].getFigurArray()[f];
//
//		}
//		for (int k = 0; k < this.Spieler[1].getFigurArray().length; k++) {
//			sf = this.Spieler[1].getFigurArray()[k];
//
//		}
//
//		return sf;
//
//	}
	
	//ist in Bearbeitung! :)
	public Spielfigur bekommeFigur() {
		Spielfigur sfSchwarz = null;
		Spielfigur sfWeiss = null;
		Spielfigur figur=null;
		
		for(int i=0;i<Spieler.length;i++){
		if(this.Spieler[i].getFarbe().equals(FarbEnum.WEISS)){
		for (int f = 0; f < this.Spieler[i].getFigurArray().length; f++) {
			sfWeiss = this.Spieler[i].getFigurArray()[f];
			}
		}else if(this.Spieler[i].getFarbe().equals(FarbEnum.SCHWARZ)){
			for (int k = 0; k < this.Spieler[i].getFigurArray().length; k++) {
				sfSchwarz = this.Spieler[i].getFigurArray()[k];
			}
		}
		}
		if(sfSchwarz.equals(null)){
			figur=sfWeiss;
		}else if(sfWeiss.equals(null)){
			figur=sfSchwarz;
		}
		return figur;
	}

	/**
	 * 
	 * Methode starteSpiel()
	 * 
	 * Spielbrett wird erstellt und Startet das Spiel und laesst den Spieler mit
	 * den weissen Spielfiguren anfangen.
	 */
	@Override
	public void starteSpiel(int spielerAnz) {

		System.out.println("--- Das Spiel beginnt! ---");
		System.out.println(" ");

		System.out.println(this.toString());
		System.out.println("");
		System.out.println("---Das Spielbrett wird erstellt---");
		System.out.println("");

		if (spielerAnz < 3 && spielerAnz >= 1) {

			spielbrett = new Spielbrett();
			this.setSpielerAnz(spielerAnz);
			this.figurSetzen();
//			Spieler[0]=null;
//			Spieler[1]=null;
			System.out.println("");
			for (Spieler s : Spieler) {
				if (s.getFarbe().equals(FarbEnum.WEISS)) {
					System.out.println(s.getName() + " du musst anfangen :-)");
					break;
				}
			}

		}
	}

	private void setSpielerAnz(int spielerAnz) {
		this.spielerAnz = spielerAnz;

	}

	// @Override
	// public void setzeFigurAufPos(Spielfigur sf, String ID) {
	//
	// String getID = ID;
	// spielfeld.setId(getID);
	// }

	@Override
	public String toString() {
		String s = "";
		s += "Es sind folgende Spieler im Spiel: \n";
		s += "\n";
		for (Spieler spieler : Spieler) {
			s += spieler.toString() + " | ";
		}
		return s;
	}

	@Override
	public void werdeDame(Spielfigur figur) {

		if (figur.getId().contains("W")) {
			if (figur.getPosition().contains("A")) {

				istDame = true;
			}

		}
		if (figur.getId().contains("S")) {
			if (figur.getPosition().contains("L")) {
				istDame = true;

			}

		}

	}

	@Override
	public void umwandeln(String id) {

		// String s=new String("A1");
		// String ss=new String("A1");

		// for(int i = 0; i<sb.getFeld().length;i++){
		String s = new String(id);

		char Buchstabe = s.charAt(0);
		char Zahl = s.charAt(1);
		System.out.println(Buchstabe);
		System.out.println(Zahl);
		// String[] bla = s.split(""+Buchstabe);
		// String Zahl = bla[0];

		// }

	}

	// char Buchstabe = s.charAt(0);
	// char Zahl = ss.charAt(1);

	// System.out.println(Buchstabe);
	// System.out.println(Zahl);
	@Override
	public void laufen(String altePos, String zielPos) {

		altePos = spielbrett.getSpielfeld().getId();

		System.out.println();

	}

}