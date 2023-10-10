import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Buzoia;
import domain.Erabiltzaile;
import domain.Event;
import domain.Mezua;
import domain.Mugimendua;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class MezuaBidaliDAWTest {

	// sut:system under test
	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();
	
	LinkedList<Mugimendua> lista = new LinkedList<Mugimendua>();
	Mugimendua mug = new Mugimendua(100, null, null, "abibidea");
	User user1 = new User("user", "izena", "abizena", "user", 26, "asd@gmail.com", 0, lista);
	Buzoia buzoi1 = new Buzoia(new LinkedList<Mezua>(), user1);

	@Before
	public void setUp() {	
		user1.setBuzoia(buzoi1);
	}

	@Test
	public void test1() {
		try {
			boolean emaitza = false;
			sut.open(false);
			emaitza = sut.mezuaBidali(null, user1);
			sut.close();
			assertEquals(emaitza, false);
		} catch (Exception e) {
			fail("Ezin da hemendik joan");
		}
	}

	@Test
	public void test2() {
		Date d = new Date();
		Mezua m = new Mezua("","",d,buzoi1);
		try {
			sut.open(false);
			sut.mezuaBidali(m, null);
			sut.close();
			fail("Ez, exepzioa altxatu behar du");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void test3() {
		Date d = new Date();
		Mezua m = new Mezua("","",d,buzoi1);
		try {
			boolean emaitza = false;
			sut.open(false);
			emaitza = sut.mezuaBidali(m, user1);
			sut.close();
			assertEquals(emaitza, true);
		} catch (Exception e) {
			fail("Ezin da hemendik joan");
		}
	}
	
	@Test
	public void test4() {
		Date d = new Date();
		Mezua m = new Mezua("","",d,buzoi1);
		User user2 = new User("testUser", "test", "java", "test", 26, "test@gmail.com", 0, lista);
		try {
			boolean emaitza = false;
			sut.open(false);
			emaitza = sut.mezuaBidali(m, user2);
			sut.close();
			assertEquals(emaitza, false);
		} catch (Exception e) {
			fail("Ezin da hemendik joan");
		}
	}

}
