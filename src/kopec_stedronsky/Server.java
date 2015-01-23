package kopec_stedronsky;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Server
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class Server implements Calculator, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor
	 * @param ip
	 * @param name
	 */
	public Server(String ip, String name){
		Balancer c = null;
		try {
			if ( System.getSecurityManager() == null ) {
				System.setSecurityManager( new RMISecurityManager() ); }
			Registry registry = LocateRegistry.getRegistry(ip);
			c = (Balancer) registry.lookup(name);
			c.addServer(this);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		finally{
			try {
				c.deleteServer(this);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public boolean isterreichbar(){
		return true;
	}

	@Override
	public synchronized BigDecimal pi(int anzahl_nachkommastellen) throws RemoteException {
		return new CalculatorImpl().pi(anzahl_nachkommastellen);
	}
}
