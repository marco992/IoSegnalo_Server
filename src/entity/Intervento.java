package entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
//@Table (name="Intervento")
public class Intervento {
	@Id
	private int id;
	private int responsabile;
	private int segnalazione;
	private Date datainizio;
	private Date datafine;
	
	public Intervento() {
		super();
	}
	public Intervento(int Responsabile, int Segnalazione, Date DataInizio, Date DataFine)
	{
		this.responsabile=Responsabile;
		this.segnalazione=Segnalazione;
		this.datainizio=DataInizio;
		this.datafine=DataFine;
	}
	public int getID() {
		return id;
	}
	public int getResponsabile(){
		return responsabile;
	}
	public int getSegnalazione() {
		return segnalazione;
	}
	public Date getDataInizio()
	{
		return datainizio;
	}
	public Date getDataFine() {
		return datafine;
	}
	public void setID(int ID) {
		id=ID;
	}
	public void setResponsabile(int Responsabile){
		responsabile=Responsabile;
	}
	public void setSegnalazione(int Segnalazione) {
		segnalazione=Segnalazione;
	}
	public void setDataInizio(Date DataInizio)
	{
		datainizio=DataInizio;
	}
	public void setDataFine(Date DataFine) {
		datafine=DataFine;
	}
}
