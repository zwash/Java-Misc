package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;


public class Test {

	public static void main(String[] args) throws Exception{
		BufferedReader input = new BufferedReader(new FileReader("\\\\sea-fs-1\\Teams\\AQPS_2\\AQPS\\Iowa\\MSAG\\IA MSAG parsed.txt"));
		BufferedReader tnList = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\Iowa_duplicate_esn_list.txt"));
		PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\duplicate ESN MSAG count.txt");
		HashMap<String, Integer> tns = new HashMap<String, Integer>();
		String line;
		while ((line = tnList.readLine())!= null) {
			tns.put(0 + line.substring(0, 10), 0);
		}
		String s;
		while ((s = input.readLine()) != null) {
			String check = s.substring(123, 128) + s.substring(172, 178);
			if (tns.keySet().contains(check)) {
				tns.put(check, tns.get(check) + 1);
			}
		}
		for (String str : tns.keySet()) {
			int count = tns.get(str);
			writer.println(str + "," + count);
		}
		input.close();
		tnList.close();
		writer.close();
	}
}
