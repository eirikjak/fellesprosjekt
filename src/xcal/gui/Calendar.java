package xcal.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Calendar extends JPanel {

	/**
	 * Create the panel.
	 */
	public Calendar() {
		setLayout(null);

		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(388, 5, 1, 1);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 88, 922, 27);
		panel_1.add(panel_2);
		panel_2.setBackground(new Color(143, 179, 206));
		panel_2.setLayout(null);
		
		JLabel label_1 = new JLabel("DATO");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_1.setBounds(20, 4, 90, 19);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("DATO");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_2.setBounds(285, 4, 90, 19);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("DATO");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_3.setBounds(150, 4, 90, 19);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("DATO");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_4.setBounds(416, 4, 90, 19);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("DATO");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_5.setBounds(549, 4, 90, 19);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("DATO");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_6.setBounds(681, 4, 90, 19);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("DATO");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		label_7.setBounds(814, 4, 90, 19);
		panel_2.add(label_7);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 61, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(1, 47, 940, 72);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Mainpage.class.getResource("/images/kalender_bar.png")));
		
		JButton btnLastWeek = new JButton("Last Week");
		btnLastWeek.setBounds(2, 11, 132, 29);
		panel_1.add(btnLastWeek);
		btnLastWeek.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JButton btnNextWeek = new JButton("Next Week");
		btnNextWeek.setBounds(804, 11, 132, 29);
		panel_1.add(btnNextWeek);
		btnNextWeek.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		JLabel lblNewLabel_2 = new JLabel("M\u00C5NED");
		lblNewLabel_2.setBounds(334, 5, 233, 39);
		panel_1.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(35, 103, 174));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 28));
		
		JList list_1 = new JList();
		list_1.setBounds(8, 122, 126, 365);
		panel_1.add(list_1);
		
		JList list_2 = new JList();
		list_2.setBounds(141, 122, 126, 365);
		panel_1.add(list_2);
		
		JList list_3 = new JList();
		list_3.setBounds(274, 122, 126, 365);
		panel_1.add(list_3);
		
		JList list_4 = new JList();
		list_4.setBounds(540, 122, 126, 365);
		panel_1.add(list_4);
		
		JList list_5 = new JList();
		list_5.setBounds(806, 122, 126, 365);
		panel_1.add(list_5);
		
		JList list_6 = new JList();
		list_6.setBounds(673, 122, 126, 365);
		panel_1.add(list_6);
		
		JList list_7 = new JList();
		list_7.setBounds(407, 122, 126, 365);
		panel_1.add(list_7);
		

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(394, 5, 1, 1);
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Show", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblTrerg1 = new JLabel("");
		lblTrerg1.setBounds(15, 16, 66, 66);
		panel_3.add(lblTrerg1);
		lblTrerg1.setIcon(new ImageIcon(Mainpage.class.getResource("/images/andrekalendere2.png")));
		
		JButton btnNewButton_1 = new JButton("<html> &nbsp;\nOther </p><br>Calendars  </html>");
		btnNewButton_1.setBounds(100, 24, 109, 58);
		panel_3.add(btnNewButton_1);
		
				btnNewButton_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
				
				JPanel panel = new JPanel();
				panel.setBounds(3, 3, 940, 493);
				panel.setLayout(null);
				add(panel);
				
				JPanel panel_4 = new JPanel();
				panel_4.setLayout(null);
				panel_4.setBackground(new Color(143, 179, 206));
				panel_4.setBounds(6, 88, 922, 27);
				panel.add(panel_4);
				
				JLabel label = new JLabel("DATO");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setForeground(Color.WHITE);
				label.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				label.setBounds(20, 4, 90, 19);
				panel_4.add(label);
				
				JLabel label_8 = new JLabel("DATO");
				label_8.setHorizontalAlignment(SwingConstants.CENTER);
				label_8.setForeground(Color.WHITE);
				label_8.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				label_8.setBounds(285, 4, 90, 19);
				panel_4.add(label_8);
				
				JLabel label_9 = new JLabel("DATO");
				label_9.setHorizontalAlignment(SwingConstants.CENTER);
				label_9.setForeground(Color.WHITE);
				label_9.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				label_9.setBounds(150, 4, 90, 19);
				panel_4.add(label_9);
				
				JLabel label_10 = new JLabel("DATO");
				label_10.setHorizontalAlignment(SwingConstants.CENTER);
				label_10.setForeground(Color.WHITE);
				label_10.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				label_10.setBounds(416, 4, 90, 19);
				panel_4.add(label_10);
				
				JLabel label_11 = new JLabel("DATO");
				label_11.setHorizontalAlignment(SwingConstants.CENTER);
				label_11.setForeground(Color.WHITE);
				label_11.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				label_11.setBounds(549, 4, 90, 19);
				panel_4.add(label_11);
				
				JLabel label_12 = new JLabel("DATO");
				label_12.setHorizontalAlignment(SwingConstants.CENTER);
				label_12.setForeground(Color.WHITE);
				label_12.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				label_12.setBounds(681, 4, 90, 19);
				panel_4.add(label_12);
				
				JLabel label_13 = new JLabel("DATO");
				label_13.setHorizontalAlignment(SwingConstants.CENTER);
				label_13.setForeground(Color.WHITE);
				label_13.setFont(new Font("Lucida Grande", Font.BOLD, 14));
				label_13.setBounds(814, 4, 90, 19);
				panel_4.add(label_13);
				
				JLabel label_14 = new JLabel("");
				label_14.setIcon(new ImageIcon(Calendar.class.getResource("/images/kalender_bar.png")));
				label_14.setBounds(0, 47, 940, 72);
				panel.add(label_14);
				
				JButton button = new JButton("Last Week");
				button.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				button.setBounds(2, 11, 132, 29);
				panel.add(button);
				
				JButton button_1 = new JButton("Next Week");
				button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
				button_1.setBounds(804, 11, 132, 29);
				panel.add(button_1);
				
				JLabel label_15 = new JLabel("M\u00C5NED");
				label_15.setHorizontalAlignment(SwingConstants.CENTER);
				label_15.setForeground(new Color(35, 103, 174));
				label_15.setFont(new Font("Lucida Grande", Font.BOLD, 28));
				label_15.setBounds(334, 5, 233, 39);
				panel.add(label_15);
				
				JList list = new JList();
				list.setBounds(8, 122, 126, 365);
				panel.add(list);
				
				JList list_8 = new JList();
				list_8.setBounds(141, 122, 126, 365);
				panel.add(list_8);
				
				JList list_9 = new JList();
				list_9.setBounds(274, 122, 126, 365);
				panel.add(list_9);
				
				JList list_10 = new JList();
				list_10.setBounds(540, 122, 126, 365);
				panel.add(list_10);
				
				JList list_11 = new JList();
				list_11.setBounds(806, 122, 126, 365);
				panel.add(list_11);
				
				JList list_12 = new JList();
				list_12.setBounds(673, 122, 126, 365);
				panel.add(list_12);
				
				JList list_13 = new JList();
				list_13.setBounds(407, 122, 126, 365);
				panel.add(list_13);
			//	btnNewButton_1.addActionListener(new OtherCalendarsListener());
		
		
		
		
	}

}
