package a5;

import java.util.List;

/**
 * This class produces similar results as its superclass except that
 * each line of wrapped text is padded with a padding character so
 * that the length of each wrapped line is exactly equal to the
 * desired wrapped line length. 
 */
public class WrapAtSpacesAndPadWrapper extends WrapAtSpacesWrapper {

	private char padding;
	
	/**
	 * Initializes this wrapper with the string to wrap and the
	 * desired maximum wrapped line width. The padding character is
	 * set to the space character.
	 * 
	 * @param toWrap the string to wrap
	 * @param targetWidth the desired maximum wrapped line width
	 */
	public WrapAtSpacesAndPadWrapper(String toWrap, int targetWidth) {
		// IMPLEMENT THIS
		super(toWrap, targetWidth);
		this.padding = ' ';
		
	}
	
	/**
	 * Initializes this wrapper with the string to wrap, the
	 * desired maximum wrapped line width, and the character to pad
	 * the end of the wrapped lines with.
	 * 
	 * @param toWrap the string to wrap
	 * @param targetWidth the desired maximum wrapped line width
	 * @param padding the padding character
	 */
	public WrapAtSpacesAndPadWrapper(String toWrap, int targetWidth, char padding) {
		// IMPLEMENT THIS
		super(toWrap, targetWidth);
		this.padding = padding;
		
	}
	
	/**
	 * Returns the padding character.
	 * 
	 * @return the padding character
	 */
	public char paddingChar() {
		return this.padding;
	}
	
	/**
	 * Sets the padding character to the specified padding character.
	 * The string is not re-wrapped by this method. The user should call
	 * {@code wrap()} to re-wrap the string to use the new padding character.
	 * 
	 * @param padding the new padding character
	 */
	public void paddingChar(char padding) {
		this.padding = padding;
	}
	
	
	/**
	 * Wraps the string into separate lines of text.
	 */
	@Override
	public void wrap() {
		// IMPLEMENT THIS
		lines.clear();
		int currIndx = 0;
		
        while (currIndx + super.maxWidth <= toWrap.length()) {
       
            if (toWrap.charAt(currIndx) == ' ') {
                currIndx += 1;
                
            } else {
            	
                String wrap = toWrap.substring(currIndx, currIndx + super.maxWidth);
               
                if (!wrap.contains(" ")) {
                    lines.add(wrap);
                    currIndx += super.maxWidth;
                    
                } else if ((currIndx + super.maxWidth) >= toWrap.length() || toWrap.charAt(currIndx + super.maxWidth) == ' ') {
                    lines.add(wrap);
                    currIndx += super.maxWidth; 
                    
                } else {
                    int lastIndx = wrap.lastIndexOf(' ');
                    String newWrap = wrap.substring(0, lastIndx);
                    lines.add(newWrap);
                    
                    currIndx += lastIndx + 1;
                    
                    StringBuilder wrapped = new StringBuilder(newWrap);
                    int maxPad = super.maxWidth - lastIndx;
                    
                    for (int i = 0; i < maxPad; i++) {
                        
                    	wrapped.append(padding);
                    }
                    
                    lines.add(wrapped.toString());
                   
                    currIndx += lastIndx + 1;
                }
            }
        }

        while (currIndx < toWrap.length() && toWrap.charAt(currIndx) == ' ') {
            currIndx += 1;
            
        }

        if (currIndx < toWrap.length()) {
        	StringBuilder lastWrap = new StringBuilder(toWrap.substring(currIndx));
        	
            int numPad = super.maxWidth - lastWrap.length();
            
            for (int i = 0; i < numPad; i += 1) {
                lastWrap.append(padding);
            }
            lines.add(lastWrap.toString());
        }
	}
	
	public static void main(String[] args) {
		int width = 30;
		WrapAtSpacesAndPadWrapper w = new WrapAtSpacesAndPadWrapper("ABC DEFGH I JKLMNOPQ RSTUVWXYZ", width, '*');
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
		w.paddingChar('#');
		w.wrap();
		wrapped = w.getLines();
		out = String.format("width:%2d, lines:%2d, lines = %s", width, wrapped.size(), wrapped);
		System.out.println(out + "\n");
		
		width = 1;
		w.width(width);
		w.paddingChar('.');
		w.wrap();
		wrapped = w.getLines();
		out = String.format("width:%2d, lines:%2d, lines = %s", width, wrapped.size(), wrapped);
		System.out.println(out + "\n");
	}
}
