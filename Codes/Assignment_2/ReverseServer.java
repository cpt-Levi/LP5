import ReverseModule.*;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.NameComponent;
import org.omg.PortableServer.*;

public class ReverseServer {

	public static void main(String[] args) {
		try
		{
			// initialize the ORB
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			
			// initialize the BOA/POA
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			
			// creating the calculator object
			ReverseImpl rvr = new ReverseImpl();
			
			// get the object reference from the servant class
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(rvr);
			
			System.out.println("Step 1");
			Reverse h_ref = ReverseModule.ReverseHelper.narrow(ref);
			
			System.out.println("Step 2");
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			System.out.println("Step 3");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			System.out.println("Step 4");
			String name = "Reverse";
			
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, h_ref);
			
			System.out.println("Reverse Server reading and waiting ..");
			orb.run();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
