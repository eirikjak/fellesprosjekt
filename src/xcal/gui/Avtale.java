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

import javax.swing.BorderFactory;
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


public class Avtale extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	
	private JTextField textField_3;
	private JTextField textField_4;
	/**
	 * Create the panel.
	 */
	public Avtale() {
		super();
		setPreferredSize(new Dimension(570,513));
		setVisible(true);
		
		setLayout(null);
		setBounds(0,0,570,513);
		
		textField = new JTextField();
		textField.setBounds(97, 40, 417, 31);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(99, 93, 57, 31);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(175, 93, 57, 31);
		add(textField_2);
		
		JXDatePicker datePicker = new JXDatePicker();
		datePicker.setBounds(446, 93, 68, 31);
		add(datePicker);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(97, 154, 417, 31);
		add(textField_5);
		
		JLabel lblVarsel = new JLabel("Notification:");
		lblVarsel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblVarsel.setBounds(4, 357, 91, 14);
		add(lblVarsel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(97, 357, 116, 20);
		add(comboBox);
		
		JButton btnNewButton = new JButton("Lagre");
		btnNewButton.addActionListener(new OkButtonListener());
		btnNewButton.setBounds(97, 404, 181, 23);
		add(btnNewButton);
		
		JButton btnAvbryt = new JButton("Avbryt");
		btnAvbryt.addActionListener(new CancelButtonListener());
		btnAvbryt.setBounds(301, 404, 181, 23);
		add(btnAvbryt);
		
		JLabel lblNavn = new JLabel("Name:");
		lblNavn.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNavn.setBounds(4, 46, 57, 14);
		add(lblNavn);
		
		JLabel lblTidspunkt = new JLabel("Time:");
		lblTidspunkt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblTidspunkt.setBounds(4, 99, 77, 14);
		add(lblTidspunkt);
		
		JLabel lblSted = new JLabel("Location:");
		lblSted.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSted.setBounds(4, 162, 77, 14);
		add(lblSted);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(97, 219, 417, 127);
		add(textArea);
		
		JLabel lblBeskrivelse = new JLabel("Description:");
		lblBeskrivelse.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblBeskrivelse.setBounds(4, 219, 91, 14);
		add(lblBeskrivelse);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		label.setBounds(159, 75, 102, 56);
		add(label);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(257, 93, 57, 31);
		add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(333, 93, 57, 31);
		add(textField_4);
		
		JLabel label_1 = new JLabel(":");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		label_1.setBounds(317, 75, 102, 56);
		add(label_1);
		
		JLabel label_2 = new JLabel("-");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		label_2.setBounds(239, 76, 102, 56);
		add(label_2);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDate.setBounds(400, 101, 46, 14);
		add(lblDate);
		
		pack();

	}
	
	
	private class CancelButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	private class OkButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//send stuff til server
			//lukk vindu når ferdig
			
			
			
		}
		
	}
	
}
