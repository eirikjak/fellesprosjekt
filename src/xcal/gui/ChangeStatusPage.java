package xcal.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Meeting;

public class ChangeStatusPage extends JFrame {

	private JPanel contentPane;
	private Client client = Client.getClient();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeStatusPage frame = new ChangeStatusPage(null);
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
	public ChangeStatusPage(final Meeting m) {
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 142);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Change Status To", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 6, 581, 108);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnAccepted = new JButton("Accepted");
		btnAccepted.setBounds(55, 46, 117, 29);
		panel.add(btnAccepted);
		btnAccepted.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Object[] l = {m,client.getUser()};
				Object o = client.sendObject(l, Status.INVITE_ACCEPTED);
				//System.out.println(((Wrapper) o).getFlag());
				dispose();
				new MeetingPage(m);
				
				
			}
			
		});
		
		JButton btnDeclined = new JButton("Declined");
		btnDeclined.setBounds(220, 46, 117, 29);
		panel.add(btnDeclined);
		btnDeclined.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Object[] l = {m,client.getUser()};
				Object o = client.sendObject(l, Status.INVITE_DECLINED);
				//System.out.println(((Wrapper) o).getFlag());
				dispose();
				new MeetingPage(m);
				
			}
			
		});
		
		JButton btnNotAnswered = new JButton("Not Answered");
		btnNotAnswered.setBounds(406, 46, 117, 29);
		panel.add(btnNotAnswered);
		btnNotAnswered.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Object[] l = {m,client.getUser()};
				Object o = client.sendObject(l, Status.INVITE_NOANS);
				//System.out.println(((Wrapper) o).getFlag());
				dispose();
				new MeetingPage(m);
				
				
			}
			
		});
	}

}
