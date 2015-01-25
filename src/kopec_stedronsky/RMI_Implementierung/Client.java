package kopec_stedronsky.RMI_Implementierung;

import java.math.BigDecimal;
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
	public Client(String ip) throws RemoteException{
		try {
			System.out.println("starting client...");
			//Bekommen der Registry ueber die uebergebene ip
			Registry registry = LocateRegistry.getRegistry(ip);
			this.piServer = (CalculatorInterface) registry.lookup("RMI");
		} catch (NotBoundException e){
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Gibt die gewuenschte Anzahl der Nachkommastellen von Pi aus
	 * @param anzahl - gewuenschte Anzahl der Nachkommastellen
	 * @throws RemoteException
	 */
	public BigDecimal getPI(int anzahl) throws RemoteException{
		BigDecimal pi = piServer.pi(anzahl);
		System.out.println("PI = " + pi ) ;
		return pi;
	}

	/**
	 * Main startert den Client
	 * @param args - Main Arguments
	 */
	public static void main(String[] args) throws RemoteException{
		new Client("192.168.0.12").getPI(-1);
	}
}
