import static org.junit.Assert.*;
 
import java.util.LinkedList;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import dataAccess.DataAccess;
import domain.Mugimendua;
import domain.User;
import businessLogic.BLFacadeImplementation;
import businessLogic.BLFacade;
 
@RunWith (MockitoJUnitRunner.class)
public class IsLoginBLBMTest {
  	
  	DataAccess DA = Mockito.mock(DataAccess.class);
  	
  	@InjectMocks
  	 BLFacade sut=new BLFacadeImplementation(DA);
  	
  	private LinkedList<Mugimendua> lista = new LinkedList<Mugimendua>();
  	private User u = new User("josefina","josefina","pepeAb", "123", 20, "pepe@gmail.com", 0, lista);
 
  	@Test
  	public void test1() {
        	String log = null;
        	String pass = "123";
        	Mockito.doReturn(null).when(DA).isLogin(log, pass);
        	User Emaitza = (User) sut.isLogin(log, pass);
        	assertEquals(null, Emaitza);
  	}
  	
  	@Test
  	public void test2() {
        	String log = "josefina";
        	String pass = null;
        	Mockito.doReturn(null).when(DA).isLogin(log, pass);
        	User Emaitza = (User) sut.isLogin(log, pass);
        	assertEquals(null,Emaitza);
  	}
 
  	@Test
  	public void test3() {
        	String log = "josef";
        	String pass = "123";
        	Mockito.doReturn(null).when(DA).isLogin(log, pass);
        	User Emaitza = (User) sut.isLogin(log, pass);
        	assertEquals(null, Emaitza);
  	}
  	
  	@Test
  	public void test4() {
        	String log = "josefina";
        	String pass = "1456";
        	Mockito.doReturn(null).when(DA).isLogin(log, pass);
        	User Emaitza = (User) sut.isLogin(log, pass);
        	assertEquals(null,Emaitza);
  	}
  	
  	@Test
  	public void test5() {
        	String log = "josefina";
        	String pass = "123";
        	Mockito.doReturn(u).when(DA).isLogin(log, pass);
        	User Emaitza = (User) sut.isLogin(log, pass);
        	assertEquals(u,Emaitza);
  	}
}
