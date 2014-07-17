package internship.issuetracker.repository;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserRepositoryTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("config/datasource/postgres.xml",
			"config/application-context.xml");
	@Test
	public void testGetAll() {
		
		UserRepository userRepository=context.getBean(UserRepository.class);
		System.out.println(userRepository.getAll());
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind() {
		fail("Not yet implemented");
	}

}
