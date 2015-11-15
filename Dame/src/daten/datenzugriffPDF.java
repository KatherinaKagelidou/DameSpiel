package daten;


import gui.GuiSpielbrett;
import gui.StartGui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import klassen.Spiel;

public class datenzugriffPDF implements iDatenzugriff {

	
	private GuiSpielbrett gui;
	
	
	public static void main(String[] args) {

		    try {
		      Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Users/user/Desktop/test.pdf"));
		      document.open();
		      
		      //Chapters und 
		      Paragraph title1 = new Paragraph("Neue PDF",
		          FontFactory.getFont(FontFactory.HELVETICA));
		      Chapter chapter1 = new Chapter(title1, 1);
		      chapter1.setNumberDepth(0); //do not show chapter number on page

		      //... Sections
		      Paragraph title11 = new Paragraph("Das ist das DAME-Spiel",
		          FontFactory.getFont(FontFactory.HELVETICA));
		      Section section1 = chapter1.addSection(title11);
		      
//		      //Text zu Section
//		      Paragraph someSectionText = new Paragraph("This text comes as part of section 1 of chapter 1.");
//		      section1.add(someSectionText);
//		      someSectionText = new Paragraph("Following is a 3 X 2 table.");
//		      section1.add(someSectionText);

//		      //Liste zu Section
//		      List l = new List(true, false, 10);
//		      l.add(new ListItem("First item of list"));
//		      l.add(new ListItem("Second item of list"));
//		      section1.add(l);
		      
		      document.add(chapter1);
		      document.close();
		      
		    } catch (FileNotFoundException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    } catch (DocumentException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }
		  }


private void PDFexport(String DateiName, int x, int y,GuiSpielbrett g ){
  		
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy' um 'HH:mm");
		Date currentTime= new Date();
  		
		iDatenzugriff pdf = new datenzugriffPDF();
		Properties prop = new Properties();
		prop.setProperty("dateiName", DateiName+".pdf");
		prop.setProperty("Modus", "s");   // key & value -> eindeutige idenfikation

		/*
		 * Speichert das Brett ab
		 */
		try {
			pdf.oeffnen(prop);
		
			Font font = new Font(FontFamily.HELVETICA, 24);
			Font font2 = new Font(FontFamily.HELVETICA, 14);
			
			Paragraph p = new Paragraph("DAME-Spiel \n "+DateiName, font);
	
			Paragraph p1 = new Paragraph("Karte erstellt am "+formatter.format(currentTime), font2);
			
			

			p.setAlignment(Element.ALIGN_CENTER);
			p1.setAlignment(Element.ALIGN_CENTER);
		
			p.setSpacingBefore(10);
			p.setSpacingAfter(20);
			p1.setSpacingBefore(10);
			p1.setSpacingAfter(20);
			
			pdf.schreiben(p);
			pdf.schreiben(p1);
	
			
			//tabelle erzeugen fuer die brett belegung
			//PDF größe bestimmen
			PdfPTable table = new PdfPTable(x);
			table.setWidthPercentage((float) 99); 
	
			
			// Die Map wird durchlaufen und auf die Pdf erzeugt
		
			Spiel s= (Spiel) g.gibSpielZurueck();
			s.ladenCSV(DateiName+".map");
		
			
            try {
            	
            	float[] columnWidths = new float[x];
            	for (int i = 0; i < x; i++) {
            		columnWidths[i] = 25f;
				}
            	
				table.setWidths(columnWidths);
				
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			pdf.schreiben(table);
			
		} catch (IOException e) {
			System.err.println(e.getMessage());

		}finally {
			try {
				pdf.schliessen(DateiName);
			}catch(IOException fehler) {
				System.out.println(fehler.getMessage());
			}
		}
}










@Override
public void oeffnen(Properties p) throws IOException {
	// TODO Auto-generated method stub
	
}










@Override
public void schreiben(Object object) throws IOException {
	// TODO Auto-generated method stub
	
}










@Override
public Object lesen() throws IOException {
	// TODO Auto-generated method stub
	return null;
}










@Override
public void schliessen(Object object) throws IOException {
	// TODO Auto-generated method stub
	
}



}