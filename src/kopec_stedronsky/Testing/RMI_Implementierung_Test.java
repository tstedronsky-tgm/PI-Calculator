package kopec_stedronsky.Testing;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import kopec_stedronsky.RMI_Implementierung.*;

import org.junit.*;

public class RMI_Implementierung_Test {
	
	private Client client;
	
	@BeforeClass
	public static void checkIfServerIsRunning(){
		try{
			new Client("192.168.0.12");
		}catch(RemoteException e){
			System.err.println("Check if server is running or change ip address! \nThen start tests again...");
			System.exit(1);
		}
	}
	
	@Before
	public void startClient() throws RemoteException{
		this.client=new Client("192.168.0.12");
	}
	
	@After
	public void cleanUp(){
		client = null;
	}
	
	@Test
	public void piNormalfall() throws RemoteException {
		String expected = "3.1415926535";
		String actual = ""+this.client.getPI(10);
		assertEquals(expected,actual);
	}
	
	@Test
	public void piGrenzfall() throws RemoteException {
		String expected = "3";
		String actual = ""+this.client.getPI(0);
		assertEquals(expected,actual);
	}
	
	@Test
	public void piFehlerfall() throws RemoteException {
		String expected = "0E+1";
		String actual = ""+this.client.getPI(-1);
		assertEquals(expected,actual);
	} 
}
