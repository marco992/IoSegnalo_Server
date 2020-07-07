package Boundary;
import Utils.SocketManager;
import entity.Sistema;
import controller.ControllerUtente;

public class MainServer {
	public static void main (String args[]) {
		// Crea un oggetto di tipo SimpleServer in ascolto 
		// sulla porta 7777 
		//ControllerUtente C_User = new ControllerUtente();
		//System.out.println(C_User.Connetti("Giovanna", "Taglialatela"));
		SocketManager sm = new SocketManager(7777); 
		try {
			sm.runServer();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	} 
}
