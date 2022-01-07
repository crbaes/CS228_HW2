 package edu.iastate.cs228.hw2;

/**
 *  
 * @author Charlene Baes
 *
 */

public class Point implements Comparable<Point>
{
	private int x; 
	private int y;
	
	/**
	 * xORy == true, compare x coors
	 * xORy == false, c
	 */
	public static boolean xORy;
	
	/**
	 * Default Point Constructor
	 * assigns value of 0 to x and y
	 */
	public Point()
	{
		x = 0;
		y = 0;
	}
	
	/**
	 * Point Constructor with Given x and y coordinates
	 * 
	 * @param x coor
	 * @param y coor
	 */
	public Point(int x, int y)
	{
		this.x = x;  
		this.y = y;   
	}
	
	/**
	 * Copy Constructor
	 * Given Point p, assigns x & y to the given coordinates of p
	 * @param p
	 */
	public Point(Point p) 
	{
		x = p.getX();
		y = p.getY();
	}

	/**
	 * Get X Coor
	 * @return x coor
	 */
	public int getX()   
	{
		return x;
	}
	
	/**
	 * Get Y Coor
	 * @return y coor
	 */
	public int getY()
	{
		return y;
	}
	
	/** 
	 * Set the value of the static instance variable xORy. 
	 * Must use Point.xORy to set value of instance variable
	 * if xORy == true, compare x values
	 * if xORy == false, compare y values
	 * 
	 * @param xORy
	 */
	public static void setXorY(boolean xORy)
	{
		Point.xORy = xORy;
	}

	/**
	 * equals() determines if two objects are equal given one object
	 * Evaluates the x and y coordinates of both points
	 * 
	 * @param Object obj
	 * @return true if x == x and y == y for both points
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || obj.getClass() != this.getClass())
		{
			return false;
		}
    
		Point other = (Point) obj;
		
		return x == other.x && y == other.y;   
	}

	/**
	 * Compare this point with a second point q depending on the value of the static variable xORy 
	 * Uses xORy to compare either the x or y values of both points (this & q)
	 * 
	 * if this has an x/y value less than q, return -1
	 * if both x points and y points of this and q are equal, return 0
	 * if q has an x/y value less than this, return 1
	 * 
	 * @param 	q 
	 * @return  -1  if (xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y))) 
	 *                || (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x)))
	 * 		    0   if this.x == q.x && this.y == q.y)  
	 * 			1	otherwise 
	 */
	public int compareTo(Point q)
	{
		if(xORy == true && (this.x < q.x || (this.x == q.x && this.y < q.y)) || (xORy == false && (this.y < q.y || (this.y == q.y && this.x < q.x))))
			return -1;
		else if(this.x == q.x && this.y == q.y) //Point q and Point this are equal 
			return 0;
		else 
			return 1;
	}
	
	
	/**
	 * Returns String with x and y values in standard form
	 * 
	 * @return a string in the given form (x, y)
	 */
	@Override
    public String toString() 
	{
		return "(" + x + ", " + y + ")";
	}
}
