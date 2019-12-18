package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class ClowlitzMSAG {
	
	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\FULL_MSAG_20190625.txt";
	public static String inputFile2 = "C:\\Users\\zwashington\\Documents\\java test\\Cowlitz_MSAG_pre6.dat";
	public static String writeFile = "C:\\Users\\zwashington\\Documents\\java test\\Cowlitz_failed_msag.txt";
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		BufferedReader input2 = new BufferedReader(new FileReader(inputFile2));
		PrintWriter writer = new PrintWriter(writeFile);
		HashSet<String> set = new HashSet<String>();
		String line;
		while ((line = input2.readLine()) != null) {
			set.add(line.substring(0, 127));
		}
		String s;
		while((s = input.readLine()) != null) {
			if (s.substring(138, 142).contentEquals("0015")) {
				String record = s.substring(0,127);
				if (set.contains(record)) {
					set.remove(record);
				}
			}
		}
		input.close();
		input2.close();
		for (String msag : set) {
			writer.println(msag);
		}
		writer.close();
	}
}
