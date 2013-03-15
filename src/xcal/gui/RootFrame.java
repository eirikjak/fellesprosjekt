package xcal.gui;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class RootFrame {

	private static ArrayList<JPanel> panels;
	private static JFrame internalFrame;
	private static JLayeredPane contentPane;
	
	public static void init(int width, int height){
		
		internalFrame = new JFrame();
		
		internalFrame.setPreferredSize(new Dimension(width,height));
		internalFrame.setVisible(true);
		internalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		internalFrame.pack();
		contentPane = internalFrame.getLayeredPane();
		contentPane.setLayout(null);
		panels = new ArrayList<JPanel>();
		
	}
 	public static void clearAll(){
		contentPane.removeAll();
		internalFrame.pack();
		panels.clear();
		
	}
	public static void addPanel(JPanel panel){
		
		contentPane.add(panel,new Integer(panels.size()));
		panels.add(panel);
		
		
	}
	public static void removePanel(JPanel panel){
		contentPane.remove(panel);
		panels.remove(panel);
		internalFrame.pack();
		
	}
	public static void popPanel(){
		JPanel panel = panels.remove(panels.size() -1);
		contentPane.remove(panel);
		internalFrame.pack();
	}
}
