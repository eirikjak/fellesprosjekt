package xcal.gui;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class RootFrame {

	private static ArrayList<JPanel> panels;
	private static JFrame internalFrame;
	private static Container contentPane;
	
	public static void init(int width, int height){
		
		internalFrame = new JFrame();
		internalFrame.setPreferredSize(new Dimension(width,height));
		internalFrame.setVisible(true);
		internalFrame.pack();
		contentPane = internalFrame.getContentPane();
		panels = new ArrayList<JPanel>();
		
	}
 	public static void clearAll(){
		contentPane.removeAll();
		internalFrame.pack();
		panels.clear();
		
	}
	public static void addPanel(JPanel panel){
		contentPane.add(panel);
		panels.add(panel);
		internalFrame.pack();
		
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
