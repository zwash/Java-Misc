package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class CTLPSALIcheck {

	public static void main(String[] args) throws Exception {
		//String file = "CTLQP_psali_ranges_101719_";
		//for (int j = 0; j < 26; j++) {
			String currentfile = "Random_CTL_ranges";
			BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_20191008.txt"));
			PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\" + currentfile + "_check.csv");
			BufferedReader tnlist = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\" + currentfile + ".csv"));
			Map<Long, String> tns = new TreeMap<Long, String>();
			String s;
			while ((s = tnlist.readLine()) != null) {
				if (s.startsWith("NPA")) {
					continue;
				}
				String[] range = s.split(",");
				long low = Long.parseLong(range[0] + range[1] + range[2]);
				long high = Long.parseLong(range[0] + range[1] + range[3]);
				for (long i = low; i <= high; i++) {
					tns.put(i, range[4]);
				}
			}
			String line;
			while ((line = input.readLine()) != null) {
				if (line.startsWith("UHL") || line.startsWith("UTL")) {
					continue;
				}
				long ani = Long.parseLong(line.substring(1, 11));
				if (tns.keySet().contains(ani)) {
					String aliCoid = line.substring(260, 265).trim();
					/*String aliCoid2 = line.substring(475, 480);
					writer.println(ani + "," + aliCoid + "," + aliCoid2);*/
					String ctlCoid = tns.get(ani);
					if (aliCoid.contentEquals(ctlCoid)) {
						writer.println(ani + ",match,good to load");
						
					}else {
						writer.println(ani + ",no match," + aliCoid + "," + ctlCoid);
					}
					tns.remove(ani);
				}
			}
			for (long x : tns.keySet()) {
				writer.println(x + ",not found in ALI");
			}
			tnlist.close();
			input.close();
			writer.close();
		//}
	}
}
