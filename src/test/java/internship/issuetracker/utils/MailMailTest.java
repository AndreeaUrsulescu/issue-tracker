package internship.issuetracker.utils;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailMailTest {

	@Test
	public void test() {
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("classpath:config/Spring-Mail.xml");
	 
	    	MailMail mm = (MailMail) context.getBean("mailMail");
	        mm.sendMail("Alin.Stirbat@endava.com",
	    		   "Alin.Stirbat@endava.com",
	    		   "Testing123", 
	    		   "Testing only \n\n Hello Spring Email Sender");
	        
	}

}
