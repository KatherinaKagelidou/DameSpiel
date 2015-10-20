package jUnitTests;

import static org.junit.Assert.assertTrue;
import klassen.FarbEnum;
import klassen.Spiel;
import klassen.Spieler;

import org.junit.Test;

	public class TestSpiel {
		/** Hier wird getestet, ob das Spiel startet.
//		 * 
//		 */
	//	
	@Test
	public void startSpiel() {
		Spiel sb = new Spiel();
		assertTrue(sb != null);
	}

	/**
	 * die beiden Spieler dürfen nicht dieselbe Farbe haben
	 */
	@Test
	public void FigurAnSpieler() {
		Spieler s1 = new Spieler("BOB", FarbEnum.SCHWARZ, null);
		Spieler s2 = new Spieler("STEFAN", FarbEnum.WEISS, null);
		assertTrue(s1.getFarbe() != s2.getFarbe());
	}

}
