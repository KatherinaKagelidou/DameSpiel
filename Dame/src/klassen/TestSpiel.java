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
	i.laufen("E5","F4");
	i.laufen("G3","F4");
	i.laufen("E9" ,"F10");
	
		
		
		
		
		

	}

}
