package xcal.gui;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.SwingWorker;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import xcal.client.*;
import xcal.model.Invite;
import xcal.model.Meeting;

public class InviteMenu extends JFrame {


	
	private Client client = Client.getClient();
	/**
	 * Launch the application.
	 */
	
	private Invite model;
	private JLabel time;
	private JLabel date;
	private JTextPane description;
	private JLabel leader;
	private JLabel title;
	
	

	/**
	 * Create the frame.
	 */
	public InviteMenu() {
		this.model = new Invite();
		setVisible(true);
		setBounds(100, 100, 562, 439);
		getContentPane().setLayout(null);
		
		JLabel lblYouAreInvited = new JLabel("You are invited to a meeting");
		lblYouAreInvited.setFont(new Font("Dialog", Font.BOLD, 15));
		lblYouAreInvited.setBounds(57, 26, 202, 29);
		getContentPane().add(lblYouAreInvited);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(InviteMenu.class.getResource("/images/andrekalendere.png")));
		lblNewLabel.setBounds(374, 43, 142, 133);


		getContentPane().add(lblNewLabel);
		
		JLabel lblInvitertAv = new JLabel("Invited by:");
		lblInvitertAv.setFont(new Font("Dialog", Font.BOLD, 13));
		lblInvitertAv.setBounds(57, 55, 73, 16);
		getContentPane().add(lblInvitertAv);
		
		leader = new JLabel("Kari");
		leader.setFont(new Font("Helvetica", Font.BOLD, 14));
		leader.setBounds(127, 55, 188, 16);
		getContentPane().add(leader);
		
		title = new JLabel("Coffie talk meeting");
		title.setFont(new Font("Helvetica", Font.BOLD, 18));
		title.setBounds(57, 80, 228, 44);
		getContentPane().add(title);
		
		time = new JLabel(" Time: 12:30 - 14:30");
		time.setFont(new Font("Dialog", Font.PLAIN, 13));
		time.setBounds(58, 118, 218, 16);
		getContentPane().add(time);
		
		date = new JLabel("Date:  04.April.2013");
		date.setFont(new Font("Dialog", Font.PLAIN, 13));
		date.setBounds(62, 135, 132, 16);
		getContentPane().add(date);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Helvetica", Font.BOLD, 14));
		lblDescription.setBounds(57, 174, 158, 16);
		getContentPane().add(lblDescription);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Wrapper answer = new Wrapper(Status.ACCEPT, )
				//client.sendObject(o, s);
			}
		});
		btnAccept.setBounds(62, 325, 117, 29);
		getContentPane().add(btnAccept);
		
		JButton btnDecline = new JButton("Decline");
		btnDecline.setBounds(185, 325, 117, 29);
		getContentPane().add(btnDecline);
		
		description = new JTextPane();
		description.setEditable(false);
		description.setFont(new Font("Helvetica", Font.PLAIN, 14));
		description.setBounds(67, 202, 377, 114);
		getContentPane().add(description);
		
		
		btnDecline.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						model.setAnswer(1);
						model.setEmployee(client.getUser());
						client.sendObject(model, Status.UPDATE);
						return null;
					}
					
				}.execute();
				
				setVisible(false);
				dispose();
			}
		});
		
		btnAccept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						model.setAnswer(1);
						model.setEmployee(client.getUser());
						client.sendObject(model, Status.UPDATE);
						return null;
					}
					
				}.execute();
				
				setVisible(false);
				dispose();
				
			}
		});

	}
	
	public void setModel(Invite invite){
		this.model = invite;
		DateTimeFormatter timeFormat= DateTimeFormat.forPattern("H:m");
		DateTimeFormatter dateFormat = DateTimeFormat.forPattern("Y-M-d");
		Meeting meeting = invite.getMeeting();
		title.setText(meeting.getTitle());
		leader.setText(meeting.getLeader().getName());
		time.setText("Time: " +timeFormat.print(meeting.getFromTime()));
		date.setText("Date:" + dateFormat.print(meeting.getFromTime()));
		description.setText(meeting.getDescription());
		
		
		
	}
}
