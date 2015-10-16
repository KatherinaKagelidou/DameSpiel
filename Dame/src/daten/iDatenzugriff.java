package daten;

import java.io.IOException;
import java.util.Properties;

public interface iDatenzugriff {
	
	public void öffnen(Properties p) throws IOException;
	
	public void schreiben(Object object) throws IOException;

	public Object lesen()throws IOException;
	
	public void schließen(Object object) throws IOException;
}
