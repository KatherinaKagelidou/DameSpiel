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
	/**
	 * Methode zum setzen des Spielbretts und dessen Beschriftung
	 * 
	 * @param feld
	 */
	public void getSpielbrett(Spielfeld[][] feld) {

		char ch = 'A';
		char ch1 = 'L';
		int x = 1;
		String str=null;



	public Spielbrett() {

			 for (int i = 0; i < feld.length; i++) {

		            for (int  j =0; j <feld[i].length; j++) {
		            	
		            	
		            	
//		            	feld[i][j]= new Spielfeld();

		    
		                
		            System.out.println(feld[i][j]);	
		            	  
		            }}
	/**
	 * Methode zum setzen des Spielbretts und dessen Beschriftung
	 * 
	 * @param feld
	 */
	public void getSpielbrett(Spielfeld[][] feld) {

		char ch = 'A';
		char ch1 = 'L';
		int x = 1;
		String str=null;
	

		for (int i = 0; i <feld.length; i++) {
			for (int j = 0; j <12; j++) {
				if (ch <=ch1) {
					str = "" + ch + x;
					ch++;
					x++;
				}
				
//					if (i<feld.length) {
//
//						i++;
//
//					}
					Spielfeld k = new Spielfeld(str);
					System.out.println(k);
				}	
			
		
		for (int i = 0; i <feld.length; i++) {
			for (int j = 0; j <12; j++) {
				if (ch <=ch1) {
					str = "" + ch + x;
					ch++;
					x++;
				}
				
//					if (i<feld.length) {
//
//						i++;
//
//					}
					Spielfeld k = new Spielf
		feld = new Spielfeld[12][12];
		getSpielbrett(feld);
	}
eld(str);
					System.out.println(k);
				}	
			
 }
		feld = new Spielfeld[12][12];
		getSpielbrett(feld);
	}
		
	public static void main(String[] args) {
		
		Spielbrett s = new Spielbrett();
		
		System.out.println(s);
	}


		
		
		
		
		
		


}
