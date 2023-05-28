import CalculatorModule.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.*;

public class CalculatorClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calculator obj = null;
		
		try
		{
			// Initialize ORB object
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			
			// Object resolve NameService
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			// NCE -> NCEH
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			String name = "Calculator";
			obj = CalculatorHelper.narrow(ncRef.resolve_str(name));
			
			// Input
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter num1 = ");
			double a = sc.nextDouble();
			System.out.println("Enter num2 = ");
			double b = sc.nextDouble();
			sc.close();
			
			//Results
			double ADD = obj.add(a, b);
			double SUB = obj.subtract(a, b);
			double MUL = obj.multiply(a, b);
			double DIV = obj.divide(a, b);
			
			System.out.println("Addition = " + ADD);
			System.out.println("Subtraction = " + SUB);
			System.out.println("Multiplication = " + MUL);
			System.out.println("Division = " + DIV);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
