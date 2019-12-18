package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class DailyPSALI {
	
	public static String inputFile = "C:\\Users\\zwashington\\Documents\\WAPSALI.000273.dat";
	
	public static void main(String[] args) throws Exception{
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\WAPSALI273.dat");
		String s = input.readLine();
		int length = s.length();
		if (length % 512 == 0) {
			int lines = length / 512;
			int start = 0;
			int stop = 512;
			for (int i = 0; i < lines; i++) {
				String record = s.substring(start, stop);
				if (!record.startsWith("UHL") && !record.startsWith("UTL")) {
					record  = record.substring(0, 260) + "PSALI" + record.substring(265, 475) + "PSALI" + record.substring(480);
				}
				writer.println(record);
				start += 512;
				stop += 512;
			}
		}else {
			System.out.println("line length invalid");
		}
		input.close();
		writer.close();
	}
}
