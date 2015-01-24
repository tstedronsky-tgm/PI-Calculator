package kopec_stedronsky.RMI_Implementierung;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 * Client
 * @author Thomas Stedronsky
 * @author Jakub Kopec
 */
public class Client{
	//Server Instanz aus der Registry
	private CalculatorInterface piServer;
	/**
	 * Konstruktor erstellt den Client
	 * @param ip - ip der Registry
	 */
	public Client(String ip) {
		try {
			System.out.println("starting client...");
			//Bekommen der Registry ueber die uebergebene ip
			Registry registry = LocateRegistry.getRegistry(ip);
			this.piServer = (CalculatorInterface) registry.lookup("RMI");
		} catch (RemoteException | NotBoundException e){
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Gibt die gewuenschte Anzahl der Nachkommastellen von Pi aus
	 * @param anzahl - gewuenschte Anzahl der Nachkommastellen
	 * @throws RemoteException
	 */
	public void getPI(int anzahl){
		try{
			System.out.println("PI = " + piServer.pi(anzahl) ) ;
		}catch(RemoteException e){
			System.err.println(e.getMessage());
		}
		
	}
	
	/**
	 * Main startert den Client
	 * @param args - Main Arguments
	 */
	public static void main(String[] args){
		new Client("192.168.0.12").getPI(10);
	}
}
