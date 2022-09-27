import java.io.FileInputStream;
import java.io.IOException;
class Text
{
	private static byte[] b = new byte[102400];
	
	public static boolean readTextFromFile(String input)
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
			System.out.println("Input file not found.");
			System.exit(0);
		}
		return true;
	}
	
	public static int countChar(char c)
	{
		int count=0;
		for(int i=0; i<b.length; i++)
		{
			if (b[i] == c || b[i] == c-32)
				count++;
		}
		return count;
	}
}


public class Problem13 {
	public static void main(String[] args) {
		Text t = new Text();
		if (t.readTextFromFile("input_prob13.txt")) {
			for(char c = 'a'; c <= 'z'; c++) {
				 System.out.println(c + ": " + t.countChar(c));
				 }
		}
	}
}