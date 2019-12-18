package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class MainlineSublineLocationCompare {

	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\mainlines1.txt";
	public static String inputFile2 = "C:\\Users\\zwashington\\Documents\\java test\\diffSubAndMain2.txt";
	public static String writeFile = "C:\\Users\\zwashington\\Documents\\java test\\diffLocationSublines.txt";
	
	public static void main(String[] args) throws Exception{
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		BufferedReader input2 = new BufferedReader(new FileReader(inputFile2));
		PrintWriter writer = new PrintWriter(writeFile);
		HashSet<String> mainlinekeys = new HashSet<String>();
		String s;
		while ((s = input.readLine()) != null) {
			String location = s.substring(127, 187);
			String mainline = s.substring(230, 240);
			mainlinekeys.add(location + mainline);
		}
		String str;
		while ((str = input2.readLine()) != null) {
			String location = str.substring(127, 187);
			String mainline = str.substring(230, 240);
			if (!mainlinekeys.contains(location + mainline)) {
				writer.println(str);
			}
		}
		input.close();
		input2.close();
		writer.close();
		System.out.println("done");
	}
}
