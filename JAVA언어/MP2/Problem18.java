import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

class ValueComparator implements Comparator<String>
{
	private Map<String, Double> base;
	
	public ValueComparator(Map<String, Double> base)
	{
		this.base = base;
	}
	
	public int compare(String a, String b) {
		if (this.base.get(a) < this.base.get(b))
			return -1;
		else if (this.base.get(a) > this.base.get(b))
			return 1;
		else {
			int i = 0;
			while (true) {
				if (a.charAt(i) < b.charAt(i))
					return -1;
				else if (a.charAt(i) > b.charAt(i))
					return 1;
				i++;
				if (i >= a.length() || i >= b.length()) {
					return 0;
				}
			}
		}
	}
}

class MapManager
{
	public static Map<String, Double> readData(String input)
	{
		TreeMap<String, Double> map = new TreeMap<String, Double>();
		ValueComparator bvc = new ValueComparator(map);
		TreeMap<String, Double> sorted_map = new TreeMap<String, Double>(bvc){
			@Override
			public String toString()
			{
				StringBuilder sb = new StringBuilder();
		        for (java.util.Map.Entry<?, ?> e : this.entrySet()) {
		            sb.append(String.format("%s %s%n", e.getKey(), e.getValue()));
		        }
		        return sb.toString();
			}
		};
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(input));
			while(true)
			{
				String line = br.readLine();
				if (line == null) break;
				String[] arr = line.split(" ", 2);
				double tmp = Double.parseDouble(arr[1]);
				map.put(arr[0], tmp);
			}
			br.close();
		} catch (IOException e)
		{
			System.out.println("Input file not found.");
			System.exit(0);
		}
		
		sorted_map.putAll(map);
		
		return sorted_map;
	}
}

public class Problem18 {
	public static void main(String args[]) {
		Map<String, Double> map = MapManager.readData("input.txt");
		if (map == null) {
			System.out.println("Input file not found.");
			return;
		}
		System.out.println(map);
	}
}