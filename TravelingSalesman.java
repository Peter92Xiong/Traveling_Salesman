import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * In order to solve the traveling salesman problem I had help 
 * from the youtube channle "Coding Blocks" and "CS Dojo" credits to their help
 * This program uses dynamic programming and bit shifting
 * The program is O(2^n*n)
 * @author Peter Xiong
 * @version 12/7/21
 *
 */
public class TravelingSalesman
{
//	private AdjacencyLists graph;
	private static int[] visited;
	private static LinkedList<Integer> queue;
	private static int[][] graph; // the graph of edge weights
//	private static int rows;
//	private static int columns;
	private static int n; // number of nodes(cities)
	static int visitedAll;
	static int k; // the amount of possibility that you can take.

	static int[][] dpArray; //this is an array to store past results in order to make the time better.

	
	/**
	 * initialize all variables.
	 */
	public TravelingSalesman()
	{
		n = 15; // used 15 cities for this problem will need to change this number each time new graph input;
		visitedAll = (1 << n) - 1; //this is the number if all the cities have been visited
		// k = 32768;
		k = (1 << n); // this is the amount of ways you can rearrange 15 ones 2^15;
		
		graph = new int[n][n]; //initialize the graph n by n. (15 by 15)
		
		dpArray = new int[k][n]; //a large array of size 2^n and n. all possibilities
		for (int i = 0; i < (1 << n); i++) //initialize all to -1;
		{
			for (int j = 0; j < n; j++)
			{
				dpArray[i][j] = -1;
			}
		}
	}

	/**
	 * Main method to do the TSP. 
	 * will read an input file that has edge weights and place into a 2Darray
	 * to be used on the tsp method. will print the min cost
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		TravelingSalesman ts = new TravelingSalesman();
//		System.out.println(n);
//		System.out.println(visitedAll);
//		System.out.println(k);

		//finding the file and getting the file
		File file = new File("C:\\Users\\Peter\\Documents\\tsp15.txt");
		Scanner scanner = new Scanner(file); //scanner reading the file.

		int linecount = 0; //the number of line
		//this will read the txt file and put all the numbers into a 2Darray.
		while (scanner.hasNextLine())
		{
			String[] s = scanner.nextLine().split(" ");
			for (int i = 0; i < s.length; i++)
			{
				graph[linecount][i] = Integer.parseInt(s[i]);
			}
			linecount++;
		}

		System.out.println("the min cost is: " + tsp(1, 0)); // mask is first one, and we start inital city at 0;
//		System.out.println(Arrays.deepToString(graph).replace("],", "\n").replaceAll("[\\[\\]]", " "));

//		System.out.println(graph.length);
//		findMinRoute(graph);
	}

	/**
	 * recursive function;
	 * @param mask is the number of cities that has been visited so far
	 * example: 01101 <--- the 0s are the cities that have not been visited
	 * @param pos is the position of the current city
	 * @return
	 */
	public static int tsp(int mask, int pos)
	{
		//basecase if all cities have been visited
		if (mask == visitedAll)
		{ // if all cities has been visited
			// then do this return the distance from the orgin to the pos of the city the
			// base case
			return graph[pos][0];
		}
		
		//else do this because not all cities have been visited:
		
		//this if statement check if the mask and pos has been visited
		//if it has been visited, return the result stored at that index.
		//this helps with run time so the code don't repeat. 
		if (dpArray[mask][pos] != -1)
		{
			return dpArray[mask][pos];
		}

		
		int ans = Integer.MAX_VALUE; // set the answer to max fist bc answer should be lower than max the first time
		// else go to the other cities

		for (int city = 0; city < n; city++)  // city 0<15
		{
			if ((mask & (1 << city)) == 0)
			{ // this mean if its zero the city has not been visited
				// if not visited do this
				//this part is a bit confusing
				int newAns = graph[pos][city] + tsp(mask | (1 << city), city); // ORing the citites

				// compare which is min and return
				ans = Math.min(ans, newAns);
			}
		}

		return dpArray[mask][pos] = ans;
	}

//	public static void findMinRoute(int[][] tsp)
//	{
//
//		int result = 0;
//		int counter = 0;
//		int column = 0, row = 0;
//		int min = Integer.MAX_VALUE;
//		List<Integer> visitedRouteList = new ArrayList<>();
//
//		visitedRouteList.add(0);
//		int[] route = new int[tsp.length];
//
//		while (row < tsp.length && column < tsp[row].length) //this goes through the whole graph
//		{
//
//			if (counter >= tsp[row].length - 1) //the end of the row then just break.
//			{
//				break;
//			}
//
//			if (column != row && !(visitedRouteList.contains(column)))  // check if its the same "node" and check it the node's been visited
//			{ //if the node is not in the visitedlist, and node not same node (i,j) do this code: 
//				if (tsp[row][column] < min)//if the graph's weight is less than min cost (first it is max)
//				{ //if it is do this code:
//					min = tsp[row][column]; //min is now the weight of the edge.
//					route[counter] = column + 1; //the node is then place into the array
//				}
//			}
//			column++; //go to the next column
//
//			if (column == tsp[row].length) //if the column is == to graphs rows length. meaning at the end of the rows length.
//			{
//				result += min; //add up all the min length and put it into the sum int.
//				min = Integer.MAX_VALUE; //then change the min back to max to redo all over again
//				visitedRouteList.add(route[counter] - 1); //add the route at the 
//				column = 0;
//				row = route[counter] - 1;
//				counter++;
//			}
//		}
//
//		row = route[counter - 1] - 1;
//
//		for (column = 0; column < tsp.length; column++)
//		{
//
//			if ((row != column) && tsp[row][column] < min)
//			{
//				min = tsp[row][column];
//				route[counter] = column + 1;
//			}
//		}
//		result += min;
//
//		System.out.print("Min Cost is : ");
//		System.out.println(result);
//	}

}
