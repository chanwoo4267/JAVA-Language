import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

class Fruit {
	private String name;
	private double cost;
	Fruit(String name, double cost)
	{
		this.name = name;
		this.cost = cost;
	}
	public String getName()
	{
		return this.name;
	}
	public double getCost()
	{
		return this.cost;
	}
}

class FruitBox<T extends Fruit> {
	private ArrayList<T> list = new ArrayList<T>();
	void add(T item)
	{
		list.add(item);
		System.out.println(item.getName() + " " + item.getCost());
	}
	int getNumItems()
	{
		return list.size();
	}
	double getMaxPrice()
	{
		double max = 0;
		for(int i=0; i<list.size(); i++)
			if (list.get(i).getCost() >= max)
				max = list.get(i).getCost();
		return max;	
	}
	double getMinPrice()
	{
		double min = list.get(0).getCost();
		for(int i=1; i<list.size(); i++)
			if (list.get(i).getCost() < min)
				min = list.get(i).getCost();
		return min;	
	}
	String getMaxItem()
	{
		String mitem = list.get(0).getName();
		double max = 0;
		for(int i=0; i<list.size(); i++)
			if (list.get(i).getCost() >= max) {
				max = list.get(i).getCost();
				mitem = list.get(i).getName();
			}
		return mitem;
	}
	String getMinItem()
	{
		double min = list.get(0).getCost();
		String mitem = list.get(0).getName();
		for(int i=1; i<list.size(); i++)
			if (list.get(i).getCost() < min) {
				min = list.get(i).getCost();
				mitem = list.get(i).getName();
			}
		return mitem;	
	}
	double getAvgPrice()
	{
		double avg = 0;
		for(int i=0; i<list.size(); i++)
			avg += list.get(i).getCost();
		avg /= list.size();
		return avg;
	}
}

class ItemReader {
	
	public static boolean fileToBox(String input, FruitBox box)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(input));
			while(true)
			{
				String line = br.readLine();
				if (line == null) break;
				String[] arr = line.split(" ", 2);
				double tmp = Double.parseDouble(arr[1]);
				box.add(new Fruit(arr[0], tmp));
			}
			br.close();
		} catch (IOException e)
		{
			System.out.println("Input file not found.");
			System.exit(0);
		}
		return true;
	}
}

public class Problem14 {
	public static void main(String[] args) {
		FruitBox<Fruit> box = new FruitBox<>();
		boolean rv = ItemReader.fileToBox("input_prob14.txt", box);
		if(rv == false) return;
		box.add(new Fruit("orange", 9.99));
		System.out.println("----------------");
		System.out.println(" Summary");
		System.out.println("----------------");
		System.out.println("number of items: " + box.getNumItems());
		System.out.println("most expensive item: " + box.getMaxItem() + " (" +
		box.getMaxPrice() + ")");
		System.out.println("cheapest item: " + box.getMinItem() + " (" +
		box.getMinPrice() + ")");
		System.out.printf("average price of items: %.2f", box.getAvgPrice());}
}