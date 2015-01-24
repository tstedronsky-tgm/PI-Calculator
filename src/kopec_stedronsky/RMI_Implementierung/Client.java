package kopec_stedronsky.RMI_Implementierung;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client{
	private CalculatorInterface piServer;
	public Client() {
		try {
			System.out.println("starting client...");
			Registry registry = LocateRegistry.getRegistry();
			this.piServer = (CalculatorInterface) registry.lookup("RMI");
		} catch (RemoteException | NotBoundException e){
			System.err.println(e.getMessage());
		}
	}
	
	public void getPI(int anzahl) throws RemoteException{
		System.out.println("PI = " + piServer.pi(anzahl) ) ;
	}
	
	public static void main(String[] args) throws Exception {
		new Client().getPI(10);
	}
}
