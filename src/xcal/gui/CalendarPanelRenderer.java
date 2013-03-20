package xcal.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.multi.MultiLabelUI;

import xcal.model.Appointment;
import xcal.model.Meeting;

public class CalendarPanelRenderer implements ListCellRenderer {

	private ImageIcon appointment = new ImageIcon(getClass().getResource("/images/1363749823_config-date.png"));
	
	@Override
	public Component getListCellRendererComponent(JList arg0, Object arg1,
			int arg2, boolean arg3, boolean arg4) {
		// TODO Auto-generated method stub
		Appointment a = (Appointment) arg1;
		JTextArea c = new JTextArea();
		c.setLineWrap(true);
		c.setWrapStyleWord(false);
		//JTextPane area = new JTextPane();
		
		JLabel l = new JLabel();
		l.setOpaque(true);
		l.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		l.setBorder(new LineBorder(Color.black));
		c.add(l);
		
		if(a != null){
			DateFormat df = new SimpleDateFormat("HH:mm");
			Date date = (Date) a.getFromTime().toDate();
			Date dateTo = (Date) a.getToTime().toDate();
			//area.setLineWrap( true );
			//area.setWrapStyleWord( true );
			if(a instanceof Meeting){
				
				l.setText("<HTML>" + df.format(date) +"-" + df.format(dateTo) +"<BR> Title: <BR>" + a.getTitle()+"</HTML>");
				l.setIcon(appointment);
				l.setAlignmentX(JLabel.LEFT);
				
				/*area.setText(df.format(date) + " - Title:" + a.getTitle() +"\n" +"Descr: " +a.getDescription());
				area. setCaretPosition(0);
				area.insertIcon(appointment);
				area.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
				area.setAlignmentX(JLabel.LEFT_ALIGNMENT);*/
			}
		}
		
		if(arg3){
			l.setForeground(Color.WHITE);
			l.setBackground(Color.LIGHT_GRAY);
		}
		
		
		return l;
	}

}
