package no.ntnu.fp.gui;

import javax.swing.JPanel;
import javax.swing.JButton;

public class Mainpage extends JPanel {

	/**
	 * Create the panel.
	 */
	public Mainpage() {
		setLayout(null);
		
		JButton btnOpprettAvtale = new JButton("Opprett avtale");
		btnOpprettAvtale.setBounds(83, 64, 117, 29);
		add(btnOpprettAvtale);

	}

}
