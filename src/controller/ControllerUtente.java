package controller;

import entity.Sistema;

public class ControllerUtente {
	public ControllerUtente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int Connetti(String username, String password) {
		entity.Sistema sys = new Sistema();
		//controllo l'esistenza dell'utente e mi ritornerà il tipo di utente connesso
		int tipo;
		tipo=sys.getTipoUtente(username,password);
		if (tipo==-1)
				{
			//nel caso in cui il tipo restituito da Sistema sia negativo, verrà restituito -1, altrimenti il tipo di utente che si è connesso con successo.
			System.out.println("Utente non trovato oppure password errata!");
			return -1;
				}
		System.out.println("Utente trovato! Username e Password corretta.");
		return tipo;
	}
	
	public int prelevaID(String username)
	{
		entity.Sistema sys = new Sistema();
		//controllo l'esistenza dell'utente e mi ritornerà il tipo di utente connesso
		int id;
		id=sys.getIDUtente(username);
		return id;
	}
	
}
