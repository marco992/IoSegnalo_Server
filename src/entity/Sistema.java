package entity;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

//import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Sistema {
	
	private static List<Utente> utenti;
	private static List<Segnalazione> segnalazioni;
	
	public Sistema() {
		super();
	}
	
	static public List<Utente> getListaUtenti() {
		
		//preleva tutte le segnalazioni dalla tabella Segnalazioni
		
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
		sessione.beginTransaction();
		utenti = sessione.createQuery("from Utente").list();
		return utenti;
		
	}
	
    @Transactional
	static public void inserisciSegnalazione(int Tipologia, String Descrizione, int IDCittadino, Double Latitudine, Double Longitudine, String Recapito) {
		
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
		sessione.beginTransaction();

		java.util.Date utilDate = new java.util.Date();
        java.sql.Date Data = new java.sql.Date(utilDate.getTime());
        Segnalazione s = new Segnalazione();
        
        s.setStato(0);
        s.setDataModifica(Data);
        s.setTipologia(Tipologia);
        s.setDescrizione(Descrizione);
        s.setIDcittadino(IDCittadino);
        s.setLatitudine(Latitudine);
        s.setLongitudine(Longitudine);
        s.setNota("Nessuna");
        s.setRecapito(Recapito);
        
        sessione.save(s);
 
        sessione.getTransaction().commit();

    }
	
	
	static public int getTipoUtente(String Username, String Password) {
		
		//preleva dal database la persona con tale username e password (se esiste) e in caso di esito positivo, il tipo di utente, altrimenti -1
		
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
		sessione.beginTransaction();
		List<Utente> results = sessione.createQuery("from Utente").list();
		for(Utente c: results){
			System.out.println("UserName:"+c.getUsername()+", ID:"+c.getId());
			if((c.getUsername().equals(Username)) && (c.getPassword().equals(Password)))
				return c.getTipo();
		}	
		return -1;
		
	}
	 
	static public int getIDUtente(String Username) {
		
		//preleva dal database la persona con tale username e password (se esiste) e in caso di esito positivo, il tipo di utente, altrimenti -1
		
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
		sessione.beginTransaction();
		List<Utente> results = sessione.createQuery("from Utente").list();
		for(Utente c: results){
			System.out.println("UserName:"+c.getUsername()+", ID:"+c.getId());
			if(c.getUsername().equals(Username))
				return c.getId();
		}	
		return -1;
		
	}
	
	static public List<Segnalazione> getListaSegnalazioni() {
		
		//preleva tutte le segnalazioni dalla tabella Segnalazioni
		
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
		sessione.beginTransaction();
		segnalazioni = sessione.createQuery("from Segnalazione").list();
		return segnalazioni;
		
	}

}
