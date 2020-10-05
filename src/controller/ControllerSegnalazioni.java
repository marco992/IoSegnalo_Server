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
		//inserisci segnalazione richiamando funzione in Sistema
		Sistema.inserisciSegnalazione(Tipologia, Descrizione, IDCittadino, Latitudine, Longitudine, Recapito);
	}
	
	public void eliminaSegnalazione(int IdSegnalazione) {
		
	}
	
	public void modificaStatoSegnalazione(int CodiceSegnalazione, int CodiceUtente, int NuovoStato) {
		List<entity.Segnalazione> Segnalazioni = Sistema.getListaSegnalazioni();
		java.util.Date utilDate = new java.util.Date();
		ArrayList out = new ArrayList();
		int i;
		for(i=0;i<Segnalazioni.size();i++)
		{
			if(Segnalazioni.get(i).getId()==CodiceSegnalazione) {
				entity.Segnalazione temp = Segnalazioni.get(i);
		        java.sql.Date Data = new java.sql.Date(utilDate.getTime());
				temp.setDataModifica(Data);
				temp.setStato(NuovoStato);
				Sistema.updateSegnalazione(temp);
			}
		}
	}
	
	public ArrayList richiediListaSegnalazioniUtente(int IdUtente) {
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
	
	public ArrayList richiediListaSegnalazionibyStato(int IdUtente, int Stato) {
		List<entity.Segnalazione> Segnalazioni = Sistema.getListaSegnalazioni();
		ArrayList out = new ArrayList();
		int i;
		for(i=0;i<Segnalazioni.size();i++)
		{
			if(Segnalazioni.get(i).getStato()==Stato) {
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
	
	public List<entity.Segnalazione> richiediListaSegnalazioni() {
		
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
