


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Buzoia;
import domain.Event;
import domain.Mezua;
import domain.Mugimendua;
import domain.Question;
import domain.User;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MezuaBidaliBLBMTest {
	
     DataAccess dataAccess=Mockito.mock(DataAccess.class);
     //Event mockedEvent=Mockito.mock(Event.class);
	
	@InjectMocks
	 BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	//sut.createQuestion:  The event has one question with a queryText. 
	
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
		Date d = new Date();
		Mezua m = new Mezua("","",d,buzoi1);
		Mockito.when(dataAccess.mezuaBidali(m, null)).thenThrow(IllegalArgumentException.class);
		try {
			sut.mezuaBidali(m, null);
			fail("Ez, exepzioa altxatu behar du");
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	
	@Test
	public void test2() {
		Mockito.when(dataAccess.mezuaBidali(null, user1)).thenReturn(false);
		try {
			boolean emaitza = false;
			emaitza = sut.mezuaBidali(null, user1);
			assertEquals(emaitza, false);
		} catch (Exception e) {
			
		}
		
	}
	
	@Test 
	public void test3() {
		Date d = new Date();
		Mezua m = new Mezua("","",d,buzoi1);
		User user2 = new User("testUser", "test", "java", "test", 26, "test@gmail.com", 0, lista);
		Mockito.when(dataAccess.mezuaBidali(m, user2)).thenReturn(false);
		try {
			boolean emaitza = false;
			emaitza=sut.mezuaBidali(m, user2);
			assertEquals(emaitza, false);
		} catch (Exception e) {
			fail("Ezin da hemendik joan");
		}
	}
	
	@Test 
	public void test4() {
		Date d = new Date();
		Mezua m = new Mezua("","",d,buzoi1);
		Mockito.when(dataAccess.mezuaBidali(m, user1)).thenReturn(true);
		try {
			boolean emaitza = false;
			emaitza=sut.mezuaBidali(m, user1);
			assertEquals(emaitza, true);
		} catch (Exception e) {
			fail("Ezin da hemendik joan");
		}
	}
	
			
}




