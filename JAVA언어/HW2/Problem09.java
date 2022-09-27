class Point
{
	private int div;
	private double[] ary;
	Point (double[] ary)
	{
		div = ary.length;
		this.ary = ary;
	}
	
	int isDiv()
	{
		return this.div;
	}
	
	double inArr(int i)
	{
		return ary[i];
	}
}

class EuclideanDistance extends Point
{
	
	EuclideanDistance(double[] ary) {
		super(ary);
	}
	
	static double getDist(Point p1, Point p2)
	{
		if (p1.isDiv() != p2.isDiv())
			return -1;
		else
		{
			double d=0;
			for(int i=0; i<p1.isDiv(); i++)
				d += Math.pow(p1.inArr(i) - p2.inArr(i),2);
			d = Math.sqrt(d);
			return d;
		}
	}
}

class ManhattanDistance extends Point
{
	ManhattanDistance(double[] ary)	{
		super(ary);
	}
	
	static double getDist(Point p1, Point p2)
	{
		if (p1.isDiv() != p2.isDiv())
			return -1;
		else
		{
			double d=0;
			double t;
			for(int i=0; i<p1.isDiv(); i++) {
				t = p1.inArr(i) - p2.inArr(i);
				if (t < 0)
					d -= t;
				else
					d += t;
			}
			return d;
		}
	}
}
public class Problem09 {
	public static void main(String[] args) {
		Point p1 = new Point(new double[] { 1.0, 2.0, 3.0 });
		Point p2 = new Point(new double[] { 4.0, 5.0, 6.0 });
		System.out.println("Euclidean Distance: " + EuclideanDistance.getDist(p1, p2));
		System.out.println("Manhattan Distance: " + ManhattanDistance.getDist(p1, p2));
		Point p3 = new Point(new double[] { 1.0, 2.0, 3.0 });
		Point p4 = new Point(new double[] { 4.0, 5.0 });
		System.out.println("Euclidean Distance: " + EuclideanDistance.getDist(p3, p4));
		System.out.println("Manhattan Distance: " + ManhattanDistance.getDist(p3, p4));
	}
}
