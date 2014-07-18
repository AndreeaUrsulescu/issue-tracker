package internship.issuetracker.service;

import static org.junit.Assert.*;
import internship.issuetracker.entities.User;
import internship.issuetracker.repository.UserRepository;
import internship.issuetracker.utils.EncryptData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService = new UserService();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddUser() {

		User user = new User();
		user.setUserName("testxyz");
		user.setPassword("testpass");
		userService.addUser(user);
		Mockito.verify(userRepository).create(user);

	}

	@Test
	public void testLoginUser() {

		String userName = "corbu oana";
		String password = "parola";
		String hashPassword = EncryptData.sha256(password);
		boolean actualResult;

		Mockito.when(userRepository.matchPassword(userName, hashPassword))
				.thenReturn(true);
		actualResult = userService.matchPassword(userName, password);

		assertEquals(true, actualResult);

	}

}