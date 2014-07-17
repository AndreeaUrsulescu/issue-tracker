package internship.issuetracker.validator;

import internship.issuetracker.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserValidatorTest {

    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserValidator userValidator;
    
    @Test
    public void testInvalidUser(){
	
    }
    
    @Test
    public void testValidUser(){
	
    }

}
