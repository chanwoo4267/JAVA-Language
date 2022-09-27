import java.util.Arrays;

class SubsequenceChecker {
	private static char[] temp;
	private static char[] seqt;
	
	public static void check(String s, String seq)
	{
		int idx=0,i,count=0, j=0;
		temp = s.toCharArray();
		seqt = seq.toCharArray();
		int[] t = new int[seq.length()];
		
		for(i=0; i < s.length(); i++)
		{
			if (temp[i] == seqt[idx])
			{
				count++;
				t[j] = i;
				j++;
				idx++;
			}
		}
		
		if (idx >= seq.length()) {
			System.out.println(seq + " is a subsequence of " + s);
			for(i=0; i<seq.length(); i++)
				System.out.print(t[i] + " ");
			System.out.print("\r");
		}
		else
			System.out.println(seq + " is not a subsequence of " + s);
		
	}
}


public class Problem12 {
	public static void main(String[] args) {
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "pads");
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "padsx");
	}
}
