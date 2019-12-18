package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class TNs_in_ALI {
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_20191202.txt"));
		BufferedReader tnList = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\ctl_psali_audit_tns_notinALI.txt"));
		PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\ctl_psali_audit_tns_notinALI_check.txt");
		HashSet<String> tns = new HashSet<String>();
		String line;
		while ((line = tnList.readLine())!= null) {
			try {
				tns.add(line.substring(0, 10));
			}catch (Exception e) {
				System.out.println(line);
				continue;
			}
			
		}
		String s;
		while((s = input.readLine()) != null) {
			String ani = s.substring(1, 11);
			if (tns.contains(ani)) {
				writer.println(ani + "," + s.substring(260,265) + "," + s.substring(475, 480));
				tns.remove(ani);
			}
		}
		System.out.println(tns.size());
		input.close();
		tnList.close();
		writer.close();
	}
}

