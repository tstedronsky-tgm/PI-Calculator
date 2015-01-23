package kopec_stedronsky;

/**
 * 
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class PIThread extends Thread {
	private CalculatorBalancer c;
	
	/**
	 * Konstruktor
	 * @param Load Balancer
	 */
	public PIThread(CalculatorBalancer c){
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
				this.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.isThere();
		}
	}
}
