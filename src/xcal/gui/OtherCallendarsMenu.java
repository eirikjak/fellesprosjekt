package xcal.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.border.TitledBorder;

import xcal.client.*;
import xcal.model.Employee;
import xcal.server.query.EmployeeQ;

public class OtherCallendarsMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtherCallendarsMenu frame = new OtherCallendarsMenu();
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
	public OtherCallendarsMenu() {
		Client client = Client.getClient();
		setTitle("Add other calendars");
		this.setVisible(true);
	
		setBounds(0, 0, 570, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final DefaultListModel model = new DefaultListModel();
		Employee e = new Employee();
		Object o = client.sendObject(e, Status.GET_ALL).getContent();
		ArrayList<Employee> empList = ((ArrayList<Employee>)o);
		for(int i=0; i<empList.size(); i++){
			if(empList.get(i) != client.getUser()){
				model.addElement(empList.get(i));
			}
			
		}
		
		final DefaultListModel model1 = new DefaultListModel();
		model1.addElement(client.getUser());
		
		
		
		final JList list_1 = new JList(model);
		//list_1.setListData(EmployeeQ.getAllEmployees());
		list_1.setBounds(53, 153, 178, 133);
		getContentPane().add(list_1);
		
		final JList list_2 = new JList(model1);
		list_2.setBounds(336, 153, 185, 133);
		getContentPane().add(list_2);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(MeetingMenu.class.getResource("/images/1363370401_arrow.png")));
		button.setBounds(250, 173, 68, 35);
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(list_1.getSelectedIndex() != -1){
					model1.addElement(model.getElementAt(list_1.getSelectedIndex()));
					model.removeElementAt(list_1.getSelectedIndex());
				}
				
			}
		});
		getContentPane().add(button);
		
		
		
	
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon(MeetingMenu.class.getResource("/images/1363370401_arrow copy.png")));
		getContentPane().add(button_1);
		button_1.setBounds(250, 228, 68, 35);
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(list_2.getSelectedIndex() != -1){
					model.addElement(model1.getElementAt(list_2.getSelectedIndex()));
					model1.removeElementAt(list_2.getSelectedIndex());
				}
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Add other calendars");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel.setBounds(38, 50, 224, 65);
		contentPane.add(lblNewLabel);
		
		JButton btnDone = new JButton("Done");
		btnDone.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnDone.setBounds(44, 316, 117, 32);
		contentPane.add(btnDone);
		btnDone.addActionListener(new ActionListener() {
			/*ENDRES TIL FUNKSJON SOM LEGGER TIL ANDRE KALENDRE I LISTA*/
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnNewButton.setBounds(174, 316, 117, 32);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(OtherCallendarsMenu.class.getResource("/images/1363373140_user-group-new copy.png")));
		lblNewLabel_1.setBounds(369, 19, 122, 104);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(39, 140, 496, 159);
		contentPane.add(panel);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		
		
	}
}
