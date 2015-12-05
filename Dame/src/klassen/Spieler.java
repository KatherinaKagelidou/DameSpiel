package klassen;

import java.util.ArrayList;

/**
 * Die Klasse Spieler
 * @author B2
 *
 */
public class Spieler {
	private String name;
	private Spielfigur figur;
	private FarbEnum farbe;
	private KI ki;
	private static final int spielfigurMax =31;
//	private ArrayList<Spielfigur> spielfiguren = new ArrayList<Spielfigur>();
	private Spielfigur[] figurArray = new Spielfigur[spielfigurMax] ;
	private int spielfigurAnz=0;

	
	
	/**
	 * Der Konstruktor der Klasse Spieler
	 * @param name ist der Name der Spielers
	 * @param farbe für die spielfiguren
	 */
	public Spieler(String name, FarbEnum farbe, KI ki){
		this.setName(name);
		this.setFarbe(farbe);
		this.setKi(ki);
		
	}
	
	
	public Spielfigur[] getFigurArray() {

		return figurArray;
	}


	public void setFigurArray(Spielfigur[] figurArray) {
		this.figurArray = figurArray;
	}


	public void setKi(KI ki){
		this.ki=ki;
		
	
	}
	
	public KI getKi() {
		return ki;
	}
	
	public String getName() {
		return name;
	}
	/**
	 * Setter mit Ueberpruefung ob ein Name existiert oder der Name zu kurz ist
	 * @param name setzt den namen des Spielers
	 */
	public void setName(String name) {
		if((name.length()<2)||(name==null)){
			throw new RuntimeException("Name ist zu kurz");
		}
		this.name = name;
	}
	public Spielfigur getFigur() {
		return figur;
	}
	public void setFigur(Spielfigur figur) {
		this.figur = figur;
	}
	public FarbEnum getFarbe() {
		return farbe;
	}
	public void setFarbe(FarbEnum farbe) {
		if(farbe==null){
			throw new RuntimeException("Waehle eine Farbe aus!!");
		}
		this.farbe = farbe;
	}
	

	/**
	 * Fuegt einem Array spielfigur Spielfiguren hinzu
	 * 	
	 */
	
//	public void figurHinzufuegen(Spielfigur sf){
//		if(spielfigurAnz < spielfigurMax){
//			for(int i=0;i<figurArray.length;i++){
//				sf=figurArray[i];
//				this.setFigur(sf);
//			    spielfigurAnz++;
//			}
//		}
//		else {
//			throw new RuntimeException("figurHinzufuegen: Maximale Anzahl bereits erreicht!");
//	}
		
//		if(spielfigurAnz<spielfigurMax){
//			
//			
//			
//			spielfigur[i].s
//			spielfiguren.add(spielfigur);	
//		}
		
		
//	}


//	public ArrayList<Spielfigur> getSpielfiguren() {
//		return spielfiguren;
//	}


//	public void setSpielfiguren(ArrayList<Spielfigur> spielfiguren) {
//		this.spielfiguren = spielfiguren;
//	}


	/**
	 * Getter
	 * 
	 * @return Gibt die Anzahl der Spielfiguren im Spiefiguren Array zurueck
	 */
	
	public int getAnzahlFiguren() {
		return spielfigurAnz;
	}
	




	/**
	 * toString-Methode mit der Ausgabe der Attribute Name, Farbe und Figur
	 */
	@Override 
	public String toString(){
		return getName() ;

				
				
	}
	
	

}
