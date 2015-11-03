package klassen;


import klassen.FarbEnum;
import klassen.Spiel;
import klassen.iBediener;

public class TestSpiel {

	public static void main(String[] args) {

		iBediener i= new Spiel();
		
		
		
		i.addSpieler("Kathi", FarbEnum.SCHWARZ,null );
		i.addSpieler("Sarah", FarbEnum.WEISS, null);
		i.starteSpiel(2);
		i.figurSetzen();
		

		

		

		



		
		
		
		
		

	}

}
