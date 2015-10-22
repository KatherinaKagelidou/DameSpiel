package jUnitTests;

import static org.junit.Assert.*;
import klassen.FarbEnum;
import klassen.Spielbrett;
import klassen.Spielfeld;

import org.junit.Test;

public class TestSpielfeld {
	/**
	 * vergleich zweier Felder ob sie nicht identisch sind
	 */
	@Test
	public void vergleichZweierFeldID(){
		Spielbrett sb = new Spielbrett();
		Spielfeld Feld1 = new Spielfeld(sb,"1",true);
		Spielfeld Feld2 = new Spielfeld(sb,"2",false);
		Spielfeld Feld3 = new Spielfeld(sb,"3",true);
		Spielfeld Feld4 = new Spielfeld(sb,"4",false);
		
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
		Spielbrett sb = new Spielbrett();
		Spielfeld feld1 = new Spielfeld(sb,"5",true);
		Spielfeld feld2 = new Spielfeld(sb,"6",false);
		Spielfeld feld3 = new Spielfeld(sb,"7",true);
		
		assertFalse ((feld1.getId().length()<0)
		&& (feld1.getId().length()>12)
		&& (feld2.getId().length()<0)
		&& (feld2.getId().length()>12)
		&& (feld3.getId().length()<0)
		&& (feld3.getId().length()>12));
		
	}

}
