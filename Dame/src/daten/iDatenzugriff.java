package daten;

import java.io.IOException;
import java.util.Properties;

public interface iDatenzugriff {
	
//	public void öffnen(Properties p) throws IOException;
	
	public void speichern(Object object, String s) throws IOException;

	public Object laden(String s)throws IOException;
	
//	public void schließen(Object object) throws IOException;
}
