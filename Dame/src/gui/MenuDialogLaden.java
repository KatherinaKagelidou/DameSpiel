package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuDialogLaden extends JPanel  implements Serializable{

	private static final long serialVersionUID = 1L;

	JMenuBar menuBar;
	JMenuItem datei, laden;
	JPanel pnlOben;
	private JFileChooser _fileChooser = new JFileChooser();
	private EventHandler e;

	public MenuDialogLaden() {

		e=new EventHandler(this);
		pnlOben = new JPanel();
		pnlOben.setLayout(new GridLayout(0, 1, 5, 5));

		menuBar = new JMenuBar();

		datei = new JMenu("Datei");
		menuBar.add(datei);

		laden = new JMenuItem("Spiel Laden");
		datei.add(laden);

		pnlOben.add(menuBar);

		//
		laden.addActionListener(new OpenAction());
		 addListener() ;

	}

	class OpenAction implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			// ... Open a file dialog.
			int retval = _fileChooser.showOpenDialog(MenuDialogLaden.this);
			if (retval == JFileChooser.APPROVE_OPTION) {
				// ... The user selected a file, get it, use it.
				File file = _fileChooser.getSelectedFile();

			}
		}

	}

	 private void addListener() {
	
	 laden.addActionListener(new EventHandler(this));
	 laden.setActionCommand("laden");
	
	
	
	 }

}
