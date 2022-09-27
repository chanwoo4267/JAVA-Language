import java.util.Scanner;

public class Problem5 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] input = new int[5];
		int first=0, second=0;
		int firstp=0, secondp=0;
		System.out.println("Enter exam scores of each student.");
		for(int i=0; i<5;)
		{
			i++;
			System.out.print("Score of student " + i + ": ");
			input[i-1] = sc.nextInt();
			if (input[i-1] >= first)
			{
				second = first;
				first = input[i-1];
				secondp = firstp;
				firstp = i;
			}
			
			else if (input[i-1] >= second) {
				second = input[i-1];
				secondp = i;
			}
		}
			
		System.out.println("The 1st place is student " + firstp + " with " + first + " points.");
		System.out.println("The 2nd place is student " + secondp + " with " + second + " 10points.");
	}
}
