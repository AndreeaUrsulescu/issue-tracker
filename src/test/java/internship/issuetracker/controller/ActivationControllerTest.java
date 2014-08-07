package internship.issuetracker.controller;

import static org.junit.Assert.assertEquals;
import internship.issuetracker.entities.Activation;
import internship.issuetracker.service.ActivationService;
import internship.issuetracker.service.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
@RunWith(MockitoJUnitRunner.class)
public class ActivationControllerTest {

	@Mock
	ActivationService activationService;
	@Mock
	UserService userService;
	@InjectMocks
	ActivationController activationController=new ActivationController();
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void activateTest()
	{
		Activation activation=new Activation();
		activation.setEmail("random@random.fr");
		activation.setPassword("randomPass");
		activation.setUserName("randomPierre");
		activation.encryptPasswordAndKeyHash();
		String hashKey=activation.getKeyHash();
		
		Mockito.when(activationService.getActivation(hashKey)).thenReturn(activation);
		
		String view=activationController.activate(hashKey);
		assertEquals(view,"activationSuccess");
		
		Mockito.when(activationService.getActivation(hashKey)).thenReturn(activation);

		view=activationController.activate(hashKey+"a");
		assertEquals(view,"activationFailure");
		
		Mockito.when(activationService.getActivation(hashKey)).thenReturn(null);

		view=activationController.activate(hashKey);
		assertEquals(view,"activationFailure");
	}
}
		

