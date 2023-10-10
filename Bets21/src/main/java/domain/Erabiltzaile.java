package domain;

import javax.persistence.*;

@Entity
public  class Erabiltzaile {
	@Id
	private String userName;
	private String izena;
	private String abizena;
	private String pasahitza;
	private int adina;
	private String postaElektro;
	@OneToOne(cascade=CascadeType.PERSIST)
	private Buzoia buzoia;
	
	/**
	 * 0 -> erabiltzaile erregistratua
	 * 1 -> admin
	 * 2 -> langile
	 */
	//private int userType;
	
	public Erabiltzaile (String pUserName, String pIzena, String pAbizena, String pPasahitza, int pAdina, String pPostaElektro) {
		
		this.userName=pUserName;
		this.izena=pIzena;
		this.abizena=pAbizena;
		this.pasahitza=pPasahitza;
		this.adina=pAdina;
		this.postaElektro=pPostaElektro;
		
	}
	
	public Erabiltzaile() {
		
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIzena() {
		return this.izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena() {
		return this.abizena;
	}

	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}

	public String getPasahitza() {
		return this.pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public int getAdina() {
		return this.adina;
	}

	public void setAdina(int adina) {
		this.adina = adina;
	}

	public String getPostaElektro() {
		return this.postaElektro;
	}

	public void setPostaElektro(String postaElektro) {
		this.postaElektro = postaElektro;
	}
	
	public Buzoia getBuzoia() {
		return buzoia;
	}

	public void setBuzoia(Buzoia buzoia) {
		this.buzoia = buzoia;
	}
	
	public void mezuaJaso(Mezua m) {
		this.buzoia.getMezuak().add(m);
	}
	
	public String mezuakIkusi() {
		String output="";
		if(buzoia.getMezuak()!=null)
		for(Mezua m:buzoia.getMezuak()) {
			output+=m.toString()+"\n";
		}else {
			output="ez daude mezurik";
		}
		return output;
	}

}
