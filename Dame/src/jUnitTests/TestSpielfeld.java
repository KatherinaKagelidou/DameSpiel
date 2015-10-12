package jUnitTests;

import static org.junit.Assert.*;

import klassen.Spielfeld;

import org.junit.Test;

public class TestSpielfeld {
	/**
	 * vergleich zweier Felder ob sie nicht identisch sind
	 */
	@Test
	public void vergleichZweierFeldID(){
		Spielfeld Feld1 = new Spielfeld(1);
		Spielfeld Feld2 = new Spielfeld(2);
		Spielfeld Feld3 = new Spielfeld(3);
		Spielfeld Feld4 = new Spielfeld(4);
		
		System.out.println(Feld1);
		System.out.println(Feld2);
		System.out.println(Feld3);
		System.out.println(Feld4);
		assertTrue((Feld1.getId() !=Feld2.getId())
		& (Feld1.getId() !=Feld3.getId())
		& (Feld1.getId() !=Feld4.getId())
		& (Feld2.getId() !=Feld1.getId())
		& (Feld2.getId() !=Feld3.getId())
		& (Feld2.getId() !=Feld4.getId())
		& (Feld3.getId() !=Feld1.getId())
		& (Feld3.getId() !=Feld2.getId())
		& (Feld3.getId() !=Feld4.getId()));
	}
	@Test
	public void vergleichFeldgröße(){
		/**
		 * vergleich ob die Feldgrößer kleiner 0 oder größer 12 sind
		 */
		Spielfeld feld1 = new Spielfeld(5);
		Spielfeld feld2 = new Spielfeld(6);
		Spielfeld feld3 = new Spielfeld(7);
		
		assertFalse ((feld1.getId()<0)
		&& (feld1.getId()>12)
		&& (feld2.getId()<0)
		&& (feld2.getId()>12)
		&& (feld3.getId()<0)
		&& (feld3.getId()>12));
		
	}

}
