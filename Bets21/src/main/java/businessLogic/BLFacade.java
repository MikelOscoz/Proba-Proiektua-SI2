package businessLogic;

import java.util.Vector;
import java.util.Date;





import domain.Question;
import domain.Quote;
import domain.User;
import domain.Apostatu;
import domain.Erabiltzaile;
import domain.Event;
import domain.Mezua;
import domain.Mugimendua;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	//#################
	@WebMethod Quote createQuote(String quote, Question question,double mult);
	//###################
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	@WebMethod public Erabiltzaile isLogin(String log, String pass);

	@WebMethod public boolean storeUser(User u);
	
	@WebMethod public boolean isRegistered(String log);
	
	@WebMethod public boolean eventExists(Event e);
	
	@WebMethod public boolean storeEvent(Event e);
	
	@WebMethod boolean storeQuote(Quote q);
	
	@WebMethod public boolean updateMoney(User u,double d);
	
	@WebMethod public boolean emaitzaBanatu(Quote quote);
	

	@WebMethod public User storeMugimenduak(User u, Mugimendua mugi);
	
	@WebMethod public User getUser(User u);


	@WebMethod public boolean storeApostua(Apostatu ap, Vector<Question> q);
	
	@WebMethod public boolean removeMoney(User user, double money);
	
	@WebMethod public boolean storeEmaitza(Question quest, Quote q);
	
	@WebMethod public String isEmaitza(Question q);
	
	@WebMethod public boolean mezuaBidali(Mezua m,Erabiltzaile jasotzailea);

	@WebMethod public Erabiltzaile getUserWithLog(String log);
	
	@WebMethod public Erabiltzaile getErabiltzaile(Erabiltzaile e);
	
	@WebMethod public Event getEvent(Event ev);
	
	@WebMethod public boolean deleteEvent(Event e);
	
	@WebMethod public boolean pasahitzaAldatu(User u,String pass);

	@WebMethod public boolean erabiltzaileaJarraitu(User target , User jarraitzaile);

}
