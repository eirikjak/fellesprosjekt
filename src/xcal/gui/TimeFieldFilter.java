package xcal.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

public class TimeFieldFilter extends DocumentFilter {
	
	@Override
	public void insertString(FilterBypass fb, int offset, String string,AttributeSet attr) throws BadLocationException {
		
		
		
	}
	
	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
		String string = text;
		for (int i = 0; i < string.length(); i++){
			if(!Character.isDigit(string.charAt(i))){
				return;
			}
		}
		
		if( offset > 2 || offset + string.length() > 2){
			return;
		}
	
	
	
		super.replace(fb, offset, length, text, attrs);
	}
	
	@Override
	public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
		// TODO Auto-generated method stub
	
		super.remove(fb, offset, length);
	}

}
