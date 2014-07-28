package internship.issuetracker.repository;

import internship.issuetracker.entities.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml", "classpath:config/application-context.xml" })
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Test
	public void testExists() {
		
		if(!userRepository.exists("foobar")){
			User user=new User();
			user.setUserName("foobar");
			user.setEmail("just@mail.a");
			user.setPassword("parola");
			userRepository.create(user);
		}				
		assert(userRepository.exists("foobar"));
	}
	
	@Test
	public void testMatchPassword() {
		assert(userRepository.matchPassword("foobar","parola") && !userRepository.matchPassword("foobarx","parolax") );
	}
	
}
