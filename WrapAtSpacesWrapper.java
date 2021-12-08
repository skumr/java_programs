package a5;

import java.util.Arrays;
import java.util.List;

/**
 * A class that wraps a string by breaking the string into lines
 * of length equal to or less than the desired wrapped line length.
 * The breaking occurs at spaces in the string where possible. If
 * a wrapped line contains no strings before the desired wrapped
 * line length, then the line is broken at the desired wrapped
 * line length.
 *
 */
public class WrapAtSpacesWrapper extends AbstractStringWrapper {

	/**
	 * Initializes this wrapper with the string to wrap and the
	 * desired maximum wrapped line width.
	 * 
	 * @param toWrap the string to wrap
	 * @param targetWidth the desired maximum wrapped line width
	 */
	public WrapAtSpacesWrapper(String toWrap, int targetWidth) {
		super(toWrap, targetWidth);
		
	}

	
	/**
	 * Wraps the string into separate lines of text.
	 */
	public void wrap() {
		// IMPLEMENT THIS
		lines.clear();
		int currIndx = 0;
		
        while (currIndx + maxWidth <= toWrap.length()) {
       
            if (toWrap.charAt(currIndx) == ' ') {
                currIndx += 1;
                
            } else {
                String wrap = toWrap.substring(currIndx, currIndx + maxWidth);
               
                if (!wrap.contains(" ")) {
                    lines.add(wrap);
                    currIndx += maxWidth;
                    
                } else if ((currIndx + maxWidth) >= toWrap.length() || toWrap.charAt(currIndx + maxWidth) == ' ') {
                    lines.add(wrap);
                    currIndx += maxWidth;
                    
                } else {
                    int lastIndx = wrap.lastIndexOf(' ');
                    
                    String newWrap = wrap.substring(0, lastIndx);
                    lines.add(newWrap);
                    
                    currIndx += lastIndx + 1;
                }
            }
        }

        while (currIndx < toWrap.length() && toWrap.charAt(currIndx) == ' ') {
            currIndx += 1;
            
        }

        if (currIndx < toWrap.length()) {
            lines.add(toWrap.substring(currIndx));
        }
		
		
	}

	public static void main(String[] args) {
		int width = 30;
		AbstractStringWrapper w = new WrapAtSpacesWrapper("ABC DEFGH I JKLMNOPQ RSTUVWXYZ", width);
		w.wrap();
		List<String> wrapped = w.getLines();
		String out = String.format("width:%2d, lines:%2d, lines = %s", width, wrapped.size(), wrapped);
		System.out.println(out + "\n");
		
		width = 20;
		w.width(width);
		w.wrap();
		wrapped = w.getLines();
		out = String.format("width:%2d, lines:%2d, lines = %s", width, wrapped.size(), wrapped);
		System.out.println(out + "\n");
		
		width = 5;
		w.width(width);
		w.wrap();
		wrapped = w.getLines();
		out = String.format("width:%2d, lines:%2d, lines = %s", width, wrapped.size(), wrapped);
		System.out.println(out + "\n");
		
		width = 1;
		w.width(width);
		w.wrap();
		wrapped = w.getLines();
		out = String.format("width:%2d, lines:%2d, lines = %s", width, wrapped.size(), wrapped);
		System.out.println(out + "\n");

	}
}
