package xcal.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Component;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Mainpage extends JPanel {

	/**
	 * Create the panel.
	 */
	public Mainpage() {
		setLayout(null);

		setBounds(0, 0, 1000, 700);
		
		JLabel lblTrerg = new JLabel("");
		lblTrerg.setIcon(new ImageIcon(Mainpage.class.getResource("/images/andrekalendere2.png")));
<<<<<<< HEAD
		lblTrerg.setBounds(778, 44, 66, 76);
=======
		lblTrerg.setBounds(341, 41, 66, 76);
>>>>>>> 45cd177ad536bc371fd221c99f03e5636a10215c
		add(lblTrerg);
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Create new", TitledBorder.TRAILING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(30, 44, 232, 97);
		add(panel);
		panel.setLayout(null);
		

		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(12, 8, 91, 90);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Mainpage.class.getResource("/images/appointmentAdd3.png")));
		
		JButton btnOpprettAvtale = new JButton("Appointment");
		btnOpprettAvtale.addActionListener(new NewAppointmentListener());
		btnOpprettAvtale.setBounds(97, 52, 108, 29);
		panel.add(btnOpprettAvtale);
		btnOpprettAvtale.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JButton btnNewButton = new JButton("Meeting");
		btnNewButton.addActionListener(new NewMeetingListener());
		btnNewButton.setBounds(98, 25, 108, 29);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(143, 179, 206));
		panel_2.setBounds(40, 256, 922, 27);
		add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_1 = new JLabel("DATO");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_1.setBounds(20, 4, 90, 19);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("DATO");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_2.setBounds(285, 4, 90, 19);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("DATO");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_3.setBounds(150, 4, 90, 19);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("DATO");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_4.setBounds(416, 4, 90, 19);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("DATO");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_5.setBounds(549, 4, 90, 19);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("DATO");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_6.setBounds(681, 4, 90, 19);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("DATO");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_7.setBounds(814, 4, 90, 19);
		panel_2.add(label_7);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(32, 166, 940, 493);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(1, 47, 940, 72);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Mainpage.class.getResource("/images/kalender_bar.png")));
		
		JButton btnLastWeek = new JButton("Last Week");
		btnLastWeek.setBounds(2, 11, 132, 29);
		panel_1.add(btnLastWeek);
		btnLastWeek.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JButton btnNextWeek = new JButton("Next Week");
		btnNextWeek.setBounds(804, 11, 132, 29);
		panel_1.add(btnNextWeek);
		btnNextWeek.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JLabel lblNewLabel_2 = new JLabel("M\u00C5NED");
		lblNewLabel_2.setBounds(334, 5, 233, 39);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(35, 103, 174));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 28));
		
		JList list_1 = new JList();
		list_1.setBounds(8, 122, 126, 365);
		panel_1.add(list_1);
		
		JList list_2 = new JList();
		list_2.setBounds(141, 122, 126, 365);
		panel_1.add(list_2);
		
		JList list_3 = new JList();
		list_3.setBounds(274, 122, 126, 365);
		panel_1.add(list_3);
		
		JList list_4 = new JList();
		list_4.setBounds(540, 122, 126, 365);
		panel_1.add(list_4);
		
		JList list_5 = new JList();
		list_5.setBounds(806, 122, 126, 365);
		panel_1.add(list_5);
		
		JList list_6 = new JList();
		list_6.setBounds(673, 122, 126, 365);
		panel_1.add(list_6);
		
		JList list_7 = new JList();
		list_7.setBounds(407, 122, 126, 365);
		panel_1.add(list_7);
		
		JButton btnNewButton_1 = new JButton("<html> &nbsp;\nOther </p><br>Calendars  </html>");
<<<<<<< HEAD
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnNewButton_1.addActionListener(new OtherCalendarsListener());
		btnNewButton_1.setBounds(858, 55, 109, 58);
=======
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new OtherCalendarsListener());
		btnNewButton_1.setBounds(429, 55, 109, 58);
>>>>>>> 45cd177ad536bc371fd221c99f03e5636a10215c
		add(btnNewButton_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(323, 34, 241, 95);
		add(panel_3);
		
	
		
		

	}
	
	
	private class OtherCalendarsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new OtherCallendarsMenu();
			
		}
			
		
		
	}
	private class NewAppointmentListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			new AppointmentMenu();
			
		}
		
	}
	
	private class NewMeetingListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			new MeetingMenu();
			
		}
		
		
	}
}
