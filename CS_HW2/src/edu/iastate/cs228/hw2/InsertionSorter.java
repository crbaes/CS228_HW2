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
 * 
 * This class implements insertion sort and extends AbstractSorter  
 *
 * Variables in AbstractSorter: Point[] points, Comparator<Point> pointComparator
 * Methods in AbstractSorter: Constructor (Point[] pts), setComparator, 
 * 		sort(), getMedian(), getPoints(Point[] pts), swap
 *
 */

public class InsertionSorter extends AbstractSorter 
{	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts); //call AbstractSorter constructor
		super.algorithm = "insertion sort";
	}	

	
	/** 
	 * Perform Insertion sort for given points[] array
	 * Split between a sorted and unsorted part of the array
	 * While iterating through points[], sorted must stay sorted by placing values in the correct position
	 */
	@Override 
	public void sort()
	{
		startTime = System.nanoTime();
		
		for (int i = 1; i < this.points.length; i++) 
		{
			Point key = this.points[i];
			int j = i - 1;
 
			while((j >= 0) && (pointComparator.compare(this.points[j], key) > 0)) 
			{
				super.swap(j, j + 1);
				--j;
			}
			
			points[j + 1] = key;
		}		
		
		endTime = System.nanoTime();
		time = endTime - startTime;
	}
}
