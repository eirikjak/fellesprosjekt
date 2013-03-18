package xcal.gui;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JTextField;
import java.awt.Color;
import java.awt.CardLayout;
import org.jdesktop.swingx.JXDatePicker;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Appointment;
import xcal.model.Location;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.DropMode;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import javax.swing.border.TitledBorder;



public class AppointmentMenu extends JFrame {
	private JTextField name;
	private JTextField fromHour;
	private JTextField fromMinute;
	private JTextField location;
	private JXDatePicker datePicker;
	private JTextField toHour;
	private JTextField toMinute;
	private JTextArea description;
	private JComboBox notificationBox;
	private HashMap<String, Integer> notificationMap;
	private Client client = Client.getClient();
	
	/**
	 * Create the panel.
	 */
	
	public AppointmentMenu() {
		super();
		setTitle("New appointment");
		setPreferredSize(new Dimension(695,513));
		setVisible(true);
		
		getContentPane().setLayout(null);
		setBounds(0,0,648,519);
		
		name = new JTextField();
		name.setBounds(182, 40, 469, 31);
		getContentPane().add(name);
		name.setColumns(10);
		
		fromHour = new JTextField();
		((AbstractDocument)fromHour.getDocument()).setDocumentFilter(new TimeFieldFilter());
		fromHour.setBounds(181, 93, 57, 31);
		getContentPane().add(fromHour);
		fromHour.setColumns(10);
		fromMinute = new JTextField();
		((AbstractDocument)fromMinute.getDocument()).setDocumentFilter(new TimeFieldFilter());
		fromMinute.setColumns(10);
		fromMinute.setBounds(245, 93, 57, 31);
		getContentPane().add(fromMinute);
		
		datePicker = new JXDatePicker();
		datePicker.setBounds(547, 93, 104, 31);
		getContentPane().add(datePicker);
		
		
		location = new JTextField();
		location.setColumns(10);
		location.setBounds(179, 154, 472, 31);
		getContentPane().add(location);
		
		JLabel lblVarsel = new JLabel("Notification:");
		lblVarsel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVarsel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblVarsel.setBounds(70, 368, 92, 14);
		getContentPane().add(lblVarsel);
		
		notificationMap = new HashMap<String,Integer>();
		notificationMap.put("1 minute", 1);
		notificationMap.put("5 minutes", 5);
		notificationMap.put("10 minutes", 10);
		notificationMap.put("15 minutes", 15);
		notificationMap.put("30 minutes", 30);
		notificationMap.put("1 hour", 60);
		notificationMap.put("2 hours", 120);
		notificationMap.put("5 hours", 300);
		notificationMap.put("1 day", 1440);
		notificationBox = new JComboBox(new String[]{"1 minute","5 minutes","10 minutes","15 minutes",
				"30 minutes","1 hour","2 hours","5 hours","1 day"});
		
		
		notificationBox.setBounds(174, 361, 178, 31);
		getContentPane().add(notificationBox);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new OkButtonListener());
		saveButton.setBounds(172, 404, 181, 23);
		getContentPane().add(saveButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelButtonListener());
		cancelButton.setBounds(380, 404, 181, 23);
		getContentPane().add(cancelButton);
		
		JLabel title = new JLabel("Name:");
		title.setHorizontalAlignment(SwingConstants.RIGHT);
		title.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		title.setBounds(70, 48, 92, 14);
		getContentPane().add(title);
		
		JLabel lblTidspunkt = new JLabel("Time:");
		lblTidspunkt.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTidspunkt.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblTidspunkt.setBounds(70, 104, 92, 14);
		getContentPane().add(lblTidspunkt);
		
		JLabel lblSted = new JLabel("Location:");
		lblSted.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSted.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		lblSted.setBounds(70, 161, 92, 14);
		getContentPane().add(lblSted);
		
		description = new JTextArea();
		description.setBounds(181, 219, 470, 127);
		getContentPane().add(description);

		
		JLabel lblBeskrivelse = new JLabel("Description:");
		lblBeskrivelse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBeskrivelse.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblBeskrivelse.setBounds(70, 221, 94, 14);
		getContentPane().add(lblBeskrivelse);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label.setBounds(237, 82, 12, 49);
		getContentPane().add(label);
		
		toHour = new JTextField();
		((AbstractDocument)toHour.getDocument()).setDocumentFilter(new TimeFieldFilter());
		toHour.setColumns(10);
		toHour.setBounds(324, 93, 57, 31);
		getContentPane().add(toHour);
		
		toMinute = new JTextField();
		((AbstractDocument)toMinute.getDocument()).setDocumentFilter(new TimeFieldFilter());
		toMinute.setColumns(10);
		toMinute.setBounds(388, 93, 57, 31);
		getContentPane().add(toMinute);
		
		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label_1.setBounds(380, 78, 21, 56);
		getContentPane().add(label_1);
		
		JLabel lblTo = new JLabel("to");
		lblTo.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblTo.setBounds(306, 93, 21, 31);
		getContentPane().add(lblTo);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblDate.setBounds(504, 101, 46, 14);
		getContentPane().add(lblDate);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AppointmentMenu.class.getResource("/images/1363368791_sticky-notes.png")));
		lblNewLabel.setBounds(39, 211, 40, 37);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(AppointmentMenu.class.getResource("/images/1363368940_bell.png")));
		lblNewLabel_1.setBounds(40, 360, 40, 32);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(AppointmentMenu.class.getResource("/images/1363369079_Time.png")));
		lblNewLabel_2.setBounds(80, 82, 40, 49);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(AppointmentMenu.class.getResource("/images/1363369208_keyboard_layout.png")));
		lblNewLabel_3.setBounds(67, 151, 32, 37);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(AppointmentMenu.class.getResource("/images/1363369311_diagram-02.png")));
		lblNewLabel_4.setBounds(73, 29, 40, 42);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(AppointmentMenu.class.getResource("/images/1363369435_event.png")));
		lblNewLabel_5.setBounds(463, 87, 40, 42);
		getContentPane().add(lblNewLabel_5);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(173, 32, 484, 326);
		getContentPane().add(panel);
		
		pack();

	}
	
	
	private void Close(){
		setVisible(false);
		dispose();
	}
	

	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Close();
		}
		
		
	}
	
	
	private class OkButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//send stuff til server
			//lukk vindu når ferdig
			boolean valid = true;
			int fromHourDate = 0;
			int fromMinuteDate = 0;
			int toHourDate = 0;
			int toMinuteDate = 0;
			try{
				
				fromHourDate = Integer.parseInt(fromHour.getText());
				if(!(fromHourDate >= 0 &&  fromHourDate <24)){
					valid = false;
				}
				fromMinuteDate = Integer.parseInt(fromMinute.getText());
				if(!(fromMinuteDate >= 0 && fromMinuteDate < 60)){
					valid = false;
				}
				toHourDate = Integer.parseInt(toHour.getText());
				if(!(toHourDate >= 0 &&  toHourDate <24)){
					valid = false;
				}
				toMinuteDate = Integer.parseInt(toMinute.getText());
				if(!(toMinuteDate >= 0 && toMinuteDate < 60)){
					valid = false;
				}
			}catch(NumberFormatException e1){
				valid = false;
			}
			
			Date inputDate = datePicker.getDate();
			if(inputDate == null){
				valid = false;
				inputDate = new Date();
			}
			DateTime today = DateTime.now();
			DateTime startTime;
			DateTime endTime;
			
			
			if((DateTimeComparator.getInstance().compare(new DateTime(inputDate), today)) <= 0){
				valid = false;
			}
			
			System.out.println(valid);
			startTime = new DateTime(inputDate.getYear(),inputDate.getMonth(),inputDate.getDay(),fromHourDate,fromMinuteDate,0);
			endTime= new DateTime(inputDate.getYear(),inputDate.getMonth(),inputDate.getDay(),toHourDate,toMinuteDate,0);
			if(DateTimeComparator.getInstance().compare(startTime, endTime) > 0){
				valid = false;
			}
			
			String loc = location.getText();
			if(!(loc.length() > 0))
				valid = false;
			
			String desc = description.getText();
			String titleString = name.getText();
			if(!(titleString.length() > 0)){
				valid = false;
			}
			
			int notification = notificationMap.get(notificationBox.getSelectedItem());
			System.out.println(notification);
			
			if(valid){
				System.out.println("sending shit");
			Appointment app = new Appointment(startTime, endTime ,titleString, desc, client.getUser(), new Location(loc));
			System.out.println(app);
			Wrapper response = client.sendObject(app, Status.CREATE);
			}
			Close();
			
			
			
		}
		
	}
}
