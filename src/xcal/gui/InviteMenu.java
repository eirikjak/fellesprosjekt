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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import xcal.client.*;

public class InviteMenu extends JFrame {


	
	private Client client = Client.getClient();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InviteMenu frame = new InviteMenu();
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
	public InviteMenu() {
		setBounds(100, 100, 562, 439);
		getContentPane().setLayout(null);
		
		JLabel lblYouAreInvited = new JLabel("You are invited to a meeting");
		lblYouAreInvited.setFont(new Font("Helvetica", Font.BOLD, 14));
		lblYouAreInvited.setBounds(57, 26, 202, 29);
		getContentPane().add(lblYouAreInvited);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(InviteMenu.class.getResource("/images/andrekalendere.png")));
		lblNewLabel.setBounds(374, 43, 142, 133);


		getContentPane().add(lblNewLabel);
		
		JLabel lblInvitertAv = new JLabel("Invited by:");
		lblInvitertAv.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblInvitertAv.setBounds(67, 55, 73, 16);
		getContentPane().add(lblInvitertAv);
		
		JLabel lblKari = new JLabel("Kari");
		lblKari.setFont(new Font("Helvetica", Font.BOLD, 14));
		lblKari.setBounds(137, 55, 188, 16);
		getContentPane().add(lblKari);
		
		JLabel lblMeatingSubject = new JLabel("Coffie talk meeting");
		lblMeatingSubject.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblMeatingSubject.setBounds(57, 80, 228, 44);
		getContentPane().add(lblMeatingSubject);
		
		JLabel lblNewLabel_1 = new JLabel(" Time: 12:30 - 14:30");
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(63, 117, 218, 16);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date:  04.April.2013");
		lblNewLabel_2.setBounds(67, 136, 132, 16);
		getContentPane().add(lblNewLabel_2);
		
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
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setFont(new Font("Helvetica", Font.PLAIN, 14));
		textPane.setBounds(67, 202, 377, 114);
		getContentPane().add(textPane);

	}
}
