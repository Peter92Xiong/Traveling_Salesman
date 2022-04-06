import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ReadFile {

	static int[][] numArray;
	
	public static void main(String[] args) throws IOException {

		// http://textfiles.com/100/captmidn.txt
		numArray = new int [15][15];
		File file = new File("C:\\Users\\Peter\\Documents\\tsp15.txt");
		Scanner scanner = new Scanner(file);

//		String fileContent = "THIS IS A NEW FILE MADE BY US";
//		while (scan.hasNextLine()) {
//			fileContent = fileContent.concat(scan.nextLine() + "\n");
//		}
		int linecount = 0;
		while(scanner.hasNextLine()) {
//			for(int i = 0; i < numArray.length; i++) {
//				String[] line = sc.nextLine().trim().split("");
//				for(int j = 0; j <line.length; j++) {
//					numArray[i][j] = Integer.parseInt(line[j]);
//				}
//			}
//			System.out.println(scanner.nextLine());
			String[] s = scanner.nextLine().split(" ");
			for (int i = 0; i < s.length; i++) {
				numArray[linecount][i] = Integer.parseInt(s[i]);
			}
			linecount++;
		}
		System.out.println(Arrays.deepToString(numArray));
		//System.out.println(scan.nextLine());
		//FileWriter writer = new FileWriter("C:\\Users\\Peter\\Documents\\tsp15.txt");
//		writer.write(fileContent);
//		writer.close();

	}

}