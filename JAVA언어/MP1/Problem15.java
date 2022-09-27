import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

class Item
{
	private String str;
	private int num=0;
	Item(String str) {
		this.str = str;
		num++;
	}
	
	public String getstr()
	{
		return this.str;
	}
	
	public void addone()
	{
		this.num++;
	}
	
	@Override
	public String toString()
	{
		return str + " " + num;
	}
}

class MyFileReader
{
	private static byte[] b = new byte[102400];
	
	public static boolean readDataFromFile(String input, ArrayList<Item> list)
	{
		try {
			FileInputStream in = new FileInputStream(input);
			while(true) {
				int rv = in.read(b);
				if (rv == -1) break;
			}
			in.close();
		} catch (IOException e)
		{
			return false;
		}
		
		int idx=0;
		int len=0;
		int j;
		byte[] temp = new byte[1280];
	
		for(int i=0; i<b.length; i++)
		{
			if (65 <= b[i] && b[i] <= 90)
				b[i] += 32;
			
			if (b[i] != 32 && b[i] != 0) {
				temp[idx] = b[i];
				idx++;
				len++;
			}
			
			else if (b[i] == 32)
			{
				String str = new String(temp);
				String strt = str.substring(0,len);
				for(j=0; j<list.size(); j++)
				{
					if (list.get(j).getstr().equals(strt)) {
						list.get(j).addone();
						break;
						}
				}
				if (j != list.size())
				{
					idx=0;
					len=0;
					continue;
				}
				list.add(new Item(strt));
				idx=0;
				len=0;
			}
			else
			{
				String str = new String(temp);
				String strt = str.substring(0,len);
				for(j=0; j<list.size(); j++)
				{
					if (list.get(j).getstr().equals(strt)) {
						list.get(j).addone();
						break;
						}
				}
				if (j != list.size())
				{
					idx=0;
					len=0;
					continue;
				}
				list.add(new Item(strt));
				idx=0;
				len=0;
				break;
			}
		}
		
		return true;
	}
}

public class Problem15 {
	public static void main(String[] args) {
		ArrayList<Item> list = new ArrayList<>();
		boolean rv = MyFileReader.readDataFromFile("input_prob15.txt", list);
		if (rv == false) {
			System.out.println("Input file not found.");
			return;
		}
		for(Item it: list) System.out.println(it); 
	}
}