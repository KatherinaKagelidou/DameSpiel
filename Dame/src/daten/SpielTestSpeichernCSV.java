package daten;

import java.io.IOException;
import java.io.PrintWriter;

import klassen.FarbEnum;
import klassen.Spiel;
import klassen.Spieler;
import klassen.iBediener;

public class SpielTestSpeichernCSV {
	public static void main(String[] args) throws IOException {
		PrintWriter pw = null;
		iDatenzugriff d=new DatenzugriffSerialisiert();
		
		Spiel i = new Spiel();
		
	
		
//		Spieler weiss =new Spieler("Kathi",FarbEnum.SCHWARZ,null);
//		Spieler schwarz=new Spieler("Branki",FarbEnum.WEISS,null);
//		
//		s.nimmtTeil(weiss);
//		s.nimmtTeil(schwarz);
//		
//		s.starteSpiel();
//	s.ermittleSpielerAmZug();
//		
//		s.laufen("H2", "G3","white 30");
//	
//		s.laufen("E11", "F10","black 30");
		
		

		i.addSpieler("Kathi", FarbEnum.SCHWARZ,null );
		i.addSpieler("Sarah", FarbEnum.WEISS, null);
		i.starteSpiel();
		
		

		i.laufen("H4", "G3","white 29");
		i.zugBeenden();
		
	
		
		
		
		i.speichernCSV("BinCSV");
		
//		s.ladenCSV("koko");
	}}
