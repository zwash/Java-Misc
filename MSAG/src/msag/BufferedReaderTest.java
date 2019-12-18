package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.lang.Integer;

public class BufferedReaderTest {
	
	public static void main(String[] args) throws Exception{
		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\WAFULLMSAG_write.txt"));
		String line;
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		while((line = input.readLine()) != null) {
			if (counts.containsKey(line.substring(134, 138))) {
				String psapid = line.substring(134, 138);
				int msagcount = counts.get(psapid);
				msagcount++;
				counts.put(psapid, msagcount);
			}else {
				counts.put(line.substring(134, 138), 0);
			}
		}
		input.close();
		System.out.println(counts);
	}
}
