package kopec_stedronsky.Balancer_Implementierung;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Balancer fuer den Calculator
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class LoadBalancer extends UnicastRemoteObject implements BalancerInterface{
	//Serial-ID
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Server> server;
	private int index;
	
	/**
	 * Konstruktor
	 * @param name
	 * @throws RemoteException
	 */
	public LoadBalancer(String name) throws RemoteException{
		new PIThread(this);
		try {
			this.server = new ArrayList<Server>();
			this.index = -1;
			LocateRegistry.createRegistry(1099);
			Naming.bind(name, this);
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Checkt ob ein server da ist
	 */
	public void isThere(){
		for(Server c : server){
			if(!c.isterreichbar()){
				try {
					this.deleteServer(c);
				} catch (RemoteException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * Server wird den Balancer hinzugefuegt
	 * @param Server
	 * @throws AlreadyBoundException 
	 * @throws MalformedURLException 
	 */
	@Override
	public void addServer(Server server) throws RemoteException, MalformedURLException, AlreadyBoundException{
		this.server.add(server);
		Naming.bind("Server"+this.server.size(), server);
	}
	
	/**
	 * Pi mit den jeweiligen Kommastellen wird zurueckgegeben 
	 * @param stellen
	 * @throws RemoteException
	 */
	@Override
	public BigDecimal getResult(int stellen) throws RemoteException{
		this.index++;
		if(index == server.size())index = 0;
		return this.server.get(this.index).pi(stellen);
	}
	
	/**
	 * Server wird entfernt 
	 * @param Server
	 * @throws RemoteException
	 */
	@Override
	public void deleteServer(Server server) throws RemoteException {
		this.server.remove(server);
	}

}
