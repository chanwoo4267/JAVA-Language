import java.util.Scanner;

public class Problem1 {
	public static void main(String[] args) {
		System.out.print("ASCII code teller. Enter a letter: ");
		String input;
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine();
		int s_len = input.length();
		if (s_len == 1) {
			char ch = input.charAt(0);
			int n = (int)ch;
			if ( (65 <= n && n <= 90) || (97 <= n && n <= 122)) 
				System.out.println("The ASCII code of " + input + " is " + n + ".");
			else {
				System.out.println("You must input a single uppercase or lowercase alphabet.");
				System.exit(0);
				//System.out.println("terminate error");
			}
		}
		else {
			System.out.println("You must input a single uppercase or lowercase alphabet.");
			System.exit(0);
			//System.out.println("terminate error");
		}
	}
}
