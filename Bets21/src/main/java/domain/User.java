package domain;

import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
@Entity
public class User extends Erabiltzaile {

	private double dirua;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private LinkedList<Mugimendua> mugimenduak;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Set<User> jarraitzaileak;
	
	public User(String pUserName, String pIzena, String pAbizena, String pPasahitza, int pAdina, String pPostaElektro,int dirua,LinkedList<Mugimendua> mugimenduak) {
		super(pUserName, pIzena, pAbizena, pPasahitza, pAdina, pPostaElektro);
		this.dirua=dirua;
		this.mugimenduak=mugimenduak;
	}
	
	public User() {
		super();
	}

	public double getDirua() {
		return dirua;
	}

	public void setDirua(double dirua) {
		this.dirua = dirua;
	}

	public LinkedList<Mugimendua> getMugimenduak() {
		return mugimenduak;
	}

	public void setMugimenduak(LinkedList<Mugimendua> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}
	
	public Set<User> getJarraitzaileak() {
		return jarraitzaileak;
	}

	public void setJarraitzaileak(Set<User> jarraitzaileak) {
		this.jarraitzaileak = jarraitzaileak;
	}

	/*
	public String printMugimenduak() {
		String output="";
		if(mugimenduak!=null)
		for(Mugimendua m:mugimenduak) {
			output+=m.toString()+"\n";
		}else {
			output="ez dago mugimendurik";
		}
		return output;
		
	}
	*/
	public String printMugimenduak() {
		String output = "";
		if (mugimenduak != null) {
			for (int i = mugimenduak.size() - 1; i>-1;i--) {
				output += mugimenduak.get(i).toString() + "\n";
			}
		} else {
			output = "ez dago mugimendurik";
		}
		return output;
	}
	
	public Mugimendua addMugimenduak(Mugimendua mugi) {
		//Mugimendua mugi = new Mugimendua(diruKop, gertaera,  quest,  s);
		mugimenduak.add(mugi);
		return mugi;
	}
	
	public void jarraitzaileaGehitu(User u) {
		if(this.jarraitzaileak!=null) {
			this.jarraitzaileak.add(u);
		}else {
			this.jarraitzaileak=new HashSet<User>();
			this.jarraitzaileak.add(u);
		}
	}
	
}
