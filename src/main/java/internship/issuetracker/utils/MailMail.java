package internship.issuetracker.utils;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
 
public class MailMail
{
	private MailSender mailSender;
 
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String to, String subject, String msg) {
 
		SimpleMailMessage message = new SimpleMailMessage();
 
		message.setFrom("Graduates@endava.com");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}
}