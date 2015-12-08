package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import daten.DatenzugriffSerialisiert;
import daten.iDatenzugriff;
import klassen.Spiel;

public class GuiSpielLaden extends JFrame{

	

	private static final long serialVersionUID = 1L;
	private JLabel jl = new JLabel("");
	private JFileChooser jfc;
	private String pfad;
	private String datei = "";
	private boolean richtigerDateityp = false;
	private GuiSpielbrett brett;
	private Spieler1AuswahlDialog eins;
	private Spieler2AuswahlDialog zwei;
	

	/**
	 * Konstruktor fuer JLadeSpiel
	 */
	public GuiSpielLaden() {
		super("Spiel Laden");
		JPanel jp = new JPanel();
		this.add(jl);
		this.setContentPane(jp);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(false);
		
		jfc = new JFileChooser();
		
		int status = jfc.showOpenDialog(this);
		
		if(status == JFileChooser.APPROVE_OPTION){
			File f = jfc.getSelectedFile();
			pfad = f.getAbsolutePath();
			datei = f.getName();
			
			if(datei.endsWith(".csv") || datei.endsWith(".ser")){
				richtigerDateityp = true;
				
			} 
			else{
				Object[] options = { "OK" };
				JOptionPane.showOptionDialog(this,"Falsche Datei versucht zu laden!\n Zuleassige Formate: '.csv' oder '.ser'!","Achtung!", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE, null, options,options[0]);
				this.setVisible(false); 
				this.dispose();
				new GuiSpielLaden();
				return;
			}
			
		} 
		else if(status == JFileChooser.CANCEL_OPTION){
			this.setEnabled(false);
			this.setVisible(false); 
			this.dispose(); 
			new StartGui();
		}
		
		if(richtigerDateityp){
			if(pfad.endsWith(".ser")){
				Spiel sp  = (Spiel) this.loadGameSer(pfad);
				if(brett == null){
					brett = new GuiSpielbrett(eins, zwei);
				}
				
				brett.setSpiel(sp);
				this.dispose();
			}
		}
	}

	/**
	 * Methode zum Laden des Serialen Spieles
	 * @param pfad ist der Pfad wo es gespeichert werden soll
	 * @return den pfad
	 */
		public Object loadGameSer(String pfad){
		iDatenzugriff serialisieren = new DatenzugriffSerialisiert();
		return(Spiel) serialisieren.laden2(pfad);
	}

	
	
}
