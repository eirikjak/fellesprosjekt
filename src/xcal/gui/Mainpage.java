package xcal.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JList;

public class Mainpage extends JPanel {

	/**
	 * Create the panel.
	 */
	public Mainpage() {
		setLayout(null);

		setBounds(0, 0, 1000, 700);
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(35, 37, 279, 95);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(12, 6, 90, 83);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Mainpage.class.getResource("/images/appointmentAdd3.png")));
		
		JButton btnOpprettAvtale = new JButton("New Appointment");
		btnOpprettAvtale.addActionListener(new AvtaleButtonListener());
		btnOpprettAvtale.setBounds(89, 47, 164, 35);
		panel.add(btnOpprettAvtale);
		btnOpprettAvtale.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JButton btnNewButton = new JButton("New Meeting");
		btnNewButton.setBounds(104, 8, 133, 35);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(35, 169, 940, 493);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(1, 47, 940, 72);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Mainpage.class.getResource("/images/kalender bar.png")));
		
		JButton btnLastWeek = new JButton("Last Week");
		btnLastWeek.setBounds(2, 5, 117, 39);
		panel_1.add(btnLastWeek);
		btnLastWeek.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JButton btnNextWeek = new JButton("Next Week");
		btnNextWeek.setBounds(820, 5, 117, 39);
		panel_1.add(btnNextWeek);
		btnNextWeek.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel lblNewLabel_2 = new JLabel("M\u00C5NED");
		lblNewLabel_2.setBounds(334, 5, 233, 39);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(35, 103, 174));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 28));
		
		JList list = new JList();
		list.setBounds(8, 122, 126, 365);
		panel_1.add(list);
		
		JList list_1 = new JList();
		list_1.setBounds(141, 122, 126, 365);
		panel_1.add(list_1);
		
		JList list_2 = new JList();
		list_2.setBounds(274, 122, 126, 365);
		panel_1.add(list_2);
		
		JList list_3 = new JList();
		list_3.setBounds(540, 122, 126, 365);
		panel_1.add(list_3);
		
		JList list_4 = new JList();
		list_4.setBounds(806, 122, 126, 365);
		panel_1.add(list_4);
		
		JList list_5 = new JList();
		list_5.setBounds(673, 122, 126, 365);
		panel_1.add(list_5);
		
		JList list_6 = new JList();
		list_6.setBounds(407, 122, 126, 365);
		panel_1.add(list_6);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

	
		
		

	}
	
	private class AvtaleButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			RootFrame.addPanel(new Avtale());
			
			
			
		}
		
	}


		


}
