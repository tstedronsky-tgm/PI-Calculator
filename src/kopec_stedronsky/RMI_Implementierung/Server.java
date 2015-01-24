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
 * RMI DateServer
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class Server implements CalculatorInterface{
	public Server(String name){
		try{
			System.out.println("Starting server...");
			Registry registry = LocateRegistry.createRegistry(1099);
			CalculatorInterface stub = (CalculatorInterface) UnicastRemoteObject.exportObject(this, 0);
			registry.bind(name, this);
			System.out.println("Server bound...");
			System.out.println("Press any key to unbound object...");
			System.in.read();
			registry.unbind(name);
		}catch(AlreadyBoundException | IOException | NotBoundException e){
			System.err.println(e.getMessage());
		}
	}

	@Override
	public BigDecimal pi(int anzahl_nachkommastellen) throws RemoteException {
		return new Calculator().pi(anzahl_nachkommastellen);
	}

	public static void main(String[] args) throws Exception{
		new Server("RMI");
	}
}