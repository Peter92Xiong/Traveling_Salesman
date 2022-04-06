import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Arrays;
import java.util.Scanner;

public class Read2DArray {
   public static void main(String args[]) throws Exception {
      Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\Peter\\Documents\\tsp15.txt")));
      int rows = 15;
      int columns = 15;
      int [][] myArray = new int[rows][columns];
      while(sc.hasNextLine()) {
         for (int i=0; i<myArray.length; i++) {
            String[] line = sc.nextLine().trim().split(" ");
            for (int j=0; j<line.length; j++) {
               myArray[i][j] = Integer.parseInt(line[j]);
            }
         }
      }
      System.out.println(Arrays.deepToString(myArray));
   }
}