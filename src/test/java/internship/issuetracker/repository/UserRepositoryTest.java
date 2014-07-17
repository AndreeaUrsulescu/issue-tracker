package internship.issuetracker.repository;

import internship.issuetracker.entities.User;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//crw: you may consider using spring-test to remove boiler plate code for context configuration
//crw: you'll have to add spring-test dependency in pom and add the code below to your test class
//crw: @RunWith(SpringJUnit4ClassRunner.class)
//crw: @ContextConfiguration(locations = {"/config/datasource/postgres.xml",
//crw:        "/config/application-context.xml"})
public class UserRepositoryTest {

	UserRepository userRepository;
	
	@Before
	public void setUp(){
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
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
