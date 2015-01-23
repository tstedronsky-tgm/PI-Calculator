package kopec_stedronsky;
import java.util.*;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject ;


/**
 * Balancer für den Calculator
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class CalculatorImpl extends UnicastRemoteObject implements Calculator{
	public CalculatorImpl() throws RemoteException{
	}
	
	
	@Override
	public BigDecimal pi(int anzahl_nachkommastellen) throws RemoteException {
		return Pi.pi(anzahl_nachkommastellen);
	}
}