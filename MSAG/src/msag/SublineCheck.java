package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class SublineCheck {

	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\sublines1.txt";
	public static String inputFile2 = "C:\\Users\\zwashington\\Documents\\java test\\sublines2.txt";
	public static String writeFile = "C:\\Users\\zwashington\\Documents\\java test\\mainlines1.txt";
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		PrintWriter writer = new PrintWriter(writeFile);
		HashSet<String> mainlines = new HashSet<String>();
		String s;
		while ((s = input.readLine()) != null) {
			mainlines.add(s.substring(230, 240));
		}
		input.close();
		BufferedReader input2 = new BufferedReader(new FileReader(inputFile2));
		String str;
		while ((str = input2.readLine()) != null) {
			mainlines.add(str.substring(230, 240));
		}
		String str2;
		input2.close();
		input2 = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\PSALITNOG.txt"));
		while ((str2 = input2.readLine()) != null) {
			if (!(str2.substring(1, 11).contentEquals(str2.substring(230, 240)))) {
				mainlines.add(str2.substring(230, 240));
			}

		}
		input2.close();
		BufferedReader westali = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\WAFULLTN1.txt"));
		String s2;
		while ((s2 = westali.readLine()) != null) {
			String ani = s2.substring(1, 11);
			if (mainlines.contains(ani)) {
				writer.println(s2);
			}
		}
		westali.close();
		westali = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\WAFULLTN2.txt"));
		String s3;
		while ((s3 = westali.readLine()) != null) {
			String ani = s3.substring(1, 11);
			if (mainlines.contains(ani)) {
				writer.println(s3);
			}
		}
		String s4;
		westali.close();
		westali = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\PSALITNOG.txt"));
		while ((s4 = westali.readLine()) != null) {
			String ani = s4.substring(1, 11);
			if (mainlines.contains(ani)) {
				writer.println(s4);
			}
		}
		writer.close();
		System.out.println("done");
	}
}
