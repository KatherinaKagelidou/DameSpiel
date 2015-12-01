package daten;

import java.io.PrintWriter;

import klassen.FarbEnum;
import klassen.Spiel;
import klassen.Spieler;
import klassen.iBediener;

public class SpielTestSpeichernCSV {
	public static void main(String[] args) {
		PrintWriter pw = null;
		iDatenzugriff d=new DatenzugriffSerialisiert();
		
		iBediener spiel = new Spiel();
		
	
		
		spiel.addSpieler("Kathi", FarbEnum.SCHWARZ,null );
		spiel.addSpieler("Sarah", FarbEnum.WEISS, null);

		
		spiel.starteSpiel();
//		spiel.ermittleSpielerAmZug();
		
		spiel.laufen("H2", "G3");
		
		spiel.laufen("E11", "F10");
		
		
	
		
		
		
		
		spiel.speichernCSV("hallo");
//		s.ladenCSV("koko");
	}}
