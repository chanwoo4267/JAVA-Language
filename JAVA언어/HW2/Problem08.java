
abstract class Shape
{
	private double n;
	protected Shape(double d)
	{
		this.n = d;
	}
	
	double ret()
	{
		return this.n;
	}
}

class Circle extends Shape
{
	Circle(double n)
	{
		super(n*n*Math.PI);
	}
}

class Square extends Shape
{
	Square(double n)
	{
		super(n*n);
	}
}

class Rectangle extends Shape
{
	Rectangle(double a,double b)
	{
		super(a*b);
	}
}

public class Problem08 {
	
	public static void main(String[] args) {
		Shape[] arr = { new Circle(5.0), new Square(4.0), new Rectangle(3.0, 4.0), new Square(5.0) };
		System.out.println("Total area of the shapes is: " + sumArea(arr));
	}

	private static double sumArea(Shape[] arr) {
		double sum=0;
		sum += arr[0].ret();
		sum += arr[1].ret();
		sum += arr[2].ret();
		sum += arr[3].ret();
		return sum;
	}
}


