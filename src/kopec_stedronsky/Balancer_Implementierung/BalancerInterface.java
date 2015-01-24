package kopec_stedronsky.Balancer_Implementierung;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * Balancer Interface
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public interface BalancerInterface extends Remote{
	/**
	 * Diese Methode fuegt den Server dem Loadbalancer hinzu
	 * @param server - Server welcher hinzugefügt werden soll
	 * @throws RemoteException
	 * @throws MalformedURLException
	 * @throws AlreadyBoundException
	 */
	public void addServer(Server server)throws RemoteException, MalformedURLException, AlreadyBoundException;
	
	/**
	 * Liefert die gewuenschten Nachkommastellen von Pi
	 * @param stellen - Anzahl der gewuenschten Nachkommastellen
	 * @return - die gewuenschten Nachkommastellen
	 * @throws RemoteException
	 */
	public BigDecimal getResult(int stellen)throws RemoteException;
	
	/**
	 * Loescht einen bestimmten Server vom Loadbalancer
	 * @param server - Server welcher geloescht werden soll
	 * @throws RemoteException
	 */
	public void deleteServer(Server server)throws RemoteException;
}
