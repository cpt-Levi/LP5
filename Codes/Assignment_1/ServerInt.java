import java.rmi.*;

public interface ServerInt extends Remote{
	double add(double a, double b) throws RemoteException;
}
