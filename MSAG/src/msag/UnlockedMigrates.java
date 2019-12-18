package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class UnlockedMigrates {

	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_20190429.txt";
	public static String migrateFile = "C:\\Users\\zwashington\\Documents\\java test\\migrate_unlocked.txt";
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		PrintWriter writerMigrate = new PrintWriter(migrateFile);
		String s;
		int count = 0;
		while ((s = input.readLine()) != null) {
			if (s.length() == 512) {
				if (s.charAt(0) == 'I') {
					//s = "M" + s.substring(1);
					//writerMigrate.println(s);
					count++;
				}
			}
		}
		input.close();
		writerMigrate.close();
		System.out.println("done");
		System.out.println("Lines with FOC I: " + count);
	}
}
