import java.util.Scanner;

interface IntSequenceStr
{
	public boolean hasNext();
	public String next();
}

class BinarySequenceStr implements IntSequenceStr
{
	public String n;
	public int idx;
	public BinarySequenceStr(int num)
	{
		idx=0;
		n = "";
		while (num > 0)
		{
			n = ((num % 2) == 0 ? "0" : "1") + n;
	        num = num / 2;
		}
	}
	
	public boolean hasNext()
	{
		if (idx < n.length())
			return true;
		else
			return false;
	}
	
	public String next()
	{
		idx++;
		return n.substring(idx-1,idx);
	}
}

public class Problem07 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a positive integer: ");
		String str = in.nextLine();
		int num = Integer.parseInt(str);
		in.close();
		System.out.println("Integer: " + num);
		IntSequenceStr seq = new BinarySequenceStr(num);
		System.out.print("Binary number: ");
		while (seq.hasNext())
			System.out.print(seq.next());
		System.out.println(" ");
	}
}
