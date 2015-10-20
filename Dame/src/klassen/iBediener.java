package klassen;

public interface iBediener {

	void spielerHinzu(String name, String farbe, KI ki);
	
	void startSpiel(int AnzSpieler);
	
	void zeigeSpieler();
	
	void spielfeldBelegt(int id, int zielPos);

}
