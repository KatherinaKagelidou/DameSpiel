package klassen;

import gui.Spieler1AuswahlDialog;

import java.awt.Component;
import java.io.IOException;

public interface iBediener {
	
	public void addSpieler(String name, FarbEnum farbe,KI ki);
	public void starteSpiel();


	public void figurSetzen();


	void gibKoordinate(String s);
	void laufen(String aktPos, String zielPos, String figurId);
	Spielfigur gebeFigur(String figurId);
	void belegungCSV();

	
	void zugBeenden();
	void schlagen(Spielfeld aktPos, Spielfeld zielPos, Spielfigur spielfigur);
	void speichernCSV(String dateiname);
	Spiel ladenCSV(String s);
	void speichernSerial(String s);
	Spiel ladenSerial(String s);
	boolean hatGewonnen();
	public Spieler getSpieler1();
	public Spieler getSpieler2();

	

}
