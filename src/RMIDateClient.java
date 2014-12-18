import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIDateClient{
	private Calculator piServer;
	public RMIDateClient(String ip) {
		try {
			
			System.out.println("starting client...");
			Registry registry = LocateRegistry.getRegistry(ip);
			this.piServer = (Calculator) registry.lookup("Calculator");
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
		new RMIDateServer("RMI");
//		new RMIDateClient("127.0.0.1").getPI(10);
	}
}