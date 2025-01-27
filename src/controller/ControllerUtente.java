package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Archivio;
import entity.Utente;

public class ControllerUtente {
	
	public ControllerUtente() {
		super();
	}
	
	public List<Integer> Connetti(String username, String password) {
		entity.Archivio sys = new Archivio();
		List<Utente> utenti = sys.getListaUtenti();
		//controllo l'esistenza dell'utente e mi ritornerÓ il tipo di utente connesso
		int tipo=-1;
		int i;
		List<Integer> risposta = new ArrayList<Integer>();
		for(i=0; i<utenti.size(); i++){
			System.out.println("UserName:"+utenti.get(i).getUsername()+", ID:"+utenti.get(i).getId());
			if((utenti.get(i).getUsername().equals(username)) && (utenti.get(i).getPassword().equals(password)))
				{
				risposta = new ArrayList<Integer>();
				risposta.add(utenti.get(i).getTipo());
				risposta.add(utenti.get(i).getId());
				return risposta;
				}
		}	
		risposta.add(-1);
		risposta.add(-1);
		return risposta;
	}
	
}
