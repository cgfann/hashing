/*
	Charlotte Fanning
	CSC 201 Fall 2020
	Programming Assignment 3 - Quadratic Probing
	28 October 2020
 */

public class RGB
{
	private int r;
	private int g;
	private int b;
	
	public void setR(int red)
	{
		r = red;
	}
	
	public void setG(int green)
	{
		g = green;
	}
	
	public void setB(int blue)
	{
		b = blue;
	}
	
	public boolean equals(RGB color)
	{
		if ((r + g + b) != (color.r + color.g + color.b))
		{
			return false;       // preliminary check to avoid unnecessary and time-consuming further checking for unequal colors
		}
		else
		{
			return (r == color.r) && (g == color.g) && (b == color.b);
		}
	}
	
	public int getR()
	{
		return r;
	}
	
	public int getG()
	{
		return g;
	}
	
	public int getB()
	{
		return b;
	}
	
	
}