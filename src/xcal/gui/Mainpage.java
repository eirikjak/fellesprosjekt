package xcal.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

public class Mainpage extends JPanel {

	/**
	 * Create the panel.
	 */
	public Mainpage() {
		setLayout(null);
		setBounds(0, 0, 1000, 700);
		JButton btnOpprettAvtale = new JButton("Opprett avtale");
		btnOpprettAvtale.setBounds(83, 64, 117, 29);
		add(btnOpprettAvtale);
		
		btnOpprettAvtale.addActionListener(new AvtaleButtonListener());

	}
	
	private class AvtaleButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			RootFrame.addPanel(new Avtale());
			
			
			
		}
		
	}

}
