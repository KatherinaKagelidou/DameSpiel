package klassen;
/**
 * Klasse Spielfigur
 * @author B2
 *
 */
public class Spielfigur {
	
	
	private FarbEnum farbe;
	private int position;
	
	
	/**
	 * Konstruktor
	 * @param farbe der Spielfigur
	 * @param position der Figur
	 */
	
	public Spielfigur(FarbEnum farbe, int position){
		
		this.setFarbe(farbe);
		this.setPosition(position);
		
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
	
	public int getPosition(){
		return position;
	}
	
	/**
	 * Setter mit Ueberpfuefung pb die Position der Spielfigur
	 * größer 0 ist
	 * @param position setzt die position der spielfigur
	 */
	public void setPosition(int position){
		
		if(position<0){
			throw new RuntimeException("Keine gueltige Position auf dem Spielbrett");
		}
		this.position=position;
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








