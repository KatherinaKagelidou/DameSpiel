package jUnitTests;

import static org.junit.Assert.*;
import klassen.FarbEnum;
import klassen.Spielfigur;

import org.junit.Test;

public class TestSpielfigur {


	/** Hier wird ueberprueft ob die Spielfiguren die selbe ID haben
	 *  
	 */

	@Test
	public void test() {
		Spielfigur F1 = new Spielfigur(FarbEnum.SCHWARZ,3);
		Spielfigur F2 = new Spielfigur(FarbEnum.WEISS,60);
		Spielfigur F3 = new Spielfigur(FarbEnum.SCHWARZ,20);
		Spielfigur F4 = new Spielfigur(FarbEnum.WEISS,30);

		System.out.println(F1);
		System.out.println(F2);
		System.out.println(F3);
		System.out.println(F4);
		assertTrue((F1.getPosition() != F2.getPosition())
				& (F1.getPosition() != F3.getPosition())
				& (F1.getPosition() != F4.getPosition())
				& (F2.getPosition() != F1.getPosition())
				& (F2.getPosition() != F3.getPosition())
				& (F2.getPosition() != F4.getPosition())
				& (F3.getPosition() != F1.getPosition())
				& (F3.getPosition() != F2.getPosition())
				& (F3.getPosition() != F4.getPosition()));
	}
	
	
	@Test
	public  void test1(){
		
		Spielfigur s1 = new Spielfigur(FarbEnum.WEISS,100);
		Spielfigur s2 = new Spielfigur(FarbEnum.WEISS,50);
		Spielfigur s3 = new Spielfigur(FarbEnum.WEISS,30);
		Spielfigur s4 = new Spielfigur(FarbEnum.WEISS,130);
		
		assertFalse((s1.getPosition() <0 )
				& (s1.getPosition() >100)
				& (s2.getPosition() <0)
				& (s2.getPosition() >100)
				& (s3.getPosition() <0)
				& (s3.getPosition() >100)
				& (s4.getPosition() <0)
				& (s4.getPosition() >100));
	}
	



}
