class PalindromeChecker {
	private static char[] temp;
	PalindromeChecker()
	{
		this.temp = null;
	}
	
	public static void check(String input)
	{
		temp = input.toCharArray();
		for(int idx=0; idx < input.length() >> 1; idx++)
		{
			if (temp[idx] != temp[input.length() -1 -idx])
			{
				System.out.println(input + " is not a palindrome.");
				return;
			}
		}

		System.out.println(input + " is a palindrome.");
	}
	
	public static void check(int input)
	{
		String t;
		t = Integer.toString(input);
		temp = t.toCharArray();
		for(int idx=0; idx < t.length() >> 1; idx++)
		{
			if (temp[idx] != temp[t.length() -1 -idx])
			{
				System.out.println(input + " is not a palindrome.");
				return;
			}
		}

		System.out.println(input + " is a palindrome.");
	}
}


public class Problem11 {
	public static void main(String[] args) {
		PalindromeChecker.check("abcde");
		PalindromeChecker.check("abcba");
		PalindromeChecker.check(1234);
		PalindromeChecker.check(12321);
	}
}
