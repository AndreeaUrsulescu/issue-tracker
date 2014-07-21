package internship.issuetracker.repository;

import internship.issuetracker.entities.User;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserRepositoryTest {

	UserRepository userRepository;
	
	@SuppressWarnings("resource")
	@Before
	public void setUp(){
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/h2.xml",
				"config/application-context.xml");
		userRepository = context.getBean(UserRepository.class);
	}
	
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
