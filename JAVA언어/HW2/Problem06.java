interface IntSequence
{
	public boolean hasNext();
	public int next();
}

class FibonacciSequence implements IntSequence
{
	public int a,b;
	
	public FibonacciSequence()
	{
		a=0;
		b=1;
	}
	
	public boolean hasNext()
	{
		return true;
	}
	
	public int next()
	{
		if (a==0) {
			a=1;
			return 0;
		}
		else if (a==1 && b==1)
		{
			b += a;
			return 1;
		}
		else
		{
			int t=a;
			a = b;
			b += t;
			return t;
		}
	}
}
public class Problem06{
	 public static void main(String[] args) {
		 
		 IntSequence seq = new FibonacciSequence();
	 
		 for(int i=0; i<20; i++) {
			 if(seq.hasNext() == false) break;
			 System.out.print(seq.next() + " ");
		 }
		 System.out.println(" ");
	 	}
	}
