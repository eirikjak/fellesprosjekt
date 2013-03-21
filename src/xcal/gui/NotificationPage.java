package xcal.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Notification;

public class NotificationPage extends JFrame {

	private JPanel contentPane;
	private Client client=Client.getClient();
	private Wrapper response;
	private Notification notification;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotificationPage frame = new NotificationPage(null);
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
	public NotificationPage(Notification noti) {
		
		this.setVisible(true);
		notification=noti;
		//if this is string type...
		int appointment_hour=notification.getAppointment().getFromTime().getHourOfDay();
		int appointment_min=notification.getAppointment().getFromTime().getMinuteOfHour();
				
		int appointment_month=notification.getAppointment().getFromTime().getMonthOfYear();
		int appointment_day=notification.getAppointment().getFromTime().getDayOfMonth();
		int appointment_year=notification.getAppointment().getFromTime().getYear();
		//end string types
		
		
		
		
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 310);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Panel.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(NotificationPage.class.getResource("/images/1363730336_Warning.png")));
		lblNewLabel.setBounds(354, 110, 134, 128);
		contentPane.add(lblNewLabel);
		
		JLabel lblNotification = new JLabel("Notification");
		lblNotification.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		lblNotification.setBounds(62, 57, 140, 27);
		contentPane.add(lblNotification);
		
		JLabel lblNewLabel_1 = new JLabel(noti.getAppointment().getTitle());
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_1.setBounds(72, 110, 227, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblStarts = new JLabel("Starts time:");
		lblStarts.setBounds(73, 148, 80, 16);
		contentPane.add(lblStarts);
		
		
		
		JLabel lblNewLabel_2 = new JLabel(appointment_hour+":"+appointment_min);//print from time
		lblNewLabel_2.setBounds(158, 148, 74, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Start date:");
		lblNewLabel_3.setBounds(72, 168, 81, 21);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(appointment_day+"."+appointment_month+"."+appointment_year);
		lblNewLabel_4.setBounds(158, 170, 74, 16);
		contentPane.add(lblNewLabel_4);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(53, 105, 243, 98);
		contentPane.add(panel);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(51, 215, 243, 35);
		
		//what happens when accepted
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				response=client.sendObject(notification, Status.DESTROY);
				
				if(response.getFlag()==Status.SUCCESS)
					System.out.println("notification deleted");
					
				
				//exit jframe
				setVisible(false);
				dispose(); 
				
			}
		});
		contentPane.add(btnOk);
		

	}
}
