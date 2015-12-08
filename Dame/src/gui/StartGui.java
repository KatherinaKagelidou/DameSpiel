package gui;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StartGui  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton button = new JButton("Jetzt spielen");
	JFrame fra;
	private JLabel backImgPanel = new JLabel(new ImageIcon("pics\\startbildNeu.png"));
	private EventHandler event;

	MenuDialogLaden laden;

	public StartGui() {

		fra = new JFrame("Game Dame");
		fra.setSize(500, 500);
		fra.setLocationRelativeTo(null);
		fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		event = new EventHandler(this);

		this.laden = new MenuDialogLaden();

		erstelle();
		hinzufuegen();
		addListener();

		fra.setResizable(false);
		fra.pack();
		fra.setVisible(true);

	}

	private void erstelle() {
		backImgPanel.setLayout(null);
		backImgPanel.setOpaque(false);
		backImgPanel.add(button);
		backImgPanel.add(laden.pnlOben);
		button.setBounds(70, 410, 110, 30);
		button.setBackground(new Color(153, 102, 0));
		backImgPanel.setBounds(0, 0, 400, 300);

		laden.pnlOben.setBounds(1, 1, 640, 20);
	

	}

	private void hinzufuegen() {
		fra.getContentPane().add(backImgPanel);

	}

	private void addListener() {
		button.addActionListener(event);
		button.setActionCommand("button");
	}

	public JButton getButton() {
		return button;
	}

	public JFrame getFra() {
		return fra;
	}
	

}
