package controller;

import java.util.ArrayList;
import java.util.List;

import entity.Sistema;

public class ControllerSegnalazioni {

	private List<entity.Segnalazione> ListaSegnalazioni;

	public ControllerSegnalazioni() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void aggiungiSegnalazione() {
		
	}
	public void eliminaSegnalazione(int IdSegnalazione) {
		
	}
	
	public void modificaSegnalazione(int NuovoStato) {
		
	}
	
	public ArrayList getListaSegnalazioniUtente(int IdUtente) {
		List<entity.Segnalazione> Segnalazioni = Sistema.getListaSegnalazioni();
		ArrayList out = new ArrayList();
		int i;
		for(i=0;i<Segnalazioni.size();i++)
		{
			if(Segnalazioni.get(i).getIDcittadino()==IdUtente)
				System.out.println("Sono entrato! ID utente1"+IdUtente + "ID utente 2:"+Segnalazioni.get(i).getId());

				out.add(Segnalazioni.get(i).getId());
				out.add(Segnalazioni.get(i).getLatitudine());
				out.add(Segnalazioni.get(i).getLongitudine());
				out.add(Segnalazioni.get(i).getDataModifica());
				out.add(Segnalazioni.get(i).getStato());
				System.out.println("Stato: " + Segnalazioni.get(i).getStato());
		}
		if (out.size()>0)
			return out;
		else
			return null;
	}
	
	public List<entity.Segnalazione> getListaSegnalazioniCompleta() {

		return null;
	}
	
}
