package controller;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entity.Utente;

import java.io.*;

	public class ControllerComunicazione { 
		private int port; 
		private ServerSocket server;
		private boolean enabled;
		ControllerUtente C_User;
		
		String Username;

	public ControllerComunicazione (int port) 
	{ 
		this.port = port; 
		if(!avviaServer()) 
			System.err.println("Errore durante la creazione del Server"); 
		}
	
		public void setEnable(boolean stato)
		{
			enabled=stato;
		}

	private boolean avviaServer() { 
		enabled=true;
		try { 
			server = new ServerSocket(port); 
			} 
		catch (IOException ex) {
			ex.printStackTrace();
			return false;
			} 
		System.out.println("Server creato con successo!"); 
		return true; 
	}

	public void runServer() throws ClassNotFoundException 
	{ 
		int tipo=-1;
		int tipoRisposta=-1;
    	int IDUtente = -1;

		while (enabled) 
		{ 
			try { 
				// Il server resta in attesa di una richiesta 
				System.out.println("Server in attesa di richieste…"); 
				Socket s1 = server.accept(); 
				System.out.println("Un client si e’ connesso…");
				
				
        		OutputStream OutStream = s1.getOutputStream(); 
        		ObjectOutputStream objectOutputStream = new ObjectOutputStream(OutStream);
				InputStream InStream = s1.getInputStream();
		        ObjectInputStream input;
		        input = new ObjectInputStream(InStream);
        		
		        System.out.println("Effettuo la lettura dei dati dal client...");

		        ArrayList messaggioIN = new ArrayList();
		        messaggioIN = (ArrayList)input.readObject();
		        System.out.println("Effettuo la lettura dei dati dal client...");

                if (messaggioIN != null) 
                {
                    System.out.println("client: " + messaggioIN.get(0));
                    //verifico il primo elemento dell'arraylist per distinguere i vari messaggi
                    switch(messaggioIN.get(0).toString()) {
                    case "0":
                    	C_User = new ControllerUtente();
                    	tipo=C_User.Connetti((String)messaggioIN.get(1), (String)messaggioIN.get(2));
                    	IDUtente=C_User.prelevaID((String)messaggioIN.get(1));
                    	tipoRisposta=0;
                		System.out.println(" Tipo: " + tipo);
                		System.out.println(" ID: " + IDUtente);

                    break;
                    case "1":
                    	IDUtente=Integer.parseInt(messaggioIN.get(1).toString());
                    	System.out.println("IDUtente: "+IDUtente);
                    	tipoRisposta=1;
                    	
                    break;
                    // eventuali altri case
                    //case valueN:
                    //...
                    //default:
                    }
                }
		        //input.close();


                ArrayList MessaggioOutput = new ArrayList();

                switch(tipoRisposta)
                {
                	case 0:
                		System.out.println("Effettuo adesso l'invio della risposta di login...");
                		MessaggioOutput.clear();
                		MessaggioOutput.add(0);
                		MessaggioOutput.add(tipo);
                		MessaggioOutput.add(IDUtente);
                		System.out.println(MessaggioOutput.get(2).toString());
                		objectOutputStream.writeObject(MessaggioOutput);
                        objectOutputStream.flush();
                		break;
                    case 1:
                		System.out.println("Effettuo adesso l'invio della risposta di vis. segnalazioni...");
                    	ControllerSegnalazioni C_Segnalazione = new ControllerSegnalazioni();
                		MessaggioOutput.clear();
                		MessaggioOutput.add(1);
                		ArrayList out = C_Segnalazione.getListaSegnalazioniUtente(IDUtente);
                		int i;
                		if(out!=null)
                		for(i=0;i<out.size();i++) {
                			System.out.println("indice: "+i + ", Informazione inviata: "+out.get(i).toString());
                			MessaggioOutput.add(out.get(i).toString());
                		}
                		objectOutputStream.writeObject(MessaggioOutput);
                        objectOutputStream.flush();
                    break;
                }
                //TimeUnit.SECONDS.sleep(35);
		        
		        objectOutputStream.close();
		        input.close();
		        s1.close();
		        
		        System.out.println("Chiusura connessione effettuata");
		        

			} catch (IOException ex) 
				{ 
					ex.printStackTrace(); 
					System.out.println("Errore!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		 
	}
		

	}
	
	public ArrayList riceviDati() {
		return null;
	}
	
	public void inviaDati(ArrayList dati) {
		
	}

}
