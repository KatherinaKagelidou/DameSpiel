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

		i.laufen("H2", "G3", "white 30");
        i.laufen("E1", "F2", "black 25");
        i.laufen("G3", "F2", "white 30");
        i.laufen("E3", "F4", "black 26");
        
        i.laufen("H4", "G5", "white 29");
        i.laufen("F4", "G3", "black 26");
        
        i.laufen("H12", "G11", "white 25");
        i.laufen("G3", "H4", "black 26");
        i.laufen("I11", "H12", "white 19");
        i.laufen("E5", "F4", "black 27");
        i.laufen("G5", "F4", "white 29");
        i.laufen("E7", "F6", "black 28");
//        i.laufen("H6", "G7", "white 28");
        
       System.out.println("farbe2: "+i.farbePlayer()); 

//        i.laufen("G3", "F2", "white 30");
//        i.laufen("E3", "F4", "black 26");
//        

	
		
		
		
		
		

	}

}
