package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class StagingTableCleanup {
	
	public static String ali_File = "C:\\Users\\zwashington\\Documents\\java test\\ALI_20191212153646.csv"; // CSV FULL ALI EXTRACT
	public static String staging_File = "C:\\Users\\zwashington\\Documents\\java test\\prod_flatali_121219.csv"; // ALI STAGING TABLE
	public static String staging_File_new = "C:\\Users\\zwashington\\Documents\\java test\\cleanstaging_prod_121219.csv"; // NEW, CLEAN STAGING TABLE
	public static String bad_flatALIID = "C:\\Users\\zwashington\\Documents\\java test\\badIDs_121219.txt"; // LIST OF FLAT ALI IDS TO REMOVE
	
	public static void main(String[] args) throws Exception {
		PrintWriter writer = new PrintWriter(staging_File_new);
		HashMap<String, String> goodlines = removeDups(); 
		goodlines = checkALI(goodlines);
		HashSet<String> goodids = new HashSet<String>();
		for (String key : goodlines.keySet()) {
			String[] value = goodlines.get(key).split(",", 3);
			writer.println(value[1] + "," + value[2]);
			goodids.add(value[1]);
		}
		printbadaliids(goodids);
		writer.close();
	}
	
	public static HashMap<String, String> removeDups() throws Exception{
		BufferedReader staging = new BufferedReader(new FileReader(staging_File));
		String s;
		HashMap<String, String> result = new HashMap<String, String>();
		while ((s = staging.readLine())!= null) { //reads staging table .csv file
			if (s.startsWith("FlatALI")) {
				continue;
			}
			String[] entry = s.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1); //split .csv into array, ignores commas inside quotes
			String key = entry[1] + entry[28].trim() + entry[29].trim();
			Date date = getSTDate(entry[53]);
			if (!result.containsKey(key)) {
				result.put(key, entry[53] + "," +s);
			}else {
				String[] check = result.get(key).split(",", 2);
				Date checkdate = getSTDate(check[0]);
				if (date.compareTo(checkdate) > 0 ) { //checks if date in current entry is later than date inside map
					result.put(key, entry[53] + "," +s);
				}
			}
		}
		staging.close();
		return result;
	}
	
	public static Date getSTDate(String s) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
		Date date = sdf.parse(s);
		return date;
	}
	
	public static Date getALIDate(String s) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = sdf.parse(s);
		return date;
	}
	
	public static HashMap<String, String> checkALI(HashMap<String, String> map) throws Exception{
		BufferedReader ali = new BufferedReader(new FileReader(ali_File));
		String s;
		while ((s = ali.readLine()) != null) {
			if (s.startsWith("ANI")) {
				continue;
			}
			String[] aliArray = s.split(",");
			String key = aliArray[0].replaceAll("^\"|\"$", "") + aliArray[6].replaceAll("^\"|\"$", "") + aliArray[7].replaceAll("^\"|\"$", "");
			//String key = aliArray[0].replaceAll("^\"|\"$", "") + aliArray[aliArray.length - 11].replaceAll("^\"|\"$", "") + aliArray[aliArray.length - 10].replaceAll("^\"|\"$", "");
			if (map.containsKey(key)) {
				Date alidate = getALIDate(aliArray[10].replaceAll("^\"|\"$", ""));
				//Date alidate = getALIDate(aliArray[aliArray.length - 3].replaceAll("^\"|\"$", ""));
				String[] value = map.get(key).split(",", 2);
				Date stagingdate = getSTDate(value[0]);
				if(alidate.compareTo(stagingdate) > 0) {
					map.remove(key);
				}
			}
		}
		ali.close();
		return map;
	}
	
	public static void printbadaliids (HashSet<String> set) throws Exception{
		PrintWriter writer = new PrintWriter(bad_flatALIID);
		BufferedReader staging = new BufferedReader(new FileReader(staging_File));
		String s;
		writer.println("FLATALIID");
		while ((s = staging.readLine())!= null) {
			if (s.startsWith("FlatALI")) {
				continue;
			}
			String[] entry = s.split(",", 2);
			String flatALIID = entry[0];
			if (!set.contains(flatALIID)) {
				writer.println(flatALIID);
			}
		}
		writer.close();
		staging.close();
	}
}
