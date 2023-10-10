package domain;

import javax.persistence.Entity;

@Entity
public class Langile extends Erabiltzaile {

	public Langile(String pUserName, String pIzena, String pAbizena, String pPasahitza, int pAdina, String pPostaElektro) {
		super(pUserName, pIzena, pAbizena, pPasahitza, pAdina, pPostaElektro);
	}
	
}
