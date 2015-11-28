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

	i.laufen("H2","G3");
	i.laufen("E3","F4");
	i.laufen("H12","G11");
	i.laufen("F4" ,"G3");
	i.laufen("G11", "F10");
//	i.laufen("H6", "G7");
//    i.laufen("E11", "F10");
//    i.laufen("H8", "G7");
//	System.out.println(i.gibFigur("G11"));
	
//	System.out.println(i.gibFeld("white 26"));
	
	
		
		
		
		
		

	}

}
