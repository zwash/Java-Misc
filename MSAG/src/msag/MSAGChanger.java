package msag;

import java.util.*;
import java.io.*;
import java.lang.Integer;
public class MSAGChanger {
	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\EXAMPLE MSAG.txt";
	public static String outputFile = "C:\\Users\\zwashington\\Documents\\java test\\EXAMPLE_write.txt";
	public static String fipsFile = "C:\\Users\\zwashington\\Documents\\java test\\FIPS_lookup.txt";
	public static String esnFile = "C:\\Users\\zwashington\\Documents\\java test\\ESN_lookup2.txt";
	public static String esnDuplicate = "C:\\Users\\zwashington\\Documents\\java test\\ESN_duplicate.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		Map<String,String> fipsMap = fipsLookup(fipsFile);
		Map<Integer,String> esnMap = esnLookup(esnFile); 
		Map<Integer, Integer> duplicateMap = duplicateLookup(esnDuplicate);
		Scanner input = new Scanner(new File(inputFile));
		PrintWriter writer = new PrintWriter(outputFile);
		Set<Integer> unknowns = new HashSet<Integer>();
		int count = 1; 
		while (input.hasNextLine()) {
			String s = input.nextLine();
			String countyCode = s.substring(172, 178);
			String esnFull = s.substring(123, 128);
			int esn = Integer.parseInt(esnFull);
			if (countyCode.equals("KINGWA") && duplicateMap.containsKey(esn)) { 
				esn = duplicateMap.get(esn);
				esnFull = esnText(esn);
			}
			if (fipsMap.containsKey(countyCode)) {
				String fips = fipsMap.get(countyCode);
				s = s.substring(0, 138) + fips + s.substring(142, 172) + "      " + s.substring(178);
			}else {
				System.out.println("error with FIPS on line " + count);
			}
			if (esnMap.containsKey(esn)) {
				String psapid = esnMap.get(esn);
				s = s.substring(0,123) + esnFull + s.substring(128,134) + psapid + s.substring(138);
			}else {
				unknowns.add(esn);
				System.out.println(s);
			}
			writer.println(s);
			count++;
		}
		System.out.println(unknowns.toString());
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

