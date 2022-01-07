package edu.iastate.cs228.hw2;

/**
 *  
 * @author Charlene Baes
 *
 */

import java.util.Comparator;

import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter
{
	
	protected Point[] points;    // array of points operated on by a sorting algorithm. 
	                             // stores ordered points after a call to sort(). 
	
	protected String algorithm = null; // "selection sort", "insertion sort", "mergesort", or"quicksort".
		 
	protected Comparator<Point> pointComparator = null;  //pointComparator compares points to see if they are equal
	
	/**
	 * Track time in nanoseconds that it takes to perform a sort
	 * Displayed to the console 
	 * time = endTime - startTime
	 */
	protected long startTime;
	protected long endTime;
	protected long time; 

	/**
	 * No implementation needed. Provides a default super constructor to subclasses. 
	 * Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	 */
	protected AbstractSorter()
	{
	
	}
	
	/**
	 * Constructor of Abstract Sorter
	 * Iterates through each element of pts[] parameter input and copy each element into points[]
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		if(pts == null || pts.length == 0)
			throw new IllegalArgumentException();
		
		points = new Point[pts.length];
		
		for(int i = 0; i < pts.length; i++)
			points[i] = pts[i];
	}

	/**
	 * Sets the variable setXorY, true if order == 0, and false if order == 1
	 * Generates a new Comparator and assigns it to pointComparator
     * Wrapper method: uses the compareTo method in the Point class
	 * 
	 * @param order  0   by x-coordinate 
	 * 				 1   by y-coordinate	    
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 *        
	 */
	public void setComparator(int order) throws IllegalArgumentException
	{
			if(order == 0)
				Point.setXorY(true);  // x coor, true
			else
				Point.setXorY(false); // y coor, false

			
			pointComparator = new Comparator<Point>() //generate new comparator
			{
				@Override 
				public int compare(Point p1, Point p2)
				{
					return p1.compareTo(p2);	
				}
			};
	}

	/** FIX ME: SET AS PROTECTED AT END
	 * Use the created pointComparator to conduct sorting.  
	 * 
	 * Should be protected. Made public for testing. 
	 */
	protected abstract void sort(); 
	
	
	/**
	 * Obtain the point in the array points[] that has median index 
	 * Find the length of the points[] array and divide by 2 to find the median
	 * 
	 * @return	median point 
	 */
	public Point getMedian()
	{
		return points[points.length/2]; 
	}
	
	
	/**
	 * Iterate through all elements of points[] and copy given elements into pts[]
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		for(int i = 0; i < pts.length; i++)
			pts[i] = points[i]; 
	}
	

	/**
	 * Given the index of two elements in points[], swap their position
	 * by using a temp Point variable to holds its values
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		Point temp = points[i];
		points[i] = points[j];
		points[j] = temp;
	}	
}
