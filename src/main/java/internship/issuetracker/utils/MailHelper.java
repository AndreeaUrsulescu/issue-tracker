package internship.issuetracker.utils;

import internship.issuetracker.entities.Email;

import org.springframework.beans.factory.annotation.Autowired;

public class MailHelper implements Runnable {

	@Autowired
	private MailMail mailMail;
	private Email email;

	
	public void setUp(Email email){
		this.email=email;
	}
	
	@Override
	public void run() {
		mailMail.sendMail(email);
		
	}

}
