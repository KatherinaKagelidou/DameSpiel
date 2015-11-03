package daten;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

import klassen.Spiel;

public class DatenzugriffCSV implements iDatenzugriff {

	private static final String split = ";";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String FILE_HEADER = "id,firstName,lastName,gender,age";
/**
 * die methode laden aus dem interface iDatenzugriff wurde �berschrieben und neu implementiert
 * eine csv datei wird ausgelesen
 */
	@Override
	public Object laden(String filename) {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		Object obj = null;
		try {
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			obj = ois.readObject();
			if (obj instanceof Spiel) {
				Spiel sp = (Spiel) obj;
//				
//				System.out.println(sp.getBrett()); // true
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
				}
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
				}
		}
		return obj;
	}
	
	/**
	 * die methode speichern aus dem interface iDatenzugriff wurde �berschrieben und neu implementiert
	 * eine csv datei wird ausgelesen
	 */

	@Override
	public void speichern(Object spiel, String filename) {
		
		FileWriter fileWriter = null;

		try {
			fileWriter = new FileWriter(filename);

			// Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			// Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {

			System.out.println("Error in CsvFileWriter !!!");

			e.printStackTrace();

		} finally {

			try {

				fileWriter.flush();
				fileWriter.close();

			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();

			}

		}
	}

}
