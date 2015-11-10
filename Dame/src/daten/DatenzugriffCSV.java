package daten;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import klassen.Spiel;

public class DatenzugriffCSV implements iDatenzugriff {

	private BufferedReader br;
	private BufferedWriter bw;
	
	/**
	 * Diese Methode öffnet die CSV Datei.

	 */
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
	
	/**
	 * Diese Methode schließt die CSV - Datei

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

}
