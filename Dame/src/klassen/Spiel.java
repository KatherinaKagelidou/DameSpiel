package klassen;

import java.util.ArrayList;

public class Spiel implements iBediener {

	private Spielbrett sb;
	private int AnzSpieler = 2;
	private int spielerAmZug = 0;
	private ArrayList<Spieler> spielerliste = new ArrayList<Spieler>();
	private int zielPos;
	
	public Spiel(){
		
	}
	
	
	
	public int getAnzSpieler() {
		return AnzSpieler;
	}



	public void setAnzSpieler(int anzSpieler) {
		AnzSpieler = anzSpieler;
	}



	public int getSpielerAmZug() {
		return spielerAmZug;
	}



	public void setSpielerAmZug(int spielerAmZug) {
		this.spielerAmZug = spielerAmZug;
	}



	public void spielerHinzu(String name, String farbe, KI ki){
		if(spielerliste.size() < this.getAnzSpieler()){
			Spieler s = new Spieler(name, FarbEnum.valueOf(farbe), ki);
				if(s.getFarbe()==null){
					for(Spieler spieler:spielerliste){
						if(spieler.getFarbe().equals(FarbEnum.SCHWARZ)){
							s.setFarbe(FarbEnum.WEISS);
							spielerliste.add(s);
						}else{
							s.setFarbe(FarbEnum.SCHWARZ);
							spielerliste.add(s);
						}
							
						
					}
					if(spielerliste.isEmpty()){
						spielerliste.add(s);
						
					}else{
						for (Spieler spieler : spielerliste) {
							// falls ein Spieler die gleiche Farbe gewählt hat
							if(s.getFarbe().equals(s.getFarbe())){
								System.out.println("Die Farbe wurde schon gewählt!");
							}
							
							
						}
						spielerliste.add(s);
					
					}
				}
				
			}
		}
	
	public void startSpiel(int AnzSpieler) {

		if (AnzSpieler == 2) {
			sb = new Spielbrett();
			this.setAnzSpieler(AnzSpieler);
			spielerliste.clear();
		
		}
	}
	
	public void zeigeSpieler() {
		for (int i = 0; i < spielerliste.size(); i++) {
			System.out.println("Name: " + spielerliste.get(i).getName()
					+ ", Farbe: " + spielerliste.get(i).getFarbe());
		}
	}
	
	public void spielfeldBelegt(int id, int zielPos){
	
		if (spielerliste.get(spielerAmZug).getFarbe()
				.equals(sb.getSpielfeld(zielPos).getFigur().getFarbe())) {
			spielerliste.get(spielerAmZug).getFigur().setPosition(spielerliste.get(spielerAmZug).getFigur()
					.getPosition());
			
			System.out.println("Auf dieser Position befindet sich bereits deine eigene Figur \n "
					+ "wähle eine andere Figur");
		}
	}
}