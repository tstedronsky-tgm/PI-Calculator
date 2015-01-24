package kopec_stedronsky;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Balancer f�r den Calculator
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class Client{
	
	/**
	 * Client 
	 * @param ip
	 * @param name
	 * @param stellen
	 */
	public Client(String ip, String name, int stellen) {
		try {
			System.out.println("starting client...");
			Registry registry = LocateRegistry.getRegistry(ip);
			BalancerInterface b = (BalancerInterface) registry.lookup(name);
			System.out.println(b.getResult(stellen));
		} catch (RemoteException e) { 
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}