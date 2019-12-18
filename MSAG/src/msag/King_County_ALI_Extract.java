package msag;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;

public class King_County_ALI_Extract {
	
	public static String aliFile = "FULL_ALI_20190905";
	public static String aliExtract = "King_County_ALI_Sept";
	public static int[] wrls_viop_esns = {2927, 2924, 2939, 2921, 2922, 2923, 7056, 3928, 3930, 7015, 3935, 3937, 3927, 3924, 3936, 7019, 3932, 3938, 7034, 3939, 
			7039, 3941, 7050, 3934, 3922, 7051, 7040, 3921, 7036, 3933, 7007, 3929, 7027, 3931, 3923, 7002};
	
	public static void main(String[] args) throws Exception {
		BufferedReader input = new BufferedReader(new FileReader("C:\\Users\\zwashington\\Documents\\java test\\" + aliFile + ".txt"));
		PrintWriter writer = new PrintWriter("\\\\sea-fs-1\\Teams\\AQPS_2\\Team Folders\\MN\\King_County_ALI\\" + aliExtract + ".csv");
		HashSet<Integer> skip_esns = new HashSet<Integer>();
		for (int x : wrls_viop_esns) {
			skip_esns.add(x);
		}
		writer.println("House number,House number suffix,Prefix Directional,Street Name,Street Suffix,Post Directional,Community,State,Location,ESN,County ID,Zip code,Zip extension,x coord,y coord,z coord");
		String s;
		while((s = input.readLine()) != null) {
			if (s.length() == 512) {
				String countycode = s.substring(256, 260);
				if (countycode.contentEquals("0033")) {
					String esn = s.substring(225, 230).trim();
					if (!skip_esns.contains(Integer.parseInt(esn))) {
						String hn = s.substring(11, 21).trim();
						String hn_suf = s.substring(21, 25).trim();
						String pre_dir = s.substring(25, 27).trim();
						String street_name = "\"" + s.substring(27, 87).trim() + "\"";
						String street_suf = s.substring(87, 91).trim();
						String post_dir = s.substring(91, 93).trim();
						String community = "\"" + s.substring(93, 125).trim() + "\"";
						String state = s.substring(125, 127).trim();
						String location = "\"" + s.substring(127, 187).trim() + "\"";
						String zip = s.substring(266, 271).trim();
						String zip_ext = s.substring(271, 275).trim();
						String x_coord = s.substring(319, 328).trim();
						String y_coord = s.substring(328, 337).trim();
						String z_coord = s.substring(337, 342).trim();
						writer.println(hn + "," + hn_suf + "," + pre_dir + "," + street_name + "," + street_suf + "," + post_dir + "," + community + "," + 
								state + "," + location + "," + esn + "," + countycode + "," + zip + "," + zip_ext + "," + x_coord + "," + y_coord + "," + z_coord);
					}
				}
			}
		}
		input.close();
		writer.close();
	}
}
