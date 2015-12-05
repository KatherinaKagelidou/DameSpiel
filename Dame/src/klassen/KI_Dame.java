package klassen;

import java.io.Serializable;
import java.util.Random;

public class KI_Dame extends KI implements Serializable {

	private static final long serialVersionUID = 1L;

	private Spiel spiel;
	private Spielfigur figur;
	private Spielbrett spielbrett;
	/**
	 * der Konstruktor der Klasse KI
	 * 
	 * @param spieler
	 *            ist die KI
	 */
	public KI_Dame() {

		laufKI();
	}

	public KI_Dame(Spiel spiel) {
		super(spiel);
	}

	//keine ahnung ob es richtig ist
	//laufMethode da als parameter keine randomFigur übergeben werden kann wird auch nichts übergeben als parameter
	//beim ausführen kommt immer eine nullppointer exception bei der ersten for schleife
	// der Fehler  auch in der klasse spiel wenn neue spieler erstellt werden mit der uebegabe der ki
	//iwas wird da falsch gemacht und nicht richtig erkannt 
	   @Override
		public void laufKI() {

			String randomFigur = null;
			String zielPos = null;

			for (int i = 0; i < spielbrett.getFelder().length; i++) {
				for (int j = 0; j < spielbrett.getFelder()[i].length; j++) {

					randomFigur = (spielbrett.getFelder()[i][j].getFigur().getId());
					// Spielfeld feld = spielbrett.getFelder()[i][j];
					
					generateFigurToInt(randomFigur);
				}
			}
			
			Spielfeld aktFeld = spiel.gebeFeld(randomFigur);

			Spielfeld zielFeld = spiel.gebeFeld(zielPos);
			Spielfigur figur = spiel.gebeFigur(aktFeld.getFigur().getId());

			if (spiel.getSpielerAmZug().equals(FarbEnum.WEISS)) {
				if (aktFeld.getFigur().getFarbe().equals(FarbEnum.WEISS)) {

					spielbrett.Umwandler(randomFigur);
					spielbrett.Umwandler(zielPos);
					System.out.println("Random Figur " + randomFigur
							+ "--------------------------------");
				}
			}
		}
       @Override
	   public void generateFigurToInt(String s) {
			Random random = new Random();

			for (int i = 0; i < spiel.getFigurWeiss().length; i++) {

				int n = random.nextInt(i);

				spiel.getFigurWeiss()[n] = figur;

				s = figur.getId();
			}
		}
}