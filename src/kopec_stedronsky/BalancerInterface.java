package kopec_stedronsky;

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

	public void addServer(Server server)throws RemoteException, MalformedURLException, AlreadyBoundException;
	
	public BigDecimal getResult(int stellen)throws RemoteException;
	
	public void deleteServer(Server server)throws RemoteException;
}
