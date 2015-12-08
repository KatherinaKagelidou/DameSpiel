package daten;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import klassen.Spiel;
import klassen.iBediener;

public interface iDatenzugriff {
	
public void oeffnen(Properties p) throws IOException;
    
	public void schreiben(Object object) throws IOException;

	public Object lesen() throws IOException;
    
	public void schliessen(Object object)throws IOException;
	
	//nachträglich eingefügt----------------------------------------------
	public Object laden(String name, String typ) throws IOException;

	//serial
	void speichern(String filename, Object spiel);

	Object laden(String filename);

	void speichernCSV(String dateiname,Object spiel);

	Object laden2(String filename);



	boolean saveGame(String path, String fileName, iBediener iBediener);

	

	
//	void speichernCSV(String dateiname);
}



