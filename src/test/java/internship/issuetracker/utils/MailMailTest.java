package internship.issuetracker.utils;

import internship.issuetracker.entities.Email;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml",
	"classpath:config/application-context.xml",
	"classpath:config/Spring-Mail.xml" })


//TODO: Needs to be rewritten(change the email adress from endava internship 2014)
@Ignore
public class MailMailTest {

    @Autowired
    private MailMail mail;

    @Test
    public void test() {
	Email email = new Email();
	email.setTo("dummy@mailinator.com");
	email.setSubject("Testing123");
	email.setContent("Content test");
	mail.sendMail(email);

    }

}
