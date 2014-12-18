import java.util.ArrayList;
import java.util.Observable;


public class LoadBalancer{
	private ArrayList<RMIDateServer> serverList;
	
	public LoadBalancer(RMIDateServer server){
		this.serverList = new ArrayList<>();
	}
	
	public void addServer(RMIDateServer server){
		this.serverList.add(server);
	}
	
	public void removeServer(RMIDateServer server){
		this.serverList.remove(server);
	}
	
	
}
