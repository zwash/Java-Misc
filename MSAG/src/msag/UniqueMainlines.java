package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
//import java.util.HashMap;
import java.util.HashSet;

public class UniqueMainlines {
	
	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\sublines1.txt";
	public static String inputFile2 = "C:\\Users\\zwashington\\Documents\\java test\\mainlines1.txt";
	public static String writeFile = "C:\\Users\\zwashington\\Documents\\java test\\diffSubAndMain2.txt";
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		BufferedReader input2 = new BufferedReader(new FileReader(inputFile2));
		PrintWriter writer = new PrintWriter(writeFile);
		HashSet<String> sublinekeys = new HashSet<String>();
		HashSet<String> mainlinekeys = new HashSet<String>();
		String s;
		while ((s = input.readLine()) != null) {
			String fullStreet = s.substring(25, 125);
			String esn = s.substring(225, 230);
			String mainline = s.substring(230, 240);
			sublinekeys.add(fullStreet + esn + mainline);
		}
		String str;
		while ((str = input2.readLine()) != null) {
			String fullStreet = str.substring(25, 125);
			String esn = str.substring(225, 230);
			String mainline = str.substring(230, 240);
			if (sublinekeys.contains(fullStreet + esn + mainline)) {
				sublinekeys.remove(fullStreet + esn + mainline);
			}else {
				mainlinekeys.add(fullStreet + esn + mainline);
			}
		}
		System.out.println("Subline records effected:");
		System.out.println(sublinekeys.size());
		System.out.println("main line set size:");
		System.out.println(mainlinekeys.size());
		BufferedReader input3 = new BufferedReader(new FileReader(inputFile));
		while ((str = input3.readLine()) != null) {
			String fullStreet = str.substring(25, 125);
			String esn = str.substring(225, 230);
			String mainline = str.substring(230, 240);
			if (sublinekeys.contains(fullStreet + esn + mainline)) {
				//writer.println(str.substring(1,11) + fullStreet + esn + mainline);
			}else {
				writer.println(str);
			}
		}
		input.close();
		input2.close();
		input3.close();
		writer.close();
		System.out.println("done");
	}
}
