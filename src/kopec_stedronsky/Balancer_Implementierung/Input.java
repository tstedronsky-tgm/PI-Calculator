package kopec_stedronsky.Balancer_Implementierung;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Diese Klasse ist fuer die Verwaltung der Arguments zustaendig
 */
public class Input {
	public Input(String[] args) throws RemoteException{
		//Default Werte
		String ip = getSystemLocalIp();
		String name = "Balancer";//Name des Balancers in der Registry
		int anzahl=10;//Anzahl der gewuenschten Nachkommastellen von Pi
		int pool=1;//Anzahl der zur Berechnung verwendeten Server

		//Argument Optionen
		Options options = new Options();
		options.addOption("h", true, "client,server");
		options.addOption("n", true, "Name des Loadbalancers in der Registry");
		options.addOption("b", true, "Ip des Objekts");
		options.addOption("d", true, "Anzahl der Stellen fuer Pi");
		options.addOption("p", true, "Serverpoolgroesse");

		
		CommandLineParser parser = new GnuParser();
		CommandLine cmd=null;
		try {
			cmd = parser.parse( options, args);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		
		if(cmd.hasOption("p")){
			pool=Integer.parseInt(cmd.getOptionValue('p'));
		}
		if(cmd.hasOption("h")){
			if(cmd.hasOption("n")){
				name=cmd.getOptionValue('n');
			}
			if(cmd.hasOption("b")){
				ip=cmd.getOptionValue('b');
			}
			if(cmd.getOptionValue('h').equals("client")){
				if(cmd.hasOption("d")){
					anzahl=Integer.parseInt(cmd.getOptionValue('d'));
				}
				new Client(ip,name,anzahl);
			}else{
				if(cmd.getOptionValue('h').equals("server")){
					new LoadBalancer(name);
					for(int i = 0; i< pool;i++){
						new Server(ip, name);
					}
				}
			}
		}
	}
	
	/**
	 * Diese Methode liefert die lokale Ip-Adresse des verwendeten 
	 * Hostsystems
	 * @return - die lokale Ip-Adresse
	 */
	public static String getSystemLocalIp(){
		InetAddress local=null;
		try {
			local=InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
		}
		return local.getHostAddress();
	}
}

