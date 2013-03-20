package xcal.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.text.DateFormatter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;

import javax.swing.JScrollPane;
import xcal.model.*;

public class CalendarPanel extends JPanel {
	private Client client;
	private Calendar tCal = Calendar.getInstance();
	private Calendar cal = Calendar.getInstance();
	private final static int currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
	private int shownWeek = cal.get(Calendar.WEEK_OF_YEAR);
	
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
		//Lists for appointments
		JList monday = new JList(mondayModel);
		monday.setCellRenderer(new CalendarPanelRenderer());
		JList tuesday = new JList(tuesdayModel);
		tuesday.setCellRenderer(new CalendarPanelRenderer());

		JList wednesday = new JList(wednesdayModel);
		wednesday.setCellRenderer(new CalendarPanelRenderer());
		JList thursday = new JList(thursdayModel);
		thursday.setCellRenderer(new CalendarPanelRenderer());
		JList friday = new JList(fridayModel);
		friday.setCellRenderer(new CalendarPanelRenderer());
		JList saturday = new JList(saturdayModel);
		saturday.setCellRenderer(new CalendarPanelRenderer());
		JList sunday = new JList(sundayModel);
		sunday.setCellRenderer(new CalendarPanelRenderer());

		//end for list appointments
				
		client = Client.getClient();
		SwingWorker w = new Worker();
		w.execute();
		setLayout(null);
		
		

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
		scrollPane.setViewportView(monday);
		panel.add(scrollPane);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(141, 122, 126, 365);
		scrollPane_1.setViewportView(tuesday);
		panel.add(scrollPane_1);
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(274, 122, 126, 365);
		scrollPane_2.setViewportView(wednesday);
		panel.add(scrollPane_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(407, 122, 126, 365);
		scrollPane_3.setViewportView(thursday);
		panel.add(scrollPane_3);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(540, 122, 126, 365);
		scrollPane_4.setViewportView(friday);
		panel.add(scrollPane_4);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(806, 122, 126, 365);
		scrollPane_6.setViewportView(sunday);
		panel.add(scrollPane_6);	
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(673, 122, 126, 365);
		panel.add(scrollPane_5);
		
		scrollPane_5.setViewportView(saturday);
		
	
	
	
		
		
	}
	
	class Worker extends SwingWorker<Void,Void>{

		private ArrayList<ArrayList<Appointment>> result;
		@Override
		protected Void doInBackground() throws Exception {
			/*
			 * Formatting the dates of days labels
			 */

			//ArrayList<Appointment> rcvd = null;

			DateFormat df = new SimpleDateFormat("dd.");
			DateFormat m = new SimpleDateFormat("MM");
			
			DateTime cal = new DateTime();
			cal = cal.withWeekOfWeekyear(shownWeek);
			cal = cal.withDayOfWeek(1);

			result = new ArrayList<ArrayList<Appointment>>();
			result.add(0, null);result.add(1, null);result.add(2, null);result.add(3, null);
			result.add(4, null);result.add(5, null);result.add(6, null);result.add(7, null);
			
			for (int i=0; i < 7; i++){
				Date c = cal.toDate();
				DateTime dtFrom = new DateTime(cal.getYear(),cal.getMonthOfYear(),cal.getDayOfMonth(),00,00,00);
				DateTime dtTo = new DateTime(cal.getYear(),cal.getMonthOfYear(),cal.getDayOfMonth(),23,59,59);
				int monthNum = Integer.valueOf(m.format(c));
				weekAppointments[i].clear();
				week[i].setText(df.format(c) + month[monthNum-1]);
				Appointment app = new Appointment(dtFrom, dtTo,"","",client.getUser());
				Object obj = client.sendObject(app, Status.TD_APP).getContent();		
				result.set(i, (ArrayList<Appointment>) ((ArrayList<Appointment>)obj).clone());
				cal = cal.plusDays(1);
			}
			return null;
		}
		@Override
		protected void done() {
			DateTimeFormatter cellDateFormatter = DateTimeFormat.forPattern("Y-M-d H:m");
			for(int i = 0; i < 7; i++){
				ArrayList<Appointment> day = result.get(i);
				if(day != null){
					for(Appointment app: day){
						weekAppointments[i].addElement(app);
					}
				}
			}
			super.done();
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
			shownWeek = shownWeek -1;
			//SwingWorker ws = new WeekWorker();
			//ws.execute();
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
