package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class File_manipulator {

	
	public static void main(String[] args) throws Exception { 
		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\WA PSALI RANGES zw.csv"));
		BufferedReader input2 = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_20191120.txt"));
		PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\psali_analysis_112219.txt");
		HashSet<String> tns = new HashSet<String>();
		String s;
		while ((s = input.readLine()) != null) {
			String[] parts = s.split(",");
			long low = Long.parseLong(parts[0] + parts[1] + parts[2]);
			long high = Long.parseLong(parts[0] + parts[1] + parts[3]);
			for (long i = low; i <= high; i++) {
				tns.add(i + "");
			}
		}
		while ((s = input2.readLine()) != null) {
			if (tns.contains(s.substring(1, 11))) {
				if (!s.substring(260, 265).contentEquals("PSALI")) {
					writer.println(s.substring(1, 11) + "," + s.substring(260, 265) + "," + s.substring(475, 480));
				}
				tns.remove(s.substring(1, 11));
			}
		}
		/*for (String x : tns) {
			writer.println(x + ",not in ali");
		}*/
		input2.close();
		writer.close();
		input.close();
	}
}
