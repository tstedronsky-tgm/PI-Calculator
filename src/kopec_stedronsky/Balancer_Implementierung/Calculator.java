package kopec_stedronsky.Balancer_Implementierung;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


/**
 * Balancer für den Calculator
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class Calculator extends UnicastRemoteObject implements CalculatorInterface{
	//Serial-ID
	private static final long serialVersionUID = 1L;

	public Calculator() throws RemoteException{
		super();
	}

	@Override
	public BigDecimal pi(int anzahl_nachkommastellen) throws RemoteException {
		return Pi.pi(anzahl_nachkommastellen);
	}
}