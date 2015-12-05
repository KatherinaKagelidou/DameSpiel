package klassen;

import klassen.FarbEnum;
import klassen.Spiel;
import klassen.iBediener;

public class TestSpiel {

	public static void main(String[] args) {

		iBediener i= new Spiel();
		
		
		
		i.addSpieler("Schwarz", FarbEnum.SCHWARZ,"Spieler");
		i.addSpieler("Weiss", FarbEnum.WEISS,"KI");
		i.starteSpiel();

		
	i.laufen("H2","G3");
	i.laufen("E5","F4");
	i.laufen("H12","G11");
	i.laufen("F4","G3");
	i.laufen("I3","H4");
	
	
		
		
		
		
		

	}

}
