package kopec_stedronsky.Balancer_Implementierung;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
/*
 * Das Calculator Interface stellt sicher, dass der Server die Methode zur
 * Berechnung on Pi implementiert
 */
public interface CalculatorInterface extends Remote{
	/*
	 * Diese Methode liefert Pi mit den gewuenschten Nachkommastellen
	 * @param anzahl_nachkommastellen - Anzahl der gewuenschten Nachkommastellen
	 */
	public BigDecimal pi(int anzahl_nachkommastellen) throws RemoteException;
}

