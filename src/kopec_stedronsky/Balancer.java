package kopec_stedronsky;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * Balancer Interface
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public interface Balancer extends Remote{

	public void addServer(Server server)throws RemoteException;
	
	public BigDecimal getResult(int stellen)throws RemoteException;
	
	public void deleteServer(Server server)throws RemoteException;
}
