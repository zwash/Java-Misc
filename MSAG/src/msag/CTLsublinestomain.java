package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class CTLsublinestomain {

	public static void main(String[] args) throws Exception{
		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\Cowlitz_ALI_pre2.dat"));
		PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\CTL_subline_fix_pre.dat");
		String s;
		while ((s = input.readLine()) != null) {
			if (s.length() == 512) {
				String companyID = s.substring(260, 265);
				if (companyID.contentEquals("LVL3 ") || companyID.contentEquals("CTLQ ")) {
					writer.println("C" + s.substring(1, 230) + "          " + s.substring(240));
				}
			}
		}
		input.close();
		writer.close();
	}
	
}
