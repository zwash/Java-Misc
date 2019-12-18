package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
//import java.util.HashSet;

public class PSALIcheck {
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_20190618.txt"));
		BufferedReader tnList = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\PSALI_tn_ranges.txt"));
		PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\andrew_psali_range_report.txt");
		HashMap<Long, Long> tns = new HashMap<Long, Long>();
		String line;
		while ((line = tnList.readLine())!= null) {
			String[] range = line.split("\t");
			tns.put(Long.parseLong(range[0]), Long.parseLong(range[1]));
		}
		String s;
		int i = 0;
		while((s = input.readLine()) != null) {
			try {
				long ani = Long.parseLong(s.substring(1, 11));
				for (long low : tns.keySet()) {
					long high = tns.get(low);
					if (low <= ani && ani <= high) {
						String customerName = s.substring(187, 219);
						String coid1 = s.substring(260, 265);
						String coid2 = s.substring(475, 480);
						writer.println(low + "," + high + "," + ani + "," + customerName + "," + coid1 + "," + coid2);
						tns.remove(low);
					}
				}
				/*if (tns.contains(s.substring(1, 11))) {
					String customerName = s.substring(187, 219);
					String coid1 = s.substring(260, 265);
					String coid2 = s.substring(475, 480);
					writer.println(ani + "," + customerName + "," + coid1 + "," + coid2);
					tns.remove(ani);
				}*/
			}
			catch (Exception e) {
				i++;
				continue;
			}
		}
		for (long ani : tns.keySet()) {
			String customerName = "not found in database";
			String coid1 = "not found in database";
			String coid2 = "not found in database";
			writer.println(ani + "," + customerName + "," + coid1 + "," + coid2);
		}
		writer.println();
		writer.println(i);
		//writer.println("TNs not Found in ALI(" + tns.size() + "):");
		//writer.println(tns.toString());
		input.close();
		tnList.close();
		writer.close();
	}
}
