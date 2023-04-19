import java.rmi.*;
import java.net.*;

public class Server {

	public static void main(String[] args) {
		try {
			ServerImpl obj = new ServerImpl();
			Naming.rebind("Server", obj);
		}
		catch(Exception e)
		{
			System.out.println("Exception: " + e);
		}
	}
}
