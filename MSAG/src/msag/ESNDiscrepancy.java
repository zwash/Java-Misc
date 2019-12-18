package msag;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.Integer;

public class ESNDiscrepancy {
	
	public static String inputFile1 = "C:\\Users\\zwashington\\Documents\\java test\\ngcsESNs2.txt";
	public static String inputFile2 = "C:\\Users\\zwashington\\Documents\\java test\\aliESNs2.txt";
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input1 = new Scanner(new File(inputFile2));
		Set<Integer> aliESN = new TreeSet<Integer>();
		while (input1.hasNextInt()) {
			aliESN.add(input1.nextInt());
		}
		input1.close();
		Scanner input2 = new Scanner(new File(inputFile1));
		Set<Integer> ngcsESN = new TreeSet<Integer>();
		while (input2.hasNextInt()) {
			int esn = input2.nextInt();
			
			ngcsESN.add(esn);
		}
		input2.close();
		System.out.println("ESNs that are in NGCS but not in ALI DB: ");
		System.out.println(ngcsESN.toString());
		System.out.println();
		System.out.println("ESNs that are in ALI DB but not in NGCS:");
		System.out.println(aliESN.toString());
	}
}
