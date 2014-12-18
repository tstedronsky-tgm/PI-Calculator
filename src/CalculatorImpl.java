
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject ;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator{
	public CalculatorImpl() throws RemoteException{
		super();
	}

	@Override
	public BigDecimal pi(int anzahl_nachkommastellen) throws RemoteException {
		return Pi.pi(anzahl_nachkommastellen);
	}
}