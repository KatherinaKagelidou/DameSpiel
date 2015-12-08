package gui;

import klassen.Spiel;
import klassen.iBediener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import daten.DatenzugriffCSV;

public class EventHandler implements ActionListener {

	private JFrame frame;
	private StartGui startGui;
	private Spieler1AuswahlDialog spieler1AuswahlDialog;
	private Spieler2AuswahlDialog spieler2AuswahlDialog;
	private GuiSpielbrett guiSpielbrett;
	private MenuDialogLaden menuDialog;
	private guiMail mail;

	private iBediener i;
	private Spiel spiel;

	public EventHandler(StartGui startGui) {
		this.startGui = startGui;
	}

	public EventHandler(Spieler1AuswahlDialog spieler1AuswahlDialog) {
		this.spieler1AuswahlDialog = spieler1AuswahlDialog;
	}

	public EventHandler(Spieler2AuswahlDialog spieler2AuswahlDialog) {
		this.spieler2AuswahlDialog = spieler2AuswahlDialog;
	}

	public EventHandler(GuiSpielbrett guiSpielbrett) {
		this.guiSpielbrett = guiSpielbrett;
	}

	public EventHandler(MenuDialogLaden menuDialog) {
		this.menuDialog = menuDialog;
	}
	
	public EventHandler(guiMail mail) {
		this.mail=mail;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		System.out.println(cmd);

		// ------------Start--------------
		if (cmd.equals("button")) {
			startGui.fra.setVisible(false);
			startGui.fra.dispose();
			new Spieler1AuswahlDialog(startGui);
			
		}
		if (cmd.equals("spielStarten")) {

			String name = spieler1AuswahlDialog.getNameEingabe().getText();
		
//		     String art="KI";
			
			if (name == null || name.length() < 2) {
				JOptionPane.showMessageDialog(spieler1AuswahlDialog,
						"Name muss mindestens 2 Zeichen enthalten!", "ERROR!",
						JOptionPane.ERROR_MESSAGE);
			} else {
				spieler1AuswahlDialog.frame.setVisible(false);
				spieler1AuswahlDialog.frame.dispose();
				new Spieler2AuswahlDialog(spieler1AuswahlDialog);
			

			}
//			if(spieler1AuswahlDialog.getArtAuswahl().getSelectedItem()){
//				System.out.println("ich bin eine ki");
//			}

		}

		if (cmd.equals("spielStarten1")) {

			String name = spieler2AuswahlDialog.getNameEingabe().getText();

			if (name == null || name.length() < 2) {
				JOptionPane.showMessageDialog(spieler2AuswahlDialog,
						"Name muss mindestens 2 Zeichen enthalten!", "ERROR!",
						JOptionPane.ERROR_MESSAGE);
			} else {

				spieler2AuswahlDialog.frame.setVisible(false);
				spieler2AuswahlDialog.frame.dispose();
				new GuiSpielbrett(spieler2AuswahlDialog.getSpieler1(), spieler2AuswahlDialog);
			}

		}

		if (cmd.equals("laden")) {
//			new GuiSpielLaden();
			spiel.speichernCSV("");
			
		}
		
		
		if(guiSpielbrett!=null){
//		for (JButton b:guiSpielbrett.getFelder()) {
////			if (cmd.equals(b.getText())&& guiSpielbrett.hatIcon(e)==true) {
//			if (cmd.equals(b.getText())) {
//				System.out.println("Das Feld mit der ID "
//						+ b.getText());
//				
//				guiSpielbrett.lauf(e);
//			}
//		}
			if (cmd.equals("ziehen")) {
				guiSpielbrett.laufText();
				
			}
			if (cmd.equals("laufKI")) {
				guiSpielbrett.laufKI();

			}
			
		}
		
		 if(cmd.equals("ser")){
				new guiSpeicherSpiel(guiSpeicherSpiel.SAVE_SER);
			}
		 if(cmd.equals("csv")){
			
				new guiSpeicherSpiel(guiSpeicherSpiel.SAVE_CSV);
					}
			
			 if(cmd.equals("pdf")){
				spielbrettPNG();
				
					new guiSpeicherSpiel(guiSpeicherSpiel.SAVE_PDF);
				
				
				}
			 
			 else if (cmd.equals("mail")){
					new guiMail(guiSpielbrett.getFrame());
				}
				
				else if (cmd.equals("Auswaehlen")){
					new mailAuswahl();
				}
				
				else if (cmd.equals("Abbrechen")){
					mail.setVisible(false);
					mail.dispose();
					mail.getOwner().setEnabled(true);
				}
				
				else if (cmd.equals("Senden")){
				  	String EmailPattern="^[a-zA-Z0-9._-]{1,20}@[a-zA-Z0-9]{1,20}.[a-zA-Z]{2,3}$";
		        	
		        	Pattern pattern=Pattern.compile(EmailPattern);
		        	Matcher regexMatcher= pattern.matcher(mail.jtfEmpfaenger.getText());
		        	
		        	if(!regexMatcher.matches()){
		        		JOptionPane.showMessageDialog(null, "Email ist falsch !");
		        	}
		        	else{
		        		String an = mail.jtfEmpfaenger.getText();
		        		try{
		        			mail.sendEmail(an);
		        		}catch (Exception ex){
		        			Object[] options = {"OK"};
		        			JOptionPane.showOptionDialog(mail.getJDialog(), ex.getMessage(), "Achtung!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
							return;
		        		}
		        		Object[] options = {"OK"};
						JOptionPane.showOptionDialog(mail.getJDialog(), "E-Mail versendet!", "Erfolg!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
						mail.dispose();
						mail.getOwner().setEnabled(true);
		        	}
				}
				
			
			}
		 public void spielbrettPNG(){
				JPanel panel=guiSpielbrett.getBrettPanel();
				BufferedImage bild=new BufferedImage(panel.getSize().width,panel.getSize().height,BufferedImage.TYPE_INT_RGB);
				panel.paint(bild.createGraphics());
				File datei=new File("brett.png");
				try{
//					datei.createNewFile();
					ImageIO.write(bild, "png", datei);
					
				}catch (IOException e){
					e.printStackTrace();
				}
			}
//		}
		
		}
//	}


