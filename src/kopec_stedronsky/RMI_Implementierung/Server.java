package kopec_stedronsky.RMI_Implementierung;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Server
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class Server implements CalculatorInterface{
	/**
	 * Konstruktor erstellt den Server
	 * @param name - Name des Servers in der Registry
	 */
	public Server(String name){
		try{
			System.out.println("Starting server...");
			//Registry am standard-Port erzeugen
			Registry registry = LocateRegistry.createRegistry(1099);
			//das Interface als 'stub' anderen Objekten die remote drauf zugreifen ermoeglichen 
			CalculatorInterface stub = (CalculatorInterface) UnicastRemoteObject.exportObject(this, 0);
			//Server mit uebergebenem Namen an die Registry binden
			registry.bind(name, this);
			System.out.println("Server bound...");
			System.out.println("Press any key to unbound object...");
			System.in.read();
			//Server unbinden falls eine Taste gedrueckt wurde
			registry.unbind(name);
		}catch(AlreadyBoundException | IOException | NotBoundException e){
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * (non-Javadoc)
	 * @see kopec_stedronsky.RMI_Implementierung.CalculatorInterface#pi(int)
	 */
	@Override
	public BigDecimal pi(int anzahl_nachkommastellen) throws RemoteException {
		return new Calculator().pi(anzahl_nachkommastellen);
	}
	
	/**
	 * Main startet den Server
	 * @param args - Main Arguments
	 */
	public static void main(String[] args){
		//Server wird mit beliebigem Namen als Parameter erzeugt unter welchem er dann in der Registry zu finden ist
		new Server("RMI");
	}
}