import java.util.Random;
import java.util.Scanner;

public class Problem2 {
	public static void main(String[] args) {
		//System.out.println("test");
		Random random = new Random();
		Scanner sc = new Scanner(System.in);
		int n = random.nextInt(101);
		int count = 1;
		int input=0;
		int ranges=1, rangel=100;
		
		
		while(true) {
			System.out.print("[" + count + "] Guess a number (" + ranges + '-' + rangel + "): ");
			input = sc.nextInt();
			if (input < ranges || input > rangel) {
				System.out.println("Not in range!");
				continue;
			}
			if (n == input) {
				System.out.println("Correct! Number of guesses: " + count);
				System.exit(0);
			}
			else if (n < input) {
				System.out.println("Too large!");
				rangel = input-1;
			}
			else {
				System.out.println("Too small!");
				ranges = input+1;
			}
			count++;
		}
	}
}
