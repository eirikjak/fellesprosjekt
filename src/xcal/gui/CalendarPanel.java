package xcal.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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

public class CalendarPanel extends JPanel {
	private Client client = Client.getClient();
	private Calendar cal = Calendar.getInstance();
	private JLabel mondayDate = new JLabel();
	private JLabel tuesdayDate = new JLabel();
	private JLabel wednesdayDate = new JLabel();
	private JLabel thursdayDate = new JLabel();
	private JLabel fridayDate = new JLabel();
	private JLabel saturdayDate = new JLabel();
	private JLabel sundayDate = new JLabel();
	private JLabel monthLbl = new JLabel();
	private JLabel[] week = {mondayDate, tuesdayDate, wednesdayDate, thursdayDate, fridayDate, saturdayDate,
			sundayDate};
	private String[] month = {"January", "February", "March","April","May","June","July","August","September","October"
			,"November", "December"};
	
	
	/**
	 * Create the panel.
	 */
	public CalendarPanel() {
		//System.out.println(cal.get(Calendar.DAY_OF_YEAR)+ "THIS IS THE DATE");
		
		/*
		 * SETTING THE DATES OF LABELS AND MONTH OF MAIN LABEL
		 */
		
		
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
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 61, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(1, 47, 940, 72);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Mainpage.class.getResource("/images/kalender_bar.png")));
		
		JButton btnLastWeek = new JButton("Last Week");
		btnLastWeek.setBounds(2, 11, 132, 29);
		panel_1.add(btnLastWeek);
		btnLastWeek.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnLastWeek.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				System.out.println("btnpressed");
				cal.set(Calendar.DAY_OF_WEEK, cal.get(Calendar.DAY_OF_WEEK));
				monthLbl.setText(month[cal.get(Calendar.MONTH)]);
				DateFormat df = new SimpleDateFormat("dd/MM");
				for(int i=0; i<7; i++){
					//System.out.println(df.format(cal.getTime()));
					week[i].setText(df.format(cal.getTime()));				
					cal.add(Calendar.DATE, 1);
				}
			}
		});
		
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
				button.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				button.setBounds(2, 11, 132, 29);
				panel.add(button);
				
				JButton button_1 = new JButton("Next Week");
				button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				button_1.setBounds(804, 11, 132, 29);
				panel.add(button_1);
				
				
				monthLbl.setHorizontalAlignment(SwingConstants.CENTER);
				monthLbl.setForeground(new Color(35, 103, 174));
				monthLbl.setFont(new Font("Lucida Grande", Font.BOLD, 28));
				monthLbl.setBounds(334, 5, 233, 39);
				panel.add(monthLbl);
				
				JList monday = new JList();
				monday.setBounds(8, 122, 126, 365);
				panel.add(monday);
				
				JList tuesday = new JList();
				tuesday.setBounds(141, 122, 126, 365);
				panel.add(tuesday);
				
				JList wednesday = new JList();
				wednesday.setBounds(274, 122, 126, 365);
				panel.add(wednesday);
				
				JList thursday = new JList();
				thursday.setBounds(540, 122, 126, 365);
				panel.add(thursday);
				
				JList friday = new JList();
				friday.setBounds(806, 122, 126, 365);
				panel.add(friday);
				
				JList saturday = new JList();
				saturday.setBounds(673, 122, 126, 365);
				panel.add(saturday);
				
				JList sunday = new JList();
				sunday.setBounds(407, 122, 126, 365);
				panel.add(sunday);
			//	btnNewButton_1.addActionListener(new OtherCalendarsListener());
		
		
		
		
	}
	
	class Worker extends SwingWorker{

		@Override
		protected Object doInBackground() throws Exception {
			cal.set(Calendar.DAY_OF_WEEK, cal.get(Calendar.DAY_OF_WEEK));
			monthLbl.setText(month[cal.get(Calendar.MONTH)]);
			DateFormat df = new SimpleDateFormat("dd/MM");
			for(int i=0; i<7; i++){
				//System.out.println(df.format(cal.getTime()));
				week[i].setText(df.format(cal.getTime()));				
				cal.add(Calendar.DATE, 1);
			}
			
			
			
			return null;
		}
		
	}

}
