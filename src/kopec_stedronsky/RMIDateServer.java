package kopec_stedronsky;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


/**
 * Balancer für den Calculator
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class RMIDateServer implements Calculator{
	

	
	public RMIDateServer(String name) throws Exception{
//		CalculatorImpl remObj = new CalculatorImpl();
//		Registry registry = LocateRegistry.createRegistry(2000);
//		Naming.bind(name, remObj);
//		
//
//		try {
//			System.out.println("starting server...");
//
//			name = "Calculator";
//			Calculator stub = (Calculator) UnicastRemoteObject.exportObject(this, 0);
//			Registry registry = LocateRegistry.getRegistry();
//
//			registry.bind(name, this);
//			System.out.println("Server bound");
//			System.out.println("Press any key to unbound object");
//			System.in.read();
//            registry.unbind(name);
//            
//		} catch (Exception e) {
//			System.err.println("Server exception:");
//			e.printStackTrace();
//		}
	}

	@Override
	public BigDecimal pi(int anzahl_nachkommastellen) throws RemoteException {
		return new CalculatorImpl().pi(anzahl_nachkommastellen);
	}


	public static void main(String[] args){
		if ( System.getSecurityManager() == null ) {
			System.setSecurityManager( new RMISecurityManager() ); }
		try{
			CalculatorImpl remObj = new CalculatorImpl();
			Registry registry = LocateRegistry.createRegistry(0);
			Naming.bind("RMI", remObj);
			Scanner sc = new Scanner(System.in);
			String s = sc.next();
			if( s.toLowerCase().equals("q")){ 
				Naming.unbind("RMI");
				System.exit(0);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}