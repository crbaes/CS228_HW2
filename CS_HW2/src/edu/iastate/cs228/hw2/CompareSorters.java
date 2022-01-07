package edu.iastate.cs228.hw2;

/**
 *  
 * @author Charlene Baes
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner; 
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{	
		int key = 0;
		int trial = 1;
		Point[] pts;
		Random rand = new Random(100);
		
		PointScanner[] scanners = new PointScanner[4]; 
		
		System.out.println("Performances of Four Sorting Algorithms in Point Scanning\n");
		
		System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)\n");
		System.out.print("Trial " + trial + ": ");
		Scanner userInput = new Scanner(System.in);
		key = userInput.nextInt();
		
		while(key != 3)
		{
			if(key == 1) //random amount of points given by user
			{
				int numPts = 0;
				System.out.print("Enter number of random points: ");
				numPts = userInput.nextInt();	
				pts = generateRandomPoints(numPts, rand);
				
				/**
				 * Initialize all elements in PointScanner scanners[] using pts
				 * and each of the 4 sorts
				 */
				scanners[0] = new PointScanner(pts, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(pts, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(pts, Algorithm.MergeSort);
				scanners[3] = new PointScanner(pts, Algorithm.QuickSort);	
			}
		
			else //(key == 2) file input
			{
				System.out.println("Points from a file\nFile name: ");
				String fileName = userInput.next();
				
				scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);		
			}
	
			for(int i = 0; i < scanners.length; i++)
			{
				scanners[i].scan();
				scanners[i].writeMCPToFile();
			}
			
			
			 // Print the statistics diagram as given in 2 on the HW doc
			System.out.println("\nalgorithm         size       time (ns)");
			System.out.println("----------------------------------"); //34 dashes
			
			//Iterate through array and make a call to stats() in order to print
			for(int i = 0; i < scanners.length; i++)
			{
				System.out.println(scanners[i].stats());
			}
			System.out.println("----------------------------------\n");
			
			//Prepare for next iteration by asking for the next key input
			trial++;
			System.out.print("Trial " + trial + ": ");
			key = userInput.nextInt();
		}
		
		System.out.println("key 3 selected to exit. goodbye.");
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] × [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		Point[] pointArray = new Point[numPts];
		
		//create a new array (length: numPts) randomly from -50 to 50
		for(int i = 0; i < numPts; i++)
			pointArray[i] = new Point(rand.nextInt(101)-50, rand.nextInt(101)-50);
		
		return pointArray;
	}
	
}
