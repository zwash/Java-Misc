package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class PSALI_split {
	
	public static int filenum = 224;
	public static void main(String[] arsg) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\PSALI" + filenum + "_fix.txt"));
		PrintWriter westpfile = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\westp_psali_" + filenum + ".dat");
		PrintWriter etcwefile = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\etcwe_psali_" + filenum + ".dat");
		String s;
		while ((s = input.readLine()) != null) {
			if (s.substring(475, 480).contentEquals("WESTP")) {
				westpfile.println(s);
			}else {
				etcwefile.println(s);
			}
		}
		input.close();
		westpfile.close();
		etcwefile.close();
		System.out.println("done");
	}
}
