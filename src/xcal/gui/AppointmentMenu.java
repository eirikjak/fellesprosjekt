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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Appointment;
import xcal.model.Location;
import xcal.model.Notification;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.DropMode;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import org.jdesktop.swingx.JXBusyLabel;



public class AppointmentMenu extends JFrame implements PropertyChangeListener {
	private JTextField name;
	private JTextField startHour;
	private JTextField startMinute;
	private JTextField location;
	private JXDatePicker datePicker;
	private JTextField endHour;
	private JTextField endMinute;
	private JTextArea description;
	private JComboBox notificationBox;
	private HashMap<String, Integer> notificationMap;
	private Client client = Client.getClient();
	private JLabel errorLabel;
	private JXBusyLabel busyLabel;
	private Appointment model;

	
	/**
	 * Create the panel.
	 */
	
	public static void main(String[] args) {
		new  AppointmentMenu();
	}
	
	public AppointmentMenu() {
		super();
		model = new Appointment();
		model.addPropertyChangeListener(this);
		setTitle("New appointment");
		setPreferredSize(new Dimension(695,513));
		setVisible(true);
		
		getContentPane().setLayout(null);
		setBounds(0,0,648,519);
		
		name = new JTextField();
		name.setBounds(182, 40, 469, 31);
		getContentPane().add(name);
		name.setColumns(10);
		
		startHour = new JTextField();
		
		((AbstractDocument)startHour.getDocument()).setDocumentFilter(new TimeFieldFilter());
		startHour.setBounds(181, 93, 57, 31);
		getContentPane().add(startHour);
		startHour.setColumns(10);
		startMinute = new JTextField();
		((AbstractDocument)startMinute.getDocument()).setDocumentFilter(new TimeFieldFilter());
		startMinute.setColumns(10);
		startMinute.setBounds(245, 93, 57, 31);
		getContentPane().add(startMinute);
		datePicker = new JXDatePicker();
		datePicker.getEditor().setEditable(false);
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
		saveButton.setBounds(172, 404, 181, 23);
		saveButton.addActionListener(new OkButtonListener());
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
		
		endHour = new JTextField();
		((AbstractDocument)endHour.getDocument()).setDocumentFilter(new TimeFieldFilter());
		endHour.setColumns(10);
		endHour.setBounds(324, 93, 57, 31);
		getContentPane().add(endHour);
		
		endMinute = new JTextField();
		((AbstractDocument)endMinute.getDocument()).setDocumentFilter(new TimeFieldFilter());
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
		
		errorLabel = new JLabel("Error on appointment creation ");
		errorLabel.setFont(new Font("Dialog", Font.BOLD, 13));
		errorLabel.setForeground(Color.RED);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(182, 438, 469, 14);
		errorLabel.setVisible(false);
		getContentPane().add(errorLabel);
		
		busyLabel = new JXBusyLabel();
		busyLabel.setBounds(375, 438, 26, 26);
		busyLabel.setVisible(false);
		getContentPane().add(busyLabel);
		
		pack();
		addListeners();
		model.setFromTime(DateTime.now().plusMinutes(10));
		model.setToTime(DateTime.now().plusHours(1));
		
		
		

	}
	public void setModel(Appointment model){
		this.model.removePropertyChangeListener(this);
		this.model = model;
		this.model.addPropertyChangeListener(this);
		
		datePicker.setDate(model.getFromTime().toDate());
		startHour.setText(new Integer(model.getFromTime().getHourOfDay()).toString());
		startMinute.setText((new Integer(model.getFromTime().getMinuteOfHour()).toString()));
		endHour.setText(new Integer(model.getToTime().getHourOfDay()).toString());
		endMinute.setText(new Integer(model.getToTime().getMinuteOfHour()).toString());
		name.setText(model.getTitle());
		description.setText(model.getDescription());
		location.setText(model.getLocationName());
	}
	private void addListeners(){
		
		startHour.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				
				model.setFromHour(startHour.getText());
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
			
				
			}
		});
		startHour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					model.setFromHour(startHour.getText());
				
				
			}
		});
		
		
		startMinute.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				model.setFromMinute(startMinute.getText());
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		startMinute.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setFromMinute(startMinute.getText());
				
			}
		});
		
		endHour.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				model.setToHour(endHour.getText());
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		endHour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setToHour(endHour.getText());
				
			}
		});
		
		
		endMinute.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent arg0) {
				model.setToMinute(endMinute.getText());
				
			}
			
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		endMinute.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				model.setToMinute(endMinute.getText());
				
			}
		});
		name.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				model.setTitle(name.getText());
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		name.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setTitle(name.getText());
				
			}
		});
		
		description.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				model.setDescription(description.getText());
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				
				
			}
		});
		
		location.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				model.setLocation(new Location(location.getText()));
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		location.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setLocation(new Location(location.getText()));
				
			}
		});
		
		notificationBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int notification = notificationMap.get(notificationBox.getSelectedItem());
				model.setNotification(new Notification(model, client.getUser(),model.getFromTime().minusMinutes(notification)));
				
			}
		});
		datePicker.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg) {
				model.setDate(new DateTime(datePicker.getDate()));
				
			}
		});
		
	
		
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String pName = evt.getPropertyName();
		
		if(pName.equals(Appointment.PROPERTY_DAY)){
			datePicker.setDate(((DateTime)evt.getNewValue()).toDate());
		}else if(pName.equals(Appointment.PROPERTY_FROM_HOUR)){
			startHour.setText(((Integer)evt.getNewValue()).toString());
		}else if(pName.equals(Appointment.PROPERTY_FROM_MINUTE)){
			startMinute.setText(((Integer)evt.getNewValue()).toString());
		}else if (pName.equals(Appointment.PROPERTY_TO_HOUR)){
			endHour.setText(((Integer)evt.getNewValue()).toString());
		}else if(pName.equals(Appointment.PROPERTY_TO_MINUTE)){
			endMinute.setText(((Integer)evt.getNewValue()).toString());
		}else if (pName.equals(Appointment.PROPERTY_TITLE)){
			name.setText((String)evt.getNewValue());
		}else if (pName.equals(Appointment.PROPERTY_DESCRIPTION)){
			description.setText((String)evt.getNewValue());
		}else if (pName.equals(Appointment.PROPERTY_LOCATION)){
			location.setText(((Location)evt.getNewValue()).getName());
		
		}
	}
	
	
	
	private void Close(){
		model.removePropertyChangeListener(this);
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
			busyLabel.setVisible(true);
			busyLabel.setBusy(true);
			SwingWorker<Void , Void> worker = new SwingWorker<Void, Void>(){
				protected Void doInBackground() throws Exception {
					
					
					
					
					System.out.println("hello");
					if(model.validateFields()){
						int notification = notificationMap.get(notificationBox.getSelectedItem());
						DateTime startTime = model.getFromTime();
						DateTime endTime = model.getToTime();
						String title = model.getTitle();
						String desc = model.getDescription();
						String loc = model.getLocationName();
						Appointment app = new Appointment(startTime, endTime ,title, desc, client.getUser(), new Location(loc));
						Notification notificationObj = new Notification(app,Client.getClient().getUser());
						notificationObj.setNotificationTime(startTime.minusMinutes(notification));
						app.setNotification(notificationObj);
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
							busyLabel.setBusy(false);
							busyLabel.setVisible(false);
							
						}
						return null;
				}

			};
			worker.execute();
			
			
			
			
			
		}
		
	}


	
}
