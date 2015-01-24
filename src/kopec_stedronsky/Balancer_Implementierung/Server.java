package kopec_stedronsky.Balancer_Implementierung;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import kopec_stedronsky.RMI_Implementierung.CalculatorInterface;

/**
 * Server
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
@SuppressWarnings("deprecation")
public class Server implements CalculatorInterface, Serializable{
	//Serial-ID
	private static final long serialVersionUID = 1L;

	/**
	 * Erstellt einen Server, meldet ihn beim LoadBalancer an
	 * und meldet ihn beim LoadBalancer wieder ab wenn der Server
	 * ausgeschaltet wird
	 * @param ip - ip der Registry
	 * @param name - name des LoadBalancers in der Registry
	 */
	public Server(String ip, String name){
		System.out.println("Starting server...");
		BalancerInterface c = null;
		try {
			if(System.getSecurityManager() == null);
			System.setSecurityManager(new RMISecurityManager());
			
			Registry registry = LocateRegistry.getRegistry(ip);
			c = (BalancerInterface) registry.lookup(name);
			c.addServer(this);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		finally{
			try {
				c.deleteServer(this);
			} catch (RemoteException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Dank dieser Methode kann man feststellen ob der Server noch vorhanden ist.
	 */
	public boolean isterreichbar(){
		return true;
	}
	
	/**
	 * @see kopec_stedronsky.Balancer_Implementierung.Calculator#pi(int)
	 */
	@Override
	public synchronized BigDecimal pi(int anzahl_nachkommastellen) throws RemoteException {
		return new Calculator().pi(anzahl_nachkommastellen);
	}
}
