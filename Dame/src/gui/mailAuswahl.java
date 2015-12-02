package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * In dieser Klasse die Auswahl de Email implementiert
 */
public class mailAuswahl extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jl = new JLabel("");
	private JFileChooser jfc;
	private String pfad;
	private String datei = "";
	private boolean richtigerDateityp = false;

	/**
	 * Konstruktor fuer die Klasse JMailAuswahl
	 */
	public mailAuswahl() {
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

		if (status == JFileChooser.APPROVE_OPTION) {
			File f = jfc.getSelectedFile();
			pfad = f.getAbsolutePath();
			datei = f.getName();

			if (datei.endsWith(".csv") || datei.endsWith(".pdf")) {
				richtigerDateityp = true;
			} else {
				Object[] options = { "OK" };
				JOptionPane
						.showOptionDialog(
								this,
								"Falsche Datei versucht zu laden!\n Zulaessige Formate: '.pdf' oder '.ser'!",
								"Achtung!", JOptionPane.DEFAULT_OPTION,
								JOptionPane.ERROR_MESSAGE, null, options,
								options[0]);
				this.setVisible(false);
				this.dispose();
				new mailAuswahl();
				return;
			}
		} else if (status == JFileChooser.CANCEL_OPTION) {
			this.setEnabled(false);
			this.setVisible(false);
			this.dispose();
			// System.out.println("hallo");

			// System.exit(0);
			// this.getOwner().setEnabled(true);
			// new JMail(brett.getJFrame());
			// System.out.println("hallo");
		}

		if (richtigerDateityp) {
			if (pfad.endsWith(".pdf")) {

			}

			if (pfad.endsWith(".ser")) {

			}
		}
	}
}
