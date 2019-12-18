package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class NRF_fullALI {
	
	public static String inputFile = "C:\\Users\\zwashington\\Documents\\java test\\FULL_ALI_20191209.txt";
	public static String tnFile = "C:\\Users\\zwashington\\Documents\\java test\\NRFcheck_121119.txt";
	public static String staging = "C:\\Users\\zwashington\\Downloads\\ALIStaging_Full_.csv";
	public static String drsearch = "C:\\Users\\zwashington\\Downloads\\ALIDRSearch_20190917.csv";
	public static String writeFile = "C:\\Users\\zwashington\\Documents\\java test\\NRFcheck_121119_results.txt";
	
	public static void main(String[] args) throws Exception{
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		BufferedReader tnList = new BufferedReader(new FileReader(tnFile));
		BufferedReader input2 = new BufferedReader(new FileReader(staging));
		BufferedReader input3 = new BufferedReader(new FileReader(drsearch));
		PrintWriter writer = new PrintWriter(writeFile);
		HashSet<String> tns = new HashSet<String>();
		String line;
		while ((line = tnList.readLine())!= null) {
			try {
				tns.add(line.substring(0, 10));
			}catch (Exception e) {
				continue;
			}
		}
		writer.println("TNs in the ALI:");
		String s;
		while((s = input.readLine()) != null) {
			if (tns.contains(s.substring(1, 11))) {
				writer.println(s.substring(1,11));
			}
		}
		writer.println("END ALI CHECK");/*
		writer.println("TNs in the ALI Staging table:");
		String s2;
		while((s2 = input2.readLine()) != null) {
			String[] arr = s2.split(",");
			if (tns.contains(arr[2])) {
				writer.println(arr[2]);
			}
		}
		writer.println("END STAGING CHECK");
		writer.println("TNs existing DR:");
		String s3;
		while((s3 = input3.readLine()) != null) {
			String[] arr = s3.split(",");
			if (tns.contains(arr[3])) {
				writer.println(arr[3]);
			}
		}
		writer.println("END ALI CHECK");*/
		input.close();
		input2.close();
		input3.close();
		tnList.close();
		writer.close();
		System.out.println("done");
	}
}
