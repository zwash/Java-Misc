package msag;

import java.io.BufferedReader;
//import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
public class ALIFormat {
	
	public static int filenum = 224;
	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\PSALI" + filenum + "_fix_pt1.txt";
			// "PSALI65_fix_pt1.txt
	public static String migrateFile = "C:\\Users\\zwashington\\Documents\\java test\\test.txt";
	public static String unlockFile = "C:\\Users\\zwashington\\Documents\\java test\\PSALI" + filenum + "_fix.txt";
			// "PSALI80_fix.txt
	public static String vendorLookup = "C:\\Users\\zwashington\\Documents\\java test\\vendor_lookup.txt";
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		PrintWriter writerMigrate = new PrintWriter(migrateFile);
		PrintWriter writerUnlock = new PrintWriter(unlockFile);
		Map<String,String> vendorMap = vendorLookup();
		Set<String> unknownIDs = new HashSet<String>();
		String s;
		while ((s = input.readLine()) != null) {
			if (s.length() == 512) {
				String companyID = s.substring(260, 265);
				if (vendorMap.containsKey(companyID.trim())) {
					String companyID2 = checkID(companyID, vendorMap);
					if (!companyID2.contentEquals(s.substring(475, 480))) {
						writerUnlock.println(s.substring(0,260) + companyID + s.substring(265, 475) + companyID2 + s.substring(480));
						//writerUnlock.println("U"+ s.substring(1));
						//s =  "M" + s.substring(1, 260) + companyID + s.substring(265, 475) + companyID2 + s.substring(480);
						//writerMigrate.println(s);
					}
				}else {
					unknownIDs.add(companyID);
					//System.out.println("did not find company id = " + companyID);
				}
			}
		}
		input.close();
		writerUnlock.close();
		writerMigrate.close();
		System.out.println(unknownIDs);
		System.out.println("done");
	}
	
	public static String checkID (String s, Map<String, String> map) {
		if (map.containsKey(s.trim())) {
			String iD = map.get(s.trim());
			while (iD.length() < 5) {
				iD = iD + " ";
			}
			return iD;
		}
		return s;
	}
	
	public static Map<String, String> vendorLookup() throws FileNotFoundException {
		Map<String,String> map = new HashMap<String, String>();
		Scanner input = new Scanner(new File(vendorLookup));
		while (input.hasNextLine()) {
			String s = input.nextLine();
			String[] iDs = s.split("\t");
			map.put(iDs[0].trim(), iDs[1].trim());
		}
		input.close();
		return map;
	}
}