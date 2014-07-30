package internship.issuetracker.utils;

import internship.issuetracker.entities.Email;
import internship.issuetracker.service.EmailService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MailMailTest {


	@Test
	public void test() {
		ApplicationContext context = 
	             new ClassPathXmlApplicationContext("classpath:config/Spring-Mail.xml");
	 
	    	MailMail mm = (MailMail) context.getBean("mailMail");
	    	Email email=new Email();
	    	email.setTo("dummy@mailinator.com");
	    	email.setSubject("Testing123");
	    	email.setContent("Content test");
	        mm.sendMail( email);
	        
	}

}
