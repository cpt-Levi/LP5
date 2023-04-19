import java.rmi.*;

public class Client {

	public static void main(String[] args) {
		try {
			String ServerURL = "rmi://" + args[0] + "/Server";
			ServerInt serverObj = (ServerInt) Naming.lookup(ServerURL);
			
			System.out.println("The first number is: " + args[1]);
			double a = Double.valueOf(args[1]).doubleValue();
			System.out.println("The first number is: " + args[2]);
			double b = Double.valueOf(args[2]).doubleValue();
			System.out.println("The sum is: " + serverObj.add(a, b));
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e);
		}
	}
}
