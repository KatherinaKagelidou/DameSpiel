package daten;

import java.io.IOException;
import java.util.Properties;

public interface iDatenzugriff {
	
	public void �ffnen(Properties p) throws IOException;
	
	public void schreiben(Object object) throws IOException;

	public Object lesen()throws IOException;
	
	public void schlie�en(Object object) throws IOException;
}
