package jUnitTests;

import static org.junit.Assert.*;
import klassen.FarbEnum;
import klassen.Spieler;

import org.junit.Test;


public class TestSpieler {

	@Test
	public void test() {
		Spieler S1 = new Spieler ("Bob",FarbEnum.SCHWARZ);
		Spieler S2 = new Spieler ("Charlie",FarbEnum.WEISS);
		
		System.out.println(S1);
		System.out.println(S2);
		assertTrue((S1.getFarbe() != S2.getFarbe()));
		
	}
	
	@Test
	public void test1() {
	Spieler S1 = new Spieler ("Bob",FarbEnum.SCHWARZ);
	Spieler S2 = new Spieler ("Charlie",FarbEnum.WEISS);
	
	System.out.println(S1);
	System.out.println(S2);
	assertTrue((S1.getName() != S2.getName()));
	}

}
