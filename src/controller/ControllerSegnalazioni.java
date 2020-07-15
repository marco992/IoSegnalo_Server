package controller;

import java.util.ArrayList;
import java.util.List;

import entity.Sistema;

public class ControllerSegnalazioni {

	public ControllerSegnalazioni() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void nuovaSegnalazione(int Tipologia, String Descrizione, int IDCittadino, Double Latitudine, Double Longitudine, String Recapito) {
		//effettua controllo correttezza dati
		//inserisci segnalazione richiamando funzione in Sistema
		Sistema.inserisciSegnalazione(Tipologia, Descrizione, IDCittadino, Latitudine, Longitudine, Recapito);
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
			if(Segnalazioni.get(i).getIDcittadino()==IdUtente) {
				//System.out.println("Sono entrato! ID utente1"+IdUtente + "ID utente 2:"+Segnalazioni.get(i).getId());
				out.add(Segnalazioni.get(i).getId());
				out.add(Segnalazioni.get(i).getLatitudine());
				out.add(Segnalazioni.get(i).getLongitudine());
				out.add(Segnalazioni.get(i).getDataModifica());
				out.add(Segnalazioni.get(i).getStato());
			}
		}
		if (out.size()>0)
			return out;
		else
			return null;
	}
	
	public List<entity.Segnalazione> getListaSegnalazioniCompleta() {
		
		List<entity.Segnalazione> Segnalazioni = Sistema.getListaSegnalazioni();
		ArrayList out = new ArrayList();
		int i;
		for(i=0;i<Segnalazioni.size();i++)
		{
				out.add(Segnalazioni.get(i).getId());
				out.add(Segnalazioni.get(i).getLatitudine());
				out.add(Segnalazioni.get(i).getLongitudine());
				out.add(Segnalazioni.get(i).getDataModifica());
				out.add(Segnalazioni.get(i).getStato());
		}
		if (out.size()>0)
			return out;
		else
			return null;
	}
	
}
