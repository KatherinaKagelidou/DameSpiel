package daten;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import klassen.Spiel;


	public class SpielTestLaden {
		public static void main(String[] args) {
			
			
			Spiel sp = new Spiel();
		
			Scanner scanner = new Scanner(System.in);

			System.out.println("Geben sie l ein um das Spiel zu laden");//oder neues Spiel spielen [n].\n" 
			String eingabe = scanner.nextLine();
			if ("l".equals(eingabe) == true) {
				System.out.println("Dateinamen eingeben (Test4).");
				String filename = scanner.nextLine();
				System.out.println("Speichertyp [csv] oder [ser] eingeben.");
				String typ = scanner.nextLine();
				if(typ.equals("csv")){
				try {
					Spiel s = new Spiel();
					s.laden(filename, typ);
				} catch (IOException e) {
					e.printStackTrace();
				}
				}
				else if(typ.equals("ser")){
					try {
						Spiel s = new Spiel();
						s.laden(filename, typ);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if ("n".equals(eingabe) == true) {

				System.out.println("Name Spieler 1:");
				String name1 = scanner.nextLine();
				System.out.println("KI? - y/n");
				while (!scanner.hasNext("[yn]")) {
					System.out.println("KI? - y/n");
					scanner.next();
				}
				String ki1 = scanner.next();
				boolean ki1erstellen = false;

				if (ki1.equals("y")) {
					ki1erstellen = true;
				}
				
				
	//
//				Spieler player1 = new Spieler(name1, FarbEnum.weiss, ki1erstellen);
	//
//				add(player1);
				System.out.println(name1 + " hinzugefuegt!");
				System.out.println("Name Spieler 2:");
				String name2 = scanner.next();
				System.out.println("KI? - y/n");
				while (!scanner.hasNext("[yn]")) {
					System.out.println("KI? - y/n");
					scanner.next();
				}
				String ki2 = scanner.next();
				boolean ki2erstellen = false;
				if (ki2.equals("y")) {
					ki2erstellen = true;
				}
	//
//				Spieler player2 = new Spieler(name2, FarbEnum.schwarz, ki2erstellen);
	//
//				add(player2);
//				System.out.println(player2 + " hinzugefuegt!");
	//
//				System.out.println("Moege das Spiel beginnen!");

//				System.out.println(spielbrett);
//				System.out.println(player1.getName() + " - " + "'" + player1.getFarbe() + "'" + " faengt an!");
				scanner.reset();
				//playerRotation(player1, player2);
				sp.getSpieler1();
				sp.getSpieler2();
				System.out.println();
				
			}
		}

	}
