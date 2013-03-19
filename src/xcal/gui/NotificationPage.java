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

public class NotificationPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotificationPage frame = new NotificationPage();
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
	public NotificationPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 310);
		contentPane = new JPanel();
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
		
		JLabel lblNewLabel_1 = new JLabel("M\u00F8te tittel");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_1.setBounds(72, 110, 227, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblStarts = new JLabel("Starts time:");
		lblStarts.setBounds(73, 148, 80, 16);
		contentPane.add(lblStarts);
		
		JLabel lblNewLabel_2 = new JLabel("Tiden");
		lblNewLabel_2.setBounds(158, 148, 74, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Start date:");
		lblNewLabel_3.setBounds(72, 168, 81, 21);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Datoen");
		lblNewLabel_4.setBounds(158, 170, 74, 16);
		contentPane.add(lblNewLabel_4);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(53, 105, 243, 98);
		contentPane.add(panel);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(51, 215, 243, 35);
		contentPane.add(btnOk);
	}
}
