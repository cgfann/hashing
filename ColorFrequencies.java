/*
	Charlotte Fanning
	CSC 201 Fall 2020
	Programming Assignment 3 - Quadratic Probing
	28 October 2020
 */

/*
	To read a .raw file with dimensions nxm pixels this program takes 4 command line arguments: String fileName.raw, int n, int m, and int arrayLength. The
	arrayLength variable is used to determine the initial size of a hash table that stores Freq objects. Freq objects contain information about unique RGB
	values and the frequency at which they appear in the pixels of the file. These objects are sorted by frequency which can be utilized in a popularity
	algorithm to transform an RGB color into an indexed color.
 */

import java.io.*;
import java.util.concurrent.TimeUnit;

public class ColorFrequencies
{
	public static void main(String[] args) throws InterruptedException
	{
		int n = 0;
		int m = 0;
		int arrayLength = 0;
		String fileName = "";
		
		if (args.length == 4)
		{
			try
			{
				n = Integer.parseInt(args[1]);
			}
			catch (NumberFormatException e)
			{
				System.err.println("Second argument must be an integer.");
				System.exit(1);
			}
			
			try
			{
				m = Integer.parseInt(args[2]);
			}
			catch (NumberFormatException e)
			{
				System.err.println("Third argument must be an integer.");
				System.exit(1);
			}
			
			try
			{
				arrayLength = Integer.parseInt(args[3]);
			}
			catch (NumberFormatException e)
			{
				System.err.println("Fourth argument must be an integer.");
				System.exit(1);
			}
			
			HashTable ht = new HashTable(arrayLength);      // construct hash table with accepted arguments
			
			DataInputStream input = null;
			
			try
			{
				fileName = args[0];
				InputStream is = new FileInputStream(fileName);
				
				input = new DataInputStream(is);
			}
			catch (FileNotFoundException e)
			{
				System.err.println("Could not open file " + fileName);
				System.exit(1);
			}
			
			try
			{
				System.out.println("Method implemented: collision resolution by quadratic probing");
				System.out.println("File name " + fileName);
				System.out.println("Dimensions " + n + "x" + m);
				System.out.println("Initial table size: " + ht.capacity);
				
				long startTime = System.nanoTime();     // starting timing before insertion and sort
				
				for (int i = 0; i < n; i++)
					for (int j = 0; j < m; j++)
					{
						RGB pixel = new RGB();
						pixel.setR(input.readUnsignedByte());
						pixel.setG(input.readUnsignedByte());
						pixel.setB(input.readUnsignedByte());
						ht.insert(pixel);
					}
				
				ht.sort();
				
				TimeUnit.SECONDS.sleep(2);      // changed to 2 second pause
				
				long endTime = System.nanoTime();       // stop timing after insertion and sort
				
				long timeElapsed = endTime - startTime;
				
				ht.printStats();
				
				System.out.println("Execution time in nanoseconds: " + timeElapsed);
				System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
				
				ht.printSorted();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Input 4 arguments: String fileName.raw, int n, int m, int arraySize");
			System.exit(1);
		}
	}
}
