package msag;

import java.io.PrintWriter;

public class CreateStagingErrors {

	public static void main(String[] args) throws Exception {
		PrintWriter goodfile = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\goodPREchanges.dat");
		PrintWriter badfile = new PrintWriter("C:\\Users\\zwashington\\Documents\\java test\\badPREchanges.dat");
		long ani = 2002060000L;
		int recordsToMake = 4;
		String exampleGood = "                Heartbeat TEST                                                    VANCOUVER UNINCORP              WA                                                            (NP) ALMOND, KEVIN & ROBIN      13    6006           R57712290 0605190011TEST2                     000                                                                                                 20190605                                                                                 TEST2                              *";
		String exampleBad = "                Heartbeat TEST                                                    VANCOUVER UNINCORP              WA                                                            (NP) ALMOND, KEVIN & ROBIN      13    6006           R57712290 0605190011TEST2                     000                                                                                                 20190605                                                                                 TEST2                               *";;
		for (int i = 0; i < recordsToMake; i++) {
			goodfile.println("I" + ani + exampleGood);
			badfile.println("I" + ani + exampleBad);
			ani++;
		}
		goodfile.close();
		badfile.close();
	}
}
