package klassen;

public class Spiel implements iBediener {

	private static int spielerAnz = 0;
	private final static int spielerMax = 2;
	private static Spieler[] spieler = new Spieler[spielerMax];
	private static int sCounter = 0;
	private Spielfigur spielfigur;
	private Spielbrett spielbrett;
	private Spielfeld spielfeld;
	private Spieler sp;

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

		if (spielerAnz == spielerMax || sCounter == spielerMax) {
			throw new RuntimeException(
					"Maximale Spieleranzahl bereits erreicht");

		} else {
			spieler[sCounter] = new Spieler(name, farbe, ki);
			
			for(int i=0; i<spieler.length; i++){
				figurZuweisen(spieler[sCounter]);
			}
			 
			
			

			for (int i = 0; i < sCounter; i++) {

				if (spieler[0].getFarbe().equals(spieler[1].getFarbe())) {

					if (spieler[0].getFarbe().equals(FarbEnum.SCHWARZ)) {

						spieler[1].setFarbe(FarbEnum.WEISS);

					}
					if (spieler[0].getFarbe().equals(FarbEnum.WEISS)) {

						spieler[1].setFarbe(FarbEnum.SCHWARZ);

					}

				}

			}
		}
		sCounter++;
	}

	
	public void figurZuweisen(Spieler spi) {

		Spielfigur figurSchwarz = null;
		Spielfigur figurWeiss = null;
		for (int i = 0; i < spielbrett.getFelder().length; i++) {
			for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {

				if (spielbrett.getFelder()[i][j].getFigur().getFarbe().equals(FarbEnum.SCHWARZ)) {
					figurSchwarz = spielbrett.getFelder()[i][j].getFigur();
				} else {
					if (spielbrett.getFelder()[i][j].getFigur().getFarbe().equals(FarbEnum.WEISS)) {
						figurWeiss = spielbrett.getFelder()[i][j].getFigur();
					}
				}

				for(int x=0; x<spieler.length; x++){
					if (spieler[x].getFarbe().equals(FarbEnum.SCHWARZ)) {
						spieler[x].figurHinzufuegen(figurSchwarz);
					} else {
						if (spieler[x].getFarbe().equals(FarbEnum.WEISS)) {
							spieler[x].figurHinzufuegen(figurWeiss);
						}
					}
					
					
				}
				
			}
		}
	}

	/**
	 * 
	 * Methode starteSpiel()
	 * 
	 * Spielbrett wird erstellt und Startet das Spiel und laesst den Spieler mit
	 * den weissen Spielfiguren anfangen.
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

				System.out.println(spieler[i].getName()
						+ " du musst anfangen :-)");
				break;
			}
		}

	}

	@Override
	public void setzeFigurAufPos(Spielfigur sf, String ID) {

		String getID = ID;
		spielfeld.setId(getID);
	}

	@Override
	public String toString() {
		String s = "";
		s += "Es sind folgende Spieler im Spiel: \n";
		s += "\n";
		for (int i = 0; i < spieler.length; i++) {
			s += spieler[i].getName() + " " + " hat die Farbe "
					+ spieler[i].getFarbe() + " | ";
		}
		return s;
	}

}