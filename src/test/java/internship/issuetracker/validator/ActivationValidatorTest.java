package internship.issuetracker.validator;

import static org.junit.Assert.*;
import internship.issuetracker.entities.Activation;
import internship.issuetracker.entities.User;
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
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

@RunWith(MockitoJUnitRunner.class)
public class ActivationValidatorTest {

    @Mock
    private ActivationService activationService;
    
    private BindingResult bindingResult;

    @InjectMocks
    private ActivationValidator activationValidator = new ActivationValidator();

    private User user;
    private Activation activation;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserName("Name");
        user.setEmail("email@foo.bar");
        user.setPassword("password");

        activation = new Activation(user);
        activation.setKeyHash("abc");
        
        
        bindingResult = new BeanPropertyBindingResult(activation, "activation");
    }
    
    @Test
    public void testInvalid() {
        Mockito.when(activationService.getActivation("abc")).thenReturn(activation);
        activationValidator.validate(activation, bindingResult);
        assertTrue(bindingResult.hasErrors());
    }
    
    @Test
    public void testValid() {
        Mockito.when(activationService.getActivation("abc")).thenReturn(new Activation());
        activationValidator.validate(activation, bindingResult);
        assertTrue(bindingResult.hasErrors());
    }
    
}
