package domain;

import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Apostatu {
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer apostuNumber;
	private Event event;
	private double dirua;
	private User user;
	//private Question question;
	private Vector<Quote> quote;
	private boolean Irabazi;
	private double multiply;
	private int kop;
	//#############################BEGIN##################################
	private boolean emaitza1;
	
	public Apostatu(Event event, double dirua, User user,boolean Irabazi, double multiply) { //Question question, boolean Irabazi, double multiply) {// ,Quote quote) {
		super();
		this.event = event;
		this.dirua = dirua;
		this.user = user;
		this.Irabazi = Irabazi;
		this.multiply = multiply;
		this.kop = 0;
		quote = new Vector<Quote>();
		this.emaitza1 = false;
	}
	
	
	public boolean isEmaitza1() {
		return emaitza1;
	}

	public void setEmaitza1(boolean emaitza1) {
		this.emaitza1 = emaitza1;
	}
	//#############################END##################################

	public Integer getApostuNumber() {
		return apostuNumber;
	}

	public void setApostuNumber(Integer apostuNumber) {
		this.apostuNumber = apostuNumber;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public double getDirua() {
		return dirua;
	}

	public void setDirua(double dirua) {
		this.dirua = dirua;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/*
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	*/
	public Vector<Quote> getQuote() {
		return quote;
	}

	
	public void setQuote(Vector<Quote> quote) {
		this.quote = quote;
	}

	public boolean isIrabazi() {
		return Irabazi;
	}

	public void setIrabazi(boolean irabazi) {
		Irabazi = irabazi;
	}

	public double getMultiply() {
		return multiply;
	}

	public void setMultiply(double multiply) {
		this.multiply = multiply;
	}

	public int getKop() {
		return kop;
	}

	public void setKop(int kop) {
		this.kop = kop;
	}

	
	/*
	@Override
	public String toString() {
		return "Apostatu [apostuNumber=" + apostuNumber + ", event=" + event + ", dirua=" + dirua + ", user=" + user
				+ ", question=" + question + ", quote=" + quote + ", Irabazi=" + Irabazi + ", multiply=" + multiply
				+ ", kop=" + kop + "]";
	}
	*/
	
	@Override
	public String toString() {
		return "Apostatu [apostuNumber=" + apostuNumber + ", event=" + event + ", dirua=" + dirua + ", user=" + user
				+ ", quote=" + quote + ", Irabazi=" + Irabazi + ", multiply=" + multiply + ", kop=" + kop + "]";
	}

	public void addQuote(Quote q) {
		quote.add(q);
		this.kop ++;
	}

	public double eguneratuMultiply(double mul, boolean egoera) {
		if (egoera)
			return this.multiply + mul;
		else
			return this.multiply - mul;
	}
	
	public void eguneratuKop() {
		kop--;
	}
	
	//#############################BEGIN##################################
	public Quote removeQuote(Question q) {
		Quote quot = null;
		int i = 0;
		boolean aurkitua = false;
		while(i<q.getQuoteList().size() && !aurkitua) {
			int j = 0;
			while (j<this.getQuote().size() && !aurkitua) {
				if (this.getQuote().get(j).equals(q.getQuoteList().get(i))) {
					aurkitua = true;
					//this.eguneratuKop();
					quot = this.getQuote().get(j);
				}
				j++;
			}
			i++;
		}
		this.getQuote().remove(quot);
		return quot;
	}
	//#############################END##################################

}
