package internship.issuetracker.repository;

import static org.junit.Assert.fail;
import internship.issuetracker.entities.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testGetAll() {
		
		assert(userRepository.getAll().size()==0);
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		User user=new User();
		user.setUserName("User1");
		user.setEmail("email@1.c");
		user.setPassword("pass");
		
		int expRez=userRepository.getAll().size()+1;
		userRepository.create(user);
		int Rez=userRepository.getAll().size();
		assert(expRez==Rez);
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
