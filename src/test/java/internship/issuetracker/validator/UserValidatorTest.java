package internship.issuetracker.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import internship.issuetracker.entities.User;
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
public class UserValidatorTest {

    @Mock
    private UserService userService;

    private BindingResult bindingResult;

    @InjectMocks
    private UserValidator userValidator = new UserValidator();

    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserName("Name");
        user.setEmail("email@foo.bar");
        user.setPassword("password");

        bindingResult = new BeanPropertyBindingResult(user, "user");
    }

    @Test
    public void testInvalidUser() {
        // The username already exists in the DB
        Mockito.when(userService.exists(user.getUserName().trim())).thenReturn(true);
        userValidator.validate(user, bindingResult);
        boolean result = bindingResult.hasErrors();
        assertTrue(result);
    }

    @Test
    public void testValidUser() {
        // The username does not exist in the DB
        Mockito.when(userService.exists(user.getUserName())).thenReturn(false);
        userValidator.validate(user, bindingResult);
        boolean result = bindingResult.hasErrors();
        assertFalse(result);

    }

}
