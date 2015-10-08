package klassen;

public class Spielfigur {
	
	
	private FarbEnum farbe;
	private int position;
	
	
	
	
	public Spielfigur(FarbEnum farbe, int position){
		
		this.setFarbe(farbe);
		this.setPosition(position);
		
	}
	
	public FarbEnum getFarbe(){
		return farbe;
	}
	
	public void setFarbe(FarbEnum farbe){
		if(farbe==null){
			throw new RuntimeException("keine gueltige Farbe");
		}
		
		
		this.farbe=farbe;
	}
	
	public int getPosition(){
		return position;
	}
	
	public void setPosition(int position){
		
		if(position<0){
			throw new RuntimeException("Keine gueltige Position auf dem Spielbrett");
		}
		this.position=position;
	}
	
	
	@Override
	public String toString() {
		return 
				("Farbe: " + getFarbe() + 
				" | "+ "ID: "+ getPosition()
				);	
	

}
}








