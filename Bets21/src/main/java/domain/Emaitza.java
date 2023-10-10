package domain;

import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

//@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Emaitza {

	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer EmaitzaNumber;
	@Id
	private Quote emaitza;
	private Vector<Apostatu> apostua = new Vector<Apostatu>();
	
	public Emaitza( Quote emaitza) {
		super();
		this.emaitza = emaitza;
	}

	public Vector<Apostatu> getApostua() {
		return apostua;
	}

	public void setApostua(Vector<Apostatu> apostua) {
		this.apostua = apostua;
	}

	public Quote getEmaitza() {
		return emaitza;
	}

	public void setEmaitza(Quote emaitza) {
		this.emaitza = emaitza;
	}

	@Override
	public String toString() {
		return "Emaitza [apostua=" + apostua + ", emaitza=" + emaitza + "]";
	}
	
	public void addApostua(Apostatu ap) {
		apostua.add(ap);
	}
}
