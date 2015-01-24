package kopec_stedronsky.Balancer_Implementierung;

import java.rmi.RemoteException;

/**
 * 
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class Main {
	public static void main(String[] args){
		try {
			new Input(args);
		} catch (RemoteException e) {
			System.err.println(e.getMessage());
		}
	}
}

