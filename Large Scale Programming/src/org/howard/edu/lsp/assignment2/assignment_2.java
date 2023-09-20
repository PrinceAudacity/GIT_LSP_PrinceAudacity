package org.howard.edu.lsp.assignment2;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class assignment_2 {

	public static void main(String[] args) {
		FileReader filename = new FileReader();
		HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
		try {
			String filepath = filename.readToString("main/java/resource/words.txt");
			System.out.println(filepath);
			String[] separated = filepath.toLowerCase().split(" ");		
			for (int x = 0; x < separated.length; x++) {
				if (separated[x].length() > 3) {
					if (hashmap.get(separated[x]) == null) {
						hashmap.put(separated[x], 1);
					}
					else {
						hashmap.put(separated[x], hashmap.get(separated[x]) +1);
					}
				}
			}
			for (String i: hashmap.keySet()) {
				System.out.println(i + " " + hashmap.get(i));
		}
		
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found in directory");
		}
	}
	
}
