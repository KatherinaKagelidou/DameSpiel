package daten;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import klassen.Spiel;
import klassen.Spieler;

public class DatenzugriffSerialisiert implements iDatenzugriff {
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private boolean lesend = false;

	@Override
	public void oeffnen(Properties p) throws IOException {
		try {
			if (lesend) {
				ois = new ObjectInputStream(new FileInputStream("datei.ser"));
			} else {
				oos = new ObjectOutputStream(new FileOutputStream("datei.ser"));
			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		}

	}

	@Override
	public void schreiben(Object object) throws IOException {
		try{
		oos.writeObject(object);
		}
		catch(FileNotFoundException e){
			System.err.println("Konnte Datei nicht erzeugen");
		}catch (Exception e){
			System.err.println("Fehler beim Schliessen");
		}
	}

	@Override
	public Object lesen() throws IOException {
		if (ois == null) {
			throw new RuntimeException("Der Reader ist nicht offen");
		}
		try {
			return ois.readObject();
		} catch (ClassNotFoundException e) {
			System.err.println("Fehler bei Ein-/Ausgabe: " + e);
			return null;
		}
	}

	@Override
	public void schliessen(Object object) throws IOException {
		if (oos != null) {
			oos.close();
			oos = null;
		}

		if (ois != null) {
			ois.close();
			ois = null;
		}
	}

	@Override
	public Object laden(String name, String typ) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void speichern(String filename, Object spiel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object laden(String filename) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
