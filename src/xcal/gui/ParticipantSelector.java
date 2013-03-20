package xcal.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import xcal.client.Client;
import xcal.client.Status;
import xcal.client.Wrapper;
import xcal.model.Employee;
import xcal.model.Group;
import xcal.model.Meeting;
import xcal.model.ParticipantSelectorModel;

import javax.swing.ListModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;
import javax.swing.JTabbedPane;

public class ParticipantSelector extends JPanel implements PropertyChangeListener {

	private JList personListLeft;
	private DefaultListModel personListModelLeft;
	private DefaultListModel personListModelRight;
	private DefaultListModel groupListModelLeft;
	private DefaultListModel groupListModelRight;
	private JList personListRight;
	private Client client;
	private ParticipantSelectorModel model;
	private JPanel panel;
	private JTextField employeeSearch;
	private JTextField groupSearch;
	private JXBusyLabel personBussyLabel;
	private JXBusyLabel groupBussyLabel ;
	private JList groupListRight;
	private JList groupListLeft;
	
	public ParticipantSelector() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(null);

		personListModelRight = new DefaultListModel();
		personListModelLeft =new DefaultListModel();
		groupListModelLeft = new DefaultListModel();
		groupListModelRight = new DefaultListModel();
		
		model = new ParticipantSelectorModel();
		model.addPropertyChangeListener(this);
		
		client = Client.getClient();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setBounds(0, 0, 495, 214);
		add(tabbedPane);
		
		
		
		panel = new JPanel();
		panel.setBorder(null);
		tabbedPane.addTab("Employees", null, panel, null);
		panel.setLayout(null);
		
		personBussyLabel = new JXBusyLabel();
		personBussyLabel.setBounds(78, 91, 26, 26);
		panel.add(personBussyLabel);
		
		JButton removeButtonEmployee = new JButton("");
		removeButtonEmployee.setIcon(new ImageIcon(ParticipantSelector.class.getResource("/images/1363370401_arrow copy.png")));
		removeButtonEmployee.setBounds(201, 112, 68, 35);
		panel.add(removeButtonEmployee);
		
		removeButtonEmployee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(personListRight.getSelectedIndex() > -1){
					model.removeEmployee((Employee) personListModelRight.get(personListRight.getSelectedIndex()));
					personListModelRight.remove(personListRight.getSelectedIndex());
				}
				
			}
		});
		
		employeeSearch = new JTextField();
		employeeSearch.setBounds(4, 6, 178, 25);
		panel.add(employeeSearch);
		employeeSearch.setColumns(10);
		
		JButton addButtonEmployee = new JButton("");
		addButtonEmployee.setIcon(new ImageIcon(ParticipantSelector.class.getResource("/images/1363370401_arrow.png")));
		addButtonEmployee.setBounds(201, 56, 68, 35);
		panel.add(addButtonEmployee);
		
		addButtonEmployee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(personListLeft.getSelectedIndex() > -1){
					model.addEmployee((Employee) personListModelLeft.get(personListLeft.getSelectedIndex()));
					personListModelLeft.remove(personListLeft.getSelectedIndex());
				}
				
			}
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(287, 40, 185, 133);
		panel.add(scrollPane_2);
		personListRight = new JList(personListModelRight);
		scrollPane_2.setViewportView(personListRight);
		personListRight.setCellRenderer((ListCellRenderer) new PersonListCellRenderer());
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(4, 40, 185, 133);
		panel.add(scrollPane_1);
		personListLeft = new JList(personListModelLeft);
		scrollPane_1.setViewportView(personListLeft);
		personListLeft.setCellRenderer((ListCellRenderer) new PersonListCellRenderer());
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("Groups", null, panel_1, null);
		
		groupBussyLabel = new JXBusyLabel();
		groupBussyLabel.setBounds(78, 91, 26, 26);
		panel_1.add(groupBussyLabel);
		
		JButton removeButtonGroup = new JButton("");
		removeButtonGroup.setIcon(new ImageIcon(ParticipantSelector.class.getResource("/images/1363370401_arrow copy.png")));
		removeButtonGroup.setBounds(201, 112, 68, 35);
		removeButtonGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(groupListRight.getSelectedIndex() > -1){
					model.removeGroup((Group) groupListModelRight.get(groupListRight.getSelectedIndex()));
					groupListModelRight.remove(groupListRight.getSelectedIndex());
				}
				
			}
		});
		panel_1.add(removeButtonGroup);
		
		groupSearch = new JTextField();
		groupSearch.setColumns(10);
		groupSearch.setBounds(4, 6, 178, 25);
		panel_1.add(groupSearch);
		
		JButton addButtonGroup = new JButton("");
		addButtonGroup.setIcon(new ImageIcon(ParticipantSelector.class.getResource("/images/1363370401_arrow.png")));
		addButtonGroup.setBounds(201, 56, 68, 35);
		addButtonGroup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(groupListLeft.getSelectedIndex() > -1){
					model.addGroup((Group) groupListModelLeft.get(groupListLeft.getSelectedIndex()));
					groupListModelLeft.remove(groupListLeft.getSelectedIndex());
				}
				
			}
		});
		panel_1.add(addButtonGroup);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(287, 40, 185, 133);
		panel_1.add(scrollPane);
		
		groupListRight = new JList(groupListModelRight);
		groupListRight.setCellRenderer((ListCellRenderer) new GroupListCellRenderer());
		scrollPane.setViewportView(groupListRight);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(4, 40, 185, 133);
		panel_1.add(scrollPane_3);
		
		groupListLeft = new JList(groupListModelLeft);
		groupListLeft.setCellRenderer((ListCellRenderer) new GroupListCellRenderer());
		scrollPane_3.setViewportView(groupListLeft);
		
	//	updateGroupList();
		//updatePersonList();
		
		
		
	}
	private void insertSorted(JList list, Employee emp){
		for (int i = 0; i< list.getModel().getSize(); i++){
			if(emp.getName().compareTo(((Component) list.getModel().getElementAt(i)).getName()) <= 0){
				((DefaultListModel) list.getModel()).add(i, emp);
				return;
			}
		}
		
		((DefaultListModel) list.getModel()).addElement(emp);
	}
	
	private void insertSorted(JList list, Group group){
		for (int i = 0; i< list.getModel().getSize(); i++){
			if(group.getName().compareTo(((Component) list.getModel().getElementAt(i)).getName()) <= 0){
				((DefaultListModel) list.getModel()).add(i, group);
				return;
			}
		}
		
		((DefaultListModel) list.getModel()).addElement(group);
	}
	
	
	public void propertyChange(PropertyChangeEvent evt) {
		
		String pName = evt.getPropertyName();
		
		if(pName.equals(ParticipantSelectorModel.PROPERTY_EMPLOYEE_ADD)){
			insertSorted(personListRight, (Employee)evt.getNewValue());
			
		}else if(pName.equals(ParticipantSelectorModel.PROPERTY_EMPLOYEE_REMOVE)){
			insertSorted(personListLeft, (Employee)evt.getNewValue());
			
		}else if(pName.equals(ParticipantSelectorModel.PROPERTY_GROUP_ADD)){
			insertSorted(groupListRight, (Group)evt.getNewValue());
			
		}else if(pName.equals(ParticipantSelectorModel.PROPERTY_GROUP_REMOVE)){
			insertSorted(groupListLeft,(Group)evt.getNewValue());
		}
		
	}
	
	private void updatePersonList(){
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
			private ArrayList<Employee> employees;
			@Override
			protected Void doInBackground() throws Exception {
				personBussyLabel.setVisible(true);
				personBussyLabel.setBusy(true);
				Employee emp = new Employee();
				Wrapper response = client.sendObject(emp, Status.GET_ALL);
				if(response.getContent() != null){
					employees = (ArrayList<Employee>) response.getContent();
				}
				
				return null;
			}
			
			@Override
			protected void done() {
				for(Employee employee: employees){
					if(!employee.getEmail().equals(client.getUser().getEmail())){
						personListModelLeft.addElement(employee);
					}
						
				}
				personBussyLabel.setBusy(false);
				personBussyLabel.setVisible(false);
				AutoCompleteDecorator.decorate(personListLeft, employeeSearch, ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
				super.done();
			}
		};
		
		worker.execute();
	}
	
	private void updateGroupList(){
		
		SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>(){
			private ArrayList<Group> groups;
			@Override
			protected Void doInBackground() throws Exception {
				groupBussyLabel.setVisible(true);
				groupBussyLabel.setBusy(true);
				Group group = new Group();
				Wrapper response = client.sendObject(group, Status.GET_ALL);
				if(response.getContent() != null){
					groups = (ArrayList<Group>) response.getContent();
				}
				return null;
			}
			
			@Override
			protected void done() {
				for(Group group: groups){
					groupListModelLeft.addElement(group);
					System.out.println(group.getMembers());
				}
				groupBussyLabel.setBusy(false);
				groupBussyLabel.setVisible(false);
				super.done();
				AutoCompleteDecorator.decorate(groupListLeft, groupSearch, ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
			}
		};
		
		worker.execute();
		
		
	}
	
	public void setModel(ParticipantSelectorModel model){
		this.model = model;
		this.personListModelRight.clear();
		this.groupListModelRight.clear();
		
		for(Employee emp: model.getEmployees()){
			personListModelRight.addElement(emp);
		}
		
		for(Group group: model.getGroups()){
			groupListModelRight.addElement(group);
		}
		
		
	}
	public ParticipantSelectorModel getModel(){
		return this.model;
	}
	private class PersonListCellRenderer implements ListCellRenderer{
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
		
		public Component getListCellRendererComponent(JList list, Employee value, int index, boolean isSelected, boolean cellHasFocus) {
			
			JLabel label = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			label.setText(value.getName());
			return label;
		}

		@Override
		public Component getListCellRendererComponent(JList arg0, Object arg1,
				int arg2, boolean arg3, boolean arg4) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	private class GroupListCellRenderer implements ListCellRenderer{
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
		
		public Component getListCellRendererComponent(JList list, Group value, int index, boolean isSelected, boolean cellHasFocus) {
			JLabel label = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			label.setText(value.getName());
			return label;
		}

		@Override
		public Component getListCellRendererComponent(JList arg0, Object arg1,
				int arg2, boolean arg3, boolean arg4) {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

	
	
}
