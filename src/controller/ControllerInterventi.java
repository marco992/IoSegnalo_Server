package controller;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Archivio;
import entity.Intervento;
import entity.Segnalazione;

public class ControllerInterventi {
	public ControllerInterventi() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    @Transactional
    public void nuovoIntervento(int CodiceSegnalazione, int IDUtente) {
		System.out.println("Codice: " + CodiceSegnalazione + "IDUtente: " + IDUtente);
		/*SessionFactory sd = new Configuration().configure().buildSessionFactory();
		Session sessione = sd.openSession();
		sessione.beginTransaction();*/

		java.util.Date utilDate = new java.util.Date();
        java.sql.Date Data = new java.sql.Date(utilDate.getTime());
        Intervento i = new Intervento();
        
        i.setSegnalazione(CodiceSegnalazione);
        i.setResponsabile(IDUtente);
        i.setDataInizio(Data);
        
        Archivio.inserisciIntervento(i);
        //sessione.save(i);
 
        //sessione.getTransaction().commit();
    }
}
