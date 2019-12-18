package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class ALIextract {

	public static int filenum = 235;	
	public static void main(String[] args) throws Exception {
			BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\WAPSALI" + filenum + ".txt"));
			PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\PSALI" + filenum + "_fix_pt1.txt");
			String line;
			while ((line = input.readLine()) != null) {
				line = line.replace("\\*", "\\*\\n\\r");
				writer.println(line);
			}
			input.close();
			writer.close();
			System.out.println("done");
		}
}
