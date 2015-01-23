package kopec_stedronsky;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Balancer für den Calculator
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class CalculatorBalancer extends UnicastRemoteObject implements Balancer, Calculator{

	private ArrayList<Server> server;
	private int index;
	
	/**
	 * Konstruktor
	 * @param name
	 * @throws RemoteException
	 */
	public CalculatorBalancer(String name) throws RemoteException{
		new PIThread(this);
		try {
			this.server = new ArrayList<Server>();
			this.index = -1;
			Registry registry = LocateRegistry.createRegistry(1099);
			Naming.bind(name, this);
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Checkt ob ein server da ist
	 */
	public void isThere(){
		int i=0;
		for(Server c : server){
			if(!c.isterreichbar()){
				try {
					this.deleteServer(c);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			i++;
		}
	}

	/**
	 * Server wird den Balancer hinzugefügt
	 * @param Server
	 */
	@Override
	public void addServer(Server server) throws RemoteException{
		this.server.add(server);
	}
	
	/**
	 * Pi mit den jeweiligen Kommastellen wird zurückgegeben 
	 * @param stellen
	 * @throws RemoteException
	 */
	@Override
	public BigDecimal getResult(int stellen) throws RemoteException{
		this.index++;
		if(index == server.size())index = 0;
		System.out.println(index);
		return this.server.get(this.index).pi(stellen);
	}
	
	/**
	 * Pi mit den jeweiligen Kommastellen wird zurückgegeben 
	 * @param stellen
	 * @throws RemoteException
	 */
	@Override
	public BigDecimal pi(int anzahl_nachkommastellen) throws RemoteException {
		return new CalculatorImpl().pi(anzahl_nachkommastellen);
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
