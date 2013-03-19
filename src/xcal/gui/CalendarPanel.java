package xcal.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.joda.time.DateTime;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;

import javax.swing.JScrollPane;
import xcal.model.*;

public class CalendarPanel extends JPanel {
	private Client client = Client.getClient();
	private Calendar tCal = Calendar.getInstance();
	private Calendar cal = Calendar.getInstance();
	
	//Labels for dates
	private JLabel mondayDate = new JLabel();
	private JLabel tuesdayDate = new JLabel();
	private JLabel wednesdayDate = new JLabel();
	private JLabel thursdayDate = new JLabel();
	private JLabel fridayDate = new JLabel();
	private JLabel saturdayDate = new JLabel();
	private JLabel sundayDate = new JLabel();
	//end labels for dates
	
	
	
	//Models
	private final DefaultListModel mondayModel = new DefaultListModel();
	private final DefaultListModel tuesdayModel = new DefaultListModel();
	private final DefaultListModel wednesdayModel = new DefaultListModel();
	private final DefaultListModel thursdayModel = new DefaultListModel();
	private final DefaultListModel fridayModel = new DefaultListModel();
	private final DefaultListModel saturdayModel = new DefaultListModel();
	private final DefaultListModel sundayModel = new DefaultListModel();
	
	
	//Arrays with weekdays, appointments, month names etc
	private JLabel[] week = {mondayDate, tuesdayDate, wednesdayDate, thursdayDate, fridayDate, saturdayDate,
			sundayDate};
	private DefaultListModel[] weekAppointments = { mondayModel, tuesdayModel, wednesdayModel, thursdayModel, fridayModel, saturdayModel, sundayModel}; 
	private String[] month = {"January", "February", "March","April","May","June","July","August","September","October"
			,"November", "December"};

	
	
	/**
	 * Create the panel.
	 */
	public CalendarPanel() {
		SwingWorker w = new Worker();
		w.execute();
		setLayout(null);
		
		//Lists for appointments
		JList monday = new JList(mondayModel);
		JList tuesday = new JList(thursdayModel);
		JList wednesday = new JList(wednesdayModel);
		JList thursday = new JList(thursdayModel);
		JList friday = new JList(fridayModel);
		JList saturday = new JList(saturdayModel);
		JList sunday = new JList(sundayModel);
		//end for list appointments

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(388, 5, 1, 1);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 88, 922, 27);
		panel_1.add(panel_2);
		panel_2.setBackground(new Color(143, 179, 206));
		panel_2.setLayout(null);
		
		
		
		/*JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 61, 16);
		panel_1.add(lblNewLabel);*/
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(1, 47, 940, 72);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Mainpage.class.getResource("/images/kalender_bar.png")));

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(394, 5, 1, 1);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Show", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblTrerg1 = new JLabel("");
		lblTrerg1.setBounds(15, 16, 66, 66);
		panel_3.add(lblTrerg1);
		lblTrerg1.setIcon(new ImageIcon(Mainpage.class.getResource("/images/andrekalendere2.png")));
		
		JButton btnNewButton_1 = new JButton("<html> &nbsp;\nOther </p><br>Calendars  </html>");
		btnNewButton_1.setBounds(100, 24, 109, 58);
		panel_3.add(btnNewButton_1);
		
				btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
				
				JPanel panel = new JPanel();
				panel.setBounds(3, 3, 940, 493);
				panel.setLayout(null);
				add(panel);
				
				JPanel panel_4 = new JPanel();
				panel_4.setLayout(null);
				panel_4.setBackground(new Color(143, 179, 206));
				panel_4.setBounds(6, 88, 922, 27);
				panel.add(panel_4);
				
				
				mondayDate.setHorizontalAlignment(SwingConstants.CENTER);
				mondayDate.setForeground(Color.WHITE);
				mondayDate.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				mondayDate.setBounds(20, 4, 90, 19);
				panel_4.add(mondayDate);
				
				
				tuesdayDate.setHorizontalAlignment(SwingConstants.CENTER);
				tuesdayDate.setForeground(Color.WHITE);
				tuesdayDate.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				tuesdayDate.setBounds(150, 4, 90, 19);
				panel_4.add(tuesdayDate);
				
				wednesdayDate.setHorizontalAlignment(SwingConstants.CENTER);
				wednesdayDate.setForeground(Color.WHITE);
				wednesdayDate.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				wednesdayDate.setBounds(285, 4, 90, 19);
				panel_4.add(wednesdayDate);
				
				thursdayDate.setHorizontalAlignment(SwingConstants.CENTER);
				thursdayDate.setForeground(Color.WHITE);
				thursdayDate.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				thursdayDate.setBounds(416, 4, 90, 19);
				panel_4.add(thursdayDate);
				
				fridayDate.setHorizontalAlignment(SwingConstants.CENTER);
				fridayDate.setForeground(Color.WHITE);
				fridayDate.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				fridayDate.setBounds(549, 4, 90, 19);
				panel_4.add(fridayDate);
				
				saturdayDate.setHorizontalAlignment(SwingConstants.CENTER);
				saturdayDate.setForeground(Color.WHITE);
				saturdayDate.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				saturdayDate.setBounds(681, 4, 90, 19);
				panel_4.add(saturdayDate);
				
				sundayDate.setHorizontalAlignment(SwingConstants.CENTER);
				sundayDate.setForeground(Color.WHITE);
				sundayDate.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				sundayDate.setBounds(814, 4, 90, 19);
				panel_4.add(sundayDate);
				
				JLabel label_14 = new JLabel("");
				label_14.setIcon(new ImageIcon(CalendarPanel.class.getResource("/images/kalender_bar.png")));
				label_14.setBounds(0, 47, 940, 72);
				panel.add(label_14);
				
				JButton button = new JButton("Last Week");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				button.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				button.setBounds(2, 11, 132, 29);
				panel.add(button);
				button.addActionListener(new LastWeekBtnListener());
				
				JButton button_1 = new JButton("Next Week");
				button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				button_1.setBounds(804, 11, 132, 29);
				panel.add(button_1);
				button_1.addActionListener(new NextWeekBtnListener());
				

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(8, 122, 126, 365);
				panel.add(scrollPane);
				
				//JList monday = new JList();
				scrollPane.setViewportView(monday);
				
				JScrollPane scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(141, 122, 126, 365);
				panel.add(scrollPane_1);
				
				//JList tuesday = new JList();
				scrollPane_1.setViewportView(tuesday);
				
				JScrollPane scrollPane_2 = new JScrollPane();
				scrollPane_2.setBounds(274, 122, 126, 365);
				panel.add(scrollPane_2);
				
				//JList wednesday = new JList();
				scrollPane_2.setViewportView(wednesday);
				
				JScrollPane scrollPane_4 = new JScrollPane();
				scrollPane_4.setBounds(540, 122, 126, 365);
				panel.add(scrollPane_4);
				
				//JList thursday = new JList();
				scrollPane_4.setViewportView(thursday);
				
				JScrollPane scrollPane_6 = new JScrollPane();
				scrollPane_6.setBounds(806, 122, 126, 365);
				panel.add(scrollPane_6);
				
				//JList friday = new JList();
				scrollPane_6.setViewportView(friday);
				
				JScrollPane scrollPane_5 = new JScrollPane();
				scrollPane_5.setBounds(673, 122, 126, 365);
				panel.add(scrollPane_5);
				
			//	JList saturday = new JList();
				scrollPane_5.setViewportView(saturday);
				
				JScrollPane scrollPane_3 = new JScrollPane();
				scrollPane_3.setBounds(407, 122, 126, 365);
				panel.add(scrollPane_3);
				
				//JList sunday = new JList();
				scrollPane_3.setViewportView(sunday);
			//	btnNewButton_1.addActionListener(new OtherCalendarsListener());
		
		
		
		
	}
	
	class Worker extends SwingWorker{

		@Override
		protected Object doInBackground() throws Exception {
			
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);			
			DateFormat df = new SimpleDateFormat("dd.");
			DateFormat m = new SimpleDateFormat("MM");
			int monthNum = Integer.valueOf(m.format(cal.getTime()));
			
			
			for(int i=0; i<7; i++){
				DateTime dtFrom = new DateTime(cal.get(Calendar.YEAR),monthNum, cal.get(Calendar.DAY_OF_MONTH),00,00,00);
				DateTime dtTo = new DateTime(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),23,59,00);
				System.out.println(dtFrom);
				week[i].setText(df.format(cal.getTime())+ month[monthNum-1]);
				ArrayList<Appointment> appList = new ArrayList();
				Appointment app = new Appointment(dtFrom, dtTo,"","",client.getUser());
				Object obj = client.sendObject(app, Status.TD_APP).getContent();
		
				ArrayList<Appointment> rcvd = (ArrayList<Appointment>)obj;
				for(Appointment a : rcvd){
					weekAppointments[i].addElement(a);
				}
				//System.out.println(weekAppointments[0]);
				cal.add(Calendar.DATE, 1);
			}
			
			cal.add(Calendar.DATE, -7);
			
		
			return null;
		}
		
		
	}
	
	class WeekWorker extends SwingWorker{

		@Override
		protected Object doInBackground() throws Exception {
			
			DateFormat df = new SimpleDateFormat("dd.");
			DateFormat m = new SimpleDateFormat("MM");
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			
			for(int i=6; i>=0; i--){
				int monthNum = Integer.valueOf(m.format(cal.getTime()));
				DateTime dtFrom = new DateTime(cal.get(Calendar.YEAR),monthNum, cal.get(Calendar.DAY_OF_MONTH),00,00,00);
				DateTime dtTo = new DateTime(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),23,59,00);
				ArrayList<Appointment> appList = new ArrayList();
				Appointment app = new Appointment(dtFrom, dtTo,"","",client.getUser());
				Object obj = client.sendObject(app, Status.TD_APP).getContent();
		
				ArrayList<Appointment> rcvd = (ArrayList<Appointment>)obj;
				for(Appointment a : rcvd){
					weekAppointments[i].addElement(a);
				}
				cal.add(Calendar.DATE, -1);
				System.out.println(df.format(cal.getTime()));
				monthNum = Integer.valueOf(m.format(cal.getTime()));
				week[i].setText(df.format(cal.getTime())+ month[monthNum-1]);	
			}

			
			return null;
		}
		
	}
	
	class NextWeekWorker extends SwingWorker{

		@Override
		protected Object doInBackground() throws Exception {
			//Select and show date
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			DateFormat df = new SimpleDateFormat("dd.");
			DateFormat m = new SimpleDateFormat("MM");
			cal.add(Calendar.DATE, 7);
			int monthNum = Integer.valueOf(m.format(cal.getTime()));
			for(int i=0; i<7; i++){
				//System.out.println(df.format(cal.getTime()));
				week[i].setText(df.format(cal.getTime())+ month[monthNum-1]);				
				cal.add(Calendar.DATE, 1);
			}
			cal.add(Calendar.DATE, -7);
			return null;
			
		}
		
	}
	
	private class LastWeekBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("btnpressed");
			SwingWorker ws = new WeekWorker();
			ws.execute();
			System.out.println("Done");
		}
			
	}
	
	private class NextWeekBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("btnpressed");
			SwingWorker nws = new NextWeekWorker();
			nws.execute();
			System.out.println("Done");
		}
		
	}

}
