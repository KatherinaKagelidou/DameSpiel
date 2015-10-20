package jUnitTests;

import static org.junit.Assert.*;
import klassen.FarbEnum;
import klassen.Spielfeld;

import org.junit.Test;

public class TestSpielfeld {
	/**
	 * vergleich zweier Felder ob sie nicht identisch sind
	 */
	@Test
	public void vergleichZweierFeldID(){
		Spielfeld Feld1 = new Spielfeld("1",FarbEnum.SCHWARZ);
		Spielfeld Feld2 = new Spielfeld("2", FarbEnum.WEISS);
		Spielfeld Feld3 = new Spielfeld("3",FarbEnum.SCHWARZ);
		Spielfeld Feld4 = new Spielfeld("4",FarbEnum.WEISS);
		
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
	public void vergleichFeldge(){
		/**
		 * vergleich ob die Feldgr��er kleiner 0 oder gr��er 12 sind
		 */
		Spielfeld feld1 = new Spielfeld("5", FarbEnum.WEISS);
		Spielfeld feld2 = new Spielfeld("6", FarbEnum.SCHWARZ);
		Spielfeld feld3 = new Spielfeld("7", FarbEnum.WEISS);
		
		assertFalse ((feld1.getId().length()<0)
		&& (feld1.getId().length()>12)
		&& (feld2.getId().length()<0)
		&& (feld2.getId().length()>12)
		&& (feld3.getId().length()<0)
		&& (feld3.getId().length()>12));
		
	}

}
