package xcal.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import xcal.model.Person;

import javax.swing.JList;





public class MeetingMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;

	private JTextField textField_3;
	private JTextField textField_4;
	private DefaultListModel<Person> model;
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
		setPreferredSize(new Dimension(570, 700));
		setVisible(true);
		
		getContentPane().setLayout(null);
		setBounds(0,0,570,666);
		
		textField = new JTextField();
		textField.setBounds(97, 40, 417, 31);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(99, 93, 57, 31);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(175, 93, 57, 31);
		getContentPane().add(textField_2);
		
		JXDatePicker datePicker = new JXDatePicker();
		datePicker.setBounds(446, 93, 68, 31);
		getContentPane().add(datePicker);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(97, 154, 417, 31);
		getContentPane().add(textField_5);
		
		JLabel lblVarsel = new JLabel("Notification:");
		lblVarsel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblVarsel.setBounds(4, 581, 91, 14);
		getContentPane().add(lblVarsel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(97, 581, 116, 20);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Lagre");
		btnNewButton.addActionListener(new OkButtonListener());
		btnNewButton.setBounds(97, 628, 181, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnAvbryt = new JButton("Avbryt");
		btnAvbryt.addActionListener(new CancelButtonListener());
		btnAvbryt.setBounds(301, 628, 181, 23);
		getContentPane().add(btnAvbryt);
		
		JLabel lblNavn = new JLabel("Name:");
		lblNavn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNavn.setBounds(4, 46, 57, 14);
		getContentPane().add(lblNavn);
		
		JLabel lblTidspunkt = new JLabel("Time:");
		lblTidspunkt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTidspunkt.setBounds(4, 99, 77, 14);
		getContentPane().add(lblTidspunkt);
		
		JLabel lblSted = new JLabel("Location:");
		lblSted.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSted.setBounds(4, 162, 77, 14);
		getContentPane().add(lblSted);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(97, 219, 417, 127);
		getContentPane().add(textArea);
		
		JLabel lblBeskrivelse = new JLabel("Description:");
		lblBeskrivelse.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblBeskrivelse.setBounds(4, 219, 91, 14);
		getContentPane().add(lblBeskrivelse);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		label.setBounds(159, 75, 102, 56);
		getContentPane().add(label);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(257, 93, 57, 31);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(333, 93, 57, 31);
		getContentPane().add(textField_4);
		
		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		label_1.setBounds(317, 75, 102, 56);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("-");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		label_2.setBounds(239, 76, 102, 56);
		getContentPane().add(label_2);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(400, 101, 46, 14);
		getContentPane().add(lblDate);
		
		JList list = new JList();
		list.setBounds(183, 406, -148, 78);
		getContentPane().add(list);
		
		JButton button = new JButton("->");
		button.setBounds(270, 426, 68, 23);
		getContentPane().add(button);
		
		JList list_2 = new JList();
		list_2.setBounds(351, 386, 163, 133);
		getContentPane().add(list_2);
		
		JList list_1 = new JList();
		list_1.setBounds(97, 386, 163, 133);
		getContentPane().add(list_1);
		
		JButton button_1 = new JButton("<-");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setBounds(270, 455, 68, 23);
		getContentPane().add(button_1);
		
		pack();
	}
	
	public void setModel(DefaultListModel<Person> model){
		
	}
	
	private void Close(){
		this.Close();
	}
	
	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Close();
			
		}
		
		
	}
	
	private class OkButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//send stuff til server
			//lukk vindu når ferdig
			Close();
			
			
			
		}
		
	}
}
