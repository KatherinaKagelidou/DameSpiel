package klassen;
/**
 * Die Klasse Spieler
 * @author B2
 *
 */
public class Spieler {
	private String name;
	private Spielfigur figur;
	private FarbEnum farbe;
	
	/**
	 * Der Konstruktor der Klasse Spieler
	 * @param name ist der Name der Spielers
	 * @param farbe für die spielfiguren
	 */
	public Spieler(String name, FarbEnum farbe){
		this.setFarbe(farbe);
		this.setFigur(figur);
		this.setName(name);
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
		this.farbe = farbe;
	}
	
	/**
	 * toString-Methode mit der Ausgabe der Attribute Name, Farbe und Figur
	 */
	@Override 
	public String toString(){
		return
				("Name: " + getName() +
				"Farbe: " + getFarbe() +
				"Figur: " + getFigur());
				
	}
	
	

}
