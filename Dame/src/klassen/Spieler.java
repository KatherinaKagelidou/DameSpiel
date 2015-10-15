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
	private KI ki;

	
	
	/**
	 * Der Konstruktor der Klasse Spieler
	 * @param name ist der Name der Spielers
	 * @param farbe für die spielfiguren
	 */
	public Spieler(String name, FarbEnum farbe, KI ki){
<<<<<<< HEAD
		this.setName(name);
=======
>>>>>>> branch 'master' of https://github.com/KatherinaKagelidou/DameSpiel.git
		this.setFarbe(farbe);
		this.setFigur(figur);
<<<<<<< HEAD
		this.setKi(ki);
	}
	
	public void setKi(KI ki){
		this.ki=ki;
	
=======
		this.setName(name);
		this.setKi(ki);
>>>>>>> branch 'master' of https://github.com/KatherinaKagelidou/DameSpiel.git
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
	
	public KI getKi() {
		return ki;
	}

<<<<<<< HEAD

=======
	public void setKi(KI ki) {
		this.ki = ki;
	}

	
>>>>>>> branch 'master' of https://github.com/KatherinaKagelidou/DameSpiel.git
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
