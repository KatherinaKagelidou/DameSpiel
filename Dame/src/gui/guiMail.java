package gui;



import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import daten.datenzugriffPDF;
import daten.mail;
import daten.iDatenzugriff;

/**
 * In dieser Klasse wird die Funktion zum Email versenden implementiert  
 */
public class guiMail extends JDialog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame spielbrett;
	private JButton jbSenden;
	private JButton jbAbbrechen;
	private JButton jbAuswaehlen;
	public final JTextField jtfEmpfaenger;
	private JLabel jlEmpfaenger = new JLabel("Email");
	private iDatenzugriff pdf;
	
	
	/**
	 * Konstruktor der Klasse JMail
	 * @param spielbrett ist das Spielbrett
	 */
	public guiMail(JFrame spielbrett){
		super(spielbrett);
		this.spielbrett=spielbrett;
		this.setTitle("E-Mail versenden");
		this.addWindowListener(new MyWindowListener());
		
		spielbrett.setEnabled(false);
		JPanel jp = new JPanel(new GridLayout(3,2));
		jtfEmpfaenger = new JTextField();
		jbSenden = new JButton("Senden");
		jbAbbrechen = new JButton("Abbrechen");
		jbAuswaehlen = new JButton("Auswaehlen");
		jbSenden.addActionListener(new EventHandler(this));
		jbSenden.setActionCommand("Senden");
		jbAbbrechen.addActionListener(new EventHandler(this));
		jbAbbrechen.setActionCommand("Abbrechen");
		jbAuswaehlen.addActionListener(new EventHandler(this));
		jbAuswaehlen.setActionCommand("Auswaehlen");
		jtfEmpfaenger.setToolTipText("E-Mail Adresse des Empfaengers hier eingeben");
		jp.add(jlEmpfaenger);
		jp.add(jtfEmpfaenger);
		jp.add(jbAuswaehlen);
		jp.add(jbAbbrechen);
		jp.add(jbSenden);
		
		setContentPane(jp);
		setSize(300,100);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	/**
	 * Innere Klasse der JMail Klasse
	 * @author A2
	 *
	 */
	class MyWindowListener implements WindowListener{
		@Override
		public void windowOpened(WindowEvent e) {}

		@Override
		public void windowClosing(WindowEvent e) {
			setVisible(false);
			dispose();
			windowClosed(e);
		}

		@Override
		public void windowClosed(WindowEvent e) {
			getOwner().setEnabled(true);
		}

		@Override
		public void windowIconified(WindowEvent e) {}

		@Override
		public void windowDeiconified(WindowEvent e) {}

		@Override
		public void windowActivated(WindowEvent e) {}

		@Override
		public void windowDeactivated(WindowEvent e) {}
	}

	/**
	 * Getter fuer JDialog
	 * @return ein Objekt der Klasse JDialog
	 */
	JDialog getJDialog(){
		return this;
	}
	
	/**
	 * Sendet eine Email
	 * @param an der empaenger der email
	 */
	public void sendEmail(String an){
		String betreff = "Dame Spiel";
		String text = "Hey du! \n "
				+ "hier ist dein bisheriger Spielverlauf vom Dame Spiel! \n\n"
				+ "Liebe Gruesse das B2 Team";
		pdf = new datenzugriffPDF();
//		pdf.speichern(spiel, filename);
		mail email = new mail(an, betreff, text, "C:/Users/Katherina/Desktop/DameNeu.pdf", "DameNeu.pdf", null, null);

				
	}
	
	
}
