package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class ANIDiscrepancy {

	public static String inputFile1 = "C:\\Users\\zwashington\\Documents\\WATN509.txt";
	public static String inputFile2 = "C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_20190401.txt";	
	
	public static void main(String[] args) throws Exception {
		BufferedReader input1 = new BufferedReader(new FileReader(inputFile1));
		BufferedReader input2 = new BufferedReader(new FileReader(inputFile2));
		HashSet<String> westAni = new HashSet<String>();
		HashSet<String> comtechAni = new HashSet<String>();
		String line;
		int count = 0;
		while ((line = input1.readLine()) != null) {
			westAni.add(line.substring(1, 11));
			count++;
		}
		String s;
		while ((s = input2.readLine()) != null) {
			String ani = s.substring(1,11);
			if (westAni.contains(ani)) {
				westAni.remove(ani);
				count--;
			}/*else {
				comtechAni.add(ani);
			}*/
		}
		input1.close();
		input2.close();
		PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\Missing_TNs_7.txt");
		for (String tn : westAni) {
			writer.println(tn);
		}
		writer.close();
		System.out.println("List of " + count + " TNs in West ALI and not in Comtech ALI: ");
		}
}
