package entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;
@Entity
//@Table (name="Segnalazione")
public class Segnalazione {		
		@Id
		private int id;
		private String tipologia;
		private String descrizione;
		private int cittadino;
		private String nota;
		private int stato;
		private Date DataModifica;
		private double latitudine;
		private double longitudine;

				public Segnalazione() {
			super();
			// TODO Auto-generated constructor stub
		}



		public Segnalazione(int id, String tipologia, String descrizione, int iDcittadino, String nota, int stato, Date dataModifica,
				double latitudine, double longitudine) {
			super();
			this.id = id;
			this.tipologia = tipologia;
			this.descrizione = descrizione;
			this.cittadino = iDcittadino;
			this.nota = nota;
			this.stato = stato;
			DataModifica = dataModifica;
			this.latitudine = latitudine;
			this.longitudine = longitudine;
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getTipologia() {
			return tipologia;
		}


		public void setTipologia(String tipologia) {
			this.tipologia = tipologia;
		}


		public String getDescrizione() {
			return descrizione;
		}


		public void setDescrizione(String descrizione) {
			this.descrizione = descrizione;
		}


		public int getIDcittadino() {
			return cittadino;
		}


		public void setIDcittadino(int iDcittadino) {
			cittadino = iDcittadino;
		}


		public int getStato() {
			return stato;
		}


		public void setStato(int stato) {
			this.stato = stato;
		}


		public Date getDataModifica() {
			return DataModifica;
		}


		public void setDataModifica(Date dataModifica) {
			DataModifica = dataModifica;
		}


		public double getLatitudine() {
			return latitudine;
		}


		public void setLatitudine(double latitudine) {
			this.latitudine = latitudine;
		}


		public double getLongitudine() {
			return longitudine;
		}


		public void setLongitudine(double longitudine) {
			this.longitudine = longitudine;
		}

		
}
