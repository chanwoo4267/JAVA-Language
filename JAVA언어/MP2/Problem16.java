import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

class Element implements Comparable<Element>
{
	private String name;
	private double price;
	
	Element(String name, double price)
	{
		this.name = name;
		this.price = price;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
	@Override
	public int compareTo(Element s)
	{
		if (this.price < s.getPrice())
			return -1;
		else if (this.price > s.getPrice())
			return 1;
		else
		{
			int i=0;
			while(true)
			{
				if (this.name.charAt(i) < s.getName().charAt(i))
					return -1;
				else if (this.name.charAt(i) > s.getName().charAt(i))
					return 1;
				i++;
				if (i > this.name.length() || i > s.getName().length())
					return 0;
			}
		}
	}
	
	public String toString()
	{
		return name + " " + price;
	}
}

class ElementReader
{
	public static ArrayList<Element> readElements(String input)
	{
		ArrayList<Element> list = new ArrayList<> ();
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(input));
			while(true)
			{
				String line = br.readLine();
				if (line == null) break;
				String[] arr = line.split(" ", 2);
				double tmp = Double.parseDouble(arr[1]);
				list.add(new Element(arr[0], tmp));
			}
			br.close();
		} catch (IOException e)
		{
			System.out.println("Input file not found.");
			System.exit(0);
		}
		return list;
	}
}

public class Problem16 {
	public static void main(String args[]) {
		ArrayList<Element> list = ElementReader.readElements("input.txt");
		if (list == null) {
			System.out.println("Input file not found.");
			return;
		}
		Collections.sort(list);
		Iterator<Element> it = list.iterator();
		while (it.hasNext())
			System.out.println(it.next());
	}
}
