package businessLogic;

import java.util.ArrayList;
//holas
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
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

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			dbManager = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
		} else
			dbManager = new DataAccess();
		dbManager.close();

	}

	public BLFacadeImplementation(DataAccess da) {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager = da;
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

		dbManager.close();

		return qry;
	};

	@WebMethod
	public Quote createQuote(String quote, Question question, double mult) {
		dbManager.open(false);
		Quote q = null;

		q = dbManager.createQuote(quote, question, mult);

		dbManager.close();
		return q;
	}

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	public void close() {
		DataAccess dB4oManager = new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD() {
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@WebMethod
	public Erabiltzaile isLogin(String log, String pass) {
		dbManager.open(false);
		Erabiltzaile emaitza = dbManager.isLogin(log, pass);
		dbManager.close();
		return emaitza;

	}

	@WebMethod
	public boolean storeUser(User u) {
		dbManager.open(false);
		boolean emaitza = dbManager.storeUser(u);
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public boolean isRegistered(String log) {
		dbManager.open(false);
		boolean emaitza = dbManager.isRegistered(log);
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public boolean eventExists(Event ev) {
		dbManager.open(false);
		boolean emaitza = dbManager.eventExists(ev);
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public boolean storeEvent(Event event) {
		dbManager.open(false);
		boolean emaitza = dbManager.storeEvent(event);
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public boolean storeQuote(Quote q) {
		dbManager.open(false);
		boolean emaitza = dbManager.storeQuote(q);
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public boolean updateMoney(User user, double kantitatea) {
		dbManager.open(false);
		boolean emaitza = dbManager.updateMoney(user, kantitatea);
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public boolean emaitzaBanatu(Quote quote) {
		System.out.println("#0");
		boolean emaitza = false;
		dbManager.open(false);
		Question galdera = quote.getQuestion();
		Vector<Apostatu> ap = new Vector<Apostatu>();
		for (Integer inte : galdera.getApostatu()) {
			ap.add(dbManager.getApostua(inte));
		}
		for (Quote q : galdera.getQuoteList()) {
			for (Apostatu apostua : ap) {

				System.out.println(ap.toString());

				int i = 0;
				boolean aurkitua = false;
				while (i < apostua.getQuote().size() && !aurkitua) {

					System.out.println("#1");
					//apostua.getQuote().get(i).getQuoteNumber() == (q.getQuoteNumber())
					if (apostua.getQuote().get(i).getQuote().equals(q.getQuote())) {

						System.out.println("#2");

						aurkitua = true;
						if (q.equals(quote)) {

							System.out.println("#3");

							Apostatu a = dbManager.eguneratuApostua(apostua, true);
							if (a.getKop() <= 0 && apostua.isIrabazi()) {
								emaitza = dbManager.betWinner(a.getUser(), a);
								Mugimendua mugi = new Mugimendua(0, null, null,
										"Apostua irabazi duzu!! Orain diru gehiago duzu zure kontu korrontean.");
								dbManager.storeMugimenduak(apostua.getUser(), mugi);
								emaitza = true;
							}
						} else {

							System.out.println("#4");

							if (apostua.isIrabazi()) {
								Apostatu a = dbManager.eguneratuApostua(apostua, false);
								Mugimendua mugi = new Mugimendua(0, null, null, "Apostua ez duzu irabazi :(");
								dbManager.storeMugimenduak(a.getUser(), mugi);
								emaitza = true;
							}
						}
					}
					i++;
				}
			}
			emaitza = true;
		}
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public User storeMugimenduak(User u, Mugimendua mugi) {
		dbManager.open(false);
		User user = null;
		user = dbManager.storeMugimenduak(u, mugi);
		dbManager.close();
		return user;
	}

	@WebMethod
	public User getUser(User u) {
		dbManager.open(false);
		User user = null;
		user = dbManager.getUser(u);
		dbManager.close();
		System.out.println(user.printMugimenduak());
		return user;
	}

	@WebMethod
	public boolean storeApostua(Apostatu ap, Vector<Question> q) {
		dbManager.open(false);
		boolean emaitza = false;
		emaitza = dbManager.storeApostua(ap);
		Apostatu apostua = dbManager.lortuApostua(ap);
		for (Question quest : q) {
			int i = 0;
			boolean aurkitua = false;
			while (i < apostua.getQuote().size() && !aurkitua) {
				if (apostua.getQuote().get(i).getQuestion().equals(quest)) {
					aurkitua = true;

					System.out.println(apostua.getApostuNumber());
					emaitza = dbManager.addApostua(apostua.getApostuNumber(), quest);
				}
				i++;
			}
			emaitza = true;
		}

		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public boolean removeMoney(User user, double money) {
		dbManager.open(false);
		boolean emaitza = dbManager.removeMoney(user, money);
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public boolean storeEmaitza(Question quest, Quote q) {
		dbManager.open(false);
		boolean emaitza = dbManager.storeEmaitza(quest, q);
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public String isEmaitza(Question q) {
		dbManager.open(false);
		String emaitza = dbManager.isEmaitza(q);
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public boolean mezuaBidali(Mezua m, Erabiltzaile jasotzailea) {
		dbManager.open(false);
		boolean emaitza = dbManager.mezuaBidali(m, jasotzailea);
		dbManager.close();
		return emaitza;
	}

	public Erabiltzaile getUserWithLog(String log) {
		dbManager.open(false);
		Erabiltzaile era = null;
		era = dbManager.getUserWithLog(log);
		dbManager.close();
		return era;
	}

	public Erabiltzaile getErabiltzaile(Erabiltzaile e) {
		dbManager.open(false);
		Erabiltzaile era = null;
		era = dbManager.getErabiltzaile(e);
		dbManager.close();
		return era;
	}

	@WebMethod
	public Event getEvent(Event ev) {
		dbManager.open(false);
		Event emaitza = dbManager.getEvent(ev);
		dbManager.close();
		return emaitza;
	}

	// #############################BEGIN##################################
	@WebMethod
	public boolean deleteEvent(Event event) {
		dbManager.open(false);
		Vector<Question> galderak = event.getQuestions();
		for (Question q : galderak) {
			if (dbManager.isEmaitza(q) == null) {
				for (Integer i : q.getApostatu()) {
					Apostatu ap = dbManager.getApostua(i);

					if (ap.isIrabazi()) {

						dbManager.EguneratuKop(ap);
						Apostatu apos = dbManager.eguneratuApostuaGertaeraEzabatzerakoan(ap, q);

						if (!apos.isEmaitza1()) {
							if (apos.getKop() <= 0) {
								dbManager.returnMoney(apos.getUser(), apos);
								Mugimendua mugi = new Mugimendua(0, event, null,
										" : Gertaera hori ezabatu da eta dirua itzuli zaizu.");
								dbManager.storeMugimenduak(ap.getUser(), mugi);
								dbManager.removeApostuaFromQuestion(ap.getApostuNumber(), q);
								dbManager.eguneratuApostua(ap, false);
							}
						}
						else {
							if (apos.getKop() <= 0) {
								dbManager.betWinner(apos.getUser(), apos);
								Mugimendua mugi = new Mugimendua(0, null, null,
										"Apostua irabazi duzu!! Orain diru gehiago duzu zure kontu korrontean.(quotaren bat galduta)");
								dbManager.storeMugimenduak(apos.getUser(), mugi);
							}
						}
					}
				}
				for (Quote k : q.getQuoteList()) {
					dbManager.deleteQuote(k);
				}
			}
			dbManager.deleteQuestion(q);
		}
		boolean emaitza = dbManager.deleteEvent(event);
		dbManager.close();
		return emaitza;
	}
	// #############################END##################################

	@WebMethod
	public boolean pasahitzaAldatu(User u, String pass) {
		dbManager.open(false);
		boolean emaitza = dbManager.pasahitzaAldatu(u, pass);
		dbManager.close();
		return emaitza;
	}

	@WebMethod
	public boolean erabiltzaileaJarraitu(User target, User jarraitzaile) {
		dbManager.open(false);
		boolean emaitza = dbManager.erabiltzaileaJarraitu(target, jarraitzaile);
		dbManager.close();
		return emaitza;
	}
}
