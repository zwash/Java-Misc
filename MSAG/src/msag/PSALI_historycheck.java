package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;

public class PSALI_historycheck {

	public static int fileStart = 23;
	public static int fileEnd = 43;
	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_20190404.txt";
	public static String writeFile = "C:\\Users\\zwashington\\Documents\\java test\\0404_psali_check.txt";
	
	public static void main(String[] args) throws Exception {
		HashSet<String> validcoid1s = new HashSet<String>();
		validcoid1s.add("CTLQP");
		validcoid1s.add("CTLP ");
		validcoid1s.add("EDMND");
		validcoid1s.add("YELM ");
		validcoid1s.add("ATGI ");
		validcoid1s.add("X5SOL");
		validcoid1s.add("FOCAL");
		validcoid1s.add("DESWA");
		PrintWriter writer = new PrintWriter(writeFile);
		String psaliFile;
		for (int i = fileStart; i <= fileEnd; i++) {
			psaliFile = "C:\\Users\\zwashington\\Documents\\java test\\west_psali_" + i + ".txt";
			System.out.println("Checking file: " + psaliFile);
			writer.println("Checking file: " + psaliFile);
			BufferedReader input;
			input = new BufferedReader(new FileReader(psaliFile));
			String s;
			BufferedReader aliextract = new BufferedReader (new FileReader(inputFile));
			HashMap<String, String> map = new HashMap<String, String>();
			while((s= input.readLine()) != null) {
				String coid1 = s.substring(260, 265);
				if (validcoid1s.contains(coid1)) {
					String ani = s.substring(1, 11);
					map.put(ani, s);
				}
			}
			input.close();
			System.out.println("map contains: " + map.size() + " elements");
			String line;
			while ((line = aliextract.readLine()) != null) {
				String ani = line.substring(1, 11);
				if (map.containsKey(ani)) {
					String dbcoid1 = line.substring(260, 265);
					//String psalicoid1 = map.get(ani).substring(260, 265);
					if (!validcoid1s.contains(dbcoid1) || !line.substring(475, 480).contentEquals("ETCWE")) {
						String excoid2 = line.substring(475, 480);
						writer.println(ani + "," + dbcoid1 + "," + excoid2);
					}
				}
			}
			aliextract.close();
		}
		System.out.println("done");
		writer.close();
	}
}
