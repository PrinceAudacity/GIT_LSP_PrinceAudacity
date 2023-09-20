package org.howard.edu.lsp.assignment2;
import java.io.FileNotFoundException;


public class WordCounterDriver {

	public static void main(String[] args) {
		FileReader fr = new FileReader();
		try {
			System.out.println(fr.readToString("main/java/resource/words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		}
		
	}

}
