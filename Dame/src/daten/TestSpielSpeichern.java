package daten;

import klassen.FarbEnum;
import klassen.Spiel;
import klassen.Spieler;

public class TestSpielSpeichern {
	public static void main(String []args){
		iDatenzugriff d=new DatenzugriffSerialisiert();
		Spiel spiel1=new Spiel();
		spiel1.addSpieler("Sarah", FarbEnum.SCHWARZ, null);
		spiel1.addSpieler("Kathi", FarbEnum.WEISS, null);
		
		spiel1.starteSpiel();
//		d.speichern(spiel1, "test.ser");
		
//		System.out.println(spiel1.);
	}
}
