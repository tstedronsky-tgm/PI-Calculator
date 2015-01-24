package kopec_stedronsky.Balancer_Implementierung;

/**
 * 
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class PIThread extends Thread {
	private LoadBalancer c;
	
	/**
	 * Konstruktor
	 * @param Load Balancer
	 */
	public PIThread(LoadBalancer c){
		this.c = c;
		this.start();
	}
	
	/**
	 * run () Methode
	 */
	@Override
	public void run() {
		while(true){
			try {
				PIThread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.isThere();
		}
	}
}
