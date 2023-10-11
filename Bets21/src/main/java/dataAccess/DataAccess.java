package dataAccess;

//hello
//Aldaketa proba egiteko
//Aldaketa sonarcloud
//Aldaketa sonarcloud github-etik
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess() {
		this(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}

			LinkedList<Mugimendua> lista = new LinkedList<Mugimendua>();
			Mugimendua mug = new Mugimendua(100, ev1, q1, "abibidea");
			lista.add(mug);

			User user1 = new User("user", "izena", "abizena", "user", 26, "asd@gmail.com", 0, lista);
			//Admin user2 = new Admin("admin", "izena", "abizena", "admin", 26, "asd@gmail.com");
			//Langile user3 = new Langile("langile", "izena", "abizena", "langile", 26, "asd@gmail.com");
			Buzoia buzoi1 = new Buzoia(new LinkedList<Mezua>(), user1);
			//Buzoia buzoi2 = new Buzoia(new LinkedList<Mezua>(), user2);
			//Buzoia buzoi3 = new Buzoia(new LinkedList<Mezua>(), user3);
			user1.setBuzoia(buzoi1);
			//user2.setBuzoia(buzoi2);
			//user3.setBuzoia(buzoi3);
			db.persist(user1);
			//db.persist(user2);
			//db.persist(user3);

			System.out.println(user1.printMugimenduak());

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Erabiltzaile isLogin(String log, String pass) {
		if (log != null && pass != null) {
			Erabiltzaile user = db.find(Erabiltzaile.class, log);
			if (user == null)
				return null;
			if (!user.getPasahitza().equals(pass))
				return null;
			return user;
		}
		return null;
	}

	public boolean storeUser(User u) {
		boolean ok = false;
		try {
			db.getTransaction().begin();
			db.persist(u);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean storeQuote(Quote q) {
		boolean ok = false;
		try {
			db.getTransaction().begin();
			db.persist(q);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean isRegistered(String log) {
		if (log != null) {
			boolean registered = false;
			try {
				User u = db.find(User.class, log);
				if (u != null) {
					registered = true;
				} else {
					registered = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return registered;
		}
		return false;
	}

	public boolean eventExists(Event event) {
		boolean exists = true;
		try {
			Event ev = db.find(Event.class, event);
			if (ev != null) {
				exists = true;
			} else {
				exists = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

	public boolean storeEvent(Event event) {
		boolean ok = false;
		try {
			db.getTransaction().begin();
			db.persist(event);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */

	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	public Quote createQuote(String quote, Question question, double mult) {
		System.out.println(">> DataAccess: createQote=> Quote= " + quote + " question= " + question);
		Question quest = db.find(Question.class, question.getQuestionNumber());
		Quote q;
		Integer zenb = -1;

		if (quest.doesQuoteExist(quote))
			q = new Quote(zenb, "", null, 0);
		else {
			db.getTransaction().begin();
			q = quest.addQuote(quote, mult);
			db.persist(q);
			db.getTransaction().commit();
		}
		return q;
	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}

		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);

	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean deleteQuote(Quote quote) {
		boolean ok = false;
		try {
			Quote k = db.find(Quote.class, quote);
			if (k != null) {
				db.getTransaction().begin();
				db.remove(k);
				db.getTransaction().commit();
			}
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean returnMoney(User user, Event event) {
		boolean ok = false;
		double dirua = 0;
		try {
			User u = db.find(User.class, user);
			db.getTransaction().begin();
			for (Mugimendua m : u.getMugimenduak()) {
				if (m.getGertaera() != null) {
					if (m.getGertaera().getEventNumber() == event.getEventNumber()) {
						dirua = m.getDiruKop();
					}
				}
			}
			u.setDirua(u.getDirua() + dirua);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean deleteQuestion(Question question) {
		boolean ok = false;
		try {
			Question qu = db.find(Question.class, question);
			db.getTransaction().begin();
			db.remove(qu);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean deleteEvent(Event event) {
		boolean ok = false;
		try {
			Event ev = db.find(Event.class, event);
			db.getTransaction().begin();
			db.remove(ev);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}
	
	public boolean removeUser(User u) {
		boolean ok = false;
		try {
			User ev = db.find(User.class, u);
			db.getTransaction().begin();
			db.remove(ev);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean updateMoney(User user, double kantitatea) {
		boolean ok = false;
		try {
			User u = db.find(User.class, user);
			db.getTransaction().begin();
			u.setDirua(u.getDirua() + kantitatea);
			db.persist(u);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean betWinner(User user, Apostatu ap) {
		System.out.println(">> DataAccess: betWinner=> User= " + user + " Apostua= " + ap);
		boolean ok = false;
		try {
			User u = db.find(User.class, user);
			db.getTransaction().begin();
			u.setDirua(u.getDirua() + ap.getDirua() * ap.getMultiply());
			db.persist(u);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean removeMoney(User user, double money) {
		boolean ok = false;
		try {
			User u = db.find(User.class, user);
			db.getTransaction().begin();
			u.setDirua(u.getDirua() - money);
			db.persist(u);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public User storeMugimenduak(User u, Mugimendua mugi) {
		System.out.println(">> DataAccess: storeMugimenduak=> User= " + u + " Mugimenduak= " + mugi);
		User user = db.find(User.class, u);
		db.getTransaction().begin();
		// user.addMugimenduak(mugi.getDiruKop(), mugi.getGertaera(), mugi.getGaldera(),
		// mugi.getDeskribapena());
		user.addMugimenduak(mugi);
		db.persist(user);
		db.getTransaction().commit();
		return user;
	}

	public User getUser(User u) {
		System.out.println(">> DataAccess: getMugimenduak=> User= " + u);
		User user = db.find(User.class, u);
		return user;
	}

	public Erabiltzaile getErabiltzaile(Erabiltzaile e) {
		// System.out.println(">> DataAccess: getMugimenduak=> User= " + u);
		Erabiltzaile erabiltzaile = db.find(Erabiltzaile.class, e);
		return erabiltzaile;
	}

	public boolean addApostua(Integer ap, Question q) {
		System.out.println(">> DataAccess: addApostua=> Question= " + q + " Apostua= " + ap);
		Question quest = db.find(Question.class, q);
		boolean ok = false;
		try {
			db.getTransaction().begin();
			quest.addApostua(ap);
			db.persist(quest);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean storeApostua(Apostatu ap) {
		System.out.println(">> DataAccess: storeApostua=>  Apostua= " + ap);// +" Quote= "+ quot);
		// Apostatu apostua = db.find(Apostatu.class, ap.getApostuNumber());
		boolean ok = false;
		try {
			db.getTransaction().begin();
			// apostua.addQuote(quot);
			// db.persist(apostua);
			db.persist(ap);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean removeApostua(Apostatu ap) {
		System.out.println(">> DataAccess: removeApostua=> Apostua= " + ap);
		boolean ok = false;
		try {
			db.getTransaction().begin();
			db.remove(ap);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public Apostatu lortuApostua(Apostatu ap) {
		System.out.println(">> DataAccess: lortuApostua=> Apostua= " + ap);
		Apostatu apostua = db.find(Apostatu.class, ap);
		return apostua;
	}

	public Apostatu getApostua(Integer q) {
		System.out.println(">> DataAccess: getApostua=> Integer= " + q);
		TypedQuery<Apostatu> query = db.createQuery("SELECT ev FROM Apostatu ev WHERE apostuNumber=?1", Apostatu.class);
		query.setParameter(1, q);
		Apostatu ap = query.getSingleResult();
		System.out.println(">> ####" + ap);
		return ap;
	}

	public boolean removeApostuaFromQuestion(Integer i, Question q) {
		System.out.println(">> DataAccess: remove=> Integer= " + i + " Question=>" + q);
		Question quest = db.find(Question.class, q);
		boolean ok = false;
		try {
			db.getTransaction().begin();
			quest.removeApostua(i);
			db.persist(quest);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean storeEmaitza(Question quest, Quote q) {
		System.out.println(">> DataAccess: storeEmaitza=> Question= " + quest + " Quote= " + q);
		Question question = db.find(Question.class, quest);
		boolean ok = false;
		try {
			db.getTransaction().begin();
			question.setResult(q.getQuote());
			;
			db.persist(question);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public String isEmaitza(Question q) {
		System.out.println(">> DataAccess: isEmaitza=> Question= " + q);
		Question question = db.find(Question.class, q);
		return question.getResult();
	}

	// #############################BEGIN##################################

	public Apostatu EguneratuKop(Apostatu ap) {
		System.out.println(">> DataAccess: eguneratuApostua=> Apostua= " + ap);
		Apostatu apostua = db.find(Apostatu.class, ap);
		db.getTransaction().begin();
		apostua.eguneratuKop();
		db.persist(apostua);
		db.getTransaction().commit();
		return db.find(Apostatu.class, apostua);
	}

	public Apostatu eguneratuApostua(Apostatu ap, boolean zer) {
		System.out.println(">> DataAccess: eguneratuApostua=> Apostua= " + ap);
		Apostatu apostua = db.find(Apostatu.class, ap);
		db.getTransaction().begin();
		if (zer) {
			apostua.eguneratuKop();
			if (!apostua.isEmaitza1())
				apostua.setEmaitza1(true);
		} else
			apostua.setIrabazi(false);
		db.persist(apostua);
		db.getTransaction().commit();
		return db.find(Apostatu.class, apostua);
	}

	public Apostatu eguneratuApostuaGertaeraEzabatzerakoan(Apostatu ap, Question q) {
		System.out.println(">> DataAccess: eguneratuApostuaEzabatuGertaera=> Apostua= " + ap + " /QUestuion= " + q);
		Apostatu apostua = db.find(Apostatu.class, ap);
		db.getTransaction().begin();
		Quote quot = ap.removeQuote(q);
		if (quot != null)
			ap.eguneratuMultiply(quot.getMultiply(), false);
		db.persist(apostua);
		db.getTransaction().commit();
		return db.find(Apostatu.class, apostua);

	}
	// #############################END###################################

	public boolean mezuaBidali(Mezua m, Erabiltzaile jasotzaile) {
		boolean ok = false;
		if (m == null) {
			return ok;
		}
		try {
			Erabiltzaile jaso = db.find(Erabiltzaile.class, jasotzaile);
			if (jaso != null) {
				db.getTransaction().begin();
				jaso.mezuaJaso(m);
				db.persist(jaso);
				db.getTransaction().commit();
				ok = true;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		return ok;
	}

	public Erabiltzaile getUserWithLog(String log) {
		Erabiltzaile era = null;
		try {
			era = db.find(Erabiltzaile.class, log);
			if (era == null) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return era;
	}

	public Buzoia buzoiaBilatu(String s) {
		System.out.println("buzoia bilatu");
		Buzoia b = db.find(Buzoia.class, s);
		return b;
	}

	public boolean returnMoney(User user, Apostatu ap) {
		boolean ok = false;
		// double dirua = 0;
		try {
			User u = db.find(User.class, user);
			db.getTransaction().begin();
			u.setDirua(u.getDirua() + ap.getDirua());
			db.persist(u);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public Event getEvent(Event ev) {
		System.out.println(">> DataAccess: getEvent=> Event = " + ev);
		Event event = db.find(Event.class, ev);
		return event;
	}

	public boolean pasahitzaAldatu(User u, String pass) {
		boolean ok = false;
		if (u == null || pass == null) {
			return ok;
		}
		try {
			User us = db.find(User.class, u);
			if (us != null) {
				db.getTransaction().begin();
				us.setPasahitza(pass);
				db.persist(us);
				db.getTransaction().commit();
				ok = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

	public boolean erabiltzaileaJarraitu(User target, User jarraitzaile) {
		boolean ok = false;
		try {
			User tar = db.find(User.class, target);
			User jarra = db.find(User.class, jarraitzaile);
			db.getTransaction().begin();
			tar.jarraitzaileaGehitu(jarra);
			db.persist(tar);
			db.getTransaction().commit();
			ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}

}



