import CalculatorModule.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class CalculatorServer {

	public static void main(String[] args) {
		
		try {
			//Initialize ORB
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			
			//Initialize POA
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			
			//Create CalculatorImpl object
			CalculatorImpl cal = new CalculatorImpl();
			
			//Get reference from servant
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(cal);
			
			//-----------------------------------//
			
			// Create Calculator Interface object
			System.out.println("Step 1");
			Calculator h_ref = CalculatorModule.CalculatorHelper.narrow(ref);
			
			// Object -> resolve NameService
			System.out.println("Step 2");
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			// NCE NCEH
			System.out.println("Step 3");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			// Name of Interface
			System.out.println("Step 4");
			String name = "Calculator";
			
			//------------------------------------//
			
			NameComponent path[] = ncRef.to_name(name);
			
			ncRef.rebind(path, h_ref);
			
			System.out.println("Calculator Server reading and listening");
			
			orb.run();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
