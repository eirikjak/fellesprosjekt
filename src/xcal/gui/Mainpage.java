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
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.gui.CalendarPanel;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class Mainpage extends JPanel {

	private Client client;
	private Wrapper response;
	/**
	 * Create the panel.
	 */
	public Mainpage() {
		setLayout(null);
		this.client = Client.getClient();

		setBounds(0, 0, 1000, 700);
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Create new", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(25, 23, 232, 97);
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
		panel_3.setBounds(280, 23, 232, 97);
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
		
		JPanel panel_1 = new JPanel();
		
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(756, 30, 211, 88);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(14, 8, 70, 73);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Mainpage.class.getResource("/images/1363727523_logout.png")));
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(101, 26, 91, 35);
		panel_1.add(logoutButton);
		logoutButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		logoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);
				
			}
		});
		calendarPanel.setVisible(true);

	}
	
	
	private class OtherCalendarsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new OtherCalendarsMenu();
			
			
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
