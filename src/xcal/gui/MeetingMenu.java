package xcal.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;
import org.joda.time.DateTime;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Appointment;
import xcal.model.Employee;
import xcal.model.Location;
import xcal.model.Meeting;
import xcal.model.Notification;
import xcal.model.Room;

import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JScrollPane;

public class MeetingMenu extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField startHour;
	private JTextField startMinute;

	private JTextField endHour;
	private JTextField endMinute;
	private DefaultListModel model;
	private JXDatePicker datePicker;
	private JComboBox locationBox;
	private JTextArea description;
	private HashMap<String, Integer> notificationMap;
	private JComboBox notificationBox;
	private JLabel errorLabel ;
	private Client client;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeetingMenu frame = new MeetingMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MeetingMenu() {
		super();
		this.client = Client.getClient();
		setTitle("New meeting");
		setPreferredSize(new Dimension(710, 700));
		setVisible(true);
		
		getContentPane().setLayout(null);
		setBounds(0,0,570,666);
		name = new JTextField();
		name.setBounds(180, 40, 469, 31);
		getContentPane().add(name);
		name.setColumns(10);
		
		startHour = new JTextField();
		startHour.setBounds(180, 93, 57, 31);
		getContentPane().add(startHour);
		startHour.setColumns(10);
		
		startMinute = new JTextField();
		startMinute.setColumns(10);
		startMinute.setBounds(245, 93, 57, 31);
		getContentPane().add(startMinute);
		
		datePicker = new JXDatePicker();
		datePicker.addActionListener(new DatePickerListener());
		datePicker.setBounds(547, 93, 104, 31);
		datePicker.getEditor().setEditable(false);
		getContentPane().add(datePicker);
		
		JLabel lblVarsel = new JLabel("Notification:");
		lblVarsel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVarsel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblVarsel.setBounds(70, 576, 92, 14);
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
		notificationBox.setBounds(174, 569, 178, 31);
		getContentPane().add(notificationBox);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new OkButtonListener());
		btnSave.setBounds(172, 612, 181, 23);
		getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelButtonListener());
		btnCancel.setBounds(393, 612, 181, 23);
		getContentPane().add(btnCancel);
		
		JLabel lblNavn = new JLabel("Name:");
		lblNavn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNavn.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNavn.setBounds(70, 48, 92, 14);
		getContentPane().add(lblNavn);
		
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
		
		endHour = new JTextField();
		endHour.setColumns(10);
		endHour.setBounds(324, 93, 57, 31);
		getContentPane().add(endHour);
		
		endMinute = new JTextField();
		endMinute.setColumns(10);
		endMinute.setBounds(388, 93, 57, 31);
		getContentPane().add(endMinute);
		
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
		lblNewLabel_1.setBounds(40, 568, 40, 32);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(AppointmentMenu.class.getResource("/images/1363369079_Time.png")));
		lblNewLabel_2.setBounds(80, 82, 40, 49);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(MeetingMenu.class.getResource("/images/1363370152_door.png")));
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
		
		JButton addButton = new JButton("");
		addButton.setIcon(new ImageIcon(MeetingMenu.class.getResource("/images/1363370401_arrow.png")));
		addButton.setBounds(379, 413, 68, 35);
		getContentPane().add(addButton);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(465, 397, 185, 133);
		getContentPane().add(scrollPane_1);
		
		JList list_2 = new JList();
		scrollPane_1.setViewportView(list_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(182, 397, 178, 133);
		getContentPane().add(scrollPane);
		
		JList list_1 = new JList();
		scrollPane.setViewportView(list_1);
		
		JButton removeButton = new JButton("");
		removeButton.setIcon(new ImageIcon(MeetingMenu.class.getResource("/images/1363370401_arrow copy.png")));
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		removeButton.setBounds(379, 469, 68, 35);
		getContentPane().add(removeButton);
		
		locationBox = new JComboBox(new String[]{"Please select time and date"});
		locationBox.setBounds(180, 156, 188, 31);
		
		getContentPane().add(locationBox);
		
		JLabel lblNewLabel_6 = new JLabel("Select:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_6.setBounds(101, 371, 61, 16);
		getContentPane().add(lblNewLabel_6);
		
		ButtonGroup buttonGrioup = new ButtonGroup();
		
		JToggleButton tglbtnPersons = new JToggleButton("Persons");
		tglbtnPersons.setBounds(176, 365, 100, 31);
		getContentPane().add(tglbtnPersons);
		buttonGrioup.add(tglbtnPersons);
		tglbtnPersons.setSelected(true);
		
		JToggleButton tglbtnGroups = new JToggleButton("Groups");
		tglbtnGroups.setBounds(263, 365, 100, 31);
		getContentPane().add(tglbtnGroups);
		buttonGrioup.add(tglbtnGroups);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(MeetingMenu.class.getResource("/images/1363371142_Person_Undefined_Male_Light.png")));
		lblNewLabel_7.setBounds(73, 355, 32, 38);
		getContentPane().add(lblNewLabel_7);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(172, 33, 489, 98);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(172, 211, 489, 143);
		getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(662, 529, -493, -165);
		getContentPane().add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(172, 362, 489, 178);
		getContentPane().add(panel_3);
		
		errorLabel = new JLabel("");
		errorLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		errorLabel.setForeground(Color.RED);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(180, 646, 469, 14);
		getContentPane().add(errorLabel);
		
		pack();
	}
	
	public void setModel(DefaultListModel model){
		
	}
	
	
	public void updateRoomsList(){
		Meeting meeting = new Meeting();
		meeting.setFromTime(new DateTime(datePicker.getDate()));
		meeting.setToTime(new DateTime(datePicker.getDate()));
		
		Wrapper response = client.sendObject(meeting, Status.GET_AVALIABLE_ROOMS);
		Room[] rooms = (Room[]) response.getContent();
		
		for (int i = 0; i <rooms.length; i++){
			System.out.println(rooms[i]);
		}
	}
	
	
	private class TimeFieldListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			
			
		}
		
	}
	private class DatePickerListener implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			updateRoomsList();
		}
		
	}
	private void Close(){
		setVisible(false);
		dispose();
	}
	
	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			
			Close();
			
		}
		
		
	}
	
	private class OkButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			//send stuff til server
			//lukk vindu når ferdig
			
			SwingWorker<Void , Void> worker = new SwingWorker<Void, Void>(){
				protected Void doInBackground() throws Exception {
					boolean valid = true;
					int fromHourDate = 0;
					int fromMinuteDate = 0;
					int toHourDate = 0;
					int toMinuteDate = 0;
					try{
						
						fromHourDate = Integer.parseInt(startHour.getText());
						if(!(fromHourDate >= 0 &&  fromHourDate <24)){
							valid = false;
						}
						fromMinuteDate = Integer.parseInt(startMinute.getText());
						if(!(fromMinuteDate >= 0 && fromMinuteDate < 60)){
							valid = false;
						}
						toHourDate = Integer.parseInt(endHour.getText());
						if(!(toHourDate >= 0 &&  toHourDate <24)){
							valid = false;
						}
						toMinuteDate = Integer.parseInt(endMinute.getText());
						if(!(toMinuteDate >= 0 && toMinuteDate < 60)){
							valid = false;
						}
					}catch(NumberFormatException e1){
						valid = false;
					}
					
					DateTime inputDate = null;
					if(datePicker.getDate()== null){
						valid = false;
						inputDate = new DateTime();
					}else{
						
						inputDate = new DateTime(datePicker.getDate());
					}
					DateTime today = DateTime.now();
					DateTime startTime;
					DateTime endTime;
					
					System.out.println(today);
					
					startTime = new DateTime(inputDate.getYear(),inputDate.getMonthOfYear(),inputDate.getDayOfMonth(),fromHourDate,fromMinuteDate,0);
					endTime= new DateTime(inputDate.getYear(),inputDate.getMonthOfYear(),inputDate.getDayOfMonth(),toHourDate,toMinuteDate,0);
					if(!today.isBefore(startTime)){
						valid = false;
					}
					if(!startTime.isBefore(endTime)){
						
						valid = false;
					}
					
					String loc = ((Room)locationBox.getSelectedItem()).getName();
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
					Notification notificationObj = new Notification(app,Client.getClient().getUser());
					notificationObj.setNotificationTime(startTime.minusMinutes(notification));
					app.setNotification(notificationObj);
					System.out.println(app);
					Wrapper response = client.sendObject(app, Status.CREATE);
					
					if(response.getFlag() != Status.SUCCESS){
						errorLabel.setText("Error on appointment creation ");
						errorLabel.setVisible(true);
					}else{
						if(response.getFlag() == Status.SUCCESS){
							Close();
						}
					}
					}else{
						errorLabel.setText("One or more invalid fields");
						errorLabel.setVisible(true);
						
					}
					return null;
				}

			};
			worker.execute();
			
			
			
			
			
		}
		
	}
}
