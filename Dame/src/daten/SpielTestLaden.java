package daten;

import java.io.IOException;

import klassen.Spiel;
import klassen.Spielbrett;

public class SpielTestLaden {
	public static void main(String[] args) throws IOException {
//		iDatenzugriff d =new DatenzugriffSerialisiert();
//		//d= new DatenzugriffSerialisiert();
//		Spiel s = (Spiel)d.laden("test.ser");
//		//iBediener p = (Spiel) d.laden();
		
		iDatenzugriff ser = new DatenzugriffSerialisiert();
		Spiel s = new Spiel();
		
		
		s.ladenSerial("koko.ser");
		
		
		
		
	}
}
