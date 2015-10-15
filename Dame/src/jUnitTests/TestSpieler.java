package jUnitTests;

import static org.junit.Assert.*;
import klassen.FarbEnum;
import klassen.Spieler;

import org.junit.Test;


public class TestSpieler {
/**
 * Test ueberprueft ob Spieler nicht die gleiche Farbe der Steine haben
 */
	@Test
	public void test() {
		Spieler S1 = new Spieler ("Bob",FarbEnum.SCHWARZ,null);
		Spieler S2 = new Spieler ("Charlie",FarbEnum.WEISS,null);
		
		System.out.println(S1);
		System.out.println(S2);
		assertTrue((S1.getFarbe() != S2.getFarbe()));
		
	}
	/**
	 * Test ueberprueft das Spieler nicht den selben Namen haben
	 */
	@Test
	public void test1() {
	Spieler S1 = new Spieler ("Bob",FarbEnum.SCHWARZ,null);
	Spieler S2 = new Spieler ("Charlie",FarbEnum.WEISS,null);
	
	System.out.println(S1);
	System.out.println(S2);
	assertTrue((S1.getName() != S2.getName()));
	}

}
