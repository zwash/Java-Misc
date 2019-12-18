package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class CowliztMSAG {
	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_PRE_20190623.txt";
	public static String writeFile = "C:\\Users\\zwashington\\Documents\\java test\\Cowlitz_ALI_pre.dat";
	
	public static void main(String[] args) throws Exception{
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		PrintWriter writer = new PrintWriter(writeFile);
		String s;
		while((s = input.readLine()) != null) {
			String community = s.substring(93, 125).trim();
			if (community.contentEquals("LONGVIEW") || community.contentEquals("LONGVIEW") || community.contentEquals("KELSO") || community.contentEquals("CASTLE ROCK") || community.contentEquals("KALAMA") || 
				community.contentEquals("WOODLAND") || community.contentEquals("TOUTLE") || community.contentEquals("SILVER LAKE")) {
				writer.println("D" + s.substring(1));
			}
		}
		input.close();
		writer.close();
	}
}
