

package klassen;



/**
 * Klasse SPielbrett
 * 
 * @author B2
 *
 */
public class Spielbrett {

	private Spielfeld spielfeld;
	private Spielbrett sb;
	private Spielfeld[][] felder =new Spielfeld[12][12];;
	private boolean schwarz = false;
	private Spiel spiel;
	private Spieler spieler;
	/**
	 * Konstruktor der Klasse Spielbrett
	 * Methode getSpielbrett wird aufgerufe 
	 * und die 12*12 Spielfelder werden dem Spielbrett uebergeben
	 * 
	 */

	public Spielbrett() {
		getSpielbrett();
		
		
	}
	
	
	
	
	

	/**
	 * Methode getSpielbrett 
	 * Felder werden erstellt mit jeweiliger Beschriftung und 
	 * deren Farbe und den Spielfiguren
	 * @param felder
	 */
	public void getSpielbrett() {

		char ch = 'L';
		char ch1 = 'A';
		int x = 1;
		String str = null;

		for (int i = 0; i < felder.length; i++) {

			for (int j = 0; j < felder[i].length; j++) {
				str = "" + ch + x;
				x++;

				Spielfeld spielfeld = new Spielfeld(this, str, schwarz);
				felder[i][j]=spielfeld;
				spielfeld.setFarbeFeld(FarbEnum.SCHWARZ);
			
		
				
				if (felder[i][j].getId().startsWith("A")
						|| felder[i][j].getId().startsWith("B")
						|| felder[i][j].getId().startsWith("C")
						|| felder[i][j].getId().startsWith("D")
						|| felder[i][j].getId().startsWith("E")) {
					if (felder[i][j].getFarbeFeld() == FarbEnum.SCHWARZ) {
						felder[i][j].setFigur(new Spielfigur(FarbEnum.SCHWARZ));
						
						
					}
				}else{
					if (felder[i][j].getId().startsWith("H")
							|| felder[i][j].getId().startsWith("I")
							|| felder[i][j].getId().startsWith("J")
							|| felder[i][j].getId().startsWith("K")
							|| felder[i][j].getId().startsWith("L")){
						if (felder[i][j].getFarbeFeld() == FarbEnum.SCHWARZ) {
							felder[i][j].setFigur(new Spielfigur(FarbEnum.WEISS));
						}
					}
				}
				
				
		
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
	

	

	public Spielfeld getSpielfeld() {
		return spielfeld;
	}






	public void setSpielfeld(Spielfeld spielfeld) {
		this.spielfeld = spielfeld;
	}






	public Spielbrett getSb() {
		return sb;
	}






	public void setSb(Spielbrett sb) {
		this.sb = sb;
	}






	public Spielfeld[][] getFelder() {
		return felder;
	}






	public void setFelder(Spielfeld[][] felder) {
		this.felder = felder;
	}






	public boolean isSchwarz() {
		return schwarz;
	}






	public void setSchwarz(boolean schwarz) {
		this.schwarz = schwarz;
	}






	/**
	 * Getter fuer die Farbe
	 * @return schwarz
	 */
	public boolean getFarbe() {
		return schwarz;

	}
	
	public Spielbrett getSB(){
		return this;
	}
	
/**
 * toString Methode der Klasse Spielbrett
 */
	@Override
	public String toString() {
		String s = null;
		for(int i=0;i<felder.length;i++){
			for(int j=0;j<felder[i].length;j++){
				
				s = ""+ felder[i][j];
			}
			
		}
		return s;
	

				
	}

	
}
