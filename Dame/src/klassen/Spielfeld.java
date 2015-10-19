package klassen;
/**
 * Klasse Spielfeld
 * @author B2
 *
 */
public class Spielfeld {
	private String id;
	private Spielfigur figur;
	private boolean hatSpielfigur = false;
	private Spielbrett spielbrett;
	private Spielfeld spielfeld;
	private FarbEnum farbeFeld;
	
	/**
	 * Konstruktor der Klasse Spielfeld
	 * @param id ist das Feld auf dem sich die einzelnen Spielfiguren befinden
	 */
	public Spielfeld(String id, FarbEnum farbeFeld){
		setId(id);
		setFarbeFeld(farbeFeld);
		//this.setFigur(figur);
	}

	//public Spielfeld(Spielbrett spielbrett) {
	//	this.spielbrett=spielbrett;
	//}

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
	
	/**
	 * Setter fuer die Ueberpruefung ob auf einem Feld eine Figur ist 
	 * @param figur
	 */
	public void setFigur(Spielfigur figur){
		
		if(this.hatSpielfigur==false){
			this.figur = figur;
			}
		else System.out.println("Es befindet sich eine Figur auf dem Feld, kicken?");
		
	}
	
	public void setFarbeFeld(FarbEnum farbeFeld){
		this.farbeFeld=farbeFeld;
	}
	
	public FarbEnum getFarbeFeld(){
		return farbeFeld;
	}
	
	
	/**
	 * Entfernt die Spielfigur vom Spielfeld
	 * 
	 */
	
	public void removeSpielfigur(){
		if (this.hatSpielfigur == true){
		figur = null;
			System.out.println("Die Figur auf dem " +this +" wurde entfernt!");
			this.hatSpielfigur = false;
		}
			else throw new RuntimeException("Keine Figur auf dem Feld!");		
	}
	
	
	/**
	* Prueft ob Felf belegt ist
	* 
	* @return false Gibt false zurueck, wenn Spielfeld nicht belegt ist.
	* @return true Gibt true zurueck, wenn auf einem Spielfeld bereits eine Figur steht.
	*/
	public boolean istFeldBelegt(){
		if(this.hatSpielfigur==true){
			return true;
			}
		return false;
	}
	
	/**
	 * toString-Methode mit der AUgabe der Attribute Id und Figur
	 */
	@Override
	public String toString(){
		return ("Id : "  + getId() +
				" hat die Farbe " + getFarbeFeld() + 
				" mit der Figur: " + getFigur());
	}
}
