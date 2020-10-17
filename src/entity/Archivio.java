package entity;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
//import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Archivio {
	
	private static List<Utente> utenti;
	private static List<Segnalazione> segnalazioni;
	
	public Archivio() {
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
	
	
	
	static public List<Segnalazione> getListaSegnalazioni() {
		
		//preleva tutte le segnalazioni dalla tabella Segnalazioni
		
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
		sessione.beginTransaction();
		segnalazioni = sessione.createQuery("from Segnalazione").list();
		return segnalazioni;
		
	}
	
	static public void aggiornaSegnalazione(Segnalazione S) {
		
		//aggiorna una specifica segnalazione sul database
		
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
        Transaction tx = sessione.beginTransaction();
		sessione.update(S);
        tx.commit();

		
	}
	
	static public void inserisciIntervento(Intervento I) {
		
		//aggiorna una specifica segnalazione sul database
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
		sessione.beginTransaction();
        sessione.save(I);
        sessione.getTransaction().commit();
	}

}
