package xcal.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Appointment;
import xcal.model.Employee;
import xcal.model.Meeting;

public class MeetingPage extends JFrame {

	private JPanel contentPane;
	private Client client = Client.getClient();
	private Meeting m ;
	private final DefaultListModel listAcceptedModel = new DefaultListModel();
	private final DefaultListModel listDeclinedModel = new DefaultListModel();
	private final DefaultListModel listNoAnswerModel = new DefaultListModel();
	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeetingPage frame = new MeetingPage(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		//MeetingPage frame = new MeetingPage(null);
		//frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public MeetingPage(Appointment a) {
		
		JList listAccepted = new JList(listAcceptedModel);
		JList listDeclined = new JList(listDeclinedModel);
		JList listNoAnswer = new JList(listNoAnswerModel);
		if(a instanceof Meeting){
			System.out.println("before worker");
			m = (Meeting) a;
			System.out.println(m);
			
			SwingWorker w = new getParticipantWorker();
			w.execute();
			System.out.println("after worker");
			
		}
		
		this.setVisible(true);
		setTitle("Meeting: " + a.getTitle());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 749, 575);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBackToCalendar = new JButton("Back to calendar");
		btnBackToCalendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
				
			}
		});
		btnBackToCalendar.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		btnBackToCalendar.setBounds(122, 50, 155, 35);
		contentPane.add(btnBackToCalendar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MeetingPage.class.getResource("/images/1363617975_event2.png")));
		lblNewLabel.setBounds(37, 30, 73, 70);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 24, 270, 84);
		contentPane.add(panel);
		
		JLabel lblMeatingSubject = new JLabel(a.getTitle());
		lblMeatingSubject.setFont(new Font("Helvetica", Font.BOLD, 18));
		lblMeatingSubject.setBounds(42, 136, 228, 44);
		getContentPane().add(lblMeatingSubject);
		
		DateFormat dfTime = new SimpleDateFormat("HH:mm");
		JLabel lblNewLabel_1 = new JLabel("Time: " + dfTime.format(a.getFromTime().toDate()) + " - " + dfTime.format(a.getToTime().toDate()));
		lblNewLabel_1.setFont(new Font("Helvetica", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(59, 179, 218, 16);
		getContentPane().add(lblNewLabel_1);
		
		DateFormat dfDate = new SimpleDateFormat("dd.MM.yyyy");
		JLabel lblNewLabel_2 = new JLabel("Date:  " + dfDate.format(a.getFromTime().toDate()));
		lblNewLabel_2.setBounds(59, 198, 132, 16);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Helvetica", Font.BOLD, 14));
		lblDescription.setBounds(41, 274, 158, 16);
		getContentPane().add(lblDescription);
		
		JTextPane descriptionPane = new JTextPane();
		descriptionPane.setText(a.getDescription());
		descriptionPane.setFont(new Font("Helvetica", Font.PLAIN, 14));
		descriptionPane.setBounds(52, 296, 377, 120);
		getContentPane().add(descriptionPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 464, 166, 55);
		panel_1.setLayout(null);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(64, 10, 87, 35);
		panel_1.add(btnDelete);
		btnDelete.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(14, 11, 38, 35);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon(MeetingPage.class.getResource("/images/1363618651_button_cancel.png")));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(213, 464, 250, 55);
		if(client.getUser().getEmail().equals(a.getLeader().getEmail())){
			contentPane.add(panel_1);
			contentPane.add(panel_2);
		}
		else{
			panel_1.setVisible(false);
			panel_2.setVisible(false);
		}
		
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(15, 9, 38, 38);
		panel_2.add(lblNewLabel_4);
		lblNewLabel_4.setIcon(new ImageIcon(MeetingPage.class.getResource("/images/1363618677_stock_save.png")));
		
		JButton btnChangeAppointment = new JButton("Change appointment");
		btnChangeAppointment.setBounds(65, 10, 171, 35);
		panel_2.add(btnChangeAppointment);
		btnChangeAppointment.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(490, 24, 228, 495);
		if(a instanceof Meeting){
			contentPane.add(panel_3);
		}
		else{
			panel_3.setVisible(false);
		}
		panel_3.setLayout(null);
		
		JLabel lblInvitationStatis = new JLabel("Invitation status");
		lblInvitationStatis.setBounds(12, 15, 137, 29);
		panel_3.add(lblInvitationStatis);
		lblInvitationStatis.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JLabel lblAccepted = new JLabel("Accepted:");
		lblAccepted.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblAccepted.setBounds(38, 68, 81, 16);
		panel_3.add(lblAccepted);
		
		JLabel lblNewLabel_5 = new JLabel("Declined:");
		lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_5.setBounds(38, 187, 81, 16);
		panel_3.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("No answer yet:");
		lblNewLabel_6.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_6.setBounds(38, 311, 103, 16);
		panel_3.add(lblNewLabel_6);
		
		JButton btnChangeStatus = new JButton("Change your status");
		btnChangeStatus.setBounds(60, 447, 143, 35);
		panel_3.add(btnChangeStatus);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(MeetingPage.class.getResource("/images/1363620201_cog_edit.png")));
		lblNewLabel_8.setBounds(14, 447, 38, 38);
		panel_3.add(lblNewLabel_8);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 89, 168, 85);
		panel_3.add(scrollPane);
		
		
		scrollPane.setViewportView(listAccepted);
		listAccepted.setToolTipText("");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(30, 207, 168, 85);
		panel_3.add(scrollPane_1);
		
		scrollPane_1.setViewportView(listDeclined);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(30, 331, 168, 85);
		panel_3.add(scrollPane_2);
		
		scrollPane_2.setViewportView(listNoAnswer);
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setIcon(new ImageIcon(MeetingPage.class.getResource("/images/1363622473_Forward.png")));
		lblNewLabel_10.setBounds(159, 12, 36, 35);
		panel_3.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setIcon(new ImageIcon(MeetingPage.class.getResource("/images/1363622750_thumb_up.png")));
		lblNewLabel_11.setBounds(10, 64, 24, 20);
		panel_3.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setIcon(new ImageIcon(MeetingPage.class.getResource("/images/1363622738_thumb_down.png")));
		lblNewLabel_12.setBounds(10, 187, 21, 16);
		panel_3.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(MeetingPage.class.getResource("/images/1363622923_error.png")));
		lblNewLabel_13.setBounds(10, 311, 23, 16);
		panel_3.add(lblNewLabel_13);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoPanel.setBounds(25, 120, 439, 323);
		contentPane.add(infoPanel);
		infoPanel.setLayout(null);
		
		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblLocation.setBounds(18, 113, 66, 16);
		infoPanel.add(lblLocation);
		
		JLabel lblNewLabel_9 = new JLabel(a.getLocationName());
		lblNewLabel_9.setBounds(96, 114, 195, 16);
		infoPanel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon(MeetingPage.class.getResource("/images/andrekalendere.png")));
		lblNewLabel_7.setBounds(353, 22, 96, 84);
		contentPane.add(lblNewLabel_7);
		
	}
	
	class getParticipantWorker extends SwingWorker{
		ArrayList<Employee> empList = new ArrayList();
		ArrayList<Integer> answList = new ArrayList();
		
		@Override
		protected Object doInBackground() throws Exception {
			
			return empList;
		}
		
		@SuppressWarnings("unchecked")
		protected void done(){
			Employee emp = client.getUser();
			//System.out.println(emp + "HEHY");
			System.out.println(m + "INSIDE WOREKR");
			Object obj = client.sendObject(m, Status.GET_PARTICIPANTS);
			//ArrayList[] list = (ArrayList[]) obj; 
			System.out.println(((Wrapper) obj).getFlag());
			empList = (ArrayList<Employee>)((Wrapper) obj).getContent();
			
//			answList = list[1];
			//System.out.println(answList);
			System.out.println(empList + "klklksds");
			/*for (int i=0; i<empList.size(); i++){
				
				if(answList.get(i) == 0){
					listDeclinedModel.addElement(empList.get(i));
				}
				else if(answList.get(i) == 1){
					listAcceptedModel.addElement(empList.get(i));
				}
				else if(answList.get(i) == null){
					listNoAnswerModel.addElement(empList.get(i));
				}
			}*/
			super.done();
			System.out.println(empList);
		}
		
	}
	
}
