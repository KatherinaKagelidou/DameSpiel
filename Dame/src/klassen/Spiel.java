package klassen;

public class Spiel implements iBediener {

	private static int spielerAnz = 0;
	private final static int spielerMax = 2;
	private static Spieler[] spieler = new Spieler[spielerMax];
	private static int sCounter = 0;
	private Spielfigur spielfigur;
	private Spielbrett spielbrett;
	private Spielfeld spielfeld;

	public Spiel() {

//		spielbrett= new Spielbrett();
	}

	/**
	 * 
	 * Methode addSpieler()
	 * 
	 * Fuegt die jeweiligen Spieler ins Spiel hinzu.
	 * Der erste Spieler waehlt eine Farbei, wobei 
	 * der zweite Spieler die Farbe automatisch bekommt
	 * die der erste Spieler nicht ausgewaehlt hat
	 * 
	 * @param name
	 *            Name des Spielers.
	 * @param farbe
	 *            Farbe des Spielers
	 */

@Override
	public void addSpieler(String name, FarbEnum farbe, KI ki) {
		
		
	
		if (spielerAnz == spielerMax || sCounter == spielerMax) {
			throw new RuntimeException(
					"Maximale Spieleranzahl bereits erreicht");

		} else {
			spieler[sCounter] = new Spieler(name, farbe, ki);
			
			for (int i = 0; i < sCounter; i++) {
				
				if (spieler[0].getFarbe().equals(spieler[1].getFarbe())) {
					
					
					if(spieler[0].getFarbe().equals(FarbEnum.SCHWARZ)){
						
						spieler[1].setFarbe(FarbEnum.WEISS);
						
					}
					if(spieler[0].getFarbe().equals(FarbEnum.WEISS)){
						
						spieler[1].setFarbe(FarbEnum.SCHWARZ);
						
					}
				
				}
					
					
					System.out.println("waehle eine andere farbe");
				 }
			}
		sCounter++;
		}
		
		
	
		
		
	




	/**
	 * 
	 * Methode starteSpiel()
	 * 
	 * Spielbrett wird erstellt und
	 * Startet das Spiel und laesst den Spieler mit den weissen Spielfiguren
	 * anfangen. 
	 */
	@Override
	public void starteSpiel() {
		
		System.out.println("--- Das Spiel beginnt! ---");
		System.out.println(" ");

		System.out.println(this.toString());
		System.out.println("");
		System.out.println("---Das Spielbrett wird erstellt---");
		System.out.println("");
		spielbrett = new Spielbrett();
		System.out.println("");
		for (int i = 0; i < spieler.length; i++) {
			if (spieler[i].getFarbe() == FarbEnum.WEISS) {
			
				System.out.println( spieler[i].getName()
						+ " du musst anfangen :-)");
				break;
			}
		}
		
	}

	
//	/**
//	 * 
//	 * Methode FigurAnSpieler()
//	 * 
//	 * Uebergibt den Spielern ihre Figuren und befuellt gleichzeitig deren Homefelder.
//	 * @param spieler Ein Objekt der Klasse Spieler
//	 */
//	@Override
//	public void FigurAnSpieler(Spieler spieler){
//		
//		for(int i = 0; i<4;i++){
//			spielfigur = new Spielfigur(spieler.getFarbe(), spieler);
//			spieler.figurHinzufuegen(spielfigur);}
//			
//			switch (spieler.getFarbe()){				
//
//			case WEISS:
//				int j=41;
//				do{
//					int r = 0;
//					spielfigur.setSpielfeld(spielbrett.getSpielbrett[][]);
//					spielfigur.setPos(j);
//					j++;
//					r++;
//				}while(j<45);
//					break;
//			case SCHWARZ:
//				int k=49;
//				do{
//					int b = 0;
//					spielfigur.setSpielfeld(sb.getHomeblau().get(b));
//					spielfigur.setPos(k);
//					k++;
//					b++;
//				}while(k<53);
//				  break;
//			
//		}
//
//		
//	}
//	
	
	@Override
	public void setzeFigurAufPos(Spielfigur sf , String ID){
		
		String getID = ID;
		spielfeld.setId(getID);
	}

//	@Override
//	public String toString() {
//		String s = "";
//		s += "Es sind folgende Spieler im Spiel: \n";
//		for (int i = 0; i < spieler.length; i++) {
//			s += spieler[i].getName() + " "
//					+ "Farbe "
//					+ spieler[i].getFarbe() + " | ";
//		}
//		return s;
//	}
	@Override
	public String toString(){
		return "";
	}
}