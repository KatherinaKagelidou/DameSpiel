package klassen;


public interface iBediener {

	public void addSpieler(String name, FarbEnum farbe,KI ki);
	public void starteSpiel(int spielerAnz);
//	public void FigurAnSpieler(Spieler spieler);

	public void figurSetzen();
	void werdeDame(Spielfigur figur);
//	void figurSetzen(Spielfeld[][] data);
	public void umwandeln(String id);
	
	void laufen(String altePos, String zielPos);

}
