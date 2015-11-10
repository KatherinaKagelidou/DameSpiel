package daten;

import klassen.Spiel;
import klassen.iBediener;


	public class SpielTestLadenCSV {
		public static void main(String[] args) {
//			iDatenzugriff d =new DatenzugriffSerialisiert();
//			d= new DatenzugriffSerialisiert();
//			Spiel s = (Spiel)d.laden("test6.csv");
			
			Spiel s  = new Spiel();
			
			s.ladenCSV("BinCSV.csv");
			
		}
}
