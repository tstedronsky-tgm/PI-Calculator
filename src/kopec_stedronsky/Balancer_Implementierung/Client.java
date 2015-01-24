package kopec_stedronsky.Balancer_Implementierung;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Client
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class Client{
	
	/**
	 * Client 
	 * @param ip - ip der Registry
	 * @param name - Name des Balancers in der Registry
	 * @param stellen - Anzahl der gewuenschten Nachkommastellen von Pi
	 */
	public Client(String ip, String name, int stellen) {
		try {
			System.out.println("starting client...");
			Registry registry = LocateRegistry.getRegistry(ip);
			BalancerInterface b = (BalancerInterface) registry.lookup(name);
			System.out.println(b.getResult(stellen));
		} catch (RemoteException | NotBoundException e) { 
			System.err.println(e.getMessage());
		}
	}
}