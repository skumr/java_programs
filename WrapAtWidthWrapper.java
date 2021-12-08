package a5;

import java.util.Arrays;
import java.util.List;

/**
 * A class that wraps a string by breaking the string into lines
 * of length equal to the desired wrapped line length (except
 * for possibly the last wrapped line which may be shorter
 * than the desired wrapped line length).
 *
 */
public class WrapAtWidthWrapper extends AbstractStringWrapper {

	/**
	 * Initializes this wrapper with the string to wrap and the
	 * desired maximum wrapped line width.
	 * 
	 * @param toWrap the string to wrap
	 * @param targetWidth the desired maximum wrapped line width
	 */
	public WrapAtWidthWrapper(String toWrap, int targetWidth) {
		// IMPLEMENT THIS
		super(toWrap, targetWidth);
		
	}
	
	/**
	 * Wraps the string into separate lines of text.
	 */
	@Override
	public void wrap() {
		// IMPLEMENT THIS
		String toWrap = super.toWrap;
		int width = super.width();
		int length = toWrap.length();
		int len = (length - (length % width))/width;
		String[] wrapped = new String[len+1];
		
		if (width > length) {
			wrapped[0] = toWrap;
			super.lines = Arrays.asList(wrapped);
			return;
		}
		
		if (width == 1) {
			toWrap = super.toWrap.replaceAll("\\s", "");
			length = toWrap.length();
			wrapped = new String[length];
			
			for (int i = 0; i < wrapped.length; i++) {
				wrapped[i] = toWrap.substring(i, i+1);
			}
		}
		
		for (int i = 0; i < wrapped.length; i++) {
			if (width > toWrap.length()) {
				wrapped[i] = toWrap;
				break;
			}
			String wrap = toWrap.substring(0, width);
			wrapped[i] = wrap;
			toWrap = toWrap.replace(wrap, "");
		}
		
		super.lines = Arrays.asList(wrapped);
		
	}

	public static void main(String[] args) {
		int width = 30;
		AbstractStringWrapper w = new WrapAtWidthWrapper("ABCDEFGHIJKLMNOPQRSTUVWXYZ", width);
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
