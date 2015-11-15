package klassen;
/**
 * Klasse Spielfigur
 * @author B2
 *
 */
public class Spielfigur {
	
	
	private FarbEnum farbe;
	private String id;
	private String position;
	private Spielfeld spielfeld;
	private static int counter = 1;
	private boolean istDame;
	
	
	
	/**
	 * Konstruktor
	 * @param farbe der Spielfigur
	 * @param istDame 
	 * @param position der Figur
	 */
	
	public Spielfigur(Spielfeld feld, FarbEnum farbe, boolean istDame){
//		this.setPosition(position);
		if(counter>30){
			counter=1;
		}
		this.setFarbe(farbe);
		this.setSpielfeld(feld);
	
		
	}
	
	public void setDame(boolean istDame) {
		this.istDame = istDame;

	}

	public boolean getDame(Spielfigur figur) {
		if (figur.getId().startsWith("w")) {
		if (figur.getPosition().contains("A")) {
			istDame = true;
		}
	}
	if (figur.getId().startsWith("b")) {
		if (figur.getPosition().contains("L")) {
			istDame = true;

		}

	}
	return istDame;
	}
	
	public String getId(){
		return id;
	}
	
	
	/**
	 * Setzen der ID
	 * 
	 * @param id
	 * setzt die ID
	 */
	
	
	public void setId(int count){
		
		switch (this.farbe) {
		case SCHWARZ:
			id ="black "+count;
			break;
		case WEISS:
			id ="white "+count;
			break;
		}
		
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
			throw new RuntimeException("Fehler setter figur - keine gueltige Farbe");
		}
		
		
		this.farbe=farbe;
	}
	
	public String getPosition(){
		 position=this.spielfeld.getId();
		 return position;
	}
	
	public void setzeFigur(Spielfeld feld){
		if(feld!=null){
			if(feld.getFigur()==null){
				this.spielfeld=feld;
				this.spielfeld.setFigur(this);
			}
		}
	}
	
	

	
	/**
	 * Setter mit Ueberpfuefung pb die Position der Spielfigur
	 * größer 0 ist
	 * @param position setzt die position der spielfigur
	 */
//	public void setPosition(String position) {	
//		if(spielfeld.istFeldBelegt()==true){
//			System.out.println("Es befindet sich eine Figur auf diesem Spielfeld. Schlagen?");
//		}else{
//			this.position= position;
//			this.setSpielfeld(spielfeld);
//			spielfeld.setFigur(this);
//			System.out.println(""+this +" wurde auf Position: " + position + " gelegt.");
//			}
//	}
	
//	public void istDame(boolean istDame){
//		if(this.getFarbe().equals(FarbEnum.WEISS)){
//			if(this.position.contains("12")){
//				this.istDame=true;
//			}
//			else{
//				this.istDame=false;
//			}
//		}
//		if(this.getFarbe().equals(FarbEnum.SCHWARZ)){
//			if(this.position.contains("1")){
//				this.istDame=true;
//			}
//			else{
//				this.istDame=false;
//			}
//		}
//		
//	}
	
	public Spielfeld getFeld(){
		return spielfeld;
	}
	
	

	@Override
	public String toString() {

		return "Spielfigur " + this.getId()+ " mit der Farbe " + 
		this.getFarbe() + " auf Feld " + this.getPosition() +  " ist Dame " + getDame(this);

	}


}
