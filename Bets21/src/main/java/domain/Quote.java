package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Quote implements Serializable {
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer QuoteNumber;
	private String quote;
	private Question question;
	private double multiply;
	
	public Quote() {
		super();
	}
	public Quote(Integer quoteNumber, String quote,  Question question, double multiply) {
		super();
		QuoteNumber = quoteNumber;
		this.quote = quote;
		this.question = question;
		this.multiply=multiply;
	}
	
	public Quote(String q, Question question, double multiply) {
		super();
		this.quote = q;
		this.question = question;
		this.multiply=multiply;
	}

	

	public double getMultiply() {
		return multiply;
	}
	public void setMultiply(double multiply) {
		this.multiply = multiply;
	}
	public Integer getQuoteNumber() {
		return QuoteNumber;
	}


	public void setQuoteNumber(Integer quoteNumber) {
		QuoteNumber = quoteNumber;
	}


	public String getQuote() {
		return quote;
	}


	public void setQuote(String quote) {
		this.quote = quote;
	}

	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return QuoteNumber + "::" + quote + "." ;
	}

}
