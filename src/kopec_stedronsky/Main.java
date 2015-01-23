package kopec_stedronsky;

import java.rmi.RemoteException;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Balancer für den Calculator
 * @author Stedronsky Thomas
 * @author Kopec Jakub
 * @version 2015-01-07
 */
public class Main {

	public static void main(String[] args) throws RemoteException {
		//		String ip = "10.0.105.224";
		String ip = "192.168.0.19";
		int anzahl=3;
		int pool=1;
		Options options = new Options();
		//Diverse Options für den Apache Common
		options.addOption("h", true, "c fuer client, s fuer server");
		options.addOption("n", true, "Name des Objekts");
		options.addOption("b", true, "IP des Balancers");
		options.addOption("d", true, "Anzahl der Stellen fuer Pi");
		options.addOption("p", true, "Serverpoolgroesse");
		Scanner scanner = new Scanner(System.in);
		String name = "";
		CommandLineParser parser = new GnuParser();
		CommandLine cmd=null;
		try {
			cmd = parser.parse( options, args);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			if(cmd.getOptionValue('h').equals("c")){
				if(cmd.hasOption("d")){
					anzahl=Integer.parseInt(cmd.getOptionValue('d'));
				}
				new Client(ip,name,anzahl);
			}else{
				if(cmd.getOptionValue('h').equals("s")){
					CalculatorBalancer l = new CalculatorBalancer(name);
					for(int i = 0; i< pool;i++){
						new Server(ip, name);
					}
				}
			}

		}
	}
}

