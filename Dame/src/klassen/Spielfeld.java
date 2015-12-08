package klassen;

import java.io.Serializable;

/**
 * Klasse Spielfeld
 * @author B2
 *
 */
public class Spielfeld implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private Spielfigur figur;
	private boolean hatSpielfigur = false;
	private Spielbrett spielbrett;
	private FarbEnum farbeFeld;
	private boolean istSchwarz;
	private int x; 
	private int y;

	/**
	 * Konstruktor der Klasse Spielfeld
	 * 
	 * @param id
	 *            ist das Feld auf dem sich die einzelnen Spielfiguren befinden
	 */
	public Spielfeld(Spielbrett brett, String id, boolean schwarz) {
		setId(id);
		if (schwarz) {
			setFarbeFeld(FarbEnum.SCHWARZ);
		} else {
			setFarbeFeld(FarbEnum.WEISS);
		}
		this.istSchwarz = schwarz;
		this.spielbrett = brett;
	
		// this.setFigur(figur);
	}

	// public Spielfeld(Spielbrett spielbrett) {
	// this.spielbrett=spielbrett;
	// }

	public String getId() {
		return id;
	}

	/**
	 * Setter mit Ueberpruefung ob die Id zwischen 0 und 100 liegt
	 * 
	 * @param id
	 *            setzt die id eines Spielfeldes
	 */
	public void setId(String id) {

		this.id = id;
	}
	

	public Spielfigur getFigur() {
		return figur;
	}

	
	/**
	 * Setter fuer die Ueberpruefung ob auf einem Feld eine Figur ist
	 * 
	 * @param figur
	 */
	public void setFigur(Spielfigur figur) {

				this.figur = figur;

			
	}

	public void setFarbeFeld(FarbEnum farbeFeld) {
		this.farbeFeld = farbeFeld;
	}

	public FarbEnum getFarbeFeld() {

		if (istSchwarz == true) {
			return FarbEnum.SCHWARZ;
		}

		return FarbEnum.WEISS;
	}

	/**
	 * Entfernt die Spielfigur vom Spielfeld
	 * 
	 */

	public void removeSpielfigur(Spielfigur figur) {
		System.out
		.println("Die Figur "+ this.getFigur()+"auf Feld " + this.getId() + " wurde entfernt!");
	
			this.setFigur(null);
			
	}

	/**
	 * Prueft ob Felf belegt ist
	 * 
	 * @return false Gibt false zurueck, wenn Spielfeld nicht belegt ist.
	 * @return true Gibt true zurueck, wenn auf einem Spielfeld bereits eine
	 *         Figur steht.
	 */
	public boolean istFeldBelegt() {
		if (this.hatSpielfigur == true) {
			return true;
		}
		return false;
	}
	
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x=x;
	}
	
	public void setY(int y){
		this.x=y;
	}

	
	//nach Datentyp nochmal schauen -- vll doch void?
	public Spielfigur entferneFigur(){
		return figur=null;
	}

	
	public int farbe(){
		int zahl=0;
		if(this.getFigur()!=null){
			if(this.getFigur().getFarbe().equals(FarbEnum.SCHWARZ)){
			zahl=FarbEnum.SCHWARZ.ordinal();
			}else{
				if(this.getFigur().getFarbe().equals(FarbEnum.WEISS)){
					zahl= FarbEnum.WEISS.ordinal();
					}
			}
			
		}
		return zahl;
	}
	
	public void getF(){
	 System.out.println(farbe());
		
	}
	
	
	
	/**
	 * Setzt eine Spielfigur auf ein Spielfeld
	 * 
	 * @param sfigur
	 *            Uebergabewert vom Typ Spielfigur
	 */

	/**
	 * toString-Methode mit der AUgabe der Attribute Id und Figur
	 */
	@Override
	public String toString() {
		String s = "";
		s += "Spielfeld+ " + this.getId() + " - Farbe " + this.getFarbeFeld() ;
		if (figur != null) {
			s += " - Figur" + getFigur() +"\n";
			s+="\n";
		}
		if (this.getId().contains("12")) {

			s += "\n";

		}
		return s;
	}
	public static void main(String[] args) {
		
		Spielbrett brett =new Spielbrett();
		Spielfeld  f = new Spielfeld(brett, "A2",false);
		Spielfeld  f1 = new Spielfeld(brett, "A1",true);
		f.getF();
		System.out.println(f);
		f1.getF();
		System.out.println(f1);
	}
}
