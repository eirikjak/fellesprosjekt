package xcal.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import xcal.model.Person;

import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;





public class MeetingMenu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	private JTextField textField_3;
	private JTextField textField_4;
	private DefaultListModel model;
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
		setTitle("New meeting");
		setPreferredSize(new Dimension(710, 700));
		setVisible(true);
		
		getContentPane().setLayout(null);
		setBounds(0,0,570,666);
		

		textField = new JTextField();
		textField.setBounds(180, 40, 469, 31);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(180, 93, 57, 31);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(245, 93, 57, 31);
		getContentPane().add(textField_2);
		
		JXDatePicker datePicker = new JXDatePicker();
		datePicker.setBounds(547, 93, 104, 31);
		getContentPane().add(datePicker);
		
		JLabel lblVarsel = new JLabel("Notification:");
		lblVarsel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblVarsel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblVarsel.setBounds(70, 576, 92, 14);
		getContentPane().add(lblVarsel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(174, 569, 178, 31);
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new OkButtonListener());
		btnNewButton.setBounds(172, 612, 181, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnAvbryt = new JButton("Cancel");
		btnAvbryt.addActionListener(new CancelButtonListener());
		btnAvbryt.setBounds(393, 612, 181, 23);
		getContentPane().add(btnAvbryt);
		
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(181, 219, 470, 127);
		getContentPane().add(textArea);
		
		JLabel lblBeskrivelse = new JLabel("Description:");
		lblBeskrivelse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBeskrivelse.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblBeskrivelse.setBounds(70, 221, 94, 14);
		getContentPane().add(lblBeskrivelse);
		
		JLabel label = new JLabel(":");
		label.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label.setBounds(237, 82, 12, 49);
		getContentPane().add(label);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(324, 93, 57, 31);
		getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(388, 93, 57, 31);
		getContentPane().add(textField_4);
		
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
		
		JList list = new JList();
		list.setBounds(183, 406, -148, 78);
		getContentPane().add(list);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(MeetingMenu.class.getResource("/images/1363370401_arrow.png")));
		button.setBounds(379, 413, 68, 35);
		getContentPane().add(button);
		
		JList list_2 = new JList();
		list_2.setBounds(465, 397, 185, 133);
		getContentPane().add(list_2);
		
		JList list_1 = new JList();
		list_1.setBounds(182, 397, 178, 133);
		getContentPane().add(list_1);
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(MeetingMenu.class.getResource("/images/1363370401_arrow copy.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setBounds(379, 469, 68, 35);
		getContentPane().add(button_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(180, 156, 178, 31);
		getContentPane().add(comboBox_1);
		
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
		
		pack();
	}
	
	public void setModel(DefaultListModel model){
		
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
