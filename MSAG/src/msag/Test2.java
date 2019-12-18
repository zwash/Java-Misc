package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class Test2 {

	public static void main(String[] args) throws Exception{
		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\FULL_MSAG_20191203.txt"));
		PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\PSAP_to_community.txt");
		HashMap<String, String> psaps = new HashMap<String, String>();
		String s;
		while ((s = input.readLine()) != null) {
			String psapid = s.substring(134, 138);
			if (!psaps.containsKey(psapid)) {
				psaps.put(psapid, s.substring(88, 120).trim());
			}else {
				String community = s.substring(88, 120).trim();
				if (!community.contentEquals(psaps.get(psapid))) {
					String oldValue = psaps.get(psapid);
					psaps.put(psapid, oldValue + "," + s.substring(88, 120).trim()); 
				}
			}
			
		}
		for (String x : psaps.keySet()) {
			writer.println(x + "," + psaps.get(x));
		}
		input.close();
		writer.close();
	}
}
