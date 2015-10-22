package klassen;

import klassen.FarbEnum;
import klassen.Spiel;
import klassen.iBediener;

public class TestSpiel {

	public static void main(String[] args) {

		iBediener i= new Spiel();
		
		
		i.addSpieler("Kathi", FarbEnum.SCHWARZ,null );
		i.addSpieler("sarah", FarbEnum.WEISS, null);

		i.starteSpiel();
		
//		System.out.println(i);


		
		
		
		
		

	}

}
