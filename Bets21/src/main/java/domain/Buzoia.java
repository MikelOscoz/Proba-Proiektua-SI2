package domain;

import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Buzoia {
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private LinkedList<Mezua> mezuak;
	
	//private String userName;
	private Erabiltzaile era;
	
	public Buzoia(LinkedList<Mezua> mezuak,Erabiltzaile era) {
		this.mezuak=mezuak;
		this.era=era;
	}
	
	public Buzoia() {
		
	}

	public LinkedList<Mezua> getMezuak() {
		return mezuak;
	}

	public void setMezuak(LinkedList<Mezua> mezuak) {
		this.mezuak = mezuak;
	}

	public Erabiltzaile getEra() {
		return era;
	}

	public void setEra(Erabiltzaile era) {
		this.era = era;
	}

	public void mezuaJaso(Mezua m) {
		this.mezuak.add(m);
	}

	

}
