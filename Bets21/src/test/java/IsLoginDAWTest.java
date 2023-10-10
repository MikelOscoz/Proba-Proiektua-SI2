import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import dataAccess.DataAccess;
import domain.Erabiltzaile;
import domain.Mugimendua;
import domain.User;
//import test.dataAccess.TestDataAccess;

public class IsLoginDAWTest {

	static DataAccess sut = new DataAccess();
	//static TestDataAccess TDA = new TestDataAccess();

	private LinkedList<Mugimendua> lista = new LinkedList<Mugimendua>();
	private User u = new User("josefina", "josefina", "pepeAb", "123", 20, "pepe@gmail.com", 0, lista);

	@Before
  	public void hasieratu() {
		sut.open(false);
		sut.storeUser(u);
		sut.close();
	}

	@Test
	public void test1() {
		String log = null;
		String pass = "123";
		sut.open(false);
		Erabiltzaile Emaitza = (Erabiltzaile) sut.isLogin(log, pass);
		sut.close();
		assertEquals(null, Emaitza);
	}

	@Test
	public void test2() {
		String log = "josefina";
		String pass = null;
		sut.open(false);
		Erabiltzaile Emaitza = (Erabiltzaile) sut.isLogin(log, pass);
		sut.close();
		assertEquals(null, Emaitza);
	}

	@Test
	public void test3() {
		String log = "josef";
		String pass = "123";
		sut.open(false);
		Erabiltzaile Emaitza = (Erabiltzaile) sut.isLogin(log, pass);
		sut.close();
		assertEquals(null, Emaitza);
	}

	@Test
	public void test4() {
		String log = "josefina";
		String pass = "123";
		sut.open(false);
		Erabiltzaile Emaitza = (Erabiltzaile) sut.isLogin(log, pass);
		sut.close();
		assertEquals(u.getUserName(), Emaitza.getUserName());
	}

	@Test
	public void test5() {
		String log = "josefina";
		String pass = "456";
		sut.open(false);
		Erabiltzaile Emaitza = (Erabiltzaile) sut.isLogin(log, pass);
		assertEquals(null, Emaitza);
		sut.close();
	}

	@After
  	public void desegin() {
		sut.open(false);
		sut.removeUser(u);
		sut.close();
	}
}
