package domain;

import java.io.*;
import java.util.ArrayList;
//import java.util.Date;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {
	
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	private String question; 
	private float betMinimum;
	private String result;  
	@XmlIDREF
	private Event event;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Integer> apostatu = new ArrayList<Integer>();

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private Vector<Quote> quoteList = new Vector<Quote>();

	public Question(){
		super();
	}
	
	public Question(Integer queryNumber, String query, float betMinimum, Event event,Vector<Quote> quoteList ) {// ,) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
		this.quoteList= quoteList;
	
	}
	
	public Question(String query, float betMinimum,  Event event) {
		super();
		this.question = query;
		this.betMinimum=betMinimum;
		this.event = event;
	}

	/**
	 * Get the  number of the question
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Set the bet number to a question
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}

	/**
	 * Get the question description of the bet
	 * @return the bet question
	 */

	public String getQuestion() {
		return question;
	}

	/**
	 * Set the question description of the bet 
	 * @param question to be setted
	 */	
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Get the minimun ammount of the bet
	 * @return the minimum bet ammount
	 */
	
	public float getBetMinimum() {
		return betMinimum;
	}

	/**
	 * Get the minimun ammount of the bet
	 * @param  betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}

	/**
	 * Get the result of the  query
	 * @return the the query result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Get the result of the  query
	 * @param result of the query to be setted
	 */
	
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Get the event associated to the bet 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}


	/**
	 * Set the event associated to the bet
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}
	

	public Vector<Quote> getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(Vector<Quote> quoteList) {
		this.quoteList = quoteList;
	}
	//######################

	public String toString(){
		return questionNumber+";"+question+";"+Float.toString(betMinimum);
	}


	public Quote addQuote(String q, double mult) {
		Quote quote= new Quote( q, this,mult);
		quoteList.add(quote);
		return quote;
	}
//######################
	public ArrayList<Integer> getApostatu() {
		return apostatu;
	}

	public void setApostatu(ArrayList<Integer> apostatu) {
		this.apostatu = apostatu;
	}

	public void addApostua(Integer ap) {
		this.apostatu.add(ap);
	}
	
	public boolean removeApostua(Integer i) {
		int j = 0;
		boolean aurkitua = false;
		while (j<apostatu.size() && !aurkitua) {
			if (apostatu.get(j)==i) {
				aurkitua=true;
				apostatu.remove(j);
			}
			j++;
		}
		return aurkitua;	
	}
	
	//##########################
	public boolean doesQuoteExist(String quote) {
		/*
		int i = 0;
		boolean aurkitua = false;
		while (i<this.getQuoteList().size() && !aurkitua){
			if (this.getQuoteList().get(i).getQuote().equals(q.getQuote()))
				aurkitua = true;
			i++;
		}
		return aurkitua;
		*/
		for (Quote q:this.getQuoteList()){
			if (q.getQuote().compareTo(quote)==0)
				return true;
		}
		return false;
	}
}
