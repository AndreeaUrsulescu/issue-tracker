package internship.issuetracker.repository;

import static org.junit.Assert.fail;
import internship.issuetracker.entities.User;
import internship.issuetracker.repository.UserRepository;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserRepositoryTest {

	
	@Test
	public void testExists() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
				"config/application-context.xml");
		UserRepository userRepository = context.getBean(UserRepository.class);
		assert(userRepository.exists("foobar"));
	}
	
	@Test
	public void testMatchPassword() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
				"config/application-context.xml");
		UserRepository userRepository = context.getBean(UserRepository.class);
		assert(userRepository.matchPassword("foobar","parola") && !userRepository.matchPassword("foobarx","parolax") );
	}
	
}
