package internship.issuetracker.utils;

import internship.issuetracker.entities.Email;
import internship.issuetracker.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailMail {
    @Autowired
    EmailService emailService;

    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(Email email) {

        SimpleMailMessage message = new SimpleMailMessage();
        email.setFrom(ApplicationParameters.EMAIL_ADDRESS);

        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getContent());
        emailService.saveEmail(email);
        mailSender.send(message);

    }
}