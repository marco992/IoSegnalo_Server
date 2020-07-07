package entity;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate.Filter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Sistema {
	
	private ArrayList<Utente> utenti;
	
	public int getTipoUtente(String Username, String Password) {
		
		//preleva dal database la persona con tale username e password (se esiste) e in caso di esito positivo, il tipo di utente, altrimenti -1
		
		SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
		//Filter filter = sessione.enableFilter("FiltroCittadini");
		//filter.setParameter("tipoUtente", new Integer(0));
		sessione.beginTransaction();
		List<Utente> results = sessione.createQuery("from Utente").list();
		for(Utente c: results){
			System.out.println("UserName:"+c.getUsername()+", ID:"+c.getId());
			if((c.getUsername().equals(Username)) && (c.getPassword().equals(Password)))
				return c.getTipo();
		}	
		return -1;
	}

	public Sistema() {
		super();
		// TODO Auto-generated constructor stub
	}
}
