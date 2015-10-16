package klassen;

import java.awt.AlphaComposite;

/**
 * Klasse SPielbrett
 * 
 * @author B2
 *
 */
public class Spielbrett {


		
		Spielfeld spielfeld;

	boolean feldSchwarz;

	/**
	 * Konstruktor der Klasse Spielbrett
	 */

	private Spielfeld[][] feld;

	public Spielbrett() {

		feld = new Spielfeld[12][12];
		getSpielbrett(feld);
	}
	
	public void getSpielbrett(Spielfeld[][] feld) {

		char ch = 'A';
		char ch1 = 'L';
		int x = 1;
		String str=null;

	

			 for (int i = 0; i <=feld.length; i++) {

		            for (int  j =0; j <=feld[i].length; j++) {
		            	str=""+ch+x;
		            	x++;
		            	if(x>12){
		            		x=1;
		            		ch++;
		            	}
		            	System.out.println(str);
		            }
		            if(ch==ch1){
		            	System.out.println(str=""+ch1+x);
		            	break;
		            }
		      }
			 
			 
			 
			 
			 }
	
		
public static void main(String[] args) {

		Spielbrett s = new Spielbrett();

		System.out.println(s);
	}


}
