package internship.issuetracker.repository;

import internship.issuetracker.entities.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/datasource/h2.xml",
	"classpath:config/application-context.xml",
	"classpath:config/Spring-Mail.xml" })
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	User user;
	User user2;
	@Test
	public void testCreate() {
		if(!userRepository.exists("boobar")){
			user2=new User();
			user2.setUserName("boobar");
			user2.setEmail("notjust@mail.a");
			user2.setPassword("parolascr");
			userRepository.create(user2);
		}
		assert(userRepository.exists("boobar") && user.equals(userRepository.findUserByUserName("boobar")));
	}
	
	public void testUpdate() {
		user2.setUserName("notboobar");
		assert(userRepository.exists("notboobar") && 
				!userRepository.exists("boobar") &&
				user.equals(userRepository.findUserByUserName("notboobar")) );
	}
	
	@Test
	public void testExists() {
		
		if(!userRepository.exists("foobar")){
			user=new User();
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
	
	@Test
	public void testFindUserByUserName() {
		assert(user.equals(userRepository.findUserByUserName("foobar")));
	}
	

	
}
