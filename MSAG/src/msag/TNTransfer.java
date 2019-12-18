package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class TNTransfer {
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_20191209.txt"));
		BufferedReader tnList = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\psali_121219_tns.txt"));
		PrintWriter writerMigrate = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\psali_121219_m.dat");
		PrintWriter writerUnlock = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\psali_121219_u.dat");
		HashSet<String> tns = new HashSet<String>();
		String line;
		while ((line = tnList.readLine())!= null) {
			tns.add(line.substring(0, 10));
		}
		String s;
		while((s = input.readLine()) != null) {
			if (tns.contains(s.substring(1, 11))) {
				if (!s.substring(1, 11).contentEquals(s.substring(230, 240))) {
					System.out.println("subline found: " + s.substring(1, 11));
					//writerMigrate.println("C" + s.substring(1, 230) + s.substring(1, 11) + s.substring(240));
				}
				String companyID = "PSALI";
				String companyID2 = "PSALI";
				writerUnlock.println("U"+ s.substring(1));
				writerMigrate.println("M" + s.substring(1, 260) + companyID + s.substring(265, 475) + companyID2 + s.substring(480));
			}
		}
		input.close();
		tnList.close();
		writerMigrate.close();
		writerUnlock.close();
	}
}
