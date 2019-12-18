package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class FindDeletes {

		public static void main(String[] args) throws Exception {
			BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\west_psali_107.dat"));
			PrintWriter writer = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\107PSdeletes.dat");
			String s;
			while((s = input.readLine()) != null) {
				if (s.charAt(0) == 'D') {
					writer.println(s);
				}
			}
			input.close();
			writer.close();
		}
}
