package klassen;

import gui.Spieler1AuswahlDialog;

import java.awt.Component;
import java.io.IOException;

public interface iBediener {
    
    public void addSpieler(String name, FarbEnum farbe,KI ki);
    public void starteSpiel();


    public void figurSetzen();


    void gibKoordinate(String s);
//    void laufen(String aktPos, String zielPos, String figurId);
    Spielfigur gebeFigur(String figurId);
    void belegungCSV();

    
    void zugBeenden();
    
    void speichernCSV(String dateiname);
    Spiel lesenCSV(String s);
    void speichernSerial(String s);
    Spiel ladenSerial(String s);

    void schlagen(String aktPos, String gegnerPos, String zielPos);
//    void testen();


    boolean hatGewonnen();
    public Spieler getSpieler1();
    public Spieler getSpieler2();
	Object laden(String name, String typ) throws IOException;
	void speichern(Object obj, String name) throws IOException;
	
	String farbePlayer();
	void laufen(String aktPos, String zielPos);
	Spieler getSpielerAmZug();
	boolean getZugOk();
	Spielfeld gibFeld(String figur);
	Spielfigur gibFigur(String feld);
	boolean istFeldBelegt(Spielfigur figur, Spielfeld feld);
	boolean istFigurDrin(String figur);
	


    

}
