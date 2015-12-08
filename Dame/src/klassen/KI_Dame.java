package klassen;

import java.util.ArrayList;

public class KI_Dame extends KI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	public KI_Dame(Spiel spiel) {
		super(spiel);
	}
	
	
	@Override
	public ArrayList<String> startZiel(){
		ArrayList<String> feld=null;
		if(kannSchlagen==false){
			datenSchlagen.clear();
			while(kannSchlagen()==false){
				if(uberprufteFigur.size()==spiel.anzahlFigur()){
					uberprufteFigur.clear();
					break;
				}
			}
			if(kannSchlagen==true){
				uberprufteFigur.clear();
				feld=datenSchlagen;
				ziel=datenSchlagen;
				kannSchlagen=false;
				return feld;
			}
		}
		
		if(kannLaufen==false){
			datenLaufen.clear();
			while(kannLaufen()==false){
			}
			if(kannLaufen==true){
				feld=datenLaufen;
				ziel=datenLaufen;
				kannLaufen=false;
			}
		}
		return feld;
	}
	
	

	
	
	

}
