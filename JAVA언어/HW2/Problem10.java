class Points
{
	private double[] ary;
	Points (double[] ary)
	{
		this.ary = ary;
	}
	
	@Override
    public String toString() {
		double t=0;
		for(int i=0; i<this.ary.length; i++)
			t += ary[i];
        return "[sum of points: " + t + " ]";
    }
	
	public double ret()
	{
		double t=0;
		for(int i=0; i<this.ary.length; i++)
			t += ary[i];
		return t;
	}
	
	public boolean equals(Points p) {
		double t=0;
		for(int i=0; i<this.ary.length; i++)
			t += ary[i];
		if (p != null && t == p.ret())
			return true;
		else
			return false;
	}
}

public class Problem10 {
	public static void main(String[] args) {
		Points p1 = new Points(new double[] { 1.0, 2.0, 3.0 });
		Points p2 = new Points(new double[] { 4.0, 5.0, 6.0 });
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p1.equals(p2));
		Points p3 = new Points(new double[] { 1.0, 4.0, 7.0 });
		Points p4 = new Points(new double[] { 3.0, 9.0 });
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p3.equals(p4));
		Points p5 = new Points(new double[] { 1.0, 2.0 });
		Points p6 = null;
		System.out.println(p5);
		System.out.println(p6);
		System.out.println(p5.equals(p6));
	}
}