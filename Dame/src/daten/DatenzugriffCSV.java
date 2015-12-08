package daten;

import gui.GuiSpielbrett;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Properties;

import klassen.FarbEnum;
import klassen.Spiel;
import klassen.Spielbrett;
import klassen.Spielfeld;
import klassen.Spielfigur;
import klassen.iBediener;

public class DatenzugriffCSV implements iDatenzugriff,Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedWriter bw;
	private BufferedReader br;
	private PrintWriter pW;
	
	
	
	private Spiel spiel;

	private Spielbrett spielbrett;
	private static iDatenzugriff daten;
	private GuiSpielbrett gui;
	
	/**
	 * Diese Methode �ffnet die CSV Datei.

	 */
	
	public DatenzugriffCSV(Spiel spiel){
		this.spiel=spiel;
	}
	
	@Override
	public void oeffnen(Properties p) throws IOException {
		String dateiname = p.getProperty("Dateiname");
		
		if(dateiname == null) {
			throw new IOException("Dateiname wurde nicht definiert!");
		}
		
		if("s".equals(p.getProperty("Modus"))) {
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dateiname)));
		} else if("l".equals(p.getProperty("Modus"))) {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(dateiname)));
		} else {
			throw new IOException("Modus wurde nicht definiert!");
		}
	}
	
	/**
	 * Diese Datei schreibt in die CSV

	 */
	@Override
	public void schreiben(Object object) throws IOException {
		if (bw == null) {
			throw new RuntimeException("Der Reader ist nicht offen");
		}
		
			bw.write((String) object);
			
		
	}
	
	/**
	 * Diese Methode liest aus der Datei
	
	 */
	@Override
	public Object lesen() throws IOException {
		if (br == null) {
			throw new RuntimeException("Der Reader ist nicht offen");
		}
		try {
			return br.readLine();
		} catch (IOException e) {
			System.err.println("Fehler bei Ein-/Ausgabe: "+e);
			return null;
		}
	}
	
	//nachtr�glich eingef�gt--------------------------------------------
	@Override
	public Object laden(String name, String typ) {
		try {
			String line;
			ArrayList<String> feld = new ArrayList<String>();
			// hier wird die .csv Datei ganz eingelesen und in feld ÃƒÆ’Ã‚Â¼bergeben
			br= new BufferedReader(new FileReader(name + "." + typ));
			while ((line = br.readLine()) != null) {
				feld.add(line);
			}
			return feld;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	/**
	 * Diese Methode schlie�t die CSV - Datei

	 */
	
	
	@Override
	public void schliessen(Object object) throws IOException {
		if(bw != null) {
			bw.close();
			bw = null;
		}
		
		if(br != null) {
			br.close();
			br = null;
		}
	}

//	@Override
//	public void speichern(String dateiname, Object spiel) {
//		
//		
//		try {
//			pW = new PrintWriter(new FileWriter(dateiname+".csv"));
//		} catch (FileNotFoundException e) {
//			System.err.println("DATEI ZUM SPEICHERN NICHT GEFUNDEN!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		pW.write("spieler :"+ this.spiel.getSpieler1().getName()+ " mit der Farbe "
//			+ this.spiel.getSpieler1().getFarbe() + " \n" + "Spieler: "
//			+ this.spiel.getSpieler2().getName() + " mit der Farbe "
//			+ this.spiel.getSpieler2().getFarbe() + "");
//		pW.println();
//		
//		Spielfeld[][] belegung = spielbrett.getFelder();
//		char ch = 'L';
//		char ch1 = 'A';
//		int x = 1;
//		String str = null;
//		boolean schwarz = true;
//
//		for (int i = 0; i < spielbrett.getFelder().length; i++) {
//
//			for (int j = 0; j < belegung[i].length; j++) {
//				str = "" + ch1 + x;
//				x++;
//
//				pW.print(belegung[i][j] = new Spielfeld(spielbrett, str,
//						schwarz));
//
//				schwarz = !schwarz;
//
//				if (x > 12) {
//					x = 1;
//					ch1++;
//					if (ch1 == ch) {
//						str = "" + ch + x;
//						schwarz = !schwarz;
//
//						break;
//
//					}
//					schwarz = !schwarz;
//
//				}
//
//			}
//			pW.println();
//		}
//
//		pW.close();
//		
//			try {
//				pW= new PrintWriter(new FileWriter(dateiname+".csv"));
//			} catch (IOException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
//		
////			try{
////			
////			bw.write("spieler :"+ this.spiel.getSpieler1().getName()+ " mit der Farbe "
////					+ this.spiel.getSpieler1().getFarbe() + " \n" + "Spieler: "
////					+ this.spiel.getSpieler2().getName() + " mit der Farbe "
////					+ this.spiel.getSpieler2().getFarbe() + "");
////			bw.newLine();
////	
////		
////
////			for (int i = 0; i < spielbrett.getFelder().length; i++) {
////				for (int j = 0; j < spielbrett.getFelder()[i].length; j++){
////					Spielfeld f = spielbrett.getFelder()[i][j];
////					bw.write("Spelbrett :" + f);	
////					bw.newLine();
////			}
////			
////			
////			}
////		} catch (Exception e) {
//////			 System.err.println("Speichern CSV fehlgeschlagen!");
//////			System.out.println("fail");
////			e.printStackTrace();
////		} finally {
////
////			try {
////				bw.close();
////			} catch (IOException fehler) {
////				System.out.println(fehler.getMessage());
////			}
////		}
//			try {
//				daten = new DatenzugriffCSV(this.spiel);
//				Properties p = new Properties();
//				p.setProperty("Dateiname", dateiname + ".csv");
//				p.setProperty("Modus", "s");
//				daten.oeffnen(p);
//
//				String s = "";
//				s += "Spieler: " + this.spiel.getSpieler1().getName() + " mit der Farbe "
//						+ this.spiel.getSpieler1().getFarbe() + " \n" + "Spieler: "
//						+ this.spiel.getSpieler2().getName() + " mit der Farbe "
//						+ this.spiel.getSpieler2().getFarbe() + "";
//				daten.schreiben(s + "\n");
//
//				for (int i = 0; i < spielbrett.getFelder().length; i++) {
//					for (int j = 0; j < spielbrett.getFelder()[i].length; j++)
//						daten.schreiben(spielbrett.getFelder()[i][j] + "\n");
//				}
//
//				daten.schreiben(this);
//
//			} catch (Exception e) {
//				// System.err.println("Speichern CSV fehlgeschlagen!");
//				System.out.println("geladen");
//			} finally {
//
//				try {
//					daten.schliessen(dateiname);
//				} catch (IOException fehler) {
//					System.out.println(fehler.getMessage());
//				}
//
//			}
		
//	}
	
//	@Override
//	public void speichern(String dateiname, Object spiel) {
//
//		try {
//			pW = new PrintWriter(new FileWriter(dateiname+".csv"));
//		} catch (FileNotFoundException e) {
//			System.err.println("DATEI ZUM SPEICHERN NICHT GEFUNDEN!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		Spielfeld[][] belegung = spielbrett.getFelder();
//		char ch = 'L';
//		char ch1 = 'A';
//		int x = 1;
//		String str = null;
//		boolean schwarz = true;
//
//		for (int i = 0; i < spielbrett.getFelder().length; i++) {
//
//			for (int j = 0; j < belegung[i].length; j++) {
//				str = "" + ch1 + x;
//				x++;
//
//				pW.print(belegung[i][j] = new Spielfeld(spielbrett, str,
//						schwarz));
//
//				schwarz = !schwarz;
//
//				if (x > 12) {
//					x = 1;
//					ch1++;
//					if (ch1 == ch) {
//						str = "" + ch + x;
//						schwarz = !schwarz;
//
//						break;
//
//					}
//					schwarz = !schwarz;
//
//				}
//
//			}
//			pW.println();
//		}
//
//		pW.close();
//	}
	@Override
	public Object laden(String filename) {
		// TODO Auto-generated method stub
		return null;
	}
	 @Override
		public void speichernCSV(String dateiname,Object spiel) {
			try {
				daten = new DatenzugriffCSV(this.spiel);
				Properties p = new Properties();
				p.setProperty("Dateiname", dateiname + ".csv");
				p.setProperty("Modus", "s");
				daten.oeffnen(p);

				String s = "";
				s += "Spieler: " + this.spiel.getSpieler1().getName() + " mit der Farbe "
						+ this.spiel.getSpieler1().getFarbe() + " \n" + "Spieler: "
						+ this.spiel.getSpieler2().getName() + " mit der Farbe "
						+ this.spiel.getSpieler2().getFarbe() + "";
				daten.schreiben(s + "\n");

			
			;
			
				for (int i = 0; i < spielbrett.getFelder().length; i++) {
					for (int j = 0; j < spielbrett.getFelder()[i].length; j++)
						daten.schreiben(spielbrett.getFelder()[i][j] + "\n");
				}

				daten.schreiben(this);

			} catch (Exception e) {
				// System.err.println("Speichern CSV fehlgeschlagen!");
				System.out.println("geladen");
			} finally {

				try {
					daten.schliessen(dateiname);
				} catch (IOException fehler) {
					System.out.println(fehler.getMessage());
				}

			}

		}

	@Override
	public void speichern(String filename, Object spiel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object laden2(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveGame(String path, String fileName, iBediener iBediener) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
