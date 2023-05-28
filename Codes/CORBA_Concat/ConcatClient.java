import ConcatModule.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.io.*;

public class ConcatClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Concat obj = null;
		
		try 
		{
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			String name = "Concat";
			obj = ConcatHelper.narrow(ncRef.resolve_str(name));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter string 1 = ");
			String str1 = br.readLine();
			System.out.println("Enter string 2 = ");
			String str2 = br.readLine();
			
			String resStr = obj.concat_strings(str1, str2);
			System.out.println(resStr);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
