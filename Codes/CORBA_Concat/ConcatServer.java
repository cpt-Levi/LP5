import ConcatModule.*;

import org.omg.CosNaming.*;

import org.omg.CORBA.*;

import org.omg.PortableServer.*;

public class ConcatServer {

	public static void main(String[] args) {
		try 
		{
			//Initialize ORB
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			
			//Initialize POA
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			
			//Concat Object
			ConcatImpl con = new ConcatImpl();
			
			//Get object reference from servant class
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(con);
			
			//----------------------------//
			
			System.out.println("Step 1");
			Concat h_ref = ConcatModule.ConcatHelper.narrow(ref);
			
			System.out.println("Step 2");
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			
			System.out.println("Step 3");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			System.out.println("Step 4");
			String name = "Concat";
			
			//----------------------------//
			
			NameComponent path[] = ncRef.to_name(name);
			
			ncRef.rebind(path, h_ref);

			System.out.println("Concat Server reading and waiting");
			
			orb.run();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
