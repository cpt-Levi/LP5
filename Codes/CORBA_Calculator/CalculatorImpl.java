import CalculatorModule.CalculatorPOA;

public class CalculatorImpl extends CalculatorPOA
{
	CalculatorImpl()
	{
		super();
		System.out.println("Calculator Object Created");
	}
	
	public double add(double a, double b)
	{
		double res = a + b;
		return res;
	}
	
	public double subtract(double a, double b)
	{
		double res = a - b;
		return res;
	}
	
	public double multiply(double a, double b)
	{
		double res = a * b;
		return res;
	}
	
	public double divide(double a, double b)
	{
		double res = a / b;
		return res;
	}
}
