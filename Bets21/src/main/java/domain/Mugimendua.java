package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Mugimendua {
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer mugiNumber;
	private double diruKop;
	private Event gertaera;
	private Question galdera;
	private String deskribapena;
	
	
	public Mugimendua(Integer mugiNumber, double diruKop, Event gertaera, Question galdera, String describapena) {
		this.diruKop=diruKop;
		this.gertaera=gertaera;
		this.galdera=galdera;
		this.deskribapena=describapena;
		this.mugiNumber=mugiNumber;
	}

	public Mugimendua(double diruKop, Event gertaera, Question galdera, String describapena) {
		this.diruKop=diruKop;
		this.gertaera=gertaera;
		this.galdera=galdera;
		this.deskribapena=describapena;
	}
	
	
	
	public Integer getMugiNumber() {
		return mugiNumber;
	}

	public void setMugiNumber(Integer mugiNumber) {
		this.mugiNumber = mugiNumber;
	}

	public String getDeskribapena() {
		return deskribapena;
	}

	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}

	public double getDiruKop() {
		return diruKop;
	}


	public void setDiruKop(double diruKop) {
		this.diruKop = diruKop;
	}


	public Event getGertaera() {
		return gertaera;
	}


	public void setGertaera(Event gertaera) {
		this.gertaera = gertaera;
	}


	public Question getGaldera() {
		return galdera;
	}


	public void setGaldera(Question galdera) {
		this.galdera = galdera;
	}
	
	public String toString() {
		if (gertaera!=null && galdera!=null && diruKop>1)
			return "Kantitatea: "+diruKop+" Gertaeran: "+gertaera.getDescription()+
				" Galderan: "+galdera.getQuestion()+"## "+ deskribapena;
		else
			return deskribapena;
	}
}
