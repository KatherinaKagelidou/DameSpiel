package klassen;

import java.util.ArrayList;

public class Spiel implements iBediener {

	private Spielbrett sb;
	private int AnzSpieler = 2;
	private int spielerAmZug = 0;
	private ArrayList<Spieler> spielerliste = new ArrayList<Spieler>();

	
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



	public void spielerHinzu(String name, String farbe, boolean ki){
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
	}
	

	

