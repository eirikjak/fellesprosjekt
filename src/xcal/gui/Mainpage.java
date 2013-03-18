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

import xcal.client.Client;

public class Mainpage extends JPanel {

	private Client client;
	/**
	 * Create the panel.
	 */
	public Mainpage(Client client) {
		setLayout(null);
		this.client = client;

		setBounds(0, 0, 1000, 700);
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Create new", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(40, 23, 232, 97);
		add(panel);
		panel.setLayout(null);
		

		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(12, 8, 91, 90);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Mainpage.class.getResource("/images/appointmentAdd3.png")));
		
		JButton btnOpprettAvtale = new JButton("Appointment");
		btnOpprettAvtale.addActionListener(new NewAppointmentListener());
		btnOpprettAvtale.setBounds(97, 55, 115, 35);
		panel.add(btnOpprettAvtale);
		btnOpprettAvtale.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JButton btnNewButton = new JButton("Meeting");
		btnNewButton.addActionListener(new NewMeetingListener());
		btnNewButton.setBounds(98, 19, 115, 35);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(32, 166, 940, 493);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 88, 922, 27);
		panel_1.add(panel_2);
		panel_2.setBackground(new Color(143, 179, 206));
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
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new OtherCalendarsListener());
		btnNewButton_1.setBounds(429, 55, 109, 58);
		add(btnNewButton_1);
		

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Show", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(300, 23, 232, 97);
		add(panel_3);
		panel_3.setLayout(null);
		

		JLabel lblTrerg1 = new JLabel("");
		lblTrerg1.setBounds(15, 16, 66, 66);
		panel_3.add(lblTrerg1);
		lblTrerg1.setIcon(new ImageIcon(Mainpage.class.getResource("/images/andrekalendere2.png")));
		
		JButton btnNewButton_11 = new JButton("<html> &nbsp;\nOther </p><br>Calendars  </html>");
		btnNewButton_11.setBounds(100, 24, 109, 58);
		panel_3.add(btnNewButton_11);
		
				btnNewButton_11.setFont(new Font("Lucida Grande", Font.BOLD, 13));
				btnNewButton_11.addActionListener(new OtherCalendarsListener());

		
	
		
		

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

			new AppointmentMenu(client);
			
		}
		
	}
	
	private class NewMeetingListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			new MeetingMenu();
			
		}
		
		
	}
}
