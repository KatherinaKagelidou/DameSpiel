package klassen;
/**
 * Klasse Spielfigur
 * @author B2
 *
 */
public class Spielfigur {
	
	
	private FarbEnum farbe;
	private String position;
	private Spieler spieler;
	private Spielfeld spielfeld;
	
	
	
	/**
	 * Konstruktor
	 * @param farbe der Spielfigur
	 * @param position der Figur
	 */
	
	public Spielfigur(FarbEnum farbe){
		
		this.setFarbe(farbe);
		this.setSpieler(spieler);
		
	}
	
	public void setSpielfeld(Spielfeld spielfeld){
		if (spielfeld==null){
			throw new RuntimeException(" Falsche Eingabe");
		}
		else{
			this.spielfeld=spielfeld;
		}
	}
	
	public FarbEnum getFarbe(){
		return farbe;
		
	}
	
	/**
	 * Setter mit der Ueberpruefung ob eine Farbe gesetzt wurde. Wenn nicht wird
	 * eine Fehlermeldung ausgeschmissen
	 * @param farbe setzt die Farbe der SPielfigur, zur Auswahl stehen schwarz und weiss
	 */
	public void setFarbe(FarbEnum farbe){
		if(farbe==null){
			throw new RuntimeException("keine gueltige Farbe");
		}
		
		
		this.farbe=farbe;
	}
	
	public String getPosition(){
		return position;
	}
	
	public void setSpieler(Spieler spieler){
		this.spieler=spieler;
	}
	
	public Spieler getSpieler(){
		return spieler;
	}
	
	public Spielfeld getSpielfeld(){
		return spielfeld;
	}
	
	/**
	 * Setter mit Ueberpfuefung pb die Position der Spielfigur
	 * größer 0 ist
	 * @param position setzt die position der spielfigur
	 */
	public void setPosition(String pos) {	
		if(spielfeld.istFeldBelegt()==true)
			System.out.println("Es befindet sich eine Figur auf diesem Spielfeld. Schlagen?");
		else{
			this.position= pos;
			this.setSpielfeld(spielfeld);
			spielfeld.setSpielfigur(this);
			System.out.println(""+this +" wurde auf Position: " + pos + " gelegt.");
			}
	}
	
	/**
	 * toString-Methode mit der Ausgabe der Attribute Farbe und ID
	 */
	
	@Override
	public String toString() {
		return 
				("Farbe: " + getFarbe() + 
				" | "+ "ID: "+ getPosition()
				);	
	

}
}








