package edu.iastate.cs228.hw2;

import java.io.File;

/**
 * 
 * @author Charlene Baes
 *
 */

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
	private String toFile;
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		points = new Point[pts.length];
		
		for(int i = 0; i < pts.length; i++)
		{
			points[i] = pts[i];
		}	
		
		sortingAlgorithm = algo;
		
		if(algo == Algorithm.SelectionSort)
			toFile = "selectionSort.txt";
		else if(algo == Algorithm.InsertionSort)
			toFile = "insertionSort.txt";
		else if(algo == Algorithm.MergeSort)
			toFile = "mergeSort.txt";
		else
			toFile = "quickSort.txt";
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		File file = new File("C:\\Users\\charb\\OneDrive\\Desktop\\cs 228\\CS_HW2\\src\\edu\\iastate\\cs228\\hw2\\" + inputFileName);
		
		Scanner scnr = new Scanner(file);
		sortingAlgorithm = algo;
		
		if(algo == Algorithm.SelectionSort)
			toFile = "selectionSort.txt";
		else if(algo == Algorithm.InsertionSort)
			toFile = "insertionSort.txt";
		else if(algo == Algorithm.MergeSort)
			toFile = "mergeSort.txt";
		//else
			//toFile = "quickSort.txt";
		
		/**
		 * Create a new ArrayList<Integer> that keeps track of the next ints in the scanner
		 * If there are not an even number of points, throw InputMismatchException
		 * Iterate through created ArrayList and create new Points(x,y) with given values
		 */
		ArrayList<Integer> pointList = new ArrayList<Integer>();
		
		while(scnr.hasNextInt())
		{
			pointList.add(scnr.nextInt());
		}
		
		if(pointList.size() % 2 != 0)
			throw new InputMismatchException();
		
		this.points = new Point[pointList.size() / 2];
		
		int index = 0;
		
		for(int i = 0; i < pointList.size(); i+=2)
		{
			points[index] = new Point(pointList.get(i), pointList.get(i+1));
			index++;
		}
				
	}

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 * @throws FileNotFoundException 
	 */
	public void scan() throws FileNotFoundException
	{
		int setNum = 0;
		int medianX = 0;
		int medianY = 0;
		
		AbstractSorter aSorter;
		
		if(sortingAlgorithm == Algorithm.SelectionSort)
			aSorter = new SelectionSorter(points);
		else if(sortingAlgorithm == Algorithm.InsertionSort)
			aSorter = new InsertionSorter(points);
		else if(sortingAlgorithm == Algorithm.MergeSort)
			aSorter = new MergeSorter(points);
		else
			aSorter = new QuickSorter(points);
		
		/**
		 * Start by comparing the x coordinates
		 * Call to sort will sort the x coordinates using the given algorithm sort
		 * Adds the given sort time to scanTime to find the total time
		 * Finds the median X coordinate using getMedian()
		 */
		aSorter.setComparator(setNum);
		aSorter.sort();
		scanTime = aSorter.time;
		medianX = aSorter.getMedian().getX();
		
		/**
		 * Compare the y coordinates with the same algorithm sort
		 * Adds the given sort time to scanTime to find the total time used to sort both x and y
		 * Finds the median Y coordinate using getMedian()
		 */
		aSorter.setComparator(setNum + 1);
		aSorter.sort();
		scanTime += aSorter.time;
		medianY = aSorter.getMedian().getY();
		
		//create a new median point using the computed x and y points
		medianCoordinatePoint = new Point(medianX, medianY);

		writeMCPToFile();
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		return String.format("%-17s %-10d %-10d", sortingAlgorithm, points.length, scanTime);
	}
	
	
	/**
	 * Displays the median coordinate point (MCP) with the format "MCP: (x, y)"
	 * Write after a call to scan()
	 * 
	 * @return MCP in given format
	 */
	@Override
	public String toString()
	{
		return "MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";
	}

	
	/**
	 *  
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		try
		{
			File outFile = new File("C:\\Users\\charb\\OneDrive\\Desktop\\cs 228\\CS_HW2\\src\\edu\\iastate\\cs228\\hw2\\" + toFile);
			PrintWriter write = new PrintWriter(outFile);
			
			write.println(this.toString());
			write.close();	
		}
		
		catch (FileNotFoundException e)
		{
			throw new FileNotFoundException();
		}
	}	

	

		
}
