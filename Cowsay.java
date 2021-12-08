package a5;

import java.util.List;

public class Cowsay {

	public static void printWrappedMessage(List<String> lines) {
		for (String line : lines) {
			System.out.println(line);
		}
	}
	
	public static void printCow() {
		String cow =
				"       \\\n" +
				"        \\   ^__^\n" +
				"         \\  (oo)\\_______\n" +
				"            (__)\\\\       )\\\\/\\\\\n" +
				"                ||----w |\n" +
				"                ||     ||\n";
		System.out.println(cow);
	}
	
	
	public static void main(String[] args) {
		String msg = "Of course you have a purpose -- to find a purpose.";
		AbstractStringWrapper w = new WrapAtSpacesWrapper(msg, 15);
		w.wrap();
		printWrappedMessage(w.getLines());
		printCow();
	}

}
