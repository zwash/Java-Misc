package msag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
public class Unlocksplit {
	
	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\CTLQ_CTL_ALI_fi1.txt";
	public static String outputFile = "C:\\Users\\zwashington\\Documents\\java test\\CTLQ_CTL_ALI_fi1_Migrate.txt";
	public static String outputFile2 = "C:\\Users\\zwashington\\Documents\\java test\\CTLQ_CTL_ALI_fi1_Unlock.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File(inputFile));
		PrintWriter writer = new PrintWriter(outputFile);
		PrintWriter writer2 = new PrintWriter(outputFile2);
		while (input.hasNextLine()) {
			String s = input.nextLine();
			if (input.hasNextLine()) {
				String s2 = input.nextLine();
				writer.println(s);
				writer2.println(s2);
			}
		}
		writer.close();
		writer2.close();
		input.close();
		System.out.println("done");
	}
}
