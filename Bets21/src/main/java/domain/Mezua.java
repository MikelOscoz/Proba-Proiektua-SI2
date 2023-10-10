package domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Mezua {
	
	private String asuntoa;
	private String mezua;
	private Date data;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Buzoia buzoia;
	
	public Mezua(String asuntoa,String mezua,Date data, Buzoia buzoia) {
		this.asuntoa=asuntoa;
		this.buzoia=buzoia;
		this.data=data;
		this.mezua=mezua;
	}

	public String getAsuntoa() {
		return asuntoa;
	}

	public void setAsuntoa(String asuntoa) {
		this.asuntoa = asuntoa;
	}

	public String getMezua() {
		return mezua;
	}

	public void setMezua(String mezua) {
		this.mezua = mezua;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Buzoia getBuzoia() {
		return buzoia;
	}

	public void setBuzoia(Buzoia buzoia) {
		this.buzoia = buzoia;
	}
	
	public String toString() {
		return "Asuntoa: "+this.asuntoa+"       Mezua: "+this.mezua;
	}

}
