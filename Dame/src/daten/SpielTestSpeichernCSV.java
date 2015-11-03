package daten;

import java.io.PrintWriter;

import klassen.FarbEnum;
import klassen.Spiel;
import klassen.Spieler;

public class SpielTestSpeichernCSV {
	public static void main(String[] args) {
		PrintWriter pw = null;
		iDatenzugriff d=new DatenzugriffSerialisiert();
		Spiel spiel1=new Spiel();
		Spieler s1=new Spieler("Kathi",FarbEnum.SCHWARZ, null);
	
		
		spiel1.starteSpiel();

		System.out.println("Hallo");
//		d.speichern(spiel1, "test6.csv");
	}
}
