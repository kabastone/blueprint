package lilas.web.outils;

import javax.naming.InitialContext;
import javax.naming.NamingException;


public class JNDIUtils  {
	
	public static Object lookUpEJB(EJBRegistry ejbRegistry){
		
		try {
			InitialContext initialContext = new InitialContext();
			return initialContext.lookup("java:app/LILAS-ejb/"+ejbRegistry.name());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
