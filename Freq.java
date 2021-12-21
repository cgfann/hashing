/*
	Charlotte Fanning
	CSC 201 Fall 2020
	Programming Assignment 3 - Quadratic Probing
	28 October 2020
 */

public class Freq
{
	private int f;      // frequency
	private boolean inSorted;
	public final RGB COLOR;
	
	public Freq(RGB color)
	{
		f = 1;
		this.COLOR = color;
		inSorted = false;
	}
	
	public void raiseFreq()
	{
		f++;
	}
	
	public int getF()
	{
		return f;
	}
	
	public RGB getCOLOR()
	{
		return COLOR;
	}
	
	public void setInSorted()      // used in HashTable sorting method
	{
		inSorted = true;
	}
	
	public boolean getInSorted()
	{
		return inSorted;
	}
}
