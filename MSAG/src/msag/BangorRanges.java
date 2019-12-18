package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class BangorRanges {

	public static long lowrange = 4253042000L;
	public static long highrange = 4253046999L;
	public static String ali_File = "C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_20190905.txt";
	public static String result = "C:\\Users\\zwashington\\Documents\\java test\\bangorRangeCheck.txt";
	
	public static void main(String[] args) throws Exception {
		BufferedReader ali = new BufferedReader(new FileReader(ali_File));
		PrintWriter writer = new PrintWriter(result);
		HashMap<Long, String> map = new HashMap<Long, String>();
		for (long i = lowrange; i <= highrange; i++) {
			map.put(i, i + ",NO,N/A,N/A,N/A,N/A");
		}
		String s;
		while ((s = ali.readLine()) != null) {
			long ani;
			try {
				ani = Long.parseLong(s.substring(1, 11));
			}catch (Exception e) {
				continue;
			}
			if (map.keySet().contains(ani)) {
				String esn = s.substring(225, 230).trim();
				String match = "N/A";
				String address = s.substring(11, 21).trim() + " " + s.substring(25, 27).trim() + " " + s.substring(27, 87).trim() + " " + s.substring(87, 91).trim() + " " + s.substring(91, 93).trim();
				String community = s.substring(93, 125).trim();
				map.put(ani, ani + ",YES," + esn + "," + match + "," + address + "," + community);
			}
		}
		for (long x : map.keySet()) {
			writer.println(map.get(x));
		}
		writer.close();
		ali.close();
	}
}
