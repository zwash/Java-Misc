package msag;

import java.util.*;
import java.io.*;
import java.lang.Integer;
public class MSAGFormat {
	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\MOSCIDMSAG.txt";
	public static String outputFile = "C:\\Users\\zwashington\\Documents\\java test\\MOSCIDMSAG_write.txt";
	public static String fipsFile = "C:\\Users\\zwashington\\Documents\\java test\\FIPS_lookup.txt";
	public static String esnFile = "C:\\Users\\zwashington\\Documents\\java test\\ESN_lookup3.txt";
	public static String esnDuplicate = "C:\\Users\\zwashington\\Documents\\java test\\ESN_duplicate.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		Map<String,String> fipsMap = fipsLookup(fipsFile);
		Map<Integer,String> esnMap = esnLookup(esnFile); 
		Map<Integer, Integer> duplicateMap = duplicateLookup(esnDuplicate);
		Scanner input = new Scanner(new File(inputFile));
		PrintWriter writer = new PrintWriter(outputFile);
		Set<Integer> unknowns = new HashSet<Integer>();
		int count = 1; 
		int navylines = 0;
		int falloutlines = 0;
		while (input.hasNextLine()) {
			String s = input.nextLine();
			String countyCode = s.substring(172, 178);
			String esnFull = s.substring(123, 128);
			if (fipsMap.containsKey(countyCode)) {
				String fips = fipsMap.get(countyCode);
				int esn = Integer.parseInt(esnFull);
				if (countyCode.equals("KINGWA") && duplicateMap.containsKey(esn)) { 
					esn = duplicateMap.get(esn);
					esnFull = esnText(esn);
				}
				if (esnMap.containsKey(esn)) {
					String psapid = esnMap.get(esn);
					s = s.substring(0,123) + esnFull + s.substring(128,134) + psapid + fips + s.substring(142, 172) + "      " + s.substring(178);
					writer.println(s);
				}else {
					unknowns.add(esn);
					if (esn != 1187 && esn != 1024 && esn != 1027 && esn != 1028 && esn != 1037) {
						falloutlines++;
						System.out.println("Did not find PSAP for ESN: " + esn + " on line " + count);
						System.out.println(s);
					}else {
						navylines++;
					}
				}
			}else {
				System.out.println("Did not find county code, line number: " + count);
				System.out.println(s);
				falloutlines++;
			}
			count++;
		}
		System.out.println(unknowns.toString());
		System.out.println("US Navy Bangor lines skipped: " + navylines);
		System.out.println("Number of fallout lines: " + falloutlines);
		input.close();
		writer.close();
	}
	
	public static Map<String, String> fipsLookup(String s) throws FileNotFoundException {
		Scanner input = new Scanner(new File(s));
		Map<String,String> fipsLookup = new HashMap<String, String>();
		while (input.hasNextLine()) {
			String line = input.nextLine();
			fipsLookup.put(line.substring(0, 6), (line.substring(7)));
		}
		input.close();
		return fipsLookup;
	}
	
	public static Map<Integer, String> esnLookup(String s) throws FileNotFoundException {
		Scanner input = new Scanner(new File(s));
		Map<Integer, String> esnLookup = new HashMap<Integer, String>();
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Scanner row = new Scanner(line);
			int i = row.nextInt();
			String psapid = row.next();
			esnLookup.put(i, psapid);
			row.close();
		}
		input.close();
		return esnLookup;
	}
	
	public static Map<Integer, Integer> duplicateLookup(String s) throws FileNotFoundException {
		Scanner input = new Scanner(new File(s)); 
		Map<Integer, Integer> duplicateLookup = new HashMap<Integer, Integer>();
		while (input.hasNextLine()) {
			String line = input.nextLine();
			Scanner row = new Scanner(line);
			duplicateLookup.put(row.nextInt(), row.nextInt());
			row.close();
		}
		input.close();
		return duplicateLookup; 
	}
	
	public static String esnText(int i) {
		String esnFull = i + "";
		while (esnFull.length() < 5) {
			esnFull = "0" + esnFull;
		}
		return esnFull;
	}
}

