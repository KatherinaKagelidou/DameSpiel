package daten;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Properties;

import klassen.Spiel;
import klassen.Spieler;
import klassen.iBediener;

public class DatenzugriffSerialisiert implements iDatenzugriff, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public void speichern(String s,Object sp){
		String sav = s;
		sav.concat(".ser");
		try{
			ObjectOutputStream oos1= new ObjectOutputStream(new FileOutputStream(sav+".ser"));
			oos1.writeObject(sp);
			oos1.close();
			System.out.println("SPIEL WURDE GESPEICHERT !");
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	 * methode laden aus dem Interface iDatenzugriff wird implementiert
	 */
		@Override
		public Object laden2(String filename) {
			ObjectInputStream ois = null;
			FileInputStream fis = null;
			Object obj = null;
			try {
			  fis = new FileInputStream(filename);
			  ois = new ObjectInputStream(fis);
			  obj = ois.readObject();
//			  	if (obj instanceof Spiel) {
//				  Spiel sp = (Spiel)obj;
//				  System.out.println(sp.getSpielerAmZug()); // String
//				  System.out.println(sp.getWuerfelZahl()); // 1
//				  System.out.println(sp.getBrett()); // true
//			  	}
			}
			catch (IOException e) {
			  e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
			  e.printStackTrace();
			}
			finally {
			  if (ois != null) try { ois.close(); } catch (IOException e) {}
			  if (fis != null) try { fis.close(); } catch (IOException e) {}
			}
			  return obj;
		}




	@Override
	public void speichernCSV(String dateiname, Object spiel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object laden(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public boolean saveGame(String path, String fileName, iBediener iBediener) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public void speichernCSV(String dateiname) {
//		// TODO Auto-generated method stub
//		
//	}
	
}
