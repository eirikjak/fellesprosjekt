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
import xcal.gui.CalendarPanel;

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
		

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Show", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(300, 23, 232, 97);
		add(panel_3);
		panel_3.setLayout(null);
		

		JLabel lblTrerg1 = new JLabel("");
		lblTrerg1.setBounds(15, 16, 66, 66);
		panel_3.add(lblTrerg1);
		lblTrerg1.setIcon(new ImageIcon(Mainpage.class.getResource("/images/andrekalendere2.png")));
		

		JButton btnNewButton_1 = new JButton("<html> &nbsp;\nOther </p><br>Calendars  </html>");
		btnNewButton_1.setBounds(109, 24, 109, 58);
		panel_3.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new OtherCalendarsListener());
		
		CalendarPanel calendarPanel = new CalendarPanel();		
				
		calendarPanel.setBounds(25, 155, 950, 500);
		add(calendarPanel);
		calendarPanel.setLayout(null);
		calendarPanel.setVisible(true);

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
