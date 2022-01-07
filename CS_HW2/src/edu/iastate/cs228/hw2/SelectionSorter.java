package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;


/**
 *  
 * @author Charlene Baes
 *
 */

/**
 * This class implements selection sort and extends AbstractSorter  
 * 
 * Selection Sort starts at index i and iterates through until
 * an element with a value < array[i] is found
 * The two elements are then swapped, and the array iteration continues
 */

public class SelectionSorter extends AbstractSorter
{
	// Other private instance variables if you need ... 
	
	/**
	 * Selection Sorter Constructor takes an array of points
	 * Calls to AbstractSorter constructor by using super 
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
		super.algorithm = "selection sort";
	}	

	
	/** 
	 * Iterate through points[], starting at minimum index i and move through array
	 * Minimum index iterates when the elements at points[0] - points[j] are sorted
	 * 
	 * Use pointComparator to see if points[j] is smaller than points[min], then swap
	 */
	@Override 
	public void sort()
	{
		startTime = System.nanoTime();
		
		for (int i = 0; i < this.points.length - 1; i++) 
		{
			int min = i;

			for (int j = i + 1; j < this.points.length; j++) 
			{
				if(pointComparator.compare(this.points[j], this.points[min]) < 0)
				{
					min = j;
				}
			}
	
			super.swap(i, min);
		}	
		
		endTime = System.nanoTime();
		
		time = endTime - startTime;
	}	
}
