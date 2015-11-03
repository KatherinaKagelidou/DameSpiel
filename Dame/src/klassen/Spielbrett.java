

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
	private Spielfeld[][] felder ;
	private boolean schwarz = false;
	private Spiel spiel;
	private Spieler spieler;
	private String str = null;
	/**
	 * Konstruktor der Klasse Spielbrett
	 * Methode getSpielbrett wird aufgerufen
	 * und die 12*12 Spielfelder werden dem Spielbrett uebergeben
	 * 
	 */

	public Spielbrett() {
		felder= new Spielfeld[12][12];
//		for(int i=0;i<12;i++){
//			for(int j=0;j<12;j++){
//			felder[i][j]=new Spielfeld(this, str, schwarz);
//			}
//		}
		feldBelegen();
	}
	


	/**
	 * Methode getSpielbrett 
	 * Felder werden erstellt mit jeweiliger Beschriftung und 
	 * deren Farbe und den Spielfiguren
	 * @param felder
	 */
	public void feldBelegen() {
		char ch = 'L';
		char ch1 = 'A';
		int x = 1;
		

		for (int i = 0; i < felder.length; i++) {

			for (int j = 0; j < felder[i].length; j++) {
				str = "" + ch + x;
				x++;

//				felder[i][j].setFarbeFeld(FarbEnum.SCHWARZ);
			
		
				felder[i][j]=new Spielfeld(this, str, schwarz);
				
			System.out.println(felder[i][j].toString());
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
	
	public Spielfeld getFeld(String id){
		int [] in = stringId(id);
		if(in[0]>64&&in[0]<77){
			return felder[in[0]-65][in[1]-1];
		}
		return null;
	}
	
	public int [] stringId(String id){
		String [] s = new String[3];
		for(int i=0; i<id.length();i++){
			s[i]=Character.toString(id.toCharArray()[i]);
		}
		int [] in = new int[2];
		char ch = s[0].charAt(0);
		in[0]=ch;
		
		if(id.length()==2){
			in[1]=Integer.parseInt(s[1]);
		}
		if(id.length()==3){
			in[1]=Integer.parseInt(s[1]+s[2]);
		}
		if(in[0]>77){
			System.out.println("fehler");
		}
		return in;
	}
	



	
///**
// * toString Methode der Klasse Spielbrett
// */
//	@Override
//	public String toString() {
//		String s = "";
//		for(int i=0;i<felder.length;i++){
//			for(int j=0;j<felder[i].length;j++){
//				
//				s = ""+ felder[i][j];
//			}
//			
//		}
//		return s;
//	
//
//				
//	}

	
}
