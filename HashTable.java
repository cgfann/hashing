/*
	Charlotte Fanning
	CSC 201 Fall 2020
	Programming Assignment 3 - Quadratic Probing
	28 October 2020
 */

public class HashTable
{
	private Freq[] ht;
	private int size;
	private int collisions = 0;
	private int rehashes = 0;
	public int capacity;
	private Freq[] sorted;
	
	public HashTable(int M)
	{
		capacity = getPrime(M);
		size = 0;
		if (capacity == -1)
		{
			System.out.println("invalid array size");
			System.exit(1);
		}
		else
		{
			ht = new Freq[capacity];
		}
	}
	
	public void insert(RGB pixel)
	{
		int slot = search(pixel);
		
		if (ht[slot] == null)
		{
			ht[slot] = new Freq(pixel);     // add new color to table
			size++;
		}
		else
		{
			ht[slot].raiseFreq();       // increase frequency of already-existing color in table
		}
		
		if (size > (capacity / 2))       // double table size and rehash
		{
			rehash();
			rehashes++;
		}
	}
	
	private void rehash()
	{
		Freq[] temp = ht;
		capacity = getPrime(temp.length * 2);
		ht = new Freq[capacity];
		size = 0;
		
		for (Freq object : temp)
		{
			if (object != null)
			{
				int slot = search(object.COLOR);
				ht[slot] = object;
				size++;
			}
		}
	}
	
	private int getPrime(int floor)
	{
		boolean prime = false;
		
		for (int i = floor; i < i * 2; i++)
		{
			for (int j = 2; j < i / 2; j++)
			{
				if ((i % j) != 0)
				{
					prime = true;       // remains true if j doesn't divide i for all j from 2 to i / 2
				}
				else
				{
					prime = false;
					break;      // exit inner loop
				}
			}
			
			if (prime)
			{
				return i;
			}
		}
		return -1;
	}
	
	private int h(RGB pixel)      // hash function
	{
		int key = (pixel.getR() + 1) * (pixel.getG() + 2) + pixel.getB();
		
		return key % ht.length;
	}
	
	private int probe(int i)        // quadratic probe sequence
	{
		collisions++;
		return i * i;
	}
	
	private int search(RGB pixel)     // check for existence of color in hash table
	{
		int home = h(pixel);
		int slot = home;
		int i = 1;
		
		while (ht[slot] != null)
		{
			if ((ht[slot].COLOR).equals(pixel))
			{
				break;        // color found in table
			}
			else
			{
				slot = (home + probe(i)) % (ht.length);       // resolve collision
				i++;
			}
			
		}
		return slot;
	}
	
	public void sort()
	{
		int highFreq;
		int index = 0;
		sorted = new Freq[256];
		
		for (int i = 0; i < 256 && i < size; i++)
		{
			highFreq = 0;
			for (int j = 0; j < ht.length; j++)     // visit each element in the table 256 times
			{
				Freq object = ht[j];
				
				if (object != null)
				{
					if (!(object.getInSorted()))        // don't add the same element to the array more than once
					{
						if (object.getF() >= highFreq)
						{
							index = j;
							highFreq = object.getF();
							
							if (i != 0)
							{
								if (((object.getF()) == (sorted[i - 1].getF())))      // stop looking if the frequency is the same as previously sorted element
								{
									break;
								}
							}
						}
					}
				}
			}
			sorted[i] = ht[index];
			sorted[i].setInSorted();
		}
	}
	
	public void printSorted()
	{
		System.out.printf("%s %8s %10s %10s %10s %n", "    ", "frequency", "r", "g", "b ");
		
		for (int i = 0; i < 256 && i < size; i++)
		{
			System.out.printf("%-3s %7s %14s %10s %9s %n", i + 1, sorted[i].getF(), (sorted[i].COLOR).getR(),
					(sorted[i].COLOR).getG(), (sorted[i].COLOR).getB());
		}
	}
	
	public void printStats()
	{
		System.out.println();
		System.out.println("Final table size: " + capacity);
		System.out.println("Number of collisions: " + collisions);
		System.out.println("Number of rehashes: " + rehashes);
	}
}