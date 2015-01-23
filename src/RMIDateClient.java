

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kopec_stedronsky.Calculator;


/**
 * RMI DateClient
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class RMIDateClient{
	private Calculator piServer;
	public RMIDateClient(String ip) {
		try {
			
			System.out.println("starting client...");
			Registry registry = LocateRegistry.getRegistry(ip);
			this.piServer = (Calculator) registry.lookup("RMI");
		} catch (RemoteException e) { 
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}
	
	public void getPI(int anzahl) throws RemoteException{
		System.out.println("PI = " + piServer.pi(anzahl) ) ;
	}
	
	public static void main(String[] args) throws Exception {
		new RMIDateClient("192.168.0.19").getPI(10);
	}
}