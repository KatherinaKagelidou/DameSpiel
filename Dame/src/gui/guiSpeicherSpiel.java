package gui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import klassen.Spiel;
import daten.DatenzugriffCSV;
import daten.DatenzugriffSerialisiert;
import daten.datenzugriffPDF;
import daten.iDatenzugriff;

public class guiSpeicherSpiel extends JFrame{


		private static final long serialVersionUID = 1L;
		
		private JFileChooser jfc = new JFileChooser();
		public String pfad;
		private int status;
		
		
		
		private String defaultName = "Dame";
		private int format;
		public static final int SAVE_CSV = 0;
		public static final int SAVE_SER = 1;
		public static final int SAVE_PDF = 2;

		/**
		 * Konstruktor der Klasse JSpeicherSpiel
		 * @param format das jeweilige format
		 * @throws IOException 
		 */
		public guiSpeicherSpiel(int format) throws IOException{
			this.format = format;
			speichern();
		}

		/**
		 * Methode zum speichern des Spielstandes
		 * @throws IOException 
		 */
		private void speichern() throws IOException {
			if(format == SAVE_SER){
				jfc.setSelectedFile(new File(defaultName+".ser"));
				status = jfc.showSaveDialog(this);
				if(status == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					pfad = f.getAbsolutePath();
					
					if(pfad.endsWith(".ser")){
						pfad = pfad.substring(0, pfad.length()-4);
					}
					
					this.saveGameSer(pfad);
					
					Object options [] = {"OK"};
					JOptionPane.showOptionDialog(getJFrame(),"Serialisierte Datei wurde erfolgreich gespeichert!", "Erfolg!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				}
				else{
					return;
				}
			}
			
			else if(format == SAVE_CSV){
				jfc.setSelectedFile(new File(defaultName+".csv"));
				status = jfc.showSaveDialog(this);
				if(status == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					pfad = f.getAbsolutePath();
					if(pfad.endsWith(".csv")){
						pfad = pfad.substring(0, pfad.length()-4);
					}
					this.saveGameCSV(pfad);
					Object options [] = {"OK"};
					JOptionPane.showOptionDialog(getJFrame(),"CSV-Datei wurde erfolgreich gespeichert!", "Erfolg!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				}
				else{
					return;
				}	
			}
			
			else if (format == SAVE_PDF){
				jfc.setSelectedFile(new File(defaultName+".pdf"));
				status = jfc.showSaveDialog(this);
				if(status == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					pfad = f.getAbsolutePath();
					
					if(pfad.endsWith(".pdf")){
						pfad = pfad.substring(0, pfad.length()-4);
					}
					
			  		this.saveGamePDF(pfad);
			  		
			  		Object options [] = {"OK"};
					JOptionPane.showOptionDialog(getJFrame(),"PDF-Datei wurde erfolgreich gespeichert!", "Erfolg!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				}
				else{
					return;
				}
				
			}
			else{
				throw new RuntimeException("Falsche Wertuebergabe beim Speichern!");
			}
		}

		/**
		 * Getter fuer JFrame
		 * @return this gibt das JFrame zurueck
		 */
		private JFrame getJFrame() {
			return this;
		}
		
		/**
		 * Methode zum serialisiert Speichern
		 * @param pfad der ausgewahelte speicherpfad
		 */
		public void saveGameSer(String pfad){
			iDatenzugriff serialisieren = new DatenzugriffSerialisiert();
			serialisieren.speichern(pfad, (Spiel) GuiSpielbrett.getSpiel());
		}
		
		/**
		 * Methode zum pdf speichern
		 * @param pfad der ausgewahelte speicherpfad
		 * @throws IOException 
		 */
		public void saveGamePDF(String pfad) throws IOException{
			iDatenzugriff pdf = new datenzugriffPDF();
			pdf.speichern(pfad, GuiSpielbrett.getSpiel());
//			pdf.schliessen(GuiSpielbrett.getSpiel());
			
		}
		
		/**
		 * Methode zum csv Speichern
		 * @param pfad der ausgewahelte speicherpfad
		 */
		public void saveGameCSV(String pfad){
			iDatenzugriff csv = new DatenzugriffCSV((Spiel) GuiSpielbrett.getSpiel());
			csv.speichern(pfad, (Spiel) GuiSpielbrett.getSpiel());
		}

}
