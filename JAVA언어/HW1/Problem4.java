import java.util.Scanner;

public class Problem4 {
	public static void main(String[] args) {
		System.out.print("Enter a text: ");
		char[] input;
		String temp;
		char[] cmp;
		int count=0;
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine().toCharArray();
		while(true) {
			System.out.print("Enter a letter: ");
			temp = sc.nextLine();
			if (temp.length() == 0)
			{
				System.out.println("You must enter a string.");
				continue;
			}
			break;
		}
		
		cmp = temp.toCharArray();
		int i,j;
		for(i=0; i<input.length; i++)
		{
			if (input[i] == cmp[0] && i + cmp.length <= input.length)
			{
				for(j=0; j<cmp.length; j++)
				{
					if (input[i+j] != cmp[j]) break;
				}
				if (j >= cmp.length) count++;
			}
		}
		
		System.out.println("There are " + count + " instances of \"" + temp + "\".");
	}
}
