package klassen;

import klassen.FarbEnum;
import klassen.Spiel;
import klassen.iBediener;

public class TestSpiel {

	public static void main(String[] args) {

		iBediener i= new Spiel();
		
		
		
		i.addSpieler("Kathi", FarbEnum.SCHWARZ,null );
		i.addSpieler("Sarah", FarbEnum.WEISS, null);
		i.starteSpiel();

	
		//black 25 wird Dame
				i.laufen("H2","G3","white 30");
				i.laufen("E1", "F2", "black 25");
				i.laufen("G3", "F4","white 30");
				i.laufen("F2", "G1","black 25");
				i.laufen("H4","G5","white 29");
				i.laufen("G1","H2","black 25");
				i.laufen("I3","H4","white 23");
				i.laufen("H2","I3","black 25");
				i.laufen("I1","H2","white 24");
				i.laufen("E11","F12","black 30");
				i.laufen("J2","I1","white 18");
				i.laufen("I3","J2","black 25");
				i.laufen("J4","I3","white 17");
				i.laufen("E5","F6","black 27");
				i.laufen("K3","J4","white 11");
				i.laufen("J2","K3","black 25");
				i.laufen("K1","J2","white 12");
				i.laufen("E7","F8","black 28");
				i.laufen("L2","K1","white 6");
				i.laufen("K3","L2","black 25");
				
				//black 25 will schlagen als Dame
				i.laufen("H6","G7","white 28");
				i.laufen("F8","G9","black 29");
				
				i.laufen("H2","G1","white 30");//um zu pruefen ob eine Dame schlagen kann wenn 2 weisse Figuren hinereinander stehen. (Darf nicht funktionieren)
				
//				i.laufen("I5","H6","white 22");//um zu pruefen ob eine Dame schlagen kann wenn 1 weisse Figur vor ihr steht.
				i.laufen("L2","I5","black 25");
				
				i.hatGewonnen();
				
				



		
		
		
		
		

	}

}
