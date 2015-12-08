package klassen;

import java.util.Random;

import klassen.FarbEnum;
import klassen.Spiel;
import klassen.iBediener;

public class TestSpiel {

	public static void main(String[] args) {

		Spiel i= new Spiel();
		
		
		i.addSpieler("Weiss", FarbEnum.WEISS,new KI_Dame(i));
		i.addSpieler("Schwarz", FarbEnum.SCHWARZ,new KI_Dame(i));
		
		i.starteSpiel();
		
//		int random=(int)(30 * Math.random()) +1;
//		System.out.println(random);


//		i.laufen("H2","G3");
//		i.laufen("E1","F2");
//		i.laufen("G3","F2");

//		System.out.println("SIZE----"+i.getFigurWeiss2().get(0));
//		System.out.println("SIZE 2----"+i.getFigurSchwarz2().size());
		
		
		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen("G3","F2");
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
//		i.laufen(i.getSpielerAmZug().getKi().startZiel().get(0),i.getSpielerAmZug().getKi().ziel.get(1));
		
		
		
		

	
//	System.out.println(i.getSpieler2().getKi().randomFigur());
//	i.laufen("E1","F2");
//	System.out.println("START----"+i.getSpielerAmZug().getKi().gebeStart());
//	System.out.println("ZIEL-----"+i.getSpielerAmZug().getKi().gebeZiel());
	
	
	
//	System.out.println(spiel.getFigurSchwarz()[25].getFeld().getId());
//	System.out.println(feld.getId());
	
//	i.laufen("H2","G3");
//	i.laufen("E1","F2");
//	
//	i.laufen("G3","E1");
//	i.laufen("F2","G1");
//	i.laufen("I3","H2");
//	i.laufen("G1","H2");
//	i.laufen("H12","G11");
//	i.laufen("I3","J4");
//	i.laufen("H12","G11");
//	i.laufen("J4","K5");
//	i.laufen("H12","G11");
//	
//	i.laufen("K5","L6");
//	i.laufen("H12","G11");
//	i.laufen("E3","F4");
//	i.laufen("H12","G11");
//	i.laufen("H12","G11");
//	i.laufen("H12","G11");
	
	
		
		
	
	
		
		
		
		

	}

}
