package controller;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

import java.io.*;

	public class ControllerComunicazione { 
		private int port; 
		private ServerSocket server;
		private boolean enabled;

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
			server.setSoTimeout(60000);
			} 
		catch (IOException ex) {
			ex.printStackTrace();
			return false;
			} 
		System.out.println("Server creato con successo!"); 
		return true; 
	}

	public void ascolta() throws ClassNotFoundException 
	{ 
		int tipo=-1;
		int tipoRisposta=-1;
    	int IDUtente = -1;
    	int Stato=-1;
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
        		
		        //System.out.println("Effettuo la lettura dei dati dal client...");

		        ArrayList messaggioIN = new ArrayList();
		        messaggioIN = (ArrayList)input.readObject();
                ArrayList MessaggioOutput = new ArrayList();

		        System.out.println("Effettuo la lettura dei dati dal client...");

                if (messaggioIN != null) 
                {
                    System.out.println("client: " + messaggioIN.get(0));
                	ControllerSegnalazioni C_Segnalazione = new ControllerSegnalazioni();

                    //verifico il primo elemento dell'arraylist per distinguere i vari messaggi
                    switch(messaggioIN.get(0).toString()) {
                    //richiesta accesso
                    case "0":
                    	ControllerUtente C_User = new ControllerUtente();
                    	List<Integer> in = new ArrayList<Integer>();
                    	in=C_User.Connetti((String)messaggioIN.get(1), (String)messaggioIN.get(2));
                    	if(in!=null) 
                    	{
                    		tipo=Integer.parseInt(in.get(0).toString());
                    		IDUtente=Integer.parseInt(in.get(1).toString());
                    		System.out.println("Effettuo adesso l'invio della risposta di login...");
                            //risposta ad una richiesta di accesso, viene inviato il tipo e id dell'utente

                    		MessaggioOutput.clear();
                    		MessaggioOutput.add(0);
                    		MessaggioOutput.add(tipo);
                    		MessaggioOutput.add(IDUtente);
                    		System.out.println(MessaggioOutput.get(2).toString());
                    		objectOutputStream.writeObject(MessaggioOutput);
                            objectOutputStream.flush();
                    		System.out.println(" Tipo: " + tipo);
                    		System.out.println(" ID: " + IDUtente);
                    	}
                    	else
                    	{
                    		tipo=-1;
                    		IDUtente=-1;
                    	}

                    break;
                    //richiesta lista segnalazioni di uno specifico cittadino
                    case "1":
                    	IDUtente=Integer.parseInt(messaggioIN.get(1).toString());
                    	System.out.println("IDUtente: "+IDUtente);
                       
                		//risposta ad una richiesta di visualizzazione segnalazioni cittadino

                    	System.out.println("Effettuo adesso l'invio della risposta di vis. segnalazioni...");
                		MessaggioOutput.clear();
                		MessaggioOutput.add(1);

                		ArrayList ListaSegnalazioni = C_Segnalazione.richiediListaSegnalazioniUtente(IDUtente);
                		int i;
                		if(ListaSegnalazioni!=null)
                		{
                			for(i=0;i<ListaSegnalazioni.size();i++) {
                				System.out.println("indice: "+i + ", Informazione inviata: "+ListaSegnalazioni.get(i).toString());
                				MessaggioOutput.add(ListaSegnalazioni.get(i).toString());
                			}
                		}
                		objectOutputStream.writeObject(MessaggioOutput);
                        objectOutputStream.flush();
                    break;
                    //inserimento nuova segnalazione
                    case "2":
                    	System.out.println("Ho ricevuto una richiesta di inserimento segnalazione");

                    	C_Segnalazione.nuovaSegnalazione(Integer.parseInt(messaggioIN.get(1).toString()), messaggioIN.get(2).toString(), Integer.parseInt(messaggioIN.get(3).toString()),Double.parseDouble(messaggioIN.get(4).toString()), Double.parseDouble(messaggioIN.get(5).toString()), messaggioIN.get(6).toString());
                    	System.out.println("Effettuo adesso l'invio della risposta di vis. segnalazioni...");
                    	 //risposta ad un inserimento nel database, esito negativo -1, esito positivo 1
                    	MessaggioOutput.clear();
                		MessaggioOutput.add(2);
                		MessaggioOutput.add(1);
                		objectOutputStream.writeObject(MessaggioOutput);
                        objectOutputStream.flush();
                    	break;
                    
                    case "4":
       
                    	Stato=Integer.parseInt(messaggioIN.get(1).toString());
                    	System.out.println("Stato: "+Stato);
                		System.out.println("Effettuo adesso l'invio della risposta di vis. segnalazioni...");
                    	C_Segnalazione = new ControllerSegnalazioni();
                		MessaggioOutput.clear();
                		MessaggioOutput.add(4);
                		ArrayList ListaSegnalazioniAperte = C_Segnalazione.richiediListaSegnalazionibyStato(IDUtente,Stato);
                		if(ListaSegnalazioniAperte!=null)
                		{
                			for(i=0;i<ListaSegnalazioniAperte.size();i++) {
                				System.out.println("indice: "+i + ", Informazione inviata: "+ListaSegnalazioniAperte.get(i).toString());
                				MessaggioOutput.add(ListaSegnalazioniAperte.get(i).toString());
                			}
                		}
                		objectOutputStream.writeObject(MessaggioOutput);
                        objectOutputStream.flush();
                	
                    break;
                    
                    case "5":
                    	IDUtente = Integer.parseInt(messaggioIN.get(1).toString());
                    	int IDSegnalazione, NuovoStato;
                    	IDSegnalazione=Integer.parseInt(messaggioIN.get(2).toString());
                    	NuovoStato=Integer.parseInt(messaggioIN.get(3).toString());
                    	System.out.println("IDUtente: "+ IDUtente + "IDSegnalazione: " + IDSegnalazione + "Nuovo stato: " + NuovoStato);
                    	ControllerSegnalazioni C_Segnalazione3 = new ControllerSegnalazioni();
                    	C_Segnalazione3.modificaStatoSegnalazione(IDSegnalazione, IDUtente, NuovoStato);
                    	ControllerInterventi C_Interventi = new ControllerInterventi();
                    	C_Interventi.inserisciIntervento(IDSegnalazione, IDUtente);
                		System.out.println("Effettuo adesso l'invio della risposta di presa in carico intervento...");
                		MessaggioOutput.clear();
                		MessaggioOutput.add(5);
                		MessaggioOutput.add(1);
                		objectOutputStream.writeObject(MessaggioOutput);
                        objectOutputStream.flush();
                    	break;
                    default: 
                    	System.out.println("Richiesta non gestita: "+ messaggioIN.get(0).toString());
                    }
                }

		        objectOutputStream.close();
		        input.close();
		        s1.close();
		        
		        System.out.println("Chiusura connessione effettuata");
		        
			} catch (Exception e) {
				e.printStackTrace();
			} 
		 
	}
		

	}

}
