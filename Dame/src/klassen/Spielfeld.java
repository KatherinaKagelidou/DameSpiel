package klassen;
/**
 * Klasse Spielfeld
 * @author B2
 *
 */
public class Spielfeld {
	private String id;
	private Spielfigur figur;
	
	/**
	 * Konstruktor der Klasse Spielfeld
	 * @param id ist das Feld auf dem sich die einzelnen Spielfiguren befinden
	 */
	public Spielfeld(String id){
		this.setId(id);
		this.setFigur(figur);
	}
	public String getId(){
		return id;
	}
	/**
	 * Setter mit Ueberpruefung ob die Id zwischen 0 und 100 liegt
	 * @param id setzt die id eines Spielfeldes
	 */
	public void setId(String id){
		if(id.length()<2){
			throw new RuntimeException("ungültiges Spielfeld");
		}
		this.id=id;
	}
	public Spielfigur getFigur(){
		return figur;
	}
	public void setFigur(Spielfigur figur){
		this.figur=figur;
	}
	
	/**
	 * toString-Methode mit der AUgabe der Attribute Id und Figur
	 */
	@Override
	public String toString(){
		return ("Id: "+getId()+
				"Figur: "+getFigur());
	}
}
