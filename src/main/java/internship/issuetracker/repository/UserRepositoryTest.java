package internship.issuetracker.repository;

import static org.junit.Assert.fail;
import internship.issuetracker.entities.User;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserRepositoryTest {

	
	

	@Test
	public void testGetAll() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
				"config/application-context.xml");
		UserRepository userRepository = context.getBean(UserRepository.class);
		int size=userRepository.getAll().size();
		System.out.println(size);
		assert(size>=0);
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
				"config/application-context.xml");
		UserRepository userRepository = context.getBean(UserRepository.class);
		int i=userRepository.getAll().size()+1;
		User user=new User();
		user.setUserName("User"+i);
		user.setEmail("email@1.c");
		user.setPassword("pass");			
		userRepository.create(user);		
		assert(1==1);
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
				"config/application-context.xml");
		UserRepository userRepository = context.getBean(UserRepository.class);
		User user=userRepository.find((long) 1);
		System.out.println(user.getUserName());
		assert(user.getUserName().equals("user1"));
	}

}
