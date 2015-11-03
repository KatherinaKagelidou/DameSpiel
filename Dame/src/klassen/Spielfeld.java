package klassen;


/**
 * Klasse Spielfeld
 * 
 * @author B2
 *
 */
public class Spielfeld {
	private String id;
	private Spielfigur figur;
	private boolean hatSpielfigur = false;
	private Spielbrett spielbrett;
	private FarbEnum farbeFeld;
	private boolean istSchwarz;

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

		if (this.hatSpielfigur) {
			this.figur = figur;
		} else
			System.out.println("Es befindet sich eine Figur auf dem Feld, klicken?");

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

	public void removeSpielfigur() {
		if (this.hatSpielfigur == true) {
			figur = null;
			System.out
					.println("Die Figur auf dem " + this + " wurde entfernt!");
			this.hatSpielfigur = false;
		} else
			throw new RuntimeException("Keine Figur auf dem Feld!");
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
		s += "[ Spielfeld+" + this.getId() + " - Farbe " + this.getFarbeFeld();
		if (figur != null) {
			s += " - Figur" + getFigur() + "]";
		}
		if (this.getId().contains("12")) {

			s += "\n";

		}
		return s;
	}
}
