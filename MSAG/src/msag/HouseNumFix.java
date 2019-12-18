package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class HouseNumFix {

	
	public static void main(String[] args) throws Exception{
		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\PSALI171_fix.txt"));
		PrintWriter fixFile = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\west_psali_171.dat");
		String line;
		int count = 0;
		while ((line = input.readLine()) != null) {
			String houseNum = line.substring(11, 21);
			if (leadingZeros(houseNum)) {
				houseNum = stripZeros(houseNum);
				fixFile.println(line.substring(0, 11) + houseNum + line.substring(21));
				count++;
			}else {
				fixFile.println(line);
			}
		}
		input.close();
		fixFile.close();
		System.out.println("Lines changed : " + count);
	}
	
	public static boolean leadingZeros(String s) {
		try {
			String check = String.valueOf(Integer.parseInt(s));
			return !(s.length() == check.length());
		}catch(Exception e) {
			return false;
		}
	}
	
	public static String stripZeros (String s) {
		String fix = String.valueOf(Integer.parseInt(s));
		for (int i = fix.length(); i < 10; i++) {
			fix = fix + " ";
		}
		return fix;
	}
}
