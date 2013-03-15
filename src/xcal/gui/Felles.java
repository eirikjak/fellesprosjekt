package xcal.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;

import javax.swing.JTextPane;
import java.awt.Label;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Felles 
{

	private JFrame frame;
	private JPanel test;
	private JTextField Navn;
	private JTextField h_fra;
	private JLabel label;
	private JTextField m_fra;
	private JLabel lblTil;
	private JTextField h_til;
	private JLabel label_1;
	private JTextField m_til;
	private JTextField date;
	private JTextArea beskrivelse;
	private JTabbedPane PersonGroup;
	private JPanel panel;
	private JButton remove;
	private JButton add;
	
	private JList<String> person;
	private JList<String> group;
	private JList<String> added_list;
	
	private Vector<String> added_list_values;
	private Vector<String> person_array_list;
	private Vector<String> group_array_list;
	
	private String[] person_array={"Gunnar","Kari","Geir","Per","Kristine","Kjell"};
	private String[] group_array={"Ledere","KaffeGruppen","Vaskere"};
	private Label rom_text;
	private Label varsel_text;
	private JButton save;
	private JButton cancel;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Felles window = new Felles();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Felles() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 690, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		added_list_values=new Vector<String>();
		person_array_list=new Vector<String>();
		group_array_list=new Vector<String>();
		
		for(int i=0;i<person_array.length;++i)
			person_array_list.add(person_array[i]);
		for(int i=0;i<group_array.length;++i)
			group_array_list.add(group_array[i]);
		
		
		test = new JPanel();
		frame.getContentPane().add(test);
		test.setLayout(new MigLayout("", "[][][][grow][grow][grow][][][][grow][grow][grow][grow][][grow]", "[][][][grow][grow][grow][][]"));
		
		Navn = new JTextField();
		Navn.setToolTipText("Navn");
		test.add(Navn, "cell 2 0 13 1,growx");
		Navn.setColumns(10);
		
		h_fra = new JTextField();
		h_fra.setToolTipText("Time fra");
		test.add(h_fra, "flowx,cell 2 1,growx");
		h_fra.setColumns(2);
		
		label = new JLabel(":");
		test.add(label, "cell 2 1,alignx trailing");
		
		m_fra = new JTextField();
		m_fra.setToolTipText("Minutt fra");
		test.add(m_fra, "cell 2 1,alignx left");
		m_fra.setColumns(2);
		
		date = new JTextField();
		date.setToolTipText("Dato");
		test.add(date, "cell 14 1,alignx right");
		date.setColumns(10);
		
		beskrivelse = new JTextArea();
		beskrivelse.setRows(7);
		beskrivelse.setToolTipText("Beskrivelse");
		
		JScrollPane scroll_beskrivelse=new JScrollPane(beskrivelse);
		
		test.add(scroll_beskrivelse, "cell 2 3 12 1,growx,aligny top");
		
		/*JComponent personList=new JComponent();
		JComponent GroupList=new JComponent();
		PersonGroup.addTab(title, component);*/
		
		PersonGroup = new JTabbedPane(JTabbedPane.TOP);
		person=new JList(person_array_list);
		
		group=new JList(group_array_list);
		
		PersonGroup.addTab("Personer",person);
		PersonGroup.addTab("Grupper",group);
		test.add(PersonGroup, "cell 2 4 2 1,growx,aligny top");
		
		panel = new JPanel();
		test.add(panel, "cell 4 4,grow");
		
		add = new JButton(">>");
		add.addActionListener(new ActionPerformed());
		
		remove = new JButton("<<");
		remove.addActionListener(new ActionPerformed());
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(remove)
						.addComponent(add))
					.addContainerGap(55, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(62, Short.MAX_VALUE)
					.addComponent(add)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(remove)
					.addGap(31))
		);
		panel.setLayout(gl_panel);
		
		added_list = new JList();
		test.add(added_list, "cell 5 4 4 1,grow");
		
		rom_text = new Label("Rom:");
		test.add(rom_text, "cell 2 5,alignx right");
		
		JComboBox Rom = new JComboBox();
		Rom.setModel(new DefaultComboBoxModel(new String[] {"Rom A", "Rom B", "Rom C"}));
		test.add(Rom, "flowy,cell 3 5");
		
		lblTil = new JLabel("Til");
		test.add(lblTil, "cell 2 1,alignx trailing");
		
		h_til = new JTextField();
		h_til.setToolTipText("Time til");
		test.add(h_til, "cell 2 1,alignx right");
		h_til.setColumns(2);
		
		label_1 = new JLabel(":");
		test.add(label_1, "cell 2 1,alignx trailing");
		
		m_til = new JTextField();
		m_til.setToolTipText("Minutter til");
		test.add(m_til, "cell 2 1,growx");
		m_til.setColumns(2);
		
		varsel_text = new Label("Varsel");
		test.add(varsel_text, "cell 2 6,alignx right");
		
		JComboBox varsel = new JComboBox();
		varsel.setModel(new DefaultComboBoxModel(new String[] {"1min", "5min", "15min", "30min", "1time", "2timer", "1dag"}));
		test.add(varsel, "cell 3 6,growx");
		
		save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		test.add(save, "cell 3 7");
		
		cancel = new JButton("Cancel");
		test.add(cancel, "cell 4 7");
	}
	
	
	
	
	private class ActionPerformed implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			if(e.getSource()==add)
			{

				if(PersonGroup.getSelectedIndex()==0)
				{
					added_list_values.add(person.getSelectedValue());
					
					for(int i=0;i<person_array_list.size();++i)
						if(person.getSelectedValue()==person_array_list.get(i))
							person_array_list.remove(i);
				}
			
				else
				{
					added_list_values.add(group.getSelectedValue());
					
					for(int i=0;i<group_array_list.size();++i)
						if(group.getSelectedValue()==group_array_list.get(i))
							group_array_list.remove(i);
				}
				
				
				
				//System.out.println(PersonGroup.getSelectedIndex()+"\n"+person.getSelectedValue()+"\n"+group.getSelectedValue());
			}
			
			if(e.getSource()==remove)
			{
				for(int i=0;i<added_list_values.size();++i)
				{
					if(added_list_values.get(i)==added_list.getSelectedValue())
						added_list_values.remove(i);
				}
			//System.out.printadded_list.getSelectedValue();
			}
			
			added_list.setListData(added_list_values);
			person.setListData(person_array_list);
			group.setListData(group_array_list);
			
			
			
			
			
			
		}
		
	}
}
