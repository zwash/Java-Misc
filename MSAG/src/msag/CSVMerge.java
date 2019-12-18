package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class CSVMerge {
	
	public static String inputFile = "\\\\sea-fs-1\\Teams\\AQPS_2\\Team Folders\\MN\\King_County_ALI\\August\\King_County_ALI_Part1.csv";
	public static String inputFile2 = "\\\\sea-fs-1\\Teams\\AQPS_2\\Team Folders\\MN\\King_County_ALI\\August\\King_County_ALI_Part2.csv";
	public static String writeFile = "\\\\sea-fs-1\\Teams\\AQPS_2\\Team Folders\\MN\\King_County_ALI\\August\\King_County_ALI_Full.csv";
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		BufferedReader input2 = new BufferedReader(new FileReader(inputFile2));
		PrintWriter writer = new PrintWriter(writeFile);
		String s;
		while ((s = input.readLine()) != null) {
			writer.println(s);
		}
		while ((s = input2.readLine()) != null) {
			writer.println(s);
		}
		input.close();
		input2.close();
		writer.close();
	}
}
