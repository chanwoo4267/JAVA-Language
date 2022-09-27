import java.util.Scanner;

public class Problem3 {
	public static void main(String[] args) {
		System.out.print("Enter a text: ");
		char[] input;
		String temp;
		char let;
		int count=0;
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine().toCharArray();
		while(true) {
			System.out.print("Enter a letter: ");
			temp = sc.next();
			if (temp.length() == 1)
			{
				let = temp.charAt(0);
				break;
			}
			System.out.println("You must enter a single letter.");
			continue;
		}
		
		for(int i=0; i<input.length; i++)
		{
			if (input[i] == let) count++;
		}
		System.out.println("There are " + count + " " + let + "'s in the text.");
	}
}
