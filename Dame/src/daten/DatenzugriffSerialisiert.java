package daten;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import klassen.Spiel;
import klassen.Spieler;

public class DatenzugriffSerialisiert implements iDatenzugriff {
	/**
	 * methode speichern aus dem Interface iDatenzugriff wird implementiert
	 */
		@Override
		public void speichern(Object spiel, String filename) {
			
			ObjectOutputStream oos = null;
			FileOutputStream fos = null;
			try {
			  fos = new FileOutputStream(filename);
			  oos = new ObjectOutputStream(fos);
			  oos.writeObject(spiel);
			}
			catch (IOException e) {
			  e.printStackTrace();
			}
			finally {
			  if (oos != null) try { 
				  oos.close(); 
				  } catch (IOException e) {
					  
				  }
			  if (fos != null) try { 
				  fos.close(); 
				  } catch (IOException e) {
					  
				  }
			}
		}
}
	/**
	 * methode laden aus dem Interface iDatenzugriff wird implementiert
	 */
//		@Override
//		public Object laden(String filename) {
//			ObjectInputStream ois = null;
//			FileInputStream fis = null;
//			Object obj = null;
//			try {
//			  fis = new FileInputStream(filename);
//			  ois = new ObjectInputStream(fis);
//			  obj = ois.readObject();
//			  if (obj instanceof Spiel) {
//			    Spiel sp = (Spiel)obj;
//			    System.out.println(sp.getSpielerAmZug()); // String
//			    System.out.println(sp.getWuerfelZahl()); // 1
//			    System.out.println(sp.getBrett()); // true
//			  }
//			  
//			  if (obj instanceof Spieler) {
//				    Spiel sp = (Spiel)obj;
//				    System.out.println(sp.getSpielerlist()); // String
//				    System.out.println(sp.getWuerfelZahl()); // 1
//				    System.out.println(sp.getBrett()); // true
//				  }
//			}
//			catch (IOException e) {
//			  e.printStackTrace();
//			}
//			catch (ClassNotFoundException e) {
//			  e.printStackTrace();
//			}
//			finally {
//			  if (ois != null) try { 
//				  ois.close(); 
//				  } catch (IOException e) {
//					  
//				  }
//			  if (fis != null) try { 
//				  fis.close(); 
//				  } catch (IOException e) {
//					  
//				  }
//			}
//			  return obj;
//		}
//
//}
