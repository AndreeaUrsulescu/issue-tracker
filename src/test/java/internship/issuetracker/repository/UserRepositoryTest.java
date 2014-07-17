package internship.issuetracker.repository;

import static org.junit.Assert.fail;
import internship.issuetracker.entities.User;
import internship.issuetracker.repository.UserRepository;

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
		assert(size>=0);
	}

	@Test
	public void testRemove() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
				"config/application-context.xml");
		UserRepository userRepository = context.getBean(UserRepository.class);
		int expRez=userRepository.getAll().size()-1;
		User user=userRepository.getAll().get(1);		
		userRepository.remove(user);
		assert(expRez==userRepository.getAll().size());
	}

	@Test
	public void testCreate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
				"config/application-context.xml");
		UserRepository userRepository = context.getBean(UserRepository.class);
		User user=new User();
		long i=userRepository.getAll().get(userRepository.getAll().size()-1).getId()+1;
		user.setUserName("testuser"+i);
		user.setEmail("testmail@yahoo.com");
		user.setPassword("pasdsdsxy");			
		userRepository.create(user);
		
		
		System.out.println("testuser"+i);
		assert(userRepository.exists("testuser"+i));
	}

	@Test
	public void testUpdate() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
				"config/application-context.xml");
		UserRepository userRepository = context.getBean(UserRepository.class);
		User user=userRepository.getAll().get(0);
				user.setPassword("chpassssa");
		userRepository.update(user);
		assert(user.getPassword()==userRepository.getAll().get(0).getPassword());
		
	}

	@Test
	public void testFind() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
				"config/application-context.xml");
		UserRepository userRepository = context.getBean(UserRepository.class);
		User user=userRepository.find((long) 1);
		assert(user.getUserName().equals("userxx1"));
	}

	@Test
	public void testExists() {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
				"config/application-context.xml");
		UserRepository userRepository = context.getBean(UserRepository.class);
		assert(userRepository.exists(userRepository.getAll().get(0).getUserName()));
	}
}
