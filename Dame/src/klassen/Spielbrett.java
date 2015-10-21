

package klassen;

import java.util.Arrays;

/**
 * Klasse SPielbrett
 * 
 * @author B2
 *
 */
public class Spielbrett {

	Spielfeld spielfeld;
	Spielbrett sb;
	

	private Spielfeld[][] feld;
	private boolean schwarz = false;
	
	/**
	 * Konstruktor der Klasse Spielbrett
	 * Methode getSpielbrett wird aufgerufe 
	 * und die 12*12 Spielfelder werden dem Spielbrett uebergeben
	 * 
	 */

	public Spielbrett() {

		feld = new Spielfeld[12][12];
		getSpielbrett(feld);
	}

	/**
	 * Methode getSpielbrett 
	 * Felder werden erstellt mit jeweiliger Beschriftung und 
	 * deren Farbe
	 * @param feld
	 */
	public void getSpielbrett(Spielfeld[][] feld) {

		char ch = 'L';
		char ch1 = 'A';
		int x = 1;
		String str = null;

		for (int i = 0; i < feld.length; i++) {

			for (int j = 0; j < feld[i].length; j++) {
				str = "" + ch + x;
				x++;

				Spielfeld spielfeld = new Spielfeld(sb, str, schwarz);
				// spielfeld.setFarbeFeld(FarbEnum.SCHWARZ);
				System.out.print(spielfeld);

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
				
			}
			
		}
		
	}

	/**
	 * Getter fuer die Farbe
	 * @return schwarz
	 */
	public boolean getFarbe() {
		return schwarz;

	}
	
	

	@Override
	public String toString() {
		return "";
	}

	public static void main(String[] args) {

		Spielbrett s = new Spielbrett();

		System.out.println(s);
	}

}
