package entity;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Id;
@Entity
//@Table (name="Utente")
@FilterDef(name="FiltroCittadini", parameters={
		@ParamDef( name="tipoUtente", type="integer" )
})
@Filters( {
    @Filter(name="FiltroCittadini", condition=":tipoUtente=tipo")
} )
public class Utente {
	
	public Utente() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	private int id;
	private String username;
	private String password;
	private int tipo;
	public Utente(String username, String password, int tipo) {
		super();
		this.username = username;
		this.password = password;
		this.tipo=tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
