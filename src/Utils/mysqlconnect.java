package Utils;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionEventListener;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Utente;

import org.hibernate.Filter;


public class mysqlconnect {
	public static void main(String args[]){  
		
		//test di inserimento nuovo cittadino nella tabella utenti
		
		/*
		Cittadino test1 = new Cittadino("NomeCittadino","CognomeCittadino",0);
		try{ 
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
		Transaction tx=sessione.beginTransaction();
		sessione.save(test1);
		tx.commit();
		sessione.close();
		}catch(Exception e){ 
				System.out.println(e);
			}  
		}
		*/
	
	//test di prelievo dati da database	
	/*	
	SessionFactory sd = new Configuration().configure().buildSessionFactory();
	Session sessione = sd.openSession();
	Cittadino test2 = (Cittadino) sessione.get(Cittadino.class, 3);
	System.out.println(test2.getUsername());
	sessione.close();
	*/
		
		
	
		//test di stampa dei soli cittadini	
		/*	
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		//Session sessione = sd.openSession();
		//CriteriaBuilder criteriaBuilder = sessione.getCriteriaBuilder();
		Session sessione = sd.openSession();
		Filter filter = sessione.enableFilter("FiltroCittadini");
		filter.setParameter("tipoUtente", new Integer(0));
		sessione.beginTransaction();
		List<Utente> results = sessione.createQuery("from Cittadino").list();
		for(Utente c: results){
			System.out.println("UserName:"+c.getUsername()+", ID:"+c.getId());
		}*/
		
	}
}
