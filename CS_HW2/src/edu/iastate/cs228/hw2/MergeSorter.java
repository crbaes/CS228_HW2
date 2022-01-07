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
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
		super.algorithm = "merge sort";
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		startTime = System.nanoTime();
		
		mergeSortRec(this.points);
		
		endTime = System.nanoTime();
		time = endTime - startTime;
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		int n = pts.length;
		int m = n / 2;
		
		if(pts.length <= 1)
			return;
		
		Point[] leftSide = new Point[m];
		for(int i = 0; i < m; ++i)
			leftSide[i] = pts[i];
		
		
		int index = 0;
		Point[] rightSide = new Point[n - m];
		for(int j = m; j < n; ++j)
		{
			rightSide[index] = pts[j];
			index++;
		}
		
		mergeSortRec(leftSide);
		mergeSortRec(rightSide);
		
		Point[] temp = merge(leftSide, rightSide);
		
		for(int i = 0; i < temp.length; ++i)
		{
			pts[i] = temp[i];
		}
	}

	private Point[] merge(Point[] first, Point[] second)
	{
		int p = first.length;
		int q = second.length;
		
		Point[] newPoint = new Point[p + q];
		
		int i = 0;
		int j = 0;
		int index = 0;
		
		while((i < p) && (j < q))
		{
			if(pointComparator.compare(first[i], second[j]) < 0)
			{
				newPoint[index++] = first[i++];
			}
			else
			{
				newPoint[index++] = second[j++];
			}
		}
		
		while(i < p)
		{
			newPoint[index++] = first[i++];
		}
		
		while(j < q)
		{
			newPoint[index++] = second[j++];
		}
		
		return newPoint;
	}
	// Other private methods if needed ...

}
